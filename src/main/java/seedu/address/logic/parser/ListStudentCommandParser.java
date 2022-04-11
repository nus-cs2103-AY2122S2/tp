package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutorial.TutorialName;

public class ListStudentCommandParser implements Parser<ListStudentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListStudentCommand
     * and returns a ListStudentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListStudentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TUTORIALNAME);

        Index index;

        if (argMultimap.getValue(PREFIX_TUTORIALNAME).isPresent() && argMultimap.getPreamble().isEmpty()) {
            TutorialName tutorialName = ParserUtil.parseTutorialName(argMultimap.getValue(PREFIX_TUTORIALNAME).get());
            return new ListStudentCommand(null, tutorialName);
        }
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new ListStudentCommand(null, null);
        }
        try {
            index = ParserUtil.parseIndex(args);
            return new ListStudentCommand(index, null);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListStudentCommand.MESSAGE_USAGE), pe);
        }
    }
}
