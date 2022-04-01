package seedu.address.logic.commands.help;

import seedu.address.commons.core.DataType;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


public class DetailHelpCommand extends HelpCommand {
    private String helpCommand;

    public DetailHelpCommand(String helpCommand) {
        this.helpCommand = helpCommand;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        switch (helpCommand) {
        case "add":
            return new CommandResult(HelpDescription.ADD_COMMAND_DESCRIPTION, getCommandDataType(), true, false);
        case "edit":
            return new CommandResult(HelpDescription.EDIT_COMMAND_DESCRIPTION, getCommandDataType(), true, false);
        case "delete":
            return new CommandResult(HelpDescription.DELETE_COMMAND_DESCRIPTION, getCommandDataType(), true, false);
        case "list":
            return new CommandResult(HelpDescription.LIST_COMMAND_DESCRIPTION, getCommandDataType(), true, false);
        case "pass":
            return new CommandResult(HelpDescription.PASS_COMMAND_DESCRIPTION, getCommandDataType(), true, false);
        case "fail":
            return new CommandResult(HelpDescription.FAIL_COMMAND_DESCRIPTION, getCommandDataType(), true, false);
        case "accept":
            return new CommandResult(HelpDescription.ACCEPT_COMMAND_DESCRIPTION, getCommandDataType(), true, false);
        case "reject":
            return new CommandResult(HelpDescription.REJECT_COMMAND_DESCRIPTION, getCommandDataType(), true, false);
        case "export":
            return new CommandResult(HelpDescription.EXPORT_COMMAND_DESCRIPTION, getCommandDataType(), true, false);
        case "exit":
            return new CommandResult(HelpDescription.EXIT_COMMAND_DESCRIPTION, getCommandDataType(), true, false);
        default:
            throw new CommandException(HelpDescription.COMMAND_NOT_FOUND_DESCRIPTION);
        }
    }

    @Override
    public DataType getCommandDataType() {
        return null;
    }
}
