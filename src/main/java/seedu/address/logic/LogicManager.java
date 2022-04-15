package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.MedBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyMedBook;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.contact.Contact;
import seedu.address.model.medical.Medical;
import seedu.address.model.patient.Patient;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.testresult.TestResult;
import seedu.address.storage.Storage;


/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final MedBookParser medBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        medBookParser = new MedBookParser();

    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = medBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveMedBook(model.getMedBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyMedBook getAddressBook() {
        return model.getMedBook();
    }

    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return model.getFilteredPatientList();
    }

    @Override
    public ObservableList<Contact> getFilteredContactList() {
        return model.getFilteredContactList();
    }

    @Override
    public ObservableList<Consultation> getFilteredConsultationList() {
        return model.getFilteredConsultationList();
    }

    @Override
    public ObservableList<Medical> getFilteredMedicalList() {
        return model.getFilteredMedicalList();
    }

    @Override
    public ObservableList<Prescription> getFilteredPrescriptionList() {
        return model.getFilteredPrescriptionList();
    }

    @Override
    public ObservableList<TestResult> getFilteredTestResultList() {
        return model.getFilteredTestResultList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getMedBookFilePath();
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
