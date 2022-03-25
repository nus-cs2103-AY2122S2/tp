package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABSTATUS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditLabCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lab.LabMark;
import seedu.address.model.lab.LabStatus;

/**
 * Parses input arguments and creates a new EditLabCommand object
 */
public class EditLabCommandParser implements Parser<EditLabCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditLabCommand
     * and returns an EditLabCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public EditLabCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LAB, PREFIX_LABSTATUS, PREFIX_LABMARK);

        if (!(ArgumentTokenizer.arePrefixesPresent(argMultimap, PREFIX_LAB, PREFIX_LABSTATUS)
                || ArgumentTokenizer.arePrefixesPresent(argMultimap, PREFIX_LAB, PREFIX_LABMARK))
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditLabCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditLabCommand.MESSAGE_USAGE), pe);
        }

        int labNumber = ParserUtil.parseLab(argMultimap.getValue(PREFIX_LAB).get()).labNumber;

        try {
            if (ArgumentTokenizer.arePrefixesPresent(argMultimap, PREFIX_LABSTATUS, PREFIX_LABMARK)) {
                LabStatus labStatus = ParserUtil.parseLabStatus(argMultimap.getValue(PREFIX_LABSTATUS).get());
                LabMark labMark = ParserUtil.parseLabMark(argMultimap.getValue(PREFIX_LABMARK).get());
                return new EditLabCommand(index, labNumber, labStatus, labMark);
            } else if (ArgumentTokenizer.isPrefixPresent(argMultimap, PREFIX_LABSTATUS)) {
                LabStatus labStatus = ParserUtil.parseLabStatus(argMultimap.getValue(PREFIX_LABSTATUS).get());
                return new EditLabCommand(index, labNumber, labStatus);
            } else {
                LabMark labMark = ParserUtil.parseLabMark(argMultimap.getValue(PREFIX_LABMARK).get());
                return new EditLabCommand(index, labNumber, labMark);
            }
        } catch (IllegalArgumentException e) { // Not a valid EditLabCommand
            throw new ParseException(e.getMessage(), e);
        }
    }

}
