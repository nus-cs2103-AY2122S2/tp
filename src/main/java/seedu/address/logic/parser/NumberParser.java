package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;



public class NumberParser {

    private final int index;
    private final Command lastCommand;

    /**
     * Parses number inputs. Used for confirming which clients the user is referring to
     * in edit and delete scenarios with clients with similar names
     *
     * @param userInput
     * @param lastCommand
     */
    public NumberParser(String userInput, Command lastCommand) {
        this.index = Integer.parseInt(userInput);
        this.lastCommand = lastCommand;
    }

    /**
     * Parses the given index in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parse() throws ParseException {
        if (lastCommand instanceof EditCommand) {
            EditCommand newCommand = (EditCommand) lastCommand;
            newCommand.setIndex(index);
            return newCommand;
        } else {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
