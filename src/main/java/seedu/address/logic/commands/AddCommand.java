package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JERSEY_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINEUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAYER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.Schedule;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a player to MyGM. "
            + "\nParameters: "
            + PREFIX_PLAYER + " "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_HEIGHT + "HEIGHT "
            + PREFIX_JERSEY_NUMBER + "JERSEYNUMBER "
            + PREFIX_WEIGHT + "WEIGHT "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PLAYER + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_HEIGHT + "180 "
            + PREFIX_JERSEY_NUMBER + "23 "
            + PREFIX_WEIGHT + "80 "
            + PREFIX_TAG + "PG "
            + PREFIX_TAG + "SG";
    public static final String MESSAGE_USAGE_LINEUP = COMMAND_WORD + ":Adds a lineup to MyGM."
            + "\nParameters: "
            + PREFIX_LINEUP + " "
            + PREFIX_NAME + "LINEUP NAME"
            + "[" + PREFIX_PLAYER + "PLAYER]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_LINEUP + " "
            + PREFIX_NAME + "Starting 5";
    public static final String MESSAGE_USAGE_SCHEDULE = COMMAND_WORD + ":Adds a schedule to MyGM."
            + "\nParameters: "
            + PREFIX_SCHEDULE + " "
            + PREFIX_NAME + "SCHEDULE NAME"
            + PREFIX_DESCRIPTION + "SCHEDULE NAME "
            + PREFIX_DATE + "DATE TIME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SCHEDULE + " "
            + PREFIX_NAME + "finals"
            + PREFIX_DESCRIPTION + "nba finals"
            + PREFIX_DATE + "01/01/2022 2000";

    public static final String MESSAGE_ADD_PERSON_SUCCESS = "New person added: %1$s.";
    public static final String MESSAGE_ADD_LINEUP_SUCCESS = "New lineup added: %1$s.";
    public static final String MESSAGE_ADD_SCHEDULE_SUCCESS = "New schedule added: %1$s.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in MyGM.";
    public static final String MESSAGE_DUPLICATE_LINEUP_NAME = "This lineup already exists in MyGM.";
    public static final String MESSAGE_DUPLICATE_SCHEDULE = "This schedule already exists in MyGM.";
    public static final String MESSAGE_DUPLICATE_JERSEY_NUMBER = "This jersey number already exists in MyGM.\n"
            + "You may consider these available ones:\n%1$s";
    public static final String MESSAGE_FULL_CAPACITY_REACHED = "MyGM has reached its full capacity with 100 players.";
    // can consider adding in a list of available jersey number

    private final Person toAddPerson;
    private final Lineup toAddLineup;
    private final Schedule toAddSchedule;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAddPerson = person;
        toAddLineup = null;
        toAddSchedule = null;
    }

    /**
     * Creates an AddCommand to add the specified {@code Lineup}
     */
    public AddCommand(Lineup lineup) {
        requireNonNull(lineup);
        toAddLineup = lineup;
        toAddPerson = null;
        toAddSchedule = null;
    }

    /**
     * Creates an AddCommand to add the specified {@code Schedule}
     */
    public AddCommand(Schedule schedule) {
        requireNonNull(schedule);
        toAddSchedule = schedule;
        toAddPerson = null;
        toAddLineup = null;
    }

    /* to add: add lineup */

    /**
     * Executes the AddCommand and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (toAddPerson != null) {
            if (model.isFull()) {
                throw new CommandException(MESSAGE_FULL_CAPACITY_REACHED);
            }

            if (model.hasPerson(toAddPerson)) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            }

            if (model.hasJerseyNumber(toAddPerson)) {
                throw new CommandException(String.format(MESSAGE_DUPLICATE_JERSEY_NUMBER,
                        model.getAvailableJerseyNumber()));
            }

            model.addPerson(toAddPerson);
            return new CommandResult(String.format(MESSAGE_ADD_PERSON_SUCCESS, toAddPerson));
        } else if (toAddLineup != null) {
            //assert toAddLineup != null;
            if (model.hasLineupName(toAddLineup.getLineupName())) {
                throw new CommandException(String.format(MESSAGE_DUPLICATE_LINEUP_NAME, toAddLineup.getLineupName()));
            }
            model.addLineup(toAddLineup);
            return new CommandResult(String.format(MESSAGE_ADD_LINEUP_SUCCESS, toAddLineup));
        } else {
            if (model.hasSchedule(toAddSchedule)) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            }

            model.addSchedule(toAddSchedule);
            return new CommandResult(String.format(MESSAGE_ADD_SCHEDULE_SUCCESS, toAddSchedule));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (toAddPerson != null) {
            return other == this // short circuit if same object
                    || (other instanceof AddCommand // instanceof handles nulls
                    && toAddPerson.equals(((AddCommand) other).toAddPerson));
        }
        if (toAddLineup != null) {
            return other == this // short circuit if same object
                    || (other instanceof AddCommand // instanceof handles nulls
                    && toAddLineup.equals(((AddCommand) other).toAddLineup));
        }
        if (toAddSchedule != null) {
            return other == this // short circuit if same object
                    || (other instanceof AddCommand // instanceof handles nulls
                    && toAddSchedule.equals(((AddCommand) other).toAddSchedule));
        }
        return false;
    }
}
