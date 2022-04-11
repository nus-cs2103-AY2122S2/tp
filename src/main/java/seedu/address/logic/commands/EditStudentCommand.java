package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.misc.InfoPanelTypes;
import seedu.address.logic.commands.misc.ViewTab;
import seedu.address.model.Model;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.EnrolledLessons;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing student in the student book.
 */
public class EditStudentCommand extends Command {

    public static final String COMMAND_WORD = "editstudent";
    public static final String SHORTENED_COMMAND_WORD = "es";
    public static final String COMMAND_DESCRIPTION = "Edit a student";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the student identified "
            + "by the index number used in the displayed student list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_STUDENT_NAME + " NAME] "
            + "[" + PREFIX_STUDENT_PHONE + " PHONE] "
            + "[" + PREFIX_STUDENT_EMAIL + " EMAIL] "
            + "[" + PREFIX_STUDENT_ADDRESS + " ADDRESS] "
            + "[" + PREFIX_STUDENT_TAG + " TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_STUDENT_PHONE + " 91234567 "
            + PREFIX_STUDENT_EMAIL + " johndoe@example.com";

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the student book.";

    private final Index index;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * @param index of the student in the filtered student list to edit
     * @param editStudentDescriptor details to edit the student with
     */
    public EditStudentCommand(Index index, EditStudentDescriptor editStudentDescriptor) {
        requireNonNull(index);
        requireNonNull(editStudentDescriptor);

        this.index = index;
        this.editStudentDescriptor = new EditStudentDescriptor(editStudentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        boolean isIndexOutOfBounds = index.getZeroBased() >= lastShownList.size();
        if (isIndexOutOfBounds) {
            throw new CommandException(Messages.MESSAGE_OOB_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = createEditedStudent(studentToEdit, editStudentDescriptor);
        requireNonNull(editedStudent);

        boolean isNotSameStudent = !studentToEdit.isSameStudent(editedStudent);
        boolean hasStudentInModel = model.hasStudent(editedStudent);
        if (isNotSameStudent && hasStudentInModel) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT, ViewTab.STUDENT);
        }

        model.setSelectedStudent(editedStudent);
        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        String commandResultMessage = String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);
        return new CommandResult(commandResultMessage, InfoPanelTypes.STUDENT, ViewTab.STUDENT);
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editStudentDescriptor}.
     */
    private Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Name updatedName = getUpdatedName(studentToEdit, editStudentDescriptor);
        Phone updatedPhone = getUpdatedPhone(studentToEdit, editStudentDescriptor);
        Email updatedEmail = getUpdatedEmail(studentToEdit, editStudentDescriptor);
        Address updatedAddress = getUpdatedAddress(studentToEdit, editStudentDescriptor);
        Set<Tag> updatedTags = getUpdatedTags(studentToEdit, editStudentDescriptor);
        EnrolledLessons enrolledLessons = studentToEdit.getEnrolledLessons();

        return new Student(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, enrolledLessons);
    }

    /**
     * Gets the Updated Name.
     * If there's no new Name, return the original.
     */
    private Name getUpdatedName(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        return editStudentDescriptor.getName().orElse(studentToEdit.getName());
    }

    /**
     * Gets the Updated Phone.
     * If there's no new Phone, return the original.
     */
    private Phone getUpdatedPhone(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        return editStudentDescriptor.getPhone().orElse(studentToEdit.getPhone());
    }

    /**
     * Gets the Updated Email.
     * If there's no new Email, return the original.
     */
    private Email getUpdatedEmail(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        return editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
    }

    /**
     * Gets the Updated Address.
     * If there's no new Address, return the original.
     */
    private Address getUpdatedAddress(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        return editStudentDescriptor.getAddress().orElse(studentToEdit.getAddress());
    }

    /**
     * Gets the Updated Tags.
     * If there's no new Tags, return the original.
     */
    private Set<Tag> getUpdatedTags(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        return editStudentDescriptor.getTags().orElse(studentToEdit.getTags());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditStudentCommand)) {
            return false;
        }

        // state check
        EditStudentCommand e = (EditStudentCommand) other;
        return index.equals(e.index)
                && editStudentDescriptor.equals(e.editStudentDescriptor);
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditStudentDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
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
                    && getTags().equals(e.getTags());
        }
    }
}
