package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    public enum CommandEnum {
        add(AddCommand.class),
        clear(ClearCommand.class),
        delete(DeleteCommand.class),
        edit(EditCommand.class),
        exit(ExitCommand.class),
        find(FindCommand.class),
        flag(FlagCommand.class),
        help(HelpCommand.class),
        list(ListCommand.class);

        private final Class<? extends Command> commandClass;

        private static final Map<String, Class<? extends Command>> nameToCommandMap = new HashMap<>();

        static {
            for (CommandEnum cmd : EnumSet.allOf(CommandEnum.class)) {
                nameToCommandMap.put(cmd.name(), cmd.commandClass);
            }
        }

        CommandEnum(Class<? extends Command> commandClass) {
            this.commandClass = commandClass;
        }

        public static boolean isValidCommand(String name) {
            return nameToCommandMap.containsKey(name);
        }

        public static Class<? extends Command> getCommand(String name) {
            return nameToCommandMap.get(name);
        }

    }

    public static final String MESSAGE_CONSTRAINTS = "";

    public static final String MESSAGE_USAGE;

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

}
