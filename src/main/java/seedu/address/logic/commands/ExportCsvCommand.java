package seedu.address.logic.commands;

import java.io.FileNotFoundException;

import seedu.address.commons.exceptions.ExportCsvOpenException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public abstract class ExportCsvCommand extends Command {
    public static final String COMMAND_WORD = "export";

    public abstract CommandResult execute(Model model) throws CommandException, FileNotFoundException,
            ExportCsvOpenException;
}
