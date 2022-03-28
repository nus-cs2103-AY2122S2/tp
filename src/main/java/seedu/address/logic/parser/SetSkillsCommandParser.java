package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

import java.util.LinkedList;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.SetSkillsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class SetSkillsCommandParser implements Parser<SetSkillsCommand> {
    @Override
    public SetSkillsCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_SKILL);
        LinkedList<Index> indices;

        try {
            indices = ParserUtil.parseIndices(argMultimap.getPreamble(), " ");
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetSkillsCommand.MESSAGE_USAGE), pe);
        }

        EditCommand.EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();
        parseSkillSetForEdit(argMultimap.getAllValues(PREFIX_SKILL)).ifPresent(editPersonDescriptor::setSkillSet);


    }
}
