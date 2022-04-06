package seedu.address.logic.commands.testresult;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICALTEST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESULT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TESTDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Nric;
import seedu.address.model.testresult.TestResult;
import seedu.address.model.testresult.TestResultWithNricPredicate;

public class AddTestResultCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final CommandType COMMAND_TYPE = CommandType.TEST;

    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + PREFIX_TYPE + "test"
            + ": Adds the results of a test taken for a patient in the MedBook. \n"
            + "Parameters: "
            + PREFIX_NRIC + "NRIC "
            + PREFIX_TESTDATE + "TEST_DATE "
            + PREFIX_MEDICALTEST + "MEDICAL_TEST "
            + PREFIX_RESULT + "TEST_RESULT \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TYPE + "test "
            + PREFIX_NRIC + "S1234567L "
            + PREFIX_TESTDATE + "2022-03-16 "
            + PREFIX_MEDICALTEST + "CT Scan "
            + PREFIX_RESULT + "Brain damage ";

    public static final String MESSAGE_SUCCESS = "New test result added: %1$s";
    public static final String MESSAGE_DUPLICATE_TESTRESULT =
            "This test result already exists in this patient's test results list";
    public static final String MESSAGE_MISSING_PATIENT = "This patient does not exists in MedBook";

    // Identifier
    private final Nric patientNric;

    private final TestResult toAdd;

    /**
     * Creates an AddTestResultCommand to add the specified {@code TestResult}
     */
    public AddTestResultCommand(Nric patientNric, TestResult testResult) {
        requireNonNull(patientNric);
        requireNonNull(testResult);
        toAdd = testResult;
        this.patientNric = patientNric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasNric(patientNric)) {
            throw new CommandException(MESSAGE_MISSING_PATIENT);
        }

        if (model.hasTestResult(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TESTRESULT);
        }

        model.addTestResult(toAdd);
        model.updateFilteredTestResultList(new TestResultWithNricPredicate(patientNric));

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), COMMAND_TYPE);
    }
}
