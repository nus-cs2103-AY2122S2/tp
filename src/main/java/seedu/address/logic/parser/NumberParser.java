package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.FlagCommand;
import seedu.address.logic.commands.MeetCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses number inputs. Used for confirming which clients the user is referring to
 * in edit and delete scenarios where clients have similar names
 */
public class NumberParser implements Parser<Command> {

    private final Command lastCommand;

    /**
     * Constructor of NumberParser.
     *
     * @param lastCommand
     */
    public NumberParser(Command lastCommand) {
        this.lastCommand = lastCommand;
    }

    /**
     * Parses the given index in the context of the EditCommand
     * and returns an EditCommand or DeleteCommmand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    @Override
    public Command parse(String userInput) throws ParseException {
        int index = Integer.parseInt(userInput);
        if (lastCommand instanceof EditCommand) {
            EditCommand newCommand = (EditCommand) lastCommand;
            newCommand.setIndex(index);
            return newCommand;
        } else if (lastCommand instanceof DeleteCommand) {
            DeleteCommand newCommand = (DeleteCommand) lastCommand;
            newCommand.setIndex(index);
            return newCommand;
        } else if (lastCommand instanceof MeetCommand) {
            MeetCommand newCommand = (MeetCommand) lastCommand;
            newCommand.setIndex(index);
            return newCommand;
        } else if (lastCommand instanceof FlagCommand) {
            FlagCommand newCommand = (FlagCommand) lastCommand;
            newCommand.setIndex(index);
            return newCommand;
        } else {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
