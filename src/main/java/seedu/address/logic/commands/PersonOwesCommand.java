package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;


/**
 * Class for the person who owes the user money.
 */
public class PersonOwesCommand extends Command {
    public static final String COMMAND_WORD = "person";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": adds the person who owes you money to Expense Expert "
            + "Parameters: "
            + PREFIX_PERSON_NAME + "NAME "
            + PREFIX_AMOUNT + "AMOUNT \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PERSON_NAME + "ALEX "
            + PREFIX_AMOUNT + "100 \n\n";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_SUCCESS_LIST = "List of all the poeple who owe you money";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in Expense Expert";

    private final Person toAdd;

    /**
     * Constructor for this class.
     * @param person person to be added to the app.
     */
    public PersonOwesCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    /**
     * Executes the given command according to the model.
     * @param model {@code Model} which the command should operate on.
     * @return The result of the operation
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        StringBuilder sb = new StringBuilder();

        model.addPerson(toAdd);
        String string = toAdd.toString();
        String name = string.split(";")[0];
        String amount = string.split(":")[1].split(" ")[1];
        String personAdded = sb.append(name).append(" Amount: $").append(amount).toString();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));

    }

    /**
     * Compares two objects of this class.
     * @param other the object to be compares.
     * @return boolean result of the comparison.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonOwesCommand // instanceof handles nulls
                && toAdd.equals(((PersonOwesCommand) other).toAdd));
    }
}
