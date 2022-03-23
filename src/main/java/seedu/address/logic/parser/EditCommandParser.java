package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.property.Property;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_PROPERTY, PREFIX_USERTYPE, PREFIX_USERIMAGE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_USERTYPE).isPresent()) {
            editPersonDescriptor.setUserType(ParserUtil.parseUserType(argMultimap.getValue(PREFIX_USERTYPE).get()));
        }
        if (argMultimap.getValue(PREFIX_USERIMAGE).isPresent()) {
            editPersonDescriptor.setUserImage(ParserUtil.parseUserImage(argMultimap.getValue(PREFIX_USERIMAGE).get()));
        }
        parsePropertiesForEdit(argMultimap.getAllValues(PREFIX_PROPERTY)).ifPresent(
                editPersonDescriptor::setProperties);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> properties} into a {@code Set<Property>} if {@code properties} is non-empty.
     * If {@code properties} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Property>} containing zero properties.
     */
    private Optional<Set<Property>> parsePropertiesForEdit(Collection<String> properties) throws ParseException {
        assert properties != null;

        if (properties.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> propertySet =
                properties.size() == 1 && properties.contains("") ? Collections.emptySet() : properties;
        return Optional.of(ParserUtil.parseProperties(propertySet));
    }
}
