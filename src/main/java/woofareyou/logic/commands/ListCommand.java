package woofareyou.logic.commands;

import static java.util.Objects.requireNonNull;

import woofareyou.model.Model;

/**
 * Lists all pets in WoofAreYou to the pet owner.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all pets!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPetListToFullPetList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
