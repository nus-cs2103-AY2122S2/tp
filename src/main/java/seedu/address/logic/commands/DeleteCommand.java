package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    public static final int DELETE_PLAYER_FROM_LINEUP = 0;
    public static final int DELETE_PLAYER_FROM_TEAM = 1;
    public static final int DELETE_PLAYER_FROM_POOL = 2;
    public static final int DELETE_LINEUP_FROM_TEAM = 3;
    public static final int DELETE_TEAM_FROM_POOL = 4;

    private final int type;
    private final String player;
    private final String lineup;
    private final String team;

    /**
     * COnstructs a new delete command.
     */
    public DeleteCommand(int type, String... models) {
        this.type = type;
        switch (type) {
        case DELETE_PLAYER_FROM_LINEUP:
            this.player = models[0];
            this.lineup = models[1];
            this.team = models[2];
            break;
        case DELETE_PLAYER_FROM_TEAM:
            this.player = models[0];
            this.lineup = null;
            this.team = models[1];
            break;
        case DELETE_PLAYER_FROM_POOL:
            this.player = models[0];
            this.lineup = null;
            this.team = null;
            break;
        case DELETE_LINEUP_FROM_TEAM:
            this.player = null;
            this.lineup = models[0];
            this.team = models[1];
            break;
        case DELETE_TEAM_FROM_POOL:
            this.player = null;
            this.lineup = null;
            this.team = models[0];
            break;
        default:
            this.player = null;
            this.lineup = null;
            this.team = null;
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        switch (type) {
        case DELETE_PLAYER_FROM_LINEUP:
            model.getMyGm().deletePlayerFromLineup(player, lineup, team);
            return new CommandResult("");
        case DELETE_PLAYER_FROM_TEAM:
            model.getMyGm().deletePlayerFromTeam(player, team);
            return new CommandResult("");
        case DELETE_PLAYER_FROM_POOL:
            model.getMyGm().deletePlayerFromPool(player);
            return new CommandResult("");
        case DELETE_LINEUP_FROM_TEAM:
            model.getMyGm().deleteLineupFromTeam(lineup, team);
            return new CommandResult("");
        case DELETE_TEAM_FROM_POOL:
            model.getMyGm().deletePlayerFromTeam(player, team);
            return new CommandResult("");
        default:
            return new CommandResult("", true, false);
        }
    }
}
