package seedu.address.logic.commands.interview;

import java.io.FileNotFoundException;

import seedu.address.commons.core.DataType;
import seedu.address.commons.exceptions.ExportCsvOpenException;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ExportCsvCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ExportInterviewCsvCommand extends ExportCsvCommand {

    public static final String MESSAGE_SUCCESS = "Interview CSV is successfully exported at export_csv/interview.csv";

    @Override
    public CommandResult execute(Model model) throws CommandException, FileNotFoundException, ExportCsvOpenException {
        model.exportCsvInterview();
        return new CommandResult(MESSAGE_SUCCESS, getCommandDataType());
    }

    @Override
    public DataType getCommandDataType() {
        return DataType.INTERVIEW;
    }
}
