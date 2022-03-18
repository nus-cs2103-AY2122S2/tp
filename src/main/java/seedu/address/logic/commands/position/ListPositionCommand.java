package seedu.address.logic.commands.position;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_POSITIONS;

import seedu.address.commons.core.DataType;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
import seedu.address.model.Model;

/**
 * Lists all positions in the address book to the user.
 */
public class ListPositionCommand extends ListCommand {
    public static final String MESSAGE_SUCCESS = "Listed all Positions";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPositionList(PREDICATE_SHOW_ALL_POSITIONS);
        return new CommandResult(MESSAGE_SUCCESS, getCommandDataType());
    }

    @Override
    public DataType getCommandDataType() {
        return DataType.POSITION;
    }
}
