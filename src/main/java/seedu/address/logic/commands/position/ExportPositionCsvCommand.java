package seedu.address.logic.commands.position;

import seedu.address.commons.core.DataType;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ExportCsvCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ExportPositionCsvCommand extends ExportCsvCommand {

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }

    @Override
    public DataType getCommandDataType() {
        return DataType.POSITION;
    }
}
