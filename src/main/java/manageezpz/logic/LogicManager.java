package manageezpz.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.ObservableList;
import manageezpz.commons.core.GuiSettings;
import manageezpz.commons.core.LogsCenter;
import manageezpz.logic.commands.Command;
import manageezpz.logic.commands.CommandResult;
import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.logic.parser.AddressBookParser;
import manageezpz.logic.parser.exceptions.ParseException;
import manageezpz.model.Model;
import manageezpz.model.ReadOnlyAddressBook;
import manageezpz.model.person.Person;
import manageezpz.model.task.Task;
import manageezpz.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";

    public static final String INPUT_CONTAINS_SPECIAL_CHARACTERS = "Please do not use invisible characters!";

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
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        //@@author ChanWeiJie-reused
        //Reused from https://stackoverflow.com/a/1795436
        //with minor modifications
        Pattern pattern = Pattern.compile("[\\p{C}]");
        Matcher matcher = pattern.matcher(commandText);
        boolean isInvalid = matcher.find();
        //@@author

        if (isInvalid) {
            throw new ParseException(INPUT_CONTAINS_SPECIAL_CHARACTERS);
        }

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
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
    public ObservableList<Task> getFilteredTaskList() {
        return model.getFilteredTaskList();
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
