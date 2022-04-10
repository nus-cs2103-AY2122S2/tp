package seedu.address.logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.ExportCsvOpenException;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.HireLahParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyHireLah;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.interview.Interview;
import seedu.address.model.position.Position;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final HireLahParser hireLahParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        hireLahParser = new HireLahParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, FileNotFoundException, ParseException,
            ExportCsvOpenException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = hireLahParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveHireLah(model.getHireLah());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyHireLah getHireLah() {
        return model.getHireLah();
    }

    @Override
    public ObservableList<Applicant> getFilteredApplicantList() {
        return model.getFilteredApplicantList();
    }

    @Override
    public ObservableList<Position> getFilteredPositionList() {
        return model.getFilteredPositionList();
    }

    @Override
    public ObservableList<Interview> getFilteredInterviewList() {
        return model.getFilteredInterviewList();
    }

    @Override
    public Path getHireLahFilePath() {
        return model.getHireLahFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
