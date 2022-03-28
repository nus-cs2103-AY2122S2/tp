package unibook.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import unibook.commons.core.GuiSettings;
import unibook.commons.core.LogsCenter;
import unibook.logic.commands.Command;
import unibook.logic.commands.CommandResult;
import unibook.logic.commands.exceptions.CommandException;
import unibook.logic.parser.UniBookParser;
import unibook.logic.parser.exceptions.ParseException;
import unibook.model.Model;
import unibook.model.ReadOnlyUniBook;
import unibook.model.module.Module;
import unibook.model.person.Person;
import unibook.storage.Storage;


/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final UniBookParser uniBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        uniBookParser = new UniBookParser();
    }

    @Override
    public CommandResult execute(String commandText,
                                 Boolean isPersonListShowing,
                                 Boolean isModuleListShowing,
                                 Boolean isGroupListShowing) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = uniBookParser.parseCommand(commandText);
        commandResult = command.execute(model,
            isPersonListShowing,
            isModuleListShowing,
                isGroupListShowing);

        try {
            storage.saveUniBook(model.getUniBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyUniBook getUniBook() {
        return model.getUniBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return model.getFilteredModuleList();
    }

    @Override
    public Path getUniBookFilePath() {
        return model.getUniBookFilePath();
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
