package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Email;
import seedu.address.model.student.GitHub;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.Telegram;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * Edits the details of an existing student in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the student identified "
            + "by the index number used in the displayed student list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_TELEGRAM + "TELEGRAM] "
            + "[" + PREFIX_GITHUB + "GITHUB] "
            + "[" + PREFIX_TUTORIAL_GROUP + "TUTORIAL_GROUP]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TELEGRAM + "johndoe "
            + PREFIX_EMAIL + "e0123456@u.nus.edu";

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the address book.";
    public static final String MESSAGE_INDEX_OUT_OF_RANGE = String.format(
        Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, "Index is larger than the number of "
            + "students in the viewed list.");

    private final Index index;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * @param index of the student in the filtered student list to edit
     * @param editStudentDescriptor details to edit the student with
     */
    public EditCommand(Index index, EditStudentDescriptor editStudentDescriptor) {
        requireNonNull(index);
        requireNonNull(editStudentDescriptor);

        this.index = index;
        this.editStudentDescriptor = new EditStudentDescriptor(editStudentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INDEX_OUT_OF_RANGE);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = createEditedStudent(studentToEdit, editStudentDescriptor);

        if (!studentToEdit.isSameStudent(editedStudent) && model.hasStudent(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.setStudent(studentToEdit, editedStudent);

        //keep the current list of filtered student and edited student
        if (!model.getFilteredStudentList().equals(model.getSortedStudentList())) {
            FilteredList<Student> filteredStudents = (FilteredList<Student>) model.getFilteredStudentList();

            @SuppressWarnings("unchecked")
            //test.getPredicate() must be a Predicate<Student> type
            Predicate<Student> predicateForFilteredStudents = (Predicate<Student>) filteredStudents.getPredicate();

            Predicate<Student> predicateToObtainEditedStudent = (Student s) -> s.equals(editedStudent);

            model.updateFilteredStudentList(predicateForFilteredStudents.or(predicateToObtainEditedStudent));
        } else {
            //the list is not filtered, show all students
            model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        }
        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent));
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editStudentDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Name updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        Email updatedEmail = editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
        Telegram updatedTelegram = editStudentDescriptor.getTelegram().orElse(studentToEdit.getTelegram());
        GitHub updatedGitHub = editStudentDescriptor.getGitHub().orElse(studentToEdit.getGitHub());
        Set<TutorialGroup> updatedTutorialGroups = editStudentDescriptor.getTutorialGroups()
            .orElse(studentToEdit.getTutorialGroups());

        return new Student(updatedName, updatedTelegram, updatedEmail, updatedGitHub, updatedTutorialGroups);
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
                && editStudentDescriptor.equals(e.editStudentDescriptor);
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditStudentDescriptor {
        private Name name;
        private Telegram telegram;
        private Email email;
        private GitHub gitHub;
        private Set<TutorialGroup> tutorialGroups;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tutorialGroups} is used internally.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setName(toCopy.name);
            setTelegram(toCopy.telegram);
            setEmail(toCopy.email);
            setGitHub(toCopy.gitHub);
            setTutorialGroups(toCopy.tutorialGroups);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, telegram, email, gitHub, tutorialGroups);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setTelegram(Telegram telegram) {
            this.telegram = telegram;
        }

        public Optional<Telegram> getTelegram() {
            return Optional.ofNullable(telegram);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setGitHub(GitHub gitHub) {
            this.gitHub = gitHub;
        }

        public Optional<GitHub> getGitHub() {
            return Optional.ofNullable(gitHub);
        }

        /**
         * Sets {@code tutorialGroups} to this object's {@code tutorialGroups}.
         * A defensive copy of {@code tutorialGroups} is used internally.
         */
        public void setTutorialGroups(Set<TutorialGroup> tutorialGroups) {
            this.tutorialGroups = (tutorialGroups != null) ? new HashSet<>(tutorialGroups) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tutorialGroups} is null.
         */
        public Optional<Set<TutorialGroup>> getTutorialGroups() {
            return (tutorialGroups != null)
                ? Optional.of(Collections.unmodifiableSet(tutorialGroups)) : Optional.empty();
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
                    && getTelegram().equals(e.getTelegram())
                    && getEmail().equals(e.getEmail())
                    && getGitHub().equals(e.getGitHub())
                    && getTutorialGroups().equals(e.getTutorialGroups());
        }
    }
}
