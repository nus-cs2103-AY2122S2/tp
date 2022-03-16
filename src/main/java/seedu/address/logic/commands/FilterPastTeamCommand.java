package seedu.address.logic.commands;

import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TEAMMATES;

public class FilterPastTeamCommand extends Command{

    public static final String COMMAND_WORD = "filterteam";

    public static final String MESSAGE_SUCCESS = "Listed all past teammates";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateDisplayPersonList(PREDICATE_SHOW_ALL_TEAMMATES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
