package woofareyou.logic.commands;

import static java.util.Objects.requireNonNull;

import woofareyou.model.PetBook;
import woofareyou.model.Model;

/**
 * Clears WoofAreYou.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new PetBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
