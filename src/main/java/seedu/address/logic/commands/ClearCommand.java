package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.InternApplyMemory;
import seedu.address.model.Model;

/**
 * Clears InternApply.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "InternApply has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setInternApplyMemory(new InternApplyMemory());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
