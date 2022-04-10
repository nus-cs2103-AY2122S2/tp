package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditBuyerCommand;
import seedu.address.logic.commands.EditBuyerCommand.EditBuyerDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditBuyerCommandParser implements Parser<EditBuyerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditBuyerCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_TAG,
                    PREFIX_HOUSE_TYPE, PREFIX_LOCATION, PREFIX_PRICE_RANGE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditBuyerCommand.MESSAGE_USAGE), pe);
        }

        EditBuyerDescriptor editBuyerDescriptor = new EditBuyerDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editBuyerDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editBuyerDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        //for tags
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editBuyerDescriptor::setTags);
        //for Property
        if (argMultimap.getValue(PREFIX_HOUSE_TYPE).isPresent()) {
            editBuyerDescriptor.setHouseType(ParserUtil.parseHouseType(argMultimap.getValue(PREFIX_HOUSE_TYPE).get()));
        }
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            editBuyerDescriptor.setLocation(ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get()));
        }
        if (argMultimap.getValue(PREFIX_PRICE_RANGE).isPresent()) {
            editBuyerDescriptor.setPriceRange(ParserUtil.parsePriceRange(
                    argMultimap.getValue(PREFIX_PRICE_RANGE).get()));
        }

        if (!editBuyerDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditBuyerCommand.MESSAGE_NOT_EDITED);
        }

        return new EditBuyerCommand(index, editBuyerDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
