package seedu.address.logic.commands.applicant;

import seedu.address.commons.core.DataType;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ExportCsvCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ExportApplicantCsvCommand extends ExportCsvCommand {
    @Override
    public DataType getCommandDataType() {
        return null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
