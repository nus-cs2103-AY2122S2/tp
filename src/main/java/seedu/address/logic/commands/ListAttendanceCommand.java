package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TUTORIAL_WEEKS;
import static seedu.address.commons.core.Messages.MESSAGE_TUTORIAL_NOT_FOUND;
import static seedu.address.logic.commands.GradeCommand.MESSAGE_STUDENT_NOT_FOUND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

import java.util.Objects;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NusNetId;
import seedu.address.model.person.Student;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialName;

public class ListAttendanceCommand extends Command {

    public static final String COMMAND_WORD = "list_attendance";

    public static final String MESSAGE_SUCCESS = "Listed attendance for class %s on week %s";

    public static final String MESSAGE_SUCCESS_STUDENT = "Listed attendance for student %s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists attendance for a specified class and week.\n"
            + "Parameters: "
            + PREFIX_TUTORIALNAME + "TUTORIAL NAME "
            + PREFIX_WEEK + "WEEK \n"
            + "or\n"
            + PREFIX_STUDENTID + "STUDENT ID\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TUTORIALNAME + "T04 " + PREFIX_WEEK + "1\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_STUDENTID + "e0123456 ";

    private final TutorialName tutorialName;
    private final NusNetId studentId;
    private final int week;

    /**
     * Constructor for a ListAttendanceCommand.
     * @param tutorialName The name of the tutorial containing the specified attendance list.
     * @param week The week of the attendance list requested.
     */
    public ListAttendanceCommand(TutorialName tutorialName, NusNetId studentId, int week) {
        this.tutorialName = tutorialName;
        this.studentId = studentId;
        this.week = week;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (studentId != null) {
            return listByStudentId(model);
        }
        return listByTutorialName(model);
    }

    private CommandResult listByTutorialName(Model model) throws CommandException {
        Tutorial tutorialToList = null;
        for (Tutorial tutorial : model.getAddressBook().getTutorialList()) {
            if (tutorial.getTutorialName().equals(tutorialName)) {
                tutorialToList = tutorial;
                break;
            }
        }

        if (tutorialToList == null) {
            throw new CommandException(MESSAGE_TUTORIAL_NOT_FOUND);
        } else {
            if (week > tutorialToList.getWeeks()) {
                String msg = String.format(MESSAGE_INVALID_TUTORIAL_WEEKS, tutorialToList.getWeeks());
                throw new CommandException(msg);
            }
            model.updateFilteredAttendanceList(tutorialToList, null);
            String successMessage = String.format(MESSAGE_SUCCESS, tutorialName, week);
            CommandResult commandResult = CommandResult.createAttendanceCommandResult(successMessage);
            commandResult.setAttendanceWeek(week);
            return commandResult;
        }
    }

    private CommandResult listByStudentId(Model model) throws CommandException {
        if (!model.hasStudentWithId(studentId)) {
            throw new CommandException(MESSAGE_STUDENT_NOT_FOUND);
        }

        Student studentToList = model.getStudentWithId(studentId);
        Tutorial tutorialToList = model.getTutorialWithName(studentToList.getTutorialName());

        model.updateFilteredAttendanceList(tutorialToList, studentToList.getName());
        String successMessage = String.format(MESSAGE_SUCCESS_STUDENT, studentToList.getName());
        CommandResult commandResult = CommandResult.createAttendanceByStudentCommandResult(successMessage);
        return commandResult;
    }

    @Override
    public boolean equals(Object other) {
        if (studentId == null) {
            return other == this // short circuit if same object
                    || (other instanceof ListAttendanceCommand // instanceof handles nulls
                    && tutorialName.equals(((ListAttendanceCommand) other).tutorialName) // state check
                    && week == ((ListAttendanceCommand) other).week
                    && Objects.equals(null, ((ListAttendanceCommand) other).studentId));
        } else {
            return other == this // short circuit if same object
                    || (other instanceof ListAttendanceCommand // instanceof handles nulls
                    && Objects.equals(tutorialName, ((ListAttendanceCommand) other).tutorialName) // state check
                    && week == ((ListAttendanceCommand) other).week
                    && studentId.equals(((ListAttendanceCommand) other).studentId));
        }
    }

}
