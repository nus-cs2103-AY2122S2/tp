package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.parser.DeleteCommandParser;
import seedu.address.logic.parser.EditCommandParser;
import seedu.address.logic.parser.consultations.AddConsultationCommandParser;
import seedu.address.logic.parser.consultations.DeleteConsultationCommandParser;
import seedu.address.logic.parser.consultations.ViewConsultationCommandParser;
import seedu.address.logic.parser.contact.AddContactCommandParser;
import seedu.address.logic.parser.contact.DeleteContactCommandParser;
import seedu.address.logic.parser.contact.ViewContactCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.medical.AddMedicalCommandParser;
import seedu.address.logic.parser.medical.DeleteMedicalCommandParser;
import seedu.address.logic.parser.medical.EditMedicalCommandParser;
import seedu.address.logic.parser.medical.ViewMedicalCommandParser;
import seedu.address.logic.parser.prescription.AddPrescriptionCommandParser;
import seedu.address.logic.parser.prescription.DeletePrescriptionCommandParser;
import seedu.address.logic.parser.prescription.ViewPrescriptionCommandParser;
import seedu.address.logic.parser.testresult.AddTestResultCommandParser;
import seedu.address.logic.parser.testresult.DeleteTestResultCommandParser;
import seedu.address.logic.parser.testresult.EditTestResultCommandParser;
import seedu.address.logic.parser.testresult.ViewTestResultCommandParser;


public enum CommandType {
    DEFAULT, CONTACT, MEDICAL, CONSULTATION, PRESCRIPTION, TEST;

    public static final String MESSAGE_CONSTRAINTS = "Command type should be either contact, medical, "
            + "consultation, prescription or test";
    private static CommandType viewCommandType = DEFAULT;
    private static String getFirstPrefixType(String text) {
        int index = text.indexOf(' ');
        if (index > -1) { // Check if there is more than one word.
            return text.substring(0, index).trim(); // Extract first word.
        } else {
            return text; // Text is the first word itself.
        }
    }
    public static void setViewCommandType(CommandType commandType) {
        viewCommandType = commandType;
    }
    /**
     * Parses {@code String commandType} into a {@code CommandType}.
     */
    public static CommandType parseCommandType(String commandType) throws ParseException {
        requireNonNull(commandType);
        String trimmedCommandType = getFirstPrefixType(commandType.trim());

        switch (trimmedCommandType) {
        case "contact":
            return CONTACT;
        case "medical":
            return MEDICAL;
        case "consultation":
            return CONSULTATION;
        case "prescription":
            return PRESCRIPTION;
        case "test":
            return TEST;
        default:
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Returns command related to adding information to patients in Medbook.
     *
     * @param commandType user input command type
     * @param arguments   user input arguments
     * @return the command based on the user input
     */
    public static Command parseAddCommandType(String commandType, String arguments) throws ParseException {
        requireNonNull(commandType);
        CommandType parsedCommandType = parseCommandType(commandType);

        switch(parsedCommandType) {
        case CONTACT:
            viewCommandType = CONTACT;
            return new AddContactCommandParser().parse(arguments);
        case MEDICAL:
            viewCommandType = MEDICAL;
            return new AddMedicalCommandParser().parse(arguments);
        case CONSULTATION:
            viewCommandType = CONSULTATION;
            return new AddConsultationCommandParser().parse(arguments);
        case PRESCRIPTION:
            viewCommandType = PRESCRIPTION;
            return new AddPrescriptionCommandParser().parse(arguments);
        case TEST:
            viewCommandType = TEST;
            return new AddTestResultCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Returns command related to viewing information to patients in Medbook.
     *
     * @param commandType user input command type
     * @param arguments user input arguments
     * @return the command based on the user input
     */
    public static Command parseViewCommandType(String commandType, String arguments) throws ParseException {
        requireNonNull(commandType);
        CommandType parsedCommandType = parseCommandType(commandType);

        switch(parsedCommandType) {
        case CONTACT:
            viewCommandType = CONTACT;
            return new ViewContactCommandParser().parse(arguments);
        case MEDICAL:
            viewCommandType = MEDICAL;
            return new ViewMedicalCommandParser().parse(arguments);
        case CONSULTATION:
            viewCommandType = CONSULTATION;
            return new ViewConsultationCommandParser().parse(arguments);
        case PRESCRIPTION:
            viewCommandType = PRESCRIPTION;
            return new ViewPrescriptionCommandParser().parse(arguments);
        case TEST:
            viewCommandType = TEST;
            return new ViewTestResultCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Returns command related to editing information in MedBook.
     *
     * @param arguments user input arguments
     * @return the command based on the user input
     */
    public static Command parseEditCommandType(String arguments) throws ParseException {
        requireNonNull(arguments);
        switch (viewCommandType) {
        case CONTACT:
            throw new ParseException("To be implemented");
        case MEDICAL:
            return new EditMedicalCommandParser().parse(arguments);
        case CONSULTATION:
            throw new ParseException("To be implemented");
        case PRESCRIPTION:
            throw new ParseException("To be implemented");
        case TEST:
            return new EditTestResultCommandParser().parse(arguments);
        default:
            return new EditCommandParser().parse(arguments);
        }
    }

    /**
     * Returns command related to deleting information to patients in MedBook.
     *
     * @param arguments user input arguments
     * @return the command based on the user input
     */
    public static Command parseDeleteCommandType(String arguments) throws ParseException {
        requireNonNull(arguments);
        switch (viewCommandType) {
        case CONTACT:
            return new DeleteContactCommandParser().parse(arguments);
        case MEDICAL:
            return new DeleteMedicalCommandParser().parse(arguments);
        case CONSULTATION:
            return new DeleteConsultationCommandParser().parse(arguments);
        case PRESCRIPTION:
            return new DeletePrescriptionCommandParser().parse(arguments);
        case TEST:
            return new DeleteTestResultCommandParser().parse(arguments);
        default:
            return new DeleteCommandParser().parse(arguments);
        }
    }
}
