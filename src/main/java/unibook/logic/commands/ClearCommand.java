package unibook.logic.commands;

import static java.util.Objects.requireNonNull;

import unibook.model.Model;
import unibook.model.UniBook;

/**
 * Clears the unibook.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "UniBook has been cleared!";


    @Override
    public CommandResult execute(Model model, Boolean isPersonListShowing,
                                 Boolean isModuleListShowing, Boolean isGroupListShowing) {
        requireNonNull(model);
        model.setUniBook(new UniBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
