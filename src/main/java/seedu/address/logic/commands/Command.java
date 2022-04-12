package seedu.address.logic.commands;

import java.io.FileNotFoundException;

import seedu.address.commons.core.DataType;
import seedu.address.commons.exceptions.ExportCsvOpenException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException, FileNotFoundException, ParseException,
            ExportCsvOpenException;

    /**
     * Returns the data type associated with the command.
     *
     * @return DataType of the command
     */
    public abstract DataType getCommandDataType();

}
