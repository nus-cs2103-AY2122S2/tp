package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddInsuranceCommand;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.AddRecordCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditInsuranceCommand;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.commands.EditRecordCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.insurance.Insurance;
import seedu.address.model.person.Person;
import seedu.address.model.record.Record;
import seedu.address.storage.Storage;
import seedu.address.ui.UiManager;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;
    private UiManager ui;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
    }

    /**
     * Set Ui.
     */
    @Override
    public void setUi(UiManager ui) {
        this.ui = ui;
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(model, commandText);
        commandResult = command.execute(model);
        updateDetail(command);

        try {
            storage.saveAddressBook(model.getAddressBook());
            storage.saveAppointmentBook(model.getAppointmentBook());
            storage.saveInsuranceBook(model.getInsuranceBook());
            storage.saveRecordBook(model.getRecordBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }


    /**
     * Updates Detail of UI when certain command is executed.
     */
    private void updateDetail(Command command) throws CommandException, ParseException {
        if (this.ui == null) {
            return;
        }

        if (command instanceof AddRecordCommand) {
            AddRecordCommand addRecordCommand = (AddRecordCommand) command;
            Record editedRecord = addRecordCommand.getToAdd();
            int index = this.model.getFilteredRecordList().size();
            this.ui.updateDetailPanel(editedRecord, index);
        }

        if (command instanceof AddInsuranceCommand) {
            AddInsuranceCommand addInsuranceCommand = (AddInsuranceCommand) command;
            Insurance editedInsurance = addInsuranceCommand.getToAdd();
            int index = this.model.getFilteredInsuranceList().size();
            this.ui.updateDetailPanel(editedInsurance, index);
        }

        if (command instanceof AddPersonCommand) {
            AddPersonCommand addPersonCommand = (AddPersonCommand) command;
            Person editedPerson = addPersonCommand.getToAdd();
            int index = this.model.getFilteredPersonList().size();
            this.ui.updateDetailPanel(editedPerson, index);
        }

        if (command instanceof EditInsuranceCommand) {
            EditInsuranceCommand editInsuranceCommand = (EditInsuranceCommand) command;
            Insurance editedInsurance = editInsuranceCommand.getEditedInsurance();
            int index = editInsuranceCommand.getEditedInsuranceIndex();
            this.ui.updateDetailPanel(editedInsurance, index);

            ObservableList<Record> records = this.model.getFilteredRecordList();
            for (int i = 0; i < records.size(); i++) {
                Record record = records.get(i);
                if (record.getInsuranceID().toString()
                        .equals(editInsuranceCommand.getInsuranceToEdit().getTitle().toString())) {
                    int recordIndex = i + 1;
                    String text = "edit -r " + recordIndex + " i/" + index;
                    Command updateCommand = addressBookParser.parseCommand(model, text);
                    updateCommand.execute(model);
                }
            }
        }

        if (command instanceof EditRecordCommand) {
            EditRecordCommand editRecordCommand = (EditRecordCommand) command;
            Record editedRecord = editRecordCommand.getEditedRecord();
            int index = editRecordCommand.getEditedRecordIndex();
            this.ui.updateDetailPanel(editedRecord, index);
        }

        if (command instanceof EditPersonCommand) {
            EditPersonCommand editPersonCommand = (EditPersonCommand) command;
            Person editedPerson = editPersonCommand.getEditedPerson();
            int index = editPersonCommand.getEditedPersonIndex();

            ObservableList<Record> records = this.model.getFilteredRecordList();
            for (int i = 0; i < records.size(); i++) {
                Record record = records.get(i);
                if (record.getClientID().toString()
                        .equals(editPersonCommand.getPersonToEdit().getName().toString())) {
                    int recordIndex = i + 1;
                    String text = "edit -r " + recordIndex + " c/" + index;
                    Command updateCommand = addressBookParser.parseCommand(model, text);
                    updateCommand.execute(model);
                }
            }
            this.ui.updateDetailPanel(editedPerson, index);
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
    public ObservableList<Insurance> getFilteredInsuranceList() {
        return model.getFilteredInsuranceList();
    }

    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return model.getFilteredAppointmentList();
    }

    @Override
    public ObservableList<Record> getFilteredRecordList() {
        return model.getFilteredRecordList();
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
    public ObservableList<Appointment> getFilteredAppointmentHistoryList() {
        return model.getFilteredAppointmentHistoryList();
    }

    @Override
    public ObservableList<Record> getFilteredExpiredRecordList() {
        return model.getFilteredExpiredRecordList();
    }
}
