package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.activity.Activity;
import seedu.address.model.person.Address;
import seedu.address.model.person.ClassCode;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Status;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Successfully edited %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    private static final Logger logger = LogsCenter.getLogger(EditCommand.class);

    public static final String MESSAGE_USAGE = COMMAND_WORD + " Command: Edits details of person by index used "
            + "in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_STATUS + "STATUS "
            + PREFIX_CLASSCODE + "CLASSCODE "
            + "[" + PREFIX_ACTIVITY + "ACTIVITIES]..."
            + "\n\nExample: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

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
        List<Person> lastShownList = model.getFilteredPersonList();
        ObservableList<Person> studentList = model.getAddressBook().getPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX
                    + " Only " + lastShownList.size() + " person(s) shown in the list.");
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        assert personToEdit != null : "A person should not be null";
        assert editedPerson != null : "An edited person should not be null";

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);

        try {
            batchUpdateNegativeToPositive(personToEdit, editedPerson, studentList, model);
        } catch (Exception ex) {
            logger.severe("Batch update failed: " + StringUtil.getDetails(ex));
        }

        try {
            batchUpdatePositiveToNegative(personToEdit, editedPerson, studentList, model);
        } catch (Exception ex) {
            logger.severe("Batch update failed: " + StringUtil.getDetails(ex));
        }


        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Batch updates the list when a person's status changes
     * from negative to positive.
     */
    private static void batchUpdateNegativeToPositive(Person personToEdit, Person editedPerson,
                                                      ObservableList<Person> studentList, Model model) {
        assert personToEdit != null : "A person should not be null";
        assert editedPerson != null : "A person should not be null";
        assert studentList != null : "The student list should not be null";
        assert model != null : "A model should not be null";

        if ((personToEdit.isNegative() || personToEdit.isCloseContact()) && editedPerson.isPositive()) {

            List<Person> filteredByClassCodeList = studentList.stream()
                    .filter(student -> (student.hasSameClassCode(editedPerson)
                            || student.hasSameActivity(editedPerson))
                            && !student.isSamePerson(editedPerson)
                            && !student.isPositive())
                    .collect(Collectors.toList());

            for (int i = 0; i < filteredByClassCodeList.size(); i++) {
                Person currentPerson = filteredByClassCodeList.get(i);
                assert currentPerson != null : "A person should not be null";
                ModelManager.editPersonStatus(currentPerson, new Status(Status.CLOSE_CONTACT), model);
            }
        }
    }

    /**
     * Batch updates the list when a person's status changes
     * from positive to negative.
     */
    private static void batchUpdatePositiveToNegative(Person personToEdit, Person editedPerson,
                                                      ObservableList<Person> studentList, Model model) {
        assert personToEdit != null : "A person should not be null";
        assert editedPerson != null : "A person should not be null";
        assert studentList != null : "The student list should not be null";
        assert model != null : "A model should not be null";

        if (personToEdit.isPositive() && editedPerson.isNegative()) {

            List<Person> filteredByClassCodeAndActivityList = studentList.stream()
                    .filter(student -> (student.hasSameClassCode(editedPerson)
                            || student.hasSameActivity(editedPerson))
                            && !student.isSamePerson(editedPerson))
                    .collect(Collectors.toList());

            for (int i = 0; i < filteredByClassCodeAndActivityList.size(); i++) {
                Person currentPerson = filteredByClassCodeAndActivityList.get(i);
                assert currentPerson != null : "A person should not be null";
                List<Person> positiveRelatedToPerson = studentList.stream()
                        .filter(student -> (student.hasSameClassCode(currentPerson)
                                || student.hasSameActivity(currentPerson))
                                && !student.isSamePerson(editedPerson)
                                && student.isPositive())
                        .collect(Collectors.toList());

                if (positiveRelatedToPerson.size() == 0) {
                    ModelManager.editPersonStatus(currentPerson, new Status(Status.NEGATIVE), model);
                }
            }
        }
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    public static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Status updatedStatus = editPersonDescriptor.getStatus().orElse(personToEdit.getStatus());
        ClassCode updatedClassCode = editPersonDescriptor.getClassCode().orElse(personToEdit.getClassCode());
        Set<Activity> updatedActivity = editPersonDescriptor.getActivities().orElse(personToEdit.getActivities());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedStatus,
                updatedClassCode, updatedActivity);
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
        private Address address;
        private Status status;
        private ClassCode classCode;
        private Set<Activity> activities;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code activities} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setStatus(toCopy.status);
            setClassCode(toCopy.classCode);
            setActivities(toCopy.activities);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, activities, status, classCode);
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

        public void setStatus(Status status) {
            this.status = status;
        }

        public Optional<Status> getStatus() {
            return Optional.ofNullable(status);
        }

        public void setClassCode(ClassCode classCode) {
            this.classCode = classCode;
        }

        public Optional<ClassCode> getClassCode() {
            return Optional.ofNullable(classCode);
        }

        /**
         * Sets {@code activities} to this object's {@code activities}.
         * A defensive copy of {@code activities} is used internally.
         */
        public void setActivities(Set<Activity> activities) {
            this.activities = (activities != null) ? new HashSet<>(activities) : null;
        }

        /**
         * Returns an unmodifiable activity set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code activity} is null.
         */
        public Optional<Set<Activity>> getActivities() {
            return (activities != null) ? Optional.of(Collections.unmodifiableSet(activities)) : Optional.empty();
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
                    && getStatus().equals(e.getStatus())
                    && getClassCode().equals(e.getClassCode())
                    && getActivities().equals(e.getActivities());
        }
    }
}
