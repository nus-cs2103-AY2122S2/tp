package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNAME;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.address.logic.commands.ListScoreCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.tutorial.TutorialName;

public class ListScoreCommandParser implements Parser<ListScoreCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListScoreCommand
     * and returns a ListScoreCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ListScoreCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap multimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_TUTORIALNAME, PREFIX_ASSESSMENTNAME);

        if (!arePrefixesPresent(multimap, PREFIX_TUTORIALNAME, PREFIX_ASSESSMENTNAME)
                || !multimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListScoreCommand.MESSAGE_USAGE));
        }

        TutorialName tutorialName = ParserUtil.parseTutorialName(multimap.getValue(PREFIX_TUTORIALNAME).get());
        AssessmentName assessmentName =
                ParserUtil.parseAssessmentName(multimap.getValue(PREFIX_ASSESSMENTNAME).get());

        return new ListScoreCommand(tutorialName, assessmentName);
    }
}
