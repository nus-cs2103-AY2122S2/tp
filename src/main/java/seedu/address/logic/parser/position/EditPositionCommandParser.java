package seedu.address.logic.parser.position;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUM_OPENINGS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENT;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.position.EditPositionCommand;
import seedu.address.logic.commands.position.EditPositionCommand.EditPositionDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.position.Requirement;

public class EditPositionCommandParser implements Parser<EditPositionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditPositionCommand
     * and returns an EditPositionCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditPositionCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_POSITION, PREFIX_NUM_OPENINGS, PREFIX_DESCRIPTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditPositionCommand.MESSAGE_USAGE), pe);
        }

        EditPositionDescriptor editPositionDescriptor = new EditPositionDescriptor();
        if (argMultimap.getValue(PREFIX_POSITION).isPresent()) {
            editPositionDescriptor.setPositionName(ParserUtil
                    .parsePositionName(argMultimap.getValue(PREFIX_POSITION).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editPositionDescriptor.setDescription(ParserUtil
                    .parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_NUM_OPENINGS).isPresent()) {
            editPositionDescriptor.setPositionOpenings(ParserUtil
                    .parseOpenings(argMultimap.getValue(PREFIX_NUM_OPENINGS).get()));
        }
        parseRequirementsForEdit(argMultimap.getAllValues(PREFIX_REQUIREMENT))
                .ifPresent(editPositionDescriptor::setRequirements);

        if (!editPositionDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditPositionCommand.MESSAGE_NOT_EDITED);
        }

        return new EditPositionCommand(index, editPositionDescriptor);
    }

    /**
     * Parses {@code Collection<String> requirements} into a {@code Set<Requirement>} if {@code requirements}
     * is non-empty.
     * If {@code requirements} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Requirement>} containing zero requirements.
     */
    private Optional<Set<Requirement>> parseRequirementsForEdit(Collection<String> requirements)
            throws ParseException {
        assert requirements != null;

        if (requirements.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> requirementSet = requirements.size() == 1 && requirements.contains("")
                ? Collections.emptySet() : requirements;
        return Optional.of(ParserUtil.parseRequirements(requirements));
    }
}
