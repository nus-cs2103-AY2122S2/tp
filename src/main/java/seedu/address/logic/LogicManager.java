package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.MeetCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.HustleBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.HustleBookHistory;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyHustleBook;
import seedu.address.model.person.Person;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final HustleBookParser hustleBookParser;
    private Command lastCommand;
    private int commandCount = 0;
    private HustleBookHistory hustleBookHistory;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        hustleBookParser = new HustleBookParser();
        hustleBookHistory = HustleBookHistory.getInstance();
        hustleBookHistory.update(getHustleBook());
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = hustleBookParser.parseCommand(commandText, lastCommand);
        commandResult = command.execute(model);
        setLastCommand(command);

        try {
            storage.saveHustleBook(model.getHustleBook());
            if (!(commandText.equals("undo") || commandText.equals("redo"))) {
                logger.info("Saving previous data state of HustleBook");
                hustleBookHistory.update(getHustleBook());
            }
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyHustleBook getHustleBook() {
        return model.getHustleBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getHustleBookFilePath() {
        return model.getHustleBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    private void setLastCommand(Command command) {
        if ((lastCommand instanceof EditCommand || lastCommand instanceof MeetCommand) && lastCommand == command) {
            commandCount++;
        } else {
            commandCount = 0;
        }
        if (commandCount >= 1) {
            lastCommand = null;
        } else {
            lastCommand = command;
        }
    }
}
