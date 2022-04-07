package seedu.address.logic.commands.help;

import seedu.address.commons.core.DataType;
import seedu.address.logic.HelpArgument;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


public class DetailHelpCommand extends HelpCommand {
    private HelpArgument helpArgument;

    public DetailHelpCommand(HelpArgument helpArgument) {
        this.helpArgument = helpArgument;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(helpArgument.toString(), getCommandDataType(), true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DetailHelpCommand // instanceof handles nulls
                && helpArgument.equals(((DetailHelpCommand) other).helpArgument)); // state check
    }

    @Override
    public DataType getCommandDataType() {
        return null;
    }
}
