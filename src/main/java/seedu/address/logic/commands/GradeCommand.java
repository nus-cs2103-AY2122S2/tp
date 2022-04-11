package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_ASSESSMENT_NOT_FOUND;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.FullMark;
import seedu.address.model.assessment.Score;
import seedu.address.model.person.Name;
import seedu.address.model.tutorial.TutorialName;

/**
 * Adds a score for a student in a particular assessment.
 */
public class GradeCommand extends Command {
    public static final String COMMAND_WORD = "grade";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gives a score to the student in the assessment. "
            + "Parameters: "
            + PREFIX_ASSESSMENTNAME + "ASSESSMENT NAME "
            + PREFIX_NAME + "NAME "
            + PREFIX_SCORE + "SCORE\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_ASSESSMENTNAME + "Test 1 "
            + PREFIX_NAME + "Amy Tan " + PREFIX_SCORE + "5";

    public static final String ADD_MESSAGE_SUCCESS = "New score added: %1$s; Student: %2$s\n"
            + "Listed all scores of Class: %3$s for Assessment: %4$s with Full mark: %5$s";
    public static final String EDIT_MESSAGE_SUCCESS = "Updated score: %1$s; Student: %2$s\n"
            + "Listed all scores of Class: %3$s for Assessment: %4$s with Full mark: %5$s";

    public static final String MESSAGE_STUDENT_NOT_FOUND = "There is no student with "
            + "the given name in camNUS";

    public static final String MESSAGE_INVALID_SCORE = "The given score is larger than the assessment's "
            + "full mark, %1$s";

    private final AssessmentName assessmentName;
    private final Name studentName;
    private final String score;
    private FullMark fullMark;
    private Score sc;
    private TutorialName tutName;

    /**
     * Creates an GradeCommand to add the {@code score} in assessment with {@code assessmentName}
     * for the student with {@code studentName}.
     */
    public GradeCommand(AssessmentName assessmentName, Name studentName, String score) {
        requireAllNonNull(assessmentName, studentName, score);
        this.assessmentName = assessmentName;
        this.studentName = studentName;
        this.score = score;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasAssessmentWithName(assessmentName)) {
            throw new CommandException(MESSAGE_ASSESSMENT_NOT_FOUND);
        }

        if (!model.hasStudentWithName(studentName)) {
            throw new CommandException(MESSAGE_STUDENT_NOT_FOUND);
        }

        fullMark = model.getAssessmentWithName(assessmentName).getFullMark();
        if (!Score.isValidScoreGivenFullMark(score, fullMark)) {
            throw new CommandException(String.format(MESSAGE_INVALID_SCORE, fullMark));
        }

        sc = new Score(score, fullMark);
        tutName = model.getTutorialNameOfStudent(studentName);

        // checks if the address book already has a result for a student in an assessment
        if (model.hasStudentResult(studentName, assessmentName)) {
            return executeEdit(model);
        } else {
            return executeAdd(model);
        }
    }

    private CommandResult executeEdit(Model model) {
        model.setStudentResult(studentName, assessmentName, sc);
        model.updateDisplayAssessmentResults(tutName, assessmentName);
        return CommandResult.createScoreCommandResult(
                String.format(EDIT_MESSAGE_SUCCESS, sc, studentName, tutName, assessmentName, fullMark));
    }

    private CommandResult executeAdd(Model model) {
        model.addStudentResult(studentName, assessmentName, sc);
        model.updateDisplayAssessmentResults(tutName, assessmentName);

        return CommandResult.createScoreCommandResult(
                String.format(ADD_MESSAGE_SUCCESS, sc, studentName, tutName, assessmentName, fullMark));
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof GradeCommand
                && studentName.equals(((GradeCommand) other).studentName)
                && assessmentName.equals(((GradeCommand) other).assessmentName)
                && score.equals(((GradeCommand) other).score));
    }
}
