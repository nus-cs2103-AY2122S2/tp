package seedu.address.logic.commands.testresult;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICALTEST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESULT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TESTDATE;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Nric;
import seedu.address.model.testresult.MedicalTest;
import seedu.address.model.testresult.Result;
import seedu.address.model.testresult.TestDate;
import seedu.address.model.testresult.TestResult;

/**
 * Edits the details of an existing test result information in MedBook.
 */
public class EditTestResultCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final CommandType COMMAND_TYPE = CommandType.TEST;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the details of the test result identified "
            + "by the index number used in the displayed test result information list. "
            + "Existing values will be overwritten by the input values.\n"
            + "NRIC FIELD CANNOT BE MODIFIED - CREATE A NEW TEST RESULT WITH THE CORRECT NRIC INSTEAD.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TESTDATE + "TEST_DATE] "
            + "[" + PREFIX_MEDICALTEST + "MEDICAL_TEST] "
            + "[" + PREFIX_RESULT + "TEST_RESULT] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_RESULT + "Brain damage";

    public static final String MESSAGE_EDIT_TEST_RESULT_SUCCESS = "Edited Test Result Information: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TEST_RESULT = "This test result already exists in MedBook.";
    public static final String MESSAGE_NRIC_EDIT_NOT_ALLOWED =
            "NRIC field cannot be modified. Create a new test result with the correct NRIC instead.";

    private final Index targetIndex;
    private final EditTestResultDescriptor editTestResultDescriptor;

    /**
     * @param targetIndex of the test result information in the filtered test result information list to edit
     * @param editTestResultDescriptor details to edit the test result information with
     */
    public EditTestResultCommand(Index targetIndex, EditTestResultDescriptor editTestResultDescriptor) {
        this.targetIndex = targetIndex;
        this.editTestResultDescriptor = new EditTestResultDescriptor(editTestResultDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<TestResult> lastShownList = model.getFilteredTestResultList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEST_RESULT_INDEX);
        }

        TestResult testResultToEdit = lastShownList.get(targetIndex.getZeroBased());
        TestResult editedTestResult = createEditedTestResult(testResultToEdit, editTestResultDescriptor);

        if (!editedTestResult.isSameTestResult(editedTestResult) && model.hasTestResult(editedTestResult)) {
            throw new CommandException(MESSAGE_DUPLICATE_TEST_RESULT);
        }

        model.setTestResult(testResultToEdit, editedTestResult);

        return new CommandResult(String.format(MESSAGE_EDIT_TEST_RESULT_SUCCESS, editedTestResult), COMMAND_TYPE);
    }

    private static TestResult createEditedTestResult(TestResult testResult,
                                               EditTestResultDescriptor editTestResultDescriptor) {
        assert testResult != null;

        Nric updatedNric = testResult.getPatientNric();
        TestDate updatedTestDate = editTestResultDescriptor.getTestDate().orElse(testResult.getTestDate());
        MedicalTest updatedMedicalTest =
                editTestResultDescriptor.getMedicalTest().orElse(testResult.getMedicalTest());
        Result updatedResult =
                editTestResultDescriptor.getResult().orElse(testResult.getResult());

        return new TestResult(updatedNric,
                updatedTestDate,
                updatedMedicalTest,
                updatedResult
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditTestResultCommand // instanceof handles nulls
                && targetIndex.equals(((EditTestResultCommand) other).targetIndex)); // state check
    }

    /**
     * Stores the details to edit the test result information with. Each non-empty field value will replace the
     * corresponding field value of the test result information.
     */
    public static class EditTestResultDescriptor {
        private TestDate testDate;
        private MedicalTest medicalTest;
        private Result result;

        public EditTestResultDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTestResultDescriptor(EditTestResultDescriptor toCopy) {
            setTestDate(toCopy.testDate);
            setMedicalTest(toCopy.medicalTest);
            setResult(toCopy.result);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(testDate, medicalTest, result);
        }

        public Optional<TestDate> getTestDate() {
            return Optional.ofNullable(testDate);
        }

        public void setTestDate(TestDate testDate) {
            this.testDate = testDate;
        }

        public Optional<MedicalTest> getMedicalTest() {
            return Optional.ofNullable(medicalTest);
        }

        public void setMedicalTest(MedicalTest medicalTest) {
            this.medicalTest = medicalTest;
        }

        public Optional<Result> getResult() {
            return Optional.ofNullable(result);
        }

        public void setResult(Result result) {
            this.result = result;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTestResultCommand.EditTestResultDescriptor)) {
                return false;
            }

            // state check
            EditTestResultDescriptor e = (EditTestResultDescriptor) other;

            return getTestDate().equals(e.getTestDate())
                    && getMedicalTest().equals(e.getMedicalTest())
                    && getResult().equals(e.getResult());
        }
    }
}
