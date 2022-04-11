package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_FAVOURITES;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModelMemento;

/**
 * Lists all persons in the address book to the user.
 */
public class ListFavouritesCommand extends Command {

    public static final String COMMAND_WORD = "list-fav";

    public static final String MESSAGE_SUCCESS = "Listed all favourites";
    private ModelMemento modelMemento;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        this.modelMemento = new ModelMemento();
        modelMemento.setModel(new ModelManager(model.makeCopy()));
        modelMemento.setPredicate(model.getModelPredicate());

        model.updateFilteredPersonList(PREDICATE_SHOW_FAVOURITES);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public CommandResult unExecute(Model model) throws CommandException {
        model.setAddressBook(this.modelMemento.getModel().getAddressBook());
        model.updateFilteredPersonList(modelMemento.getPredicate());
        return new CommandResult("List all favourite contacts", false, false);
    }
}
