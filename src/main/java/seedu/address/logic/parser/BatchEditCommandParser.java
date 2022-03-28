package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM;

import java.util.LinkedList;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.BatchEditCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class BatchEditCommandParser implements Parser<BatchEditCommand> {
    @Override
    public BatchEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SKILL, PREFIX_TEAM);
        LinkedList<Index> indices;

        try {
            indices = ParserUtil.parseIndices(argMultimap.getPreamble(), " ");
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchEditCommand.MESSAGE_USAGE), pe);
        }

        EditCommand.EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();
        ParserUtil.parseSkillSetForEdit(argMultimap.getAllValues(PREFIX_SKILL))
            .ifPresent(editPersonDescriptor::setSkillSet);
        ParserUtil.parseTeamsForEdit(argMultimap.getAllValues(PREFIX_TEAM)).ifPresent(editPersonDescriptor::setTeams);


        return new BatchEditCommand(indices, editPersonDescriptor);
    }

}
