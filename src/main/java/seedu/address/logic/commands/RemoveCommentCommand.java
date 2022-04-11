package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTID;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NusNetId;
import seedu.address.model.person.Student;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialName;

public class RemoveCommentCommand extends Command {
    public static final String COMMAND_WORD = "remove_comment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes comments for a student. \n"
            + "Parameters: "
            + PREFIX_STUDENTID + "STUDENT ID\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENTID + "e01234567 ";

    public static final String MESSAGE_SUCCESS = "Comment removed for student %s";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "The specified student does not exist.";

    private final NusNetId studentToRemoveComment;

    /**
     * Creates an RemoveCommentCommand to remove a comment for the specified {@code Student}
     */
    public RemoveCommentCommand(NusNetId studentId) {
        studentToRemoveComment = studentId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasStudentWithId(studentToRemoveComment)) {
            throw new CommandException(String.format(MESSAGE_STUDENT_NOT_FOUND));
        }

        Student student = model.getStudentWithId(studentToRemoveComment);
        TutorialName tutorialName = student.getTutorialName();
        Tutorial tutorial = model.getTutorialWithName(tutorialName);
        model.removeComment(tutorial, student.getName());
        return CommandResult.createCommentCommandResult(String.format(MESSAGE_SUCCESS, student.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveCommentCommand // instanceof handles nulls
                && studentToRemoveComment.equals(((RemoveCommentCommand) other).studentToRemoveComment));
    }
}
