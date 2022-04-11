package seedu.address.logic.commands;

import java.util.Objects;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD + " [COMMAND_WORD]";

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    public static final String INVALID_COMMAND_USAGE = "Invalid command usage";

    private final String commandWordArg;

    public HelpCommand(String commandWordArg) {
        this.commandWordArg = commandWordArg;
    }

    /**
     * Executes the help command.
     * @param model {@code Model} which the command should operate on.
     * @return A {@code CommandResult} object specifying the success message.
     * @throws CommandException if the command execution goes unexpected.
     */
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

            case SetDefaultGroupCommand.COMMAND_WORD:
                return new CommandResult(SetDefaultGroupCommand.MESSAGE_USAGE);

            case HelpCommand.COMMAND_WORD:
                return new CommandResult(HelpCommand.MESSAGE_USAGE);

            case MailIndexCommand.COMMAND_WORD:
                return new CommandResult(MailIndexCommand.MESSAGE_USAGE);

            case MailXCommand.COMMAND_WORD:
                return new CommandResult(MailXCommand.MESSAGE_USAGE);

            case MailAllCommand.COMMAND_WORD:
                return new CommandResult(MailAllCommand.MESSAGE_USAGE);

            case NewTaskCommand.COMMAND_WORD:
                return new CommandResult(NewTaskCommand.MESSAGE_USAGE);

            case RemoveTaskCommand.COMMAND_WORD:
                return new CommandResult(RemoveTaskCommand.MESSAGE_USAGE);

            case RedoCommand.COMMAND_WORD:
                return new CommandResult(RedoCommand.MESSAGE_USAGE);

            case UndoCommand.COMMAND_WORD:
                return new CommandResult(UndoCommand.MESSAGE_USAGE);

            case ImportCsvCommand.COMMAND_WORD:
                return new CommandResult(ImportCsvCommand.MESSAGE_USAGE);

            default:
                throw new CommandException(INVALID_COMMAND_USAGE);
            }
        }
    }

    /**
     * Checks whether two {@code HelpCommand} objects are equal or not.
     * @param o The other object against which comparison takes place.
     * @return A boolean value specifying whether the objects are equal or not.
     */
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

    @Override
    public int hashCode() {
        return Objects.hash(commandWordArg);
    }
}
