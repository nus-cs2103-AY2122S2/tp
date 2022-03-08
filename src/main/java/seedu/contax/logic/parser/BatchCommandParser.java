package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;

import seedu.contax.logic.commands.BatchCommand;
import seedu.contax.logic.commands.Command;
import seedu.contax.logic.parser.exceptions.ParseException;

public class BatchCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the multiple commands
     * and returns a Command object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public BatchCommand parse(String args) throws ParseException {
        if (!args.contains("&&")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchCommand.MESSAGE_USAGE));
        }
        String[] parsedArgs = args.split("&&");
        AddressBookParser addressBookParser = new AddressBookParser();
        List<Command> commandList = new ArrayList<>();
        for (String arg: parsedArgs) {
            Command command = addressBookParser.parseCommand(arg);
            commandList.add(command);
        }
        return new BatchCommand(commandList);
    }
}
