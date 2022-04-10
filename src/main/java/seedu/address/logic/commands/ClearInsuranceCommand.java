package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.InsuranceBook;
import seedu.address.model.Model;

/**
 * Clears the insurance list.
 */
public class ClearInsuranceCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Insurance book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setInsuranceBook(new InsuranceBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
