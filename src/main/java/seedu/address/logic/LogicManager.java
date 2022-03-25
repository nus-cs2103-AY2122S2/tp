package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.Transaction;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    public static final String TEMP_FILE_OPS_ERROR_MESSAGE = "Could not save temp data to file: ";
    public static final String TEMP_FILE_READ_ERROR_MESSAGE = "Could not read temp file data, might be corrupted: ";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
    }

    @Override
    public CommandResult execute(String userInput)
            throws CommandException, ParseException, IllegalArgumentException {
        logger.info("----------------[USER INPUT][" + userInput + "]");

        CommandResult commandResult = null;
        String[] commands = userInput.split("\\|");
        ReadOnlyAddressBook addressBookBeforeCommand = new AddressBook(model.getAddressBook());

        for (String commandText : commands) {
            logger.info("----------------[USER COMMAND][" + commandText + "]");

            Command command = addressBookParser.parseCommand(commandText);
            commandResult = command.execute(model);

            try {
                storage.saveAddressBook(model.getAddressBook());
            } catch (IOException ioe) {
                throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
            }

            if (commandResult.isExit() || commandResult.isShowHelp() || commandResult.isUndoPrevCommand()) {
                break;
            }
        }

        assert commandResult != null : "CommandResult is null, should not happen";
        handleTempAddressBookFiles(addressBookBeforeCommand, commandResult.isUndoPrevCommand());

        return commandResult;
    }

    /**
     * Handles managing reading and writing address book temporary files.
     *
     * @param addressBookBeforeCommand Data of the address book before modification.
     * @param isUndoPrevCommand If it's undo command, read from the temporary files. If not write to it.
     * @throws CommandException If there are any issues reading and writing the address book temporary files.
     */
    private void handleTempAddressBookFiles(ReadOnlyAddressBook addressBookBeforeCommand, boolean isUndoPrevCommand)
            throws CommandException {

        if (isUndoPrevCommand) {
            undoPrevModification();
        } else {
            savePrevAddressBookDataInTemp(addressBookBeforeCommand);
        }
    }

    /**
     * Undos previous modification made to the address book data.
     *
     * @throws CommandException If there are any issues reading previous address book data or no undos can be done.
     */
    public void undoPrevModification() throws CommandException {
        Optional<ReadOnlyAddressBook> tempAddressFileData;
        try {
            tempAddressFileData = storage.popTempAddressFileData();
        } catch (Exception exception) {
            throw new CommandException(TEMP_FILE_READ_ERROR_MESSAGE + exception, exception);
        }

        if (tempAddressFileData.isPresent()) {
            model.setAddressBook(tempAddressFileData.get());
        } else {
            throw new CommandException(UndoCommand.REACHED_UNDO_LIMIT);
        }
    }

    /**
     *  Saves address book data before the data modification made into a temporary file.
     *
     * @param addressBookBeforeCommand Data of the address book before modification.
     * @throws CommandException If there are issues saving the data into the temporary file.
     */
    public void savePrevAddressBookDataInTemp(ReadOnlyAddressBook addressBookBeforeCommand) throws CommandException {
        if (addressBookBeforeCommand.equals(model.getAddressBook())) {
            return;
        }

        //address book data before command saved to temp file
        try {
            storage.addNewTempAddressBookFile(addressBookBeforeCommand);
        } catch (Exception exception) {
            throw new CommandException(TEMP_FILE_OPS_ERROR_MESSAGE + exception, exception);
        }
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
    public ObservableList<Transaction> getFilteredTransactionList() {
        return model.getFilteredTransactionList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
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
