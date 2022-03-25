package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

/**
 * Undo previous modification made to the addressBook personList data.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_UNDO_ACKNOWLEDGEMENT = "Successfully undo previous modification made";
    public static final String REACHED_UNDO_LIMIT = "You have reached the undo limit. Cannot undo.";

    @Override
    public CommandResult execute(Model model) {
        //show full list when undoing
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(MESSAGE_UNDO_ACKNOWLEDGEMENT, false, false, true);
    }
}
