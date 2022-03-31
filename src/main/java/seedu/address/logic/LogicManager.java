package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.ArchiveCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
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
    private AddressBook addressBook;
    private AddressBook archiveBook;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();

        this.addressBook = new AddressBook(model.getAddressBook());
        this.archiveBook = new AddressBook(model.getArchiveBook());
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);
        saveBooks();
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
        if (StatusBarFooter.isArchiveBook()) {
            model.setAddressBook(addressBook);
        } else {
            model.setAddressBook(archiveBook);
        }
    }

    /**
     * Saves AddressBook and ArchiveBook
     */
    private void saveBooks() {
        try {
            if (StatusBarFooter.isArchiveBook()) {
                storage.saveArchivedAddressBook(archiveBook);
                archiveBook = new AddressBook(model.getAddressBook());
            } else {
                storage.saveAddressBook(model.getAddressBook());
                addressBook = new AddressBook(model.getAddressBook());
            }
        } catch (DataConversionException | IOException e) {
            System.out.println("Error caught: " + e);
        }
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
        try {
            if (!StatusBarFooter.isArchiveBook()) {
                archiveBook.addPerson(target);
            } else {
                addressBook.addPerson(target);
            }
        } catch (DuplicatePersonException e) {
            throw new CommandException(String.format(ArchiveCommand.MESSAGE_DUPLICATE_PERSON_ARCHIVE + "\nDeleting the contact instead"));
        } finally {
            saveBooks();
        }

        archiveBook.getPersonList().forEach(x -> System.out.println(x));
    }
}
