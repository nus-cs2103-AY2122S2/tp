package seedu.trackbeau.logic.parser.service;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_PRICE;

import seedu.trackbeau.commons.core.index.Index;
import seedu.trackbeau.logic.commands.service.EditServiceCommand;
import seedu.trackbeau.logic.commands.service.EditServiceCommand.EditServiceDescriptor;
import seedu.trackbeau.logic.parser.ArgumentMultimap;
import seedu.trackbeau.logic.parser.ArgumentTokenizer;
import seedu.trackbeau.logic.parser.Parser;
import seedu.trackbeau.logic.parser.ParserUtil;
import seedu.trackbeau.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new EditServiceCommand object
 */
public class EditServiceCommandParser implements Parser<EditServiceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditServiceCommand
     * and returns an EditServiceCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditServiceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PRICE, PREFIX_DURATION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditServiceCommand.MESSAGE_USAGE), pe);
        }

        EditServiceDescriptor editServiceDescriptor = new EditServiceDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editServiceDescriptor.setName(ParserUtil.parseServiceName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PRICE).isPresent()) {
            editServiceDescriptor.setPrice(ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get()));
        }
        if (argMultimap.getValue(PREFIX_DURATION).isPresent()) {
            editServiceDescriptor.setDuration(ParserUtil.parseDuration(argMultimap.getValue(PREFIX_DURATION).get()));
        }

        if (!editServiceDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditServiceCommand.MESSAGE_NOT_EDITED);
        }

        return new EditServiceCommand(index, editServiceDescriptor);
    }
}
