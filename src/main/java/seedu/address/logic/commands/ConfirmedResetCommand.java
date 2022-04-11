package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.util.SampleDataUtil;

/**
 * Resets the recipe book after user confirms or the user specified a forced Reset.
 */
public class ConfirmedResetCommand extends ResetCommand {
    public static final String COMMAND_WORD = "yes";
    public static final String COMMAND_NAME = "Confirmed reset";
    public static final String MESSAGE_SUCCESS = "Recipe book has been reset!";
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command! Type in reset again if you wish to reset.\n";


    @Override
    public boolean equals(Object o) {
        // instanceof handles nulls
        if (!(o instanceof ConfirmedResetCommand)) {
            return false;
        }

        // short circuit if same object
        if (this == o) {
            return true;
        }

        ConfirmedResetCommand other = (ConfirmedResetCommand) o;
        return this.toString().equals(other.toString()); // state check
    }

    @Override
    public String toString() {
        return COMMAND_NAME;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setRecipeBook(SampleDataUtil.getSampleRecipeBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
