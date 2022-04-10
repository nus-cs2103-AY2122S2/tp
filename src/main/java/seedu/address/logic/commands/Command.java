package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    public static final String COMMAND_PERSON = "-c";
    public static final String COMMAND_INSURANCE = "-i";
    public static final String COMMAND_RECORD = "-r";
    public static final String COMMAND_APPOINTMENT = "-a";
    public static final String COMMAND_APPOINTMENT_HISTORY = "-h";
    public static final String COMMAND_EXPIRED_RECORD = "-e";


    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

}
