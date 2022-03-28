package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JERSEY_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINEUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAYER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditCommand.EditScheduleDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_PLAYER, PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_HEIGHT, PREFIX_JERSEY_NUMBER, PREFIX_TAG, PREFIX_WEIGHT, PREFIX_LINEUP,
                        PREFIX_SCHEDULE, PREFIX_DESCRIPTION, PREFIX_DATE, PREFIX_INDEX);

        Name targetPlayerName;
        LineupName targetLineupName;
        Index index;

        if (argMultimap.getValue(PREFIX_LINEUP).isPresent()) {
            if (argMultimap.getValue(PREFIX_PLAYER).isPresent()) {
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
            }

            if (!argMultimap.getValue(PREFIX_NAME).isPresent()) {
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE_LINEUP));
            } else {
                targetLineupName = ParserUtil.parseLineupName(argMultimap.getValue(PREFIX_LINEUP).get());
                LineupName editLineupName = ParserUtil.parseLineupName(argMultimap.getValue(PREFIX_NAME).get());
                return new EditCommand(targetLineupName, editLineupName);
            }
        }

        // Player level
        if (arePrefixesPresent(argMultimap, PREFIX_PLAYER)) {
            try {
                targetPlayerName = ParserUtil.parsePlayer(argMultimap.getValue(PREFIX_PLAYER).get());
                // System.out.println(targetPlayerName);
            } catch (ParseException pe) {
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE_PLAYER), pe);
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

            if (argMultimap.getValue(PREFIX_HEIGHT).isPresent()) {
                editPersonDescriptor.setHeight(ParserUtil.parseHeight(argMultimap.getValue(PREFIX_HEIGHT).get()));
            }
            if (argMultimap.getValue(PREFIX_JERSEY_NUMBER).isPresent()) {
                editPersonDescriptor.setJerseyNumber(
                        ParserUtil.parseJerseyNumber(argMultimap.getValue(PREFIX_JERSEY_NUMBER).get()));
            }
            if (argMultimap.getValue(PREFIX_WEIGHT).isPresent()) {
                editPersonDescriptor.setWeight(ParserUtil.parseWeight(argMultimap.getValue(PREFIX_WEIGHT).get()));
            }
            parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

            if (!editPersonDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
            }
            return new EditCommand(targetPlayerName, editPersonDescriptor);
        }

        if (arePrefixesPresent(argMultimap, PREFIX_SCHEDULE)) {
            try {
                index = ParserUtil.parseIndex(argMultimap.getPreamble());
            } catch (ParseException pe) {
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE_SCHEDULE), pe);
            }
        }

        //Schedule level
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditScheduleDescriptor editScheduleDescriptor = new EditScheduleDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editScheduleDescriptor.setScheduleName(
                    ParserUtil.parseScheduleName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editScheduleDescriptor.setScheduleDescription(
                    ParserUtil.parseScheduleDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editScheduleDescriptor.setScheduleDateTime(
                    ParserUtil.parseScheduleDateTime(argMultimap.getValue(PREFIX_DATE).get()));
        }

        if (!editScheduleDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }


        return new EditCommand(index, editScheduleDescriptor);


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

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
