package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTID;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attendance.Comment;
import seedu.address.model.person.NusNetId;
import seedu.address.model.person.Student;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialName;

public class ViewCommentCommand extends Command {
    public static final String COMMAND_WORD = "view_comment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views comments for a student. \n"
            + "Parameters: "
            + PREFIX_STUDENTID + "STUDENT ID \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENTID + "e0123456 ";

    public static final String MESSAGE_SUCCESS = "Comment for student %s: %s";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "The specified student does not exist.";

    private final NusNetId studentToViewComment;

    /**
     * Creates an CommentCommand to add a comment for the specified {@code Student}
     */
    public ViewCommentCommand(NusNetId studentId) {
        requireNonNull(studentId);
        studentToViewComment = studentId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasStudentWithId(studentToViewComment)) {
            throw new CommandException(String.format(MESSAGE_STUDENT_NOT_FOUND));
        }

        Student student = model.getStudentWithId(studentToViewComment);
        TutorialName tutorialName = student.getTutorialName();
        Tutorial tutorial = model.getTutorialWithName(tutorialName);
        Comment comment = model.getComment(tutorial, student.getName());
        return CommandResult.createCommentCommandResult(String.format(MESSAGE_SUCCESS, student.getName(), comment));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommentCommand // instanceof handles nulls
                && studentToViewComment.equals(((ViewCommentCommand) other).studentToViewComment));
    }
}
