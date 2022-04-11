package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Displayable;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetId;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorial.TutorialName;

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
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in camNUS.";
    public static final String MESSAGE_DUPLICATE_STUDENT_ID = "Another student in camNUS has %s as their NUSNET ID";
    public static final String MESSAGE_DUPLICATE_EMAIL = "Another person in camNUS has %s as their email";
    public static final String MESSAGE_DUPLICATE_PHONE = "Another person in camNUS has %s as their phone number";
    public static final String MESSAGE_INDEX_USAGE = "Try listing a person or student e.g. list or list_student";
    public static final String MESSAGE_NOT_A_STUDENT = "This person is not a student!";
    public static final String MESSAGE_STUDENT_TAG_NOT_ADDED = "This person is not a student, student tag not added.";
    public static final String MESSAGE_STUDENT_TAG_ADDED = "Student tag has been automatically added";

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
        List<Displayable> lastShownList = model.getLastShownList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Displayable personToEdit = lastShownList.get(index.getZeroBased());
        //Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (personToEdit instanceof Student) {
            return executeEditStudent(model, (Student) personToEdit);
        }

        if (personToEdit instanceof Person) {
            return executeEditPerson(model, (Person) personToEdit);
        }

        throw new CommandException(Messages.MESSAGE_INDEX_LIST_MISMATCH + MESSAGE_INDEX_USAGE);

    }

    private CommandResult executeEditStudent(Model model, Person personToEdit) throws CommandException {
        Student editedStudent;
        Student studentToEdit = (Student) personToEdit;
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor(editPersonDescriptor);

        editedStudent = createEditedStudent(studentToEdit, editStudentDescriptor);

        // if you are changing the name to one that already exists
        if (!studentToEdit.isSamePerson(editedStudent) && model.hasPerson(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }


        // if you are changing the email to one that already exists
        if (!studentToEdit.getEmail().equals(editedStudent.getEmail())
                && model.hasPersonWithEmail(editedStudent.getEmail())) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_EMAIL, editedStudent.getEmail()));
        }

        // if you are changing the phone to one that already exists
        if (!studentToEdit.getPhone().equals(editedStudent.getPhone())
                && model.hasPersonWithPhone(editedStudent.getPhone())) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PHONE, editedStudent.getPhone()));
        }

        if (!editStudentDescriptor.hasStudentTag()) {
            editedStudent.addTag("student");
        }

        model.setPerson(studentToEdit, editedStudent);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        if (!editStudentDescriptor.hasStudentTag()) {
            return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS + "\n" + MESSAGE_STUDENT_TAG_ADDED,
                    editedStudent));
        }

        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent));
    }

    private CommandResult executeEditPerson(Model model, Person personToEdit) throws CommandException {

        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        // if you are changing the email to one that already exists
        if (!personToEdit.getEmail().equals(editedPerson.getEmail())
                && model.hasPersonWithEmail(editedPerson.getEmail())) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_EMAIL, editedPerson.getEmail()));
        }

        // if you are changing the phone to one that already exists
        if (!personToEdit.getPhone().equals(editedPerson.getPhone())
                && model.hasPersonWithPhone(editedPerson.getPhone())) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PHONE, editedPerson.getPhone()));
        }

        if (editPersonDescriptor.hasStudentTag()) {
            editedPerson.removeTag("student");
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        if (editPersonDescriptor.hasStudentTag()) {
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS + "\n" + MESSAGE_STUDENT_TAG_NOT_ADDED,
                    editedPerson));
        }

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
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags);
    }

    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Name updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        Phone updatedPhone = editStudentDescriptor.getPhone().orElse(studentToEdit.getPhone());
        Email updatedEmail = editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
        Address updatedAddress = editStudentDescriptor.getAddress().orElse(studentToEdit.getAddress());
        NusNetId updatedStudentId = editStudentDescriptor.getStudentId().orElse(studentToEdit.getStudentId());
        TutorialName updatedTutorialName = editStudentDescriptor.getTutorialName()
                .orElse(studentToEdit.getTutorialName());
        Set<Tag> updatedTags = editStudentDescriptor.getTags().orElse(studentToEdit.getTags());

        return new Student(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, updatedStudentId,
                updatedTutorialName);
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
        private Set<Tag> tags;

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
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags);
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
         * Returns true if the {@code EditPersonDescriptor} contains a student tag in its list of tags.
         */
        public boolean hasStudentTag() {
            if (tags != null) {
                return (tags.contains(new Tag("student")));
            }
            return false;
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

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditStudentDescriptor extends EditPersonDescriptor {
        private NusNetId studentId;
        private TutorialName tutorialName;

        public EditStudentDescriptor() {}

        /**
         * Overloaded constructor to instantiate an {@code EditStudentDescriptor} as a copy.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            super(toCopy);
            setStudentId(toCopy.studentId);
            setTutorialName(toCopy.tutorialName);
        }

        /**
         * Overloaded constructor to instantiate an {@code EditStudentDescriptor} from an {@code EditPersonDescriptor}.
         * this is required so that a Student object does not get replaced with a Person object.
         */
        public EditStudentDescriptor(EditPersonDescriptor toConvert) {
            super(toConvert);
            setStudentId(null);
            setTutorialName(null);
        }

        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(super.name, super.phone, super.email, super.address, super.tags,
                    studentId, tutorialName);
        }

        public void setStudentId(NusNetId studentId) {
            this.studentId = studentId;
        }

        public Optional<NusNetId> getStudentId() {
            return Optional.ofNullable(studentId);
        }

        public void setTutorialName(TutorialName tutorialName) {
            this.tutorialName = tutorialName;
        }

        public Optional<TutorialName> getTutorialName() {
            return Optional.ofNullable(tutorialName);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditStudentDescriptor)) {
                return false;
            }

            // state check
            EditStudentDescriptor e = (EditStudentDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags())
                    && getStudentId().equals(e.getStudentId())
                    && getTutorialName().equals(e.getTutorialName());
        }

    }
}
