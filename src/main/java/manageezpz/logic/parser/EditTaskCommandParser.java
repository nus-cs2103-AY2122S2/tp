package manageezpz.logic.parser;

import manageezpz.commons.core.index.Index;
import manageezpz.logic.commands.EditTaskCommand;
import manageezpz.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;
import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageezpz.logic.parser.CliSyntax.*;

public class EditTaskCommandParser implements Parser<EditTaskCommand>{

    @Override
    public EditTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_TIME, PREFIX_DATE);
        Index index;
        EditTaskRequest editTaskRequest;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_DESCRIPTION).isEmpty() && argMultimap.getValue(PREFIX_TIME).isEmpty()
                && argMultimap.getValue(PREFIX_DATE).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE));
        } else {
            return new EditTaskCommand(index, argMultimap.getValue(PREFIX_DESCRIPTION).get(),
                    argMultimap.getValue(PREFIX_TIME).get(), argMultimap.getValue(PREFIX_DATE).get());
        }
    }
}


