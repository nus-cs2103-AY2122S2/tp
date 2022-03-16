package seedu.address.logic.commands.position;

import seedu.address.commons.core.DataType;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
import seedu.address.model.Model;

public class ListPositionCommand extends ListCommand {
    public static final String MESSAGE_SUCCESS = ""; //TODO

    @Override
    public CommandResult execute(Model model) {
        //TODO
        return null;
    }

    @Override
    public DataType getCommandDataType() {
        return DataType.POSITION;
    }
}
