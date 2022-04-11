package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TUTORIAL_WEEKS;
import static seedu.address.commons.core.Messages.MESSAGE_TUTORIAL_NOT_FOUND;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NusNetId;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialName;

public class MarkAttendanceCommand extends Command {
    public static final String COMMAND_WORD = "mark_attendance";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks attendances for a class or specific person. \n"
            + "Parameters: "
            + PREFIX_TUTORIALNAME + "TUTORIAL NAME "
            + "[" + PREFIX_STUDENTID + "STUDENT ID] "
            + PREFIX_WEEK + "WEEK \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TUTORIALNAME + "T04 "
            + PREFIX_STUDENTID + "e01234567 "
            + PREFIX_WEEK + "7 ";

    public static final String MESSAGE_MULTIPLE_SUCCESS = "Attendance marked for all students on week %s";
    public static final String MESSAGE_SUCCESS = "Attendance marked for student %s on week %s";
    public static final String MESSAGE_STUDENT_NOT_IN_CLASS = "The specified student does not exist in this tutorial.";

    private final boolean isMarkMultipleAttendances;
    private final TutorialName tutorialToMark;
    private final NusNetId studentToMark;
    private final int week;

    /**
     * Creates an MarkAttendanceCommand to mark attendance the specified {@code Tutorial}
     */
    public MarkAttendanceCommand(TutorialName tutorial, NusNetId studentId, int week,
                                 boolean isMarkMultipleAttendances) {
        requireAllNonNull(tutorial, week, isMarkMultipleAttendances);
        this.isMarkMultipleAttendances = isMarkMultipleAttendances;
        tutorialToMark = tutorial;
        studentToMark = studentId;
        this.week = week;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTutorialWithName(tutorialToMark)) {
            throw new CommandException(String.format(MESSAGE_TUTORIAL_NOT_FOUND, tutorialToMark));
        }

        Tutorial tutorial = model.getTutorialWithName(tutorialToMark);

        if (week > tutorial.getWeeks()) {
            throw new CommandException(String.format(MESSAGE_INVALID_TUTORIAL_WEEKS, tutorial.getWeeks()));
        }

        if (!isMarkMultipleAttendances) {
            if (!tutorial.containsStudentWithId(studentToMark)) {
                throw new CommandException(MESSAGE_STUDENT_NOT_IN_CLASS);
            }
            model.markAttendanceForStudent(tutorial, studentToMark, week);
            model.updateFilteredAttendanceList(tutorial, null);
            CommandResult result = CommandResult
                    .createAttendanceCommandResult(String.format(MESSAGE_SUCCESS, studentToMark, week));
            result.setAttendanceWeek(week);
            return result;
        } else {
            model.markAttendanceForClass(tutorial, week);
            model.updateFilteredAttendanceList(tutorial, null);
            CommandResult result = CommandResult
                    .createAttendanceCommandResult(String.format(MESSAGE_MULTIPLE_SUCCESS, week));
            result.setAttendanceWeek(week);
            return result;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this // short circuit if same object
                || (other instanceof MarkAttendanceCommand // instanceof handles nulls
                && tutorialToMark.equals(((MarkAttendanceCommand) other).tutorialToMark)
                && isMarkMultipleAttendances == (((MarkAttendanceCommand) other).isMarkMultipleAttendances)
                && week == (((MarkAttendanceCommand) other).week))) {
            if ((studentToMark == null && (((MarkAttendanceCommand) other).studentToMark) == null)
                    || (studentToMark.equals(((MarkAttendanceCommand) other).studentToMark))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
