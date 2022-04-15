package peoplesoft.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import peoplesoft.commons.core.GuiSettings;
import peoplesoft.commons.core.LogsCenter;
import peoplesoft.logic.commands.Command;
import peoplesoft.logic.commands.CommandHelpMessage;
import peoplesoft.logic.commands.CommandResult;
import peoplesoft.logic.commands.HelpCommand;
import peoplesoft.logic.commands.exceptions.CommandException;
import peoplesoft.logic.parser.AddressBookParser;
import peoplesoft.logic.parser.exceptions.ParseException;
import peoplesoft.model.Model;
import peoplesoft.model.ReadOnlyAddressBook;
import peoplesoft.model.job.Job;
import peoplesoft.model.person.Person;
import peoplesoft.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
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
    public ObservableList<Job> getFilteredJobList() {
        return model.getFilteredJobList();
    }

    @Override
    public ObservableList<CommandHelpMessage> getCommandHelpMessageList() {
        return HelpCommand.COMMANDS;
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
