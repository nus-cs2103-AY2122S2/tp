package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.InsurancePackagesSet;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.storage.CommandStorage;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final CommandStorage commandStorage;
    private final AddressBookParser addressBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage, CommandStorage commandStorage) {
        this.model = model;
        this.storage = storage;
        this.commandStorage = commandStorage;
        addressBookParser = new AddressBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        commandStorage.addCommand(commandText);

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
            logger.info("AddressBook saved!");
            storage.saveInsurancePackages(model.getInsurancePackagesSet());
            logger.info("Insurance Packages saved!");
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
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public void saveAddressBookToCsv(Path csvFilePath) throws CommandException {
        try {
            storage.saveAddressBookToCsv(model.getAddressBook(), csvFilePath);
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
    }

    @Override
    public void readAddressBookFromCsv(Path csvFilePath) throws CommandException {
        try {
            Optional<ReadOnlyAddressBook> ab = storage.readAddressBookFromCsv(csvFilePath);
            if (ab.isPresent()) {
                ReadOnlyAddressBook readOnlyAb = ab.get();
                model.setAddressBook(readOnlyAb);

                for (Person p: readOnlyAb.getPersonList()) {
                    model.addInsurancePackage(p.getInsurancePackage());
                }

                logger.info("Successfully set address book, without saving.");
            } else {
                logger.info("No change to address book after attempting to read from CSV.");
            }
        } catch (DataConversionException | IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
    }

    @Override
    public String getPreviousCommand() {
        return commandStorage.getPreviousCommand();
    }

    @Override
    public String getNextCommand() {
        return commandStorage.getNextCommand();
    }

    @Override
    public InsurancePackagesSet getAllPackages() {
        return model.getInsurancePackagesSet();
    }
}
