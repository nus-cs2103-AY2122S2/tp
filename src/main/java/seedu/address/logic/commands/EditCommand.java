package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.common.Description;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.FriendName;
import seedu.address.model.person.Log;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends ByIndexByNameCommand {

    public static final String COMMAND_WORD = "editfriend";
    public static final String COMMAND_ALIAS = "ef";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the name or index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX ? "
            + PREFIX_CURRENT_NAME + " CURRENT_NAME"
            + "[" + PREFIX_NEW_NAME + "NEW_NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";


    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final FriendName nameOfPersonToEdit;
    private final Index targetIndex;
    private final EditPersonDescriptor editPersonDescriptor;
    private final boolean isEditByIndex;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireAllNonNull(index, editPersonDescriptor);

        this.nameOfPersonToEdit = null;
        this.targetIndex = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
        this.isEditByIndex = true;
    }

    /**
     * @param name of the friend in the person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(FriendName name, EditPersonDescriptor editPersonDescriptor) {
        requireAllNonNull(name, editPersonDescriptor);

        this.nameOfPersonToEdit = name;
        this.targetIndex = null;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
        this.isEditByIndex = false;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person personToEdit;
        Person editedPerson;

        if (isEditByIndex) { //edit by index
            personToEdit = this.getPersonByFilteredIndex(model, targetIndex);
            editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        } else { //edit by name
            personToEdit = this.getPersonByName(model, nameOfPersonToEdit);
            editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);
        }

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
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

        FriendName updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Description updatedDescription = editPersonDescriptor.getDescription().orElse(personToEdit.getDescription());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        List<Log> updatedLogs = editPersonDescriptor.getLogs().orElse(personToEdit.getLogs());
        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedDescription, updatedTags,
                updatedLogs);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        } else if (other instanceof EditCommand) { //state check
            EditCommand otherEditCommand = (EditCommand) other;
            if (otherEditCommand.isEditByIndex && this.isEditByIndex) {
                //assertion to ensure that if it is edit by index, then targetIndex will not be null
                assert (otherEditCommand.targetIndex != null && this.targetIndex != null);
                return otherEditCommand.targetIndex.equals(this.targetIndex)
                        && editPersonDescriptor.equals(otherEditCommand.editPersonDescriptor);
            } else if (!otherEditCommand.isEditByIndex && !this.isEditByIndex) {
                //assertion to ensure that if it is deletion by name, then name will not be null
                assert (otherEditCommand.nameOfPersonToEdit != null && this.nameOfPersonToEdit != null);
                return otherEditCommand.nameOfPersonToEdit.equals(this.nameOfPersonToEdit)
                        && editPersonDescriptor.equals(otherEditCommand.editPersonDescriptor);
            }
        }
        return false;
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private FriendName name;
        private Phone phone;
        private Email email;
        private Address address;
        private Description description;
        private Set<Tag> tags;
        private List<Log> logs;

        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setDescription(toCopy.description);
            setTags(toCopy.tags);
            setLogs(toCopy.logs);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, description, tags);
        }

        public void setName(FriendName name) {
            this.name = name;
        }

        public Optional<FriendName> getName() {
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

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Sets {@code logs} to this object's {@code logs}.
         */
        public void setLogs(List<Log> logs) {
            this.logs = logs;
        }

        /**
         * Returns an unmodifiable list of logs, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         *
         * @return {@code Optional#empty()} if {@code logs} is null.
         */
        public Optional<List<Log>> getLogs() {
            return (logs != null) ? Optional.of(Collections.unmodifiableList(logs)) : Optional.empty();
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
                    && getDescription().equals(e.getDescription())
                    && getTags().equals(e.getTags());
        }
    }
}
