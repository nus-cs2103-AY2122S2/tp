package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABSTATUS;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditLabStatusCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.lab.LabStatus;

/**
 * Parses input arguments and creates a new EditLabStatusCommand object
 */
public class EditLabStatusCommandParser implements Parser<EditLabStatusCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditLabStatusCommand
     * and returns an EditLabStatusCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditLabStatusCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LAB, PREFIX_LABSTATUS);

        if (!arePrefixesPresent(argMultimap, PREFIX_LAB, PREFIX_LABSTATUS)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditLabStatusCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditLabStatusCommand.MESSAGE_USAGE), pe);
        }

        int labNumber = ParserUtil.parseLab(argMultimap.getValue(PREFIX_LAB).get()).labNumber;
        LabStatus labStatus = ParserUtil.parseLabStatus(argMultimap.getValue(PREFIX_LABSTATUS).get());

        return new EditLabStatusCommand(index, labNumber, labStatus);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
