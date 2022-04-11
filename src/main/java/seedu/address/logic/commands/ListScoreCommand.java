package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_ASSESSMENT_NOT_FOUND;
import static seedu.address.commons.core.Messages.MESSAGE_TUTORIAL_NOT_FOUND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.FullMark;
import seedu.address.model.tutorial.TutorialName;

public class ListScoreCommand extends Command {
    public static final String COMMAND_WORD = "list_score";
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Lists the scores of the given class in the given assessment. "
            + "Parameters: "
            + PREFIX_ASSESSMENTNAME + "ASSESSMENT NAME "
            + PREFIX_TUTORIALNAME + "TUTORIAL NAME\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_ASSESSMENTNAME + "Test 1 "
            + PREFIX_TUTORIALNAME + "T01";


    public static final String MESSAGE_SUCCESS =
            "Listed all scores of Class: %1$s for Assessment: %2$s with Full mark: %3$s";

    private final TutorialName tutName;
    private final AssessmentName assessmentName;

    /**
     * Creates a ListScoreCommand to list the scores of students in class with {@code tutName} for assessment
     * with {@code assessmentName}.
     */
    public ListScoreCommand(TutorialName tutName, AssessmentName assessmentName) {
        this.tutName = tutName;
        this.assessmentName = assessmentName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasTutorialWithName(tutName)) {
            throw new CommandException(MESSAGE_TUTORIAL_NOT_FOUND);
        }
        if (!model.hasAssessmentWithName(assessmentName)) {
            throw new CommandException(MESSAGE_ASSESSMENT_NOT_FOUND);
        }
        FullMark fullMark = model.getAssessmentWithName(assessmentName).getFullMark();
        model.updateDisplayAssessmentResults(tutName, assessmentName);
        return CommandResult.createScoreCommandResult(
                String.format(MESSAGE_SUCCESS, tutName, assessmentName, fullMark));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListScoreCommand // instanceof handles nulls
                && tutName.equals(((ListScoreCommand) other).tutName)
                && assessmentName.equals(((ListScoreCommand) other).assessmentName));
    }
}
