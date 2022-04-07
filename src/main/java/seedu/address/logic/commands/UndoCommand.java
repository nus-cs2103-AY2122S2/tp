package seedu.address.logic.commands;

//@@author LapisRaider
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

/**
 * Undo previous modification made to the addressBook personList data.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_UNDO_ACKNOWLEDGEMENT = "Undid previous modification.";
    public static final String REACHED_UNDO_LIMIT = "Cannot undo. Undo limit reached.";

    @Override
    public CommandResult execute(Model model) {
        //show full list when undoing
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(MESSAGE_UNDO_ACKNOWLEDGEMENT, false, false, true);
    }
}
