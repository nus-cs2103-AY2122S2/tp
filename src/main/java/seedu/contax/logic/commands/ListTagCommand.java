package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.contax.commons.core.GuiListContentType;
import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.model.Model;

/**
 * Lists all tags in the address book to the user.
 */
public class ListTagCommand extends Command {
    public static final String COMMAND_WORD = "listtags";

    public static final String MESSAGE_SUCCESS = "Listed all tags";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTagList(Model.PREDICATE_SHOW_ALL_TAGS);
        return new CommandResult(MESSAGE_SUCCESS, GuiListContentType.TAG);
    }
}
