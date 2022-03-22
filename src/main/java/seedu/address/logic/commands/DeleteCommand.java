package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Represents a delete command which deletes an entity from MyGM.
 *
 * @author snoidetx
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_NO_SUCH_PERSON = "Player does not exist.";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %s$s";

    private enum DeleteCommandType {
        PLAYER, LINEUP, PLAYER_LINEUP
    }

    private final DeleteCommandType type;
    private final Name player;
    private final LineupName lineup;

    /**
     * Constructs a new delete command.
     */
    public DeleteCommand(Name player) {
        this.type = DeleteCommandType.PLAYER;
        this.player = player;
        this.lineup = null;
    }

    /**
     * Overloaded constructor for delete command.
     */
    public DeleteCommand(Name player, LineupName lineup) {
        this.type = DeleteCommandType.PLAYER_LINEUP;
        this.player = player;
        this.lineup = lineup;
    }

    /**
     * Overloaded constructor for delete command.
     */
    public DeleteCommand(LineupName lineup) {
        this.type = DeleteCommandType.LINEUP;
        this.player = null;
        this.lineup = lineup;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        switch (this.type) {
        case PLAYER:
            if (!model.hasPersonName(this.player)) {
                throw new CommandException(MESSAGE_NO_SUCH_PERSON);
            } else {
                Person toDelete = model.getPerson(this.player);
                model.deletePerson(toDelete);
                return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, this.player));
                // do I also need to delete person from lineup?
            }
        case PLAYER_LINEUP:
            // to be added
        case LINEUP:
            // to be added
        default:
        }

        return null; // temporarily
    }
}
