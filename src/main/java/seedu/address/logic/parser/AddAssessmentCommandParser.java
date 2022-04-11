package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FULLMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHTAGE;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.address.logic.commands.AddAssessmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.FullMark;
import seedu.address.model.assessment.Weightage;

public class AddAssessmentCommandParser implements Parser<AddAssessmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAssessmentCommand
     * and returns an AddAssessmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddAssessmentCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_ASSESSMENTNAME, PREFIX_WEIGHTAGE, PREFIX_FULLMARK);

        if (!arePrefixesPresent(argumentMultimap, PREFIX_ASSESSMENTNAME, PREFIX_WEIGHTAGE, PREFIX_FULLMARK)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAssessmentCommand.MESSAGE_USAGE));
        }

        // Validation checks for the fields are carried out during construction
        AssessmentName assessmentName = ParserUtil.parseAssessmentName(argumentMultimap
                .getValue(PREFIX_ASSESSMENTNAME).get());
        Weightage weightage = ParserUtil.parseWeightage(argumentMultimap.getValue(PREFIX_WEIGHTAGE).get());
        FullMark fullMark = ParserUtil.parseFullMark(argumentMultimap.getValue(PREFIX_FULLMARK).get());

        Assessment assessment = new Assessment(assessmentName, weightage, fullMark);

        return new AddAssessmentCommand(assessment);
    }

}
