package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FLAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREV_DATE_MET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Flag;
import seedu.address.model.person.Info;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PrevDateMet;
import seedu.address.model.person.Salary;
import seedu.address.model.person.ScheduledMeeting;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the hustle book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the name used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: NAME (Alphanumerical and spaces only) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_FLAG + "FLAG] "
            + "[" + PREFIX_SALARY + "SALARY] "
            + "[" + PREFIX_INFO + "INFO] "
            + "[" + PREFIX_PREV_DATE_MET + "DATE] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " John Doe "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_MULTIPLE_PERSON = "More than 1 person exists with that name. Please look at the "
            + "list below and enter the index of the client you wish to edit \n"
            + "Example: 1, 2, 3 ...";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the hustle book.";
    public static final String MESSAGE_INVALID_INDEX = "This index does not exist!";

    private final Name targetName;
    private final String targetNameStr;
    private final EditPersonDescriptor editPersonDescriptor;
    private int index = 0;

    /**
     * @param name name of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Name name, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(name);
        requireNonNull(editPersonDescriptor);


        this.targetName = name;
        this.targetNameStr = name.fullName;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }



    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        FilteredList<Person> lastShownList = (FilteredList<Person>) model.getFilteredPersonList();
        Index targetIndex;
        if (index == 0) {
            FilteredList<Person> tempList = new FilteredList<Person>(lastShownList);
            String[] nameKeywords = {targetNameStr};
            Predicate<Person> predicate = new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords));
            tempList.setPredicate(predicate);

            if (tempList.size() > 1) {
                lastShownList.setPredicate(predicate);
                return new CommandResult(MESSAGE_MULTIPLE_PERSON);
            }

            targetIndex = model.getPersonListIndex(targetName);
        } else {
            targetIndex = Index.fromOneBased(index);
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_NAME);
        }

        Person personToEdit = lastShownList.get(targetIndex.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    public void setIndex(int index) {
        this.index = index;
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
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Flag updatedFlag = editPersonDescriptor.getFlag().orElse(personToEdit.getFlag());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Salary updatedSalary = editPersonDescriptor.getSalary().orElse(personToEdit.getSalary());
        Info updatedInfo = editPersonDescriptor.getInfo().orElse(personToEdit.getInfo());
        PrevDateMet updatedPrevDateMet = editPersonDescriptor.getPrevDateMet().orElse(personToEdit.getPrevDateMet());

        // Schedule meeting remains unchanged through edit commands. MeetCommand handles CRUD for meetings.
        ScheduledMeeting scheduledMeeting = personToEdit.getScheduledMeeting();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedFlag,
                updatedTags, updatedPrevDateMet, updatedSalary, updatedInfo, scheduledMeeting);
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
        return targetName.equals(e.targetName)
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
        private Address address;
        private Flag flag;
        private Set<Tag> tags;
        private Salary salary;
        private Info info;
        private PrevDateMet prevDateMet;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setFlag(toCopy.flag);
            setInfo(toCopy.info);
            setPrevDateMet(toCopy.prevDateMet);
            setTags(toCopy.tags);
            setSalary(toCopy.salary);
            setInfo(toCopy.info);
            setPrevDateMet(toCopy.prevDateMet);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(
                    name, phone, email, address, tags, salary, info, prevDateMet);
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

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setFlag(Flag flag) {
            this.flag = flag;
        }

        public Optional<Flag> getFlag() {
            return Optional.ofNullable(flag);
        }

        public void setSalary(Salary salary) {
            this.salary = salary;
        }

        public Optional<Salary> getSalary() {
            return Optional.ofNullable(salary);
        }

        public void setInfo(Info info) {
            this.info = info;
        }

        public Optional<Info> getInfo() {
            return Optional.ofNullable(info);
        }

        public void setPrevDateMet(PrevDateMet date) {
            this.prevDateMet = date;
        }

        public Optional<PrevDateMet> getPrevDateMet() {
            return Optional.ofNullable(prevDateMet);
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
                    && getTags().equals(e.getTags());
        }
    }
}
