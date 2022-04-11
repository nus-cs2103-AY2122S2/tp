package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MESSAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTID;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attendance.Comment;
import seedu.address.model.person.NusNetId;
import seedu.address.model.person.Student;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialName;

public class AddCommentCommand extends Command {
    public static final String COMMAND_WORD = "comment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a comment for a student. \n"
            + "Parameters: "
            + PREFIX_STUDENTID + "STUDENT ID "
            + PREFIX_MESSAGE + "COMMENT \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENTID + "e01234567 "
            + PREFIX_MESSAGE + "has a lab assignment overdue. ";

    public static final String MESSAGE_SUCCESS = "Comment added for student %s: %s";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "The specified student does not exist.";

    private final NusNetId studentToComment;
    private final Comment toAdd;

    /**
     * Creates an CommentCommand to add a comment for the specified {@code Student}
     */
    public AddCommentCommand(NusNetId studentName, Comment comment) {
        studentToComment = studentName;
        toAdd = comment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasStudentWithId(studentToComment)) {
            throw new CommandException(String.format(MESSAGE_STUDENT_NOT_FOUND));
        }

        Student student = model.getStudentWithId(studentToComment);
        TutorialName tutorialName = student.getTutorialName();
        Tutorial tutorial = model.getTutorialWithName(tutorialName);
        model.addComment(tutorial, student.getName(), toAdd);
        return CommandResult.createCommentCommandResult(String.format(MESSAGE_SUCCESS, student.getName(), toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommentCommand // instanceof handles nulls
                && studentToComment.equals(((AddCommentCommand) other).studentToComment)
                && toAdd.equals(((AddCommentCommand) other).toAdd));
    }
}
