package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERIMAGE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Reminder;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Favourite;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Preference;
import seedu.address.model.person.UserType;
import seedu.address.model.property.Property;
import seedu.address.model.userimage.UserImage;
import seedu.address.model.util.UserTypeUtil;
import seedu.address.storage.ReminderPersons;


/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_PREFERENCE + "PREFERENCE]"
            + "[" + PREFIX_PROPERTY + "PROPERTY]..."
            + "[" + PREFIX_USERIMAGE + "FILEPATH:DESCRIPTION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredAndSortedPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        // remove reminder for previous instance of Person
        ReminderPersons reminderPersons = ReminderPersons.getInstance();
        Reminder previousReminder = reminderPersons.remove(personToEdit);
        if (previousReminder != null) {
            reminderPersons.add(editedPerson, previousReminder);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        //Favourite status for a client will remain unchanged when edited if not, the FavouriteCommand is redundant.
        Favourite noChangeFavourite = editPersonDescriptor.getFavourite().orElse(personToEdit.getFavourite());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        // set value of edited person's properties
        Set<Property> updatedProperties = setProperties(personToEdit, editPersonDescriptor);
        // set value of edited person's preference
        Optional<Preference> updatedPreference = setPreference(personToEdit, editPersonDescriptor);
        // set value of edited person's user type
        UserType updatedUserType = setUserType(updatedProperties, updatedPreference);
        Set<UserImage> updatedUserImages = editPersonDescriptor.getUserImages().orElse(personToEdit.getUserImages());


        return new Person(updatedName, updatedPhone, updatedEmail, noChangeFavourite, updatedAddress, updatedProperties,
                updatedPreference, updatedUserType, updatedUserImages);
    }

    private static Set<Property> setProperties(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        // default value of edited person's properties
        Set<Property> updatedProperties = personToEdit.getProperties();

        // check if person to edit contains Properties
        if (!personToEdit.getProperties().isEmpty()) {
            // if edit command contains properties
            if (editPersonDescriptor.getProperties().isPresent()) {
                updatedProperties = editPersonDescriptor.getProperties().get();
            // if edit command contains Preference
            } else if (editPersonDescriptor.getPreference().isPresent()) {
                updatedProperties = new HashSet<>();
            }
        } else if (editPersonDescriptor.getProperties().isPresent()) {
            updatedProperties = editPersonDescriptor.getProperties().get();
        }

        return updatedProperties;
    }

    private static Optional<Preference> setPreference(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        // default value of edited person's preference
        Optional<Preference> updatedPreference = personToEdit.getPreference();

        // check if person to edit contains Preference
        if (personToEdit.getPreference().isPresent()) {
            // if edit command contains Preference
            if (editPersonDescriptor.getPreference().isPresent()) {
                updatedPreference = editPersonDescriptor.getPreference();
                // if edit command contains Properties
            } else if (!editPersonDescriptor.getProperties().isEmpty()) {
                updatedPreference = Optional.ofNullable(null);
            }
        } else if (editPersonDescriptor.getPreference().isPresent()) {
            updatedPreference = editPersonDescriptor.getPreference();
        }

        return updatedPreference;
    }

    private static UserType setUserType(Set<Property> updatedProperties, Optional<Preference> updatedPreference) {
        if (!updatedProperties.isEmpty()) {
            return UserTypeUtil.createSeller();
        } else {
            return UserTypeUtil.createBuyer();
        }
    }

    @Override
    public String toString() {
        return index.toString() + editPersonDescriptor.toString();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Favourite favourite;
        private Address address;
        private Preference preference;
        private Set<Property> properties;
        private UserType userType;
        private Set<UserImage> userImages;

        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code userTypes} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setFavourite(toCopy.favourite);
            setAddress(toCopy.address);
            setPreference(toCopy.preference);
            setProperties(toCopy.properties);
            setUserType(toCopy.userType);
            setUserImages(toCopy.userImages);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, properties, preference,
                        userType, userImages);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setFavourite(Favourite favourite) {
            this.favourite = favourite;
        }

        public Optional<Favourite> getFavourite() {
            return Optional.ofNullable(favourite);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setUserType(UserType userType) {
            this.userType = userType;
        }

        public Optional<UserType> getUserType() {
            return Optional.ofNullable(userType);
        }

        public void setUserImages(Set<UserImage> userImages) {
            this.userImages = (userImages == null)
                ? null
                : new LinkedHashSet<UserImage>(userImages);
        }

        public Optional<Set<UserImage>> getUserImages() {
            return Optional.ofNullable(userImages);
        }

        public Optional<Preference> getPreference() {
            return Optional.ofNullable(preference);
        }

        public void setPreference(Preference preference) {
            this.preference = preference;
        }

        public void clearPreference() {
            this.preference = null;
        }

        /**
         * Sets {@code properties} to this object's {@code properties}.
         * A defensive copy of {@code properties} is used internally.
         */
        public void setProperties(Set<Property> properties) {
            this.properties = (properties != null) ? new HashSet<>(properties) : null;
        }

        /**
         * Returns an unmodifiable property set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code properties} is null.
         */
        public Optional<Set<Property>> getProperties() {
            return (properties != null) ? Optional.of(Collections.unmodifiableSet(properties)) : Optional.empty();
        }

        /**
         * Clears all of this object's {@code properties}.
         */
        public void clearProperties() {
            this.properties = null; // alternatively, set properties to be an empty HashSet
        }

        @Override
        public String toString() {
            return name + "|" + phone + "|" + email + "|" + favourite + "|" + address + "|" + preference + "|"
                    + properties + "|" + userType;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getPreference().equals(e.getPreference())
                    && getProperties().equals(e.getProperties());
        }
    }
}
