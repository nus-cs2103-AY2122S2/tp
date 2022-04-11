package seedu.address.logic.commands;

import java.util.Objects;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.ui.HelpWindow;



/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final StringBuilder HELP_STRING = new StringBuilder();

    public static final String COMMAND_WORD = "help";
    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD + "\n\n";

    public static final String INVALID_COMMAND_USAGE = "Invalid command usage";

    private final String commandWordArg;

    public HelpCommand(String commandWordArg) {
        this.commandWordArg = commandWordArg;
    }

    /**
     * Format help command
     * @return String for the help command
     */
    public static String helpCommandString() {
        HELP_STRING.append(HelpWindow.HELP_MESSAGE).append(Command.COMMAND).append(AddCommand.MESSAGE_USAGE)
                .append(ClearCommand.MESSAGE_USAGE).append(DeleteCommand.MESSAGE_USAGE)
                .append(EditCommand.MESSAGE_USAGE).append(ExitCommand.MESSAGE_USAGE)
                .append(FindCommand.MESSAGE_USAGE).append(ListCommand.MESSAGE_USAGE)
                .append((SetBudgetCommand.MESSAGE_USAGE))
                .append(PersonOwesCommand.MESSAGE_USAGE)
                .append(PaidPersonWhoOwedCommand.MESSAGE_USAGE)
                .append(EditPersonCommand.MESSAGE_USAGE)
                .append(ListCatCommand.MESSAGE_USAGE)
                .append(FilterCommand.MESSAGE_USAGE)
                .append(HelpCommand.MESSAGE_USAGE)
                .append(AddCategoryCommand.MESSAGE_USAGE);
        return HELP_STRING.toString();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (commandWordArg.trim().isEmpty()) {
            return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        } else {
            switch (commandWordArg.trim()) {
            case AddCommand.COMMAND_WORD:
                return new CommandResult(AddCommand.MESSAGE_USAGE);

            case EditCommand.COMMAND_WORD:
                return new CommandResult(EditCommand.MESSAGE_USAGE);

            case DeleteCommand.COMMAND_WORD:
                return new CommandResult(DeleteCommand.MESSAGE_USAGE);

            case ClearCommand.COMMAND_WORD:
                return new CommandResult(ClearCommand.MESSAGE_USAGE);

            case FindCommand.COMMAND_WORD:
                return new CommandResult(FindCommand.MESSAGE_USAGE);

            case ListCommand.COMMAND_WORD:
                return new CommandResult(ListCommand.MESSAGE_USAGE);

            case ExitCommand.COMMAND_WORD:
                return new CommandResult(ExitCommand.MESSAGE_USAGE);

            case HelpCommand.COMMAND_WORD:
                return new CommandResult(HelpCommand.MESSAGE_USAGE);

            case AddCategoryCommand.COMMAND_WORD:
                return new CommandResult(AddCategoryCommand.MESSAGE_USAGE);

            case ListCatCommand.COMMAND_WORD:
                return new CommandResult(ListCatCommand.MESSAGE_USAGE);

            case SetBudgetCommand.COMMAND_WORD:
                return new CommandResult(SetBudgetCommand.MESSAGE_USAGE);

            case FilterCommand.COMMAND_WORD:
                return new CommandResult(FilterCommand.MESSAGE_USAGE);

            case PaidPersonWhoOwedCommand.COMMAND_WORD:
                return new CommandResult(PaidPersonWhoOwedCommand.MESSAGE_USAGE);

            case PersonOwesCommand.COMMAND_WORD:
                return new CommandResult(PersonOwesCommand.MESSAGE_USAGE);

            case EditPersonCommand.COMMAND_WORD:
                return new CommandResult(EditPersonCommand.MESSAGE_USAGE);

            default:
                throw new CommandException(INVALID_COMMAND_USAGE);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HelpCommand that = (HelpCommand) o;
        return Objects.equals(commandWordArg, that.commandWordArg);
    }

    public int hashCode() {
        return Objects.hash(commandWordArg);
    }
}
