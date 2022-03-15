package seedu.address.logic.commands.help;

import seedu.address.commons.core.DataType;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

public class OverallHelpCommand extends HelpCommand {
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(HelpDescription.OVERALL_HELPING_DESCRIPTION, getCommandDataType(), true, false);
    }

    @Override
    public DataType getCommandDataType() {
        return null;
    }
}
