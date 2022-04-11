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

public class UnmarkAttendanceCommand extends Command {
    public static final String COMMAND_WORD = "unmark_attendance";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unmarks attendances for a class or specific person. \n"
            + "Parameters: "
            + PREFIX_TUTORIALNAME + "TUTORIAL NAME "
            + "[" + PREFIX_STUDENTID + "STUDENT ID] "
            + PREFIX_WEEK + "WEEK \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TUTORIALNAME + "T04 "
            + PREFIX_STUDENTID + "e01234567 "
            + PREFIX_WEEK + "7 ";

    public static final String MESSAGE_MULTIPLE_SUCCESS = "Attendance unmarked for all students on week %s";
    public static final String MESSAGE_SUCCESS = "Attendance unmarked for student %s on week %s";
    public static final String MESSAGE_STUDENT_NOT_IN_CLASS = "The specified student does not exist in this tutorial.";

    private final boolean isUnmarkMultipleAttendances;
    private final TutorialName tutorialToMark;
    private final NusNetId studentToMark;
    private final int week;

    /**
     * Creates an UnmarkAttendanceCommand to unmark attendance the specified {@code Tutorial}
     */
    public UnmarkAttendanceCommand(TutorialName tutorial, NusNetId studentId, int week,
                                   boolean isUnmarkMultipleAttendances) {
        requireAllNonNull(tutorial, week, isUnmarkMultipleAttendances);
        this.isUnmarkMultipleAttendances = isUnmarkMultipleAttendances;
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

        if (!isUnmarkMultipleAttendances) {
            if (!tutorial.containsStudentWithId(studentToMark)) {
                throw new CommandException(MESSAGE_STUDENT_NOT_IN_CLASS);
            }
            model.unmarkAttendanceForStudent(tutorial, studentToMark, week);
            model.updateFilteredAttendanceList(tutorial, null);
            CommandResult result = CommandResult
                    .createAttendanceCommandResult(String.format(MESSAGE_SUCCESS, studentToMark, week));
            result.setAttendanceWeek(week);
            return result;
        } else {
            model.unmarkAttendanceForClass(tutorial, week);
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
                || (other instanceof UnmarkAttendanceCommand // instanceof handles nulls
                && tutorialToMark.equals(((UnmarkAttendanceCommand) other).tutorialToMark)
                && isUnmarkMultipleAttendances == (((UnmarkAttendanceCommand) other).isUnmarkMultipleAttendances)
                && week == (((UnmarkAttendanceCommand) other).week))) {
            if ((studentToMark == null && (((UnmarkAttendanceCommand) other).studentToMark) == null)
                    || (studentToMark.equals(((UnmarkAttendanceCommand) other).studentToMark))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
