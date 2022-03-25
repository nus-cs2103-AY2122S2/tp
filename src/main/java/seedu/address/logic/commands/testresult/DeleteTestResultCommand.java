package seedu.address.logic.commands.testresult;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.testresult.TestResult;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteTestResultCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final CommandType COMMAND_TYPE = CommandType.TEST;

    public static final String MESSAGE_USAGE = COMMAND_WORD + " INDEX"
            + ": Deletes the test result identified by the index number used in the displayed test result list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TEST_RESULT_SUCCESS = "Deleted Test Result: %1$s";

    private final Index targetIndex;

    public DeleteTestResultCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<TestResult> lastShownList = model.getFilteredTestResultList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEST_RESULT_INDEX);
        }

        TestResult testResultToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTestResult(testResultToDelete);
        if (lastShownList.isEmpty()) {
            return new CommandResult(String.format(MESSAGE_DELETE_TEST_RESULT_SUCCESS, testResultToDelete),
                    CommandType.DEFAULT);
        }
        return new CommandResult(String.format(MESSAGE_DELETE_TEST_RESULT_SUCCESS, testResultToDelete),
                COMMAND_TYPE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTestResultCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTestResultCommand) other).targetIndex)); // state check
    }
}
