package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINEUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAYER;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Represents a put command which puts a player into a lineup.
 *
 * @author snoidetx
 */
public class PutCommand extends Command {

    public static final String COMMAND_WORD = "put";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Puts the specified player into a specified lineup.\n"
            + "Parameters: " + PREFIX_PLAYER + "NAME"
            + PREFIX_LINEUP + "LINEUP NAME" + "\n"
            + "Example: put P/Alex Chua L/starting five";

    public static final String MESSAGE_NO_SUCH_PERSON = "Player does not exist.";
    public static final String MESSAGE_NO_SUCH_LINEUP = "Lineup does not exist.";
    public static final String MESSAGE_LINEUP_FULL = "Lineup is already full (max 5).";
    public static final String MESSAGE_PUT_PERSON_SUCCESS = "Successfully put %s into %s";

    private final Name playerName;
    private final LineupName lineupName;

    /**
     * Constructs a new put command.
     */
    public PutCommand(Name playerName, LineupName lineupName) {
        this.playerName = playerName;
        this.lineupName = lineupName;
    }

    /**
     * Executes the PutCommand and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPersonName(this.playerName)) {
            throw new CommandException(MESSAGE_NO_SUCH_PERSON);
        } else if (!model.hasLineupName(this.lineupName)) {
            throw new CommandException(MESSAGE_NO_SUCH_LINEUP);
        } else if (model.getLineup(this.lineupName).reachMaximumCapacity()) {
            throw new CommandException(MESSAGE_LINEUP_FULL);
        } else {
            Person player = model.getPerson(this.playerName);
            Lineup lineup = model.getLineup(this.lineupName);
            model.putPersonIntoLineup(player, lineup);
            return new CommandResult(String.format(MESSAGE_PUT_PERSON_SUCCESS, this.playerName, this.lineupName));
        }
    }
}
