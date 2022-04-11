package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.address.logic.commands.GradeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.person.Name;

public class GradeCommandParser implements Parser<GradeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GradeCommand
     * and returns an GradeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public GradeCommand parse(String userInput) throws ParseException {
        ArgumentMultimap multimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_ASSESSMENTNAME, PREFIX_NAME, PREFIX_SCORE);

        if (!arePrefixesPresent(multimap, PREFIX_ASSESSMENTNAME, PREFIX_NAME, PREFIX_SCORE)
                || !multimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GradeCommand.MESSAGE_USAGE));
        }

        // Validation checks for the fields are carried out during construction
        AssessmentName assessmentName = ParserUtil.parseAssessmentName(multimap
                .getValue(PREFIX_ASSESSMENTNAME).get());
        Name name = ParserUtil.parseName(multimap.getValue(PREFIX_NAME).get());
        String score = ParserUtil.parseScore(multimap.getValue(PREFIX_SCORE).get());

        return new GradeCommand(assessmentName, name, score);
    }

}
