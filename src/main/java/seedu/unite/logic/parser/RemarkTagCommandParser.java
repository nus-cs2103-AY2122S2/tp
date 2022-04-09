package seedu.unite.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.unite.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.stream.Stream;

import seedu.unite.logic.commands.RemarkTagCommand;
import seedu.unite.logic.parser.exceptions.ParseException;
import seedu.unite.model.tag.Remark;
import seedu.unite.model.tag.Tag;

/**
 * Parses input arguments and creates a new RemarkTagCommand object
 */
public class RemarkTagCommandParser implements Parser<RemarkTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemarkTagCommand
     * and returns an RemarkTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemarkTagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_TAG, PREFIX_REMARK);

        if (!arePrefixesPresent(argMultimap, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {

            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemarkTagCommand.MESSAGE_USAGE));
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_REMARK)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemarkTagCommand.MESSAGE_USAGE));
        }

        Tag tag = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get());
        String remarkMessage = argMultimap.getValue(PREFIX_REMARK).orElse("");
        Remark remark = new Remark(remarkMessage);

        return new RemarkTagCommand(tag, remark);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
