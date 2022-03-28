package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;


import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

/**
 * Parses input arguments and creates a new FindPersonCommand object
 */
public class FindPersonCommandParser implements Parser<FindPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindPersonCommand
     * and returns a FindPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPersonCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_COMPANY, PREFIX_TAG);

        if (!isValid(argMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindPersonCommand.MESSAGE_USAGE));
        }

        return new FindPersonCommand(argMultimap);
    }

    private boolean isValid(ArgumentMultimap argumentMultimap) throws ParseException {
        boolean namePresent = argumentMultimap.getValue(PREFIX_NAME).isPresent();
        boolean companyNamePresent = argumentMultimap.getValue(PREFIX_COMPANY).isPresent();
        boolean tagPresent = argumentMultimap.getValue(PREFIX_TAG).isPresent();

        if (namePresent) {
            ParserUtil.parseName(argumentMultimap.getValue(PREFIX_NAME).get());
        }
        if (companyNamePresent) {
            ParserUtil.parseCompanyName(argumentMultimap.getValue(PREFIX_COMPANY).get());
        }
        if (tagPresent) {
            List<String> dummy = Arrays.asList(argumentMultimap.getValue(PREFIX_TAG).get().split("\\s+"));
            for (String s : dummy) {
                ParserUtil.parseTag(s);
            }
        }

        return namePresent || companyNamePresent || tagPresent;
    }
}

