package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.StackUndoRedo;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.Storage;

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
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
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

        try {
            storage.saveAddressBook(model.getAddressBook());

            undoRedoStack.push(command);

        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
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
    public void switchAddressBook() throws CommandException {
        Optional<ReadOnlyAddressBook> addressBookOptional;
        ReadOnlyAddressBook initialData;
        try {
            addressBookOptional = storage.readArchivedAddressBook();
            if (addressBookOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample Archived AddressBook");
            }
            initialData = addressBookOptional.orElseGet(SampleDataUtil::getSampleAddressBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty Archived AddressBook");
            initialData = new AddressBook();

        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty Archived AddressBook");
            initialData = new AddressBook();
        }

        try {
            storage.saveArchivedAddressBook(model.getAddressBook());
            storage.saveAddressBook(new AddressBook(initialData));
        } catch (IOException | DataConversionException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        model.setAddressBook(new AddressBook(initialData));
        System.out.println(getArchivedAddressBookFilePath().toString());
    }

    /**
     * 1. Remove target from existing addressbook
     * 2. Switch to alt addressbook
     * 3. Add target to alt addressbook
     * 4. Switch back to original addressbook
     */
    @Override
    public void archivePersonByIndex(String oneBasedString) throws CommandException {
        Index oneBased = Index.fromOneBased(Integer.parseInt(oneBasedString));
        Person target = model.getFilteredPersonList().get(oneBased.getZeroBased());

        model.deletePerson(target);
        switchAddressBook();
        model.addPerson(target);
        switchAddressBook();
    }
}
