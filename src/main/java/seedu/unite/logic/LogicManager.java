package seedu.unite.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.unite.commons.core.GuiSettings;
import seedu.unite.commons.core.LogsCenter;
import seedu.unite.logic.commands.Command;
import seedu.unite.logic.commands.CommandResult;
import seedu.unite.logic.commands.exceptions.CommandException;
import seedu.unite.logic.parser.UniteParser;
import seedu.unite.logic.parser.exceptions.ParseException;
import seedu.unite.model.Model;
import seedu.unite.model.ReadOnlyUnite;
import seedu.unite.model.person.Person;
import seedu.unite.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final UniteParser uniteParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        uniteParser = new UniteParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = uniteParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveUnite(model.getUnite());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    public Model getModel() {
        return model;
    }

    @Override
    public ReadOnlyUnite getUnite() {
        return model.getUnite();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getUniteFilePath() {
        return model.getUniteFilePath();
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
