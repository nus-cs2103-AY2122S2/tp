package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": \nAdds a person to the MyGM. "
            + "Parameters: "
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
            + PREFIX_TAG + "SG"
            + "\n"
            + "Adds a lineup to MyGM. Parameters: "
            + PREFIX_LINEUP + " "
            + PREFIX_NAME + "LINEUP NAME"
            + "[" + PREFIX_PLAYER + "PLAYER]...";

    public static final String MESSAGE_ADD_PERSON_SUCCESS = "New person added: %1$s.";
    public static final String MESSAGE_ADD_LINEUP_SUCCESS = "New lineup added: %1$s.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in MyGM.";
    public static final String MESSAGE_DUPLICATE_LINEUP_NAME = "This lineup already exists in MyGM.";
    public static final String MESSAGE_DUPLICATE_JERSEY_NUMBER = "This Jersey number already exists in MyGM.\n"
            + "You may consider these available ones:\n%1$s";
    public static final String MESSAGE_FULL_CAPACITY_REACHED = "MyGM has reached its full capacity with 100 players.";
    // can consider adding in a list of available jersey number

    private final Person toAddPerson;
    private final Lineup toAddLineup;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAddPerson = person;
        toAddLineup = null;
    }

    public AddCommand(Lineup lineup) {
        requireNonNull(lineup);
        toAddLineup = lineup;
        toAddPerson = null;
    }

    /* to add: add lineup */

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
        }

        if (model.hasLineupName(toAddLineup.getLineupName())) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_LINEUP_NAME, toAddLineup.getLineupName()));
        }
        model.addLineup(toAddLineup);
        return new CommandResult(String.format(MESSAGE_ADD_LINEUP_SUCCESS, toAddLineup));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAddPerson.equals(((AddCommand) other).toAddPerson)
                && toAddLineup.equals(((AddCommand) other).toAddLineup));
    }
}
