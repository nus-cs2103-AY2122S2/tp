package seedu.trackbeau.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_ALLERGIES;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_BIRTHDATE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_HAIRTYPE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_REGDATE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_SERVICES;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_SKINTYPE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_STAFFS;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.trackbeau.commons.core.index.Index;
import seedu.trackbeau.logic.commands.customer.EditCustomerCommand;
import seedu.trackbeau.logic.commands.customer.EditCustomerCommand.EditCustomerDescriptor;
import seedu.trackbeau.logic.parser.exceptions.ParseException;
import seedu.trackbeau.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCustomerCommand object
 */
public class EditCustomerCommandParser implements Parser<EditCustomerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCustomerCommand
     * and returns an EditCustomerCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCustomerCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_SKINTYPE, PREFIX_HAIRTYPE, PREFIX_BIRTHDATE,
                PREFIX_STAFFS, PREFIX_SERVICES, PREFIX_ALLERGIES, PREFIX_REGDATE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditCustomerCommand.MESSAGE_USAGE), pe);
        }

        EditCustomerDescriptor editCustomerDescriptor = new EditCustomerDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editCustomerDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editCustomerDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editCustomerDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editCustomerDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_SKINTYPE).isPresent()) {
            editCustomerDescriptor.setSkinType(ParserUtil.parseSkinType(argMultimap.getValue(PREFIX_SKINTYPE).get()));
        }
        if (argMultimap.getValue(PREFIX_HAIRTYPE).isPresent()) {
            editCustomerDescriptor.setHairType(ParserUtil.parseHairType(argMultimap.getValue(PREFIX_HAIRTYPE).get()));
        }
        if (argMultimap.getValue(PREFIX_BIRTHDATE).isPresent()) {
            editCustomerDescriptor.setBirthdate(ParserUtil
                .parseBirthdate(argMultimap.getValue(PREFIX_BIRTHDATE).get()));
        }
        if (argMultimap.getValue(PREFIX_REGDATE).isPresent()) {
            editCustomerDescriptor.setRegistrationDate(ParserUtil
                .parseRegistrationDate(argMultimap.getValue(PREFIX_REGDATE).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_STAFFS)).ifPresent(editCustomerDescriptor::setStaffs);
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_SERVICES)).ifPresent(editCustomerDescriptor::setServices);
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_ALLERGIES)).ifPresent(editCustomerDescriptor::setAllergies);

        if (!editCustomerDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCustomerCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCustomerCommand(index, editCustomerDescriptor);
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
