package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
 * Adds tutorial group to a student identified using it's displayed index from the address book
 */
public class AddTutorialGroupCommand extends Command {

    public static final String COMMAND_WORD = "addtg";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds tutorial groups to the student identified "
            + "by the index number used in the displayed student list. "
            + "Adding of tutorial groups is cumulative.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TUTORIAL_GROUP + "TUTORIAL_GROUP...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TUTORIAL_GROUP + "CS2103T W15-3 "
            + PREFIX_TUTORIAL_GROUP + "CS2101 G08";

    public static final String MESSAGE_ADD_TUTORIAL_GROUP_SUCCESS = "Added tutorial group: %1$s";
    public static final String MESSAGE_NOT_ADDED = "At least one tutorial group to add must be provided.";
    public static final String MESSAGE_DUPLICATE_TUTORIAL_GROUP = "This tutorial group already exists.";
    public static final String MESSAGE_INDEX_OUT_OF_RANGE = String.format(
        Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, "Index is larger than the number of "
        + "students in the viewed list.");

    private final Index index;
    private final AddTutorialGroupDescriptor addTutorialGroupDescriptor;

    /**
     * @param index of the student in the filtered student list to edit
     * @param details to edit the student with
     */
    public AddTutorialGroupCommand(Index index, AddTutorialGroupDescriptor details) {
        requireNonNull(index);
        requireNonNull(details);

        this.index = index;
        this.addTutorialGroupDescriptor = new AddTutorialGroupDescriptor(details);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INDEX_OUT_OF_RANGE);
        }
        Student studentToEdit = lastShownList.get(index.getZeroBased());

        // identify duplicate tutorial groups
        if (studentToEdit.tutorialGroupExists(addTutorialGroupDescriptor.tutorialGroups)) {
            throw new CommandException(MESSAGE_DUPLICATE_TUTORIAL_GROUP);
        }

        Student updatedStudent = createNewStudent(studentToEdit, addTutorialGroupDescriptor);
        model.setStudent(studentToEdit, updatedStudent);
        model.getFilteredStudentList();
        return new CommandResult(String.format(MESSAGE_ADD_TUTORIAL_GROUP_SUCCESS,
                addTutorialGroupDescriptor.tutorialGroups));
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code ddTutorialGroupDescriptor}.
     */
    private static Student createNewStudent(Student studentToEdit, AddTutorialGroupDescriptor tgDescriptor) {
        assert studentToEdit != null;
        // Defensive copy of tgDescriptor
        AddTutorialGroupDescriptor tgDescriptorCopy = new AddTutorialGroupDescriptor(tgDescriptor);

        Name currName = studentToEdit.getName();
        Email currEmail = studentToEdit.getEmail();

        Telegram currTelegram = studentToEdit.getTelegram();
        GitHub currGitHub = studentToEdit.getGitHub();

        tgDescriptorCopy.addTutorialGroups(studentToEdit.getTutorialGroups());
        Set<TutorialGroup> updatedTutorialGroups = tgDescriptorCopy.getTutorialGroups().get();

        return new Student(currName, currTelegram, currEmail, currGitHub, updatedTutorialGroups);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTutorialGroupCommand)) {
            return false;
        }

        // state check
        AddTutorialGroupCommand e = (AddTutorialGroupCommand) other;
        return index.equals(e.index)
                && addTutorialGroupDescriptor.equals(e.addTutorialGroupDescriptor);
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class AddTutorialGroupDescriptor {
        private Set<TutorialGroup> tutorialGroups;

        public AddTutorialGroupDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tutorialGroups} is used internally.
         */
        public AddTutorialGroupDescriptor(AddTutorialGroupDescriptor toCopy) {
            setTutorialGroups(toCopy.tutorialGroups);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(tutorialGroups);
        }

        /**
         * Sets {@code tutorialGroups} to this object's {@code tutorialGroups}.
         * A defensive copy of {@code tutorialGroups} is used internally.
         */
        public void setTutorialGroups(Set<TutorialGroup> tutorialGroups) {
            this.tutorialGroups = (tutorialGroups != null) ? new HashSet<>(tutorialGroups) : null;
        }

        /**
         * Returns an unmodifiable tutorial group set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tutorialGroups} is null.
         */
        public Optional<Set<TutorialGroup>> getTutorialGroups() {
            return (tutorialGroups != null)
                    ? Optional.of(Collections.unmodifiableSet(tutorialGroups)) : Optional.empty();
        }

        /**
         * Adds additional tutorial groups to the current AddTutorialGroupDescriptor
         *
         * @param tg tutorial group(s) to be added
         */
        public void addTutorialGroups(Set<TutorialGroup> tg) {
            tutorialGroups.addAll(tg);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof AddTutorialGroupDescriptor)) {
                return false;
            }

            // state check
            AddTutorialGroupDescriptor a = (AddTutorialGroupDescriptor) other;
            return getTutorialGroups().equals(a.getTutorialGroups());
        }
    }
}
