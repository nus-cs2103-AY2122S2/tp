package woofareyou.logic.commands;

import static java.util.Objects.requireNonNull;

import woofareyou.model.Model;
import woofareyou.model.PetBook;

/**
 * Clears WoofAreYou.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "WoofAreYou has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setPetBook(new PetBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
