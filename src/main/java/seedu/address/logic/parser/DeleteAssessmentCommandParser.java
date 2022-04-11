package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENTNAME;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.address.logic.commands.DeleteAssessmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assessment.AssessmentName;

public class DeleteAssessmentCommandParser implements Parser<DeleteAssessmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteAssessmentCommand
     * and returns an DeleteAssessmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteAssessmentCommand parse(String userInput) throws ParseException {
        ArgumentMultimap multimap = ArgumentTokenizer.tokenize(userInput, PREFIX_ASSESSMENTNAME);

        if (!arePrefixesPresent(multimap, PREFIX_ASSESSMENTNAME) || !multimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteAssessmentCommand.MESSAGE_USAGE));
        }

        AssessmentName assessmentName = ParserUtil.parseAssessmentName(multimap.getValue(PREFIX_ASSESSMENTNAME).get());
        return new DeleteAssessmentCommand(assessmentName);
    }

}
