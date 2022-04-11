package seedu.address.logic.commands.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEAPPLIED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;

public class AddApplicant extends Command {

    public static final String COMMAND_WORD = "addapplicant";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an Applicant to ReCLIne. "
            + "Parameters: "
            + "*" + PREFIX_NAME + "[NAME] "
            + "*" + PREFIX_NRIC + "[NRIC] "
            + "*" + PREFIX_PHONE + "[PHONE] "
            + "*" + PREFIX_EMAIL + "[EMAIL] "
            + "*" + PREFIX_ADDRESS + "[ADDRESS] "
            + "*" + PREFIX_DATEAPPLIED + "[DATE APPLIED] "
            + PREFIX_TAG + "[TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_NRIC + "S1234567D "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_DATEAPPLIED + "2022-03-21 "
            + PREFIX_TAG + "applicant "
            + PREFIX_TAG + "Competent in Java";

    public static final String MESSAGE_SUCCESS = "New applicant added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPLICANT = "This applicant already exists in the address book. "
            + "Applicants are considered duplicates if they have the same NRIC, email or phone number.";
    public static final String MESSAGE_INVALID_FIELDS = "Your command has an invalid field that should be added by"
            + " editapplicant\n (InterviewDate, i/, or Qualification, q/, or JobId, j/).";

    private final Applicant toAdd;

    /**
     * Constructs an AddApplicant to add the specified {@code Applicant}
     */
    public AddApplicant(Applicant applicant) {
        requireNonNull(applicant);
        toAdd = applicant;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasApplicant (toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICANT);
        }

        model.addApplicant(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), true, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddApplicant // instanceof handles nulls
                && toAdd.equals(((AddApplicant) other).toAdd));
    }

}
