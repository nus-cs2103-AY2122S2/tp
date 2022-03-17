package seedu.address.logic.commands.testresult;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.contact.AddContactCommand.MESSAGE_MISSING_PATIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.NricPredicate;
import seedu.address.model.testresult.TestResultWithNricPredicate;

/**
 * Lists all test results in the address book to the user.
 */
public class ViewTestResultCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final CommandType COMMAND_TYPE = CommandType.TEST;

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_TYPE + "test " + PREFIX_NRIC + "PATIENT_NRIC"
            + ": Lists all test results whose tests contain any of "
            + "the specified patient NRIC and displays them as a list with index numbers.\n"
            + "Parameters: PATIENT NRIC\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TYPE + "test " + PREFIX_NRIC + "S1234567L";

    private final Nric patientNric;

    /**
     * Creates an ViewTestResultCommand to view the specified {@code patientNric}
     */
    public ViewTestResultCommand(Nric patientNric) {
        requireNonNull(patientNric);
        this.patientNric = patientNric;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTestResultList(new TestResultWithNricPredicate(patientNric));

        if (!model.hasPerson(new NricPredicate(patientNric))) {
            throw new CommandException(MESSAGE_MISSING_PATIENT);
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_TEST_RESULTS_LISTED_OVERVIEW,
                        model.getFilteredTestResultList().size(), patientNric),
                COMMAND_TYPE);
    }
}

