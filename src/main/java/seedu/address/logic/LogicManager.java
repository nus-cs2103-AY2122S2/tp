package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.StackUndoRedo;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.storage.Storage;
import seedu.address.ui.StatusBarFooter;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;
    private final StackUndoRedo undoRedoStack;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and
     * {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        this.undoRedoStack = new StackUndoRedo();

        addressBookParser = new AddressBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        command.setData(undoRedoStack);

        commandResult = command.execute(model);

        saveBooks();

        if (!commandResult.isShowAdd() && !commandResult.isShowEdit()) {
            undoRedoStack.push(command);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public Path getArchivedAddressBookFilePath() {
        return model.getArchivedAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public void switchAddressBook() {
        model.switchAddressBook();
    }

    /**
     * Saves AddressBook and ArchiveBook
     */
    private void saveBooks() throws CommandException {
        assert((StatusBarFooter.isArchiveBook() && model.isSwapped())
        || (!StatusBarFooter.isArchiveBook() && !model.isSwapped()));
        try {
            if (StatusBarFooter.isArchiveBook()) {
                storage.saveAddressBook(model.getArchiveBook());
                storage.saveArchivedAddressBook(model.getAddressBook());
            } else {
                storage.saveAddressBook(model.getAddressBook());
                storage.saveArchivedAddressBook(model.getArchiveBook());
            }
        } catch (DataConversionException | IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
    }
}
