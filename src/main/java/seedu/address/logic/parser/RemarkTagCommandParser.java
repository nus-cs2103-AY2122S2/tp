package seedu.address.logic.parser;

import seedu.address.logic.commands.AttachTagCommand;
import seedu.address.logic.commands.RemarkTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Remark;
import seedu.address.model.tag.Tag;

import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class RemarkTagCommandParser implements Parser<RemarkTagCommand> {

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
            System.out.println("here");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemarkTagCommand.MESSAGE_USAGE));
        }

        Tag tag = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get());
        String remark_msg = argMultimap.getValue(PREFIX_REMARK).orElse("");
        Remark remark = new Remark(remark_msg);

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
