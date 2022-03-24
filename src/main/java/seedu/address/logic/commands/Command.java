package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    public enum CommandEnum {
        add(AddCommand.MESSAGE_USAGE),
        clear(ClearCommand.MESSAGE_USAGE),
        delete(DeleteCommand.MESSAGE_USAGE),
        edit(EditCommand.MESSAGE_USAGE),
        exit(ExitCommand.MESSAGE_USAGE),
        find(FindCommand.MESSAGE_USAGE),
        flag(FlagCommand.MESSAGE_USAGE),
        list(ListCommand.MESSAGE_USAGE),
        sort(SortCommand.MESSAGE_USAGE);

        public static final String MESSAGE_CONSTRAINTS = "Please include proper commands to display help for.";

        private final String messageUsage;

        CommandEnum(String messageUsage) {
            this.messageUsage = messageUsage;
        }

        public static boolean isValidCommand(String command) {
            for (CommandEnum commands : CommandEnum.values()) {
                if (command.equals(commands.name())) {
                    return true;
                }
            }
            return false;
        }

        public String getMessageUsage() {
            return messageUsage;
        }
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

}
