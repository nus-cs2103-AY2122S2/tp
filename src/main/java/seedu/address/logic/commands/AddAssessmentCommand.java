package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.CommandResult.createAssessmentCommandResult;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FULLMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHTAGE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assessment.Assessment;

public class AddAssessmentCommand extends Command {

    public static final String COMMAND_WORD = "add_assessment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an assessment to the addressbook. \n"
            + "Parameters: "
            + PREFIX_ASSESSMENTNAME + "ASSESSMENT NAME "
            + PREFIX_WEIGHTAGE + "WEIGHTAGE(0-100) "
            + PREFIX_FULLMARK + "FULL MARK\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ASSESSMENTNAME + "assignment1 "
            + PREFIX_WEIGHTAGE + "50 "
            + PREFIX_FULLMARK + "10\n";

    public static final String MESSAGE_SUCCESS = "New assessment added: %1$s";
    public static final String MESSAGE_DUPLICATE_ASSESSMENT = "This assessment already exists in the address book";

    private final Assessment toAdd;

    /**
     * Creates an AddAssessmentCommand to add the specified {@code Assessment}.
     */
    public AddAssessmentCommand(Assessment toAdd) {
        requireNonNull(toAdd);
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasAssessment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ASSESSMENT);
        }

        model.addAssessment(toAdd);
        return createAssessmentCommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof AddAssessmentCommand
                && toAdd.equals(((AddAssessmentCommand) other).toAdd));
    }
}
