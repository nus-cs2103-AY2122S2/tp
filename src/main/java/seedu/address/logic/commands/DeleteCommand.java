package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINEUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAYER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.Schedule;


/**
 * Represents a delete command which deletes an entity from MyGM.
 *
 * @author snoidetx
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE_PLAYER = COMMAND_WORD
            + ": Deletes a player from MyGM or lineup if lineup name is specified."
            + "\nParameters: "
            + PREFIX_PLAYER + "NAME "
            + "[" + PREFIX_LINEUP + "LINEUP NAME]\n"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_PLAYER + "John Doe ";

    public static final String MESSAGE_USAGE_LINEUP = COMMAND_WORD
            + ": Deletes a lineup from MyGM."
            + "\nParameters: "
            + PREFIX_LINEUP + "LINEUP NAME \n"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_LINEUP + "Starting 5";

    public static final String MESSAGE_USAGE_SCHEDULE = COMMAND_WORD
            + ": Deletes a schedule from MyGM."
            + "\nParameters: "
            + PREFIX_SCHEDULE + "INDEX \n"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_SCHEDULE + "1";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a player, lineup or schedule from MyGM\n"
            + MESSAGE_USAGE_PLAYER + "\n"
            + MESSAGE_USAGE_LINEUP + "\n"
            + MESSAGE_USAGE_SCHEDULE;

    public static final String MESSAGE_NO_SUCH_LINEUP = "Lineup does not exist.";
    public static final String MESSAGE_NO_SUCH_PERSON = "Player does not exist.";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %s";
    public static final String MESSAGE_PERSON_NOT_IN_LINEUP = "Player is not inside the lineup";
    public static final String MESSAGE_DELETE_PERSON_FROM_LINEUP_SUCCESS = "Person deleted from lineup %s: %s";
    public static final String MESSAGE_DELETE_LINEUP_SUCCESS = "Deleted Lineup: %s";
    public static final String MESSAGE_DELETE_SCHEDULE_SUCCESS = "Deleted Schedule: %s";
    public static final String MESSAGE_DELETE_FAILURE = "Delete cannot be executed.";

    private enum DeleteCommandType {
        PLAYER, LINEUP, PLAYER_LINEUP, SCHEDULE
    }

    private final DeleteCommandType type;
    private final Name player;
    private final seedu.address.model.lineup.LineupName lineup;
    private final Index targetIndex;

    /**
     * Constructs a new delete command.
     */
    public DeleteCommand(Name player) {
        this.type = DeleteCommandType.PLAYER;
        this.player = player;
        this.lineup = null;
        this.targetIndex = null;
    }

    /**
     * Overloaded constructor for delete command.
     */
    public DeleteCommand(Name player, seedu.address.model.lineup.LineupName lineup) {
        this.type = DeleteCommandType.PLAYER_LINEUP;
        this.player = player;
        this.lineup = lineup;
        this.targetIndex = null;
    }

    /**
     * Overloaded constructor for delete command.
     */
    public DeleteCommand(seedu.address.model.lineup.LineupName lineup) {
        this.type = DeleteCommandType.LINEUP;
        this.player = null;
        this.lineup = lineup;
        this.targetIndex = null;
    }

    /**
     * Overloaded constructor for delete command.
     */
    public DeleteCommand(Index targetIndex) {
        this.type = DeleteCommandType.SCHEDULE;
        this.player = null;
        this.lineup = null;
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the DeleteCommand and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
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
            }
        case PLAYER_LINEUP:
            if (!model.hasPersonName(this.player)) {
                throw new CommandException(MESSAGE_NO_SUCH_PERSON);
            }
            if (!model.hasLineupName(this.lineup)) {
                throw new CommandException(MESSAGE_NO_SUCH_LINEUP);
            }
            Person person = model.getPerson(this.player);
            Lineup lineup = model.getLineup(this.lineup);
            if (!model.isPersonInLineup(person, lineup)) {
                throw new CommandException(MESSAGE_PERSON_NOT_IN_LINEUP);
            }

            model.deletePersonFromLineup(person, lineup);
            return new CommandResult(String
                    .format(MESSAGE_DELETE_PERSON_FROM_LINEUP_SUCCESS, this.lineup, this.player));
            // to be added
        case LINEUP:
            if (!model.hasLineupName(this.lineup)) {
                throw new CommandException(MESSAGE_NO_SUCH_LINEUP);
            }
            Lineup toDelete = model.getLineup(this.lineup);
            model.deleteLineup(toDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_LINEUP_SUCCESS, this.lineup));
            // to be added
        case SCHEDULE:
            List<Schedule> lastShownList = model.getFilteredScheduleList();

            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
            }

            Schedule personToDelete = lastShownList.get(targetIndex.getZeroBased());
            model.deleteSchedule(personToDelete);
            model.updateFilteredScheduleList(Model.PREDICATE_SHOW_ACTIVE_SCHEDULES);
            return new CommandResult(String.format(MESSAGE_DELETE_SCHEDULE_SUCCESS, personToDelete));
        default:
            throw new CommandException(MESSAGE_DELETE_FAILURE);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (player != null) {
            return other == this // short circuit if same object
                    || (other instanceof DeleteCommand // instanceof handles nulls
                    && player.equals(((DeleteCommand) other).player));
        }
        if (lineup != null) {
            return other == this // short circuit if same object
                    || (other instanceof DeleteCommand // instanceof handles nulls
                    && lineup.equals(((DeleteCommand) other).lineup));
        }
        if (targetIndex != null) {
            return other == this // short circuit if same object
                    || (other instanceof DeleteCommand // instanceof handles nulls
                    && targetIndex.equals(((DeleteCommand) other).targetIndex));
        }
        return false;
    }
}
