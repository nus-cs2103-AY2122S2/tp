package seedu.address.logic.parser;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

public class NumberParser {

    int index;
    Command lastCommand;

    public NumberParser(String userInput, Command lastCommand) {
        this.index = Integer.parseInt(userInput);
        this.lastCommand = lastCommand;
    }

    public Command parse() throws ParseException {
        if (lastCommand instanceof EditCommand) {
            ((EditCommand) lastCommand).setIndex(index);
            return lastCommand;
        } else {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
