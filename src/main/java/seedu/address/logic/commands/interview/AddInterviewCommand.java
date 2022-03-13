package seedu.address.logic.commands.interview;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.add.AddCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;

public class AddInterviewCommand extends AddCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + " -i: Adds a interview to the Hirelah application. "
            + "Parameters: "
            + PREFIX_INDEX + "APPLICANT_INDEX "
            + PREFIX_DATE + "DATE "
            + PREFIX_POSITION + "POSITION ";

    public static final String MESSAGE_SUCCESS = "New interview added: %1$s";
    public static final String MESSAGE_DUPLICATE_INTERVIEW = "This interview already exists in the address book";

    private final Interview toAdd;

    /**
     * Creates an AddApplicantCommand to add the specified {@code Interview}
     */
    public AddInterviewCommand(Interview interview) {
        requireNonNull(interview);
        toAdd = interview;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // TODO: Check duplicated persons
        // if (model.hasPerson(toAdd)) {
        //   throw new CommandException(MESSAGE_DUPLICATE_INTERVIEW);
        // }
        // model.addInterview(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddInterviewCommand // instanceof handles nulls
                && toAdd.equals(((AddInterviewCommand) other).toAdd));
    }
}
