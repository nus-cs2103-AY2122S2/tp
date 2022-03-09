package unibook.logic.commands;

import static java.util.Objects.requireNonNull;

import unibook.logic.commands.exceptions.CommandException;
import unibook.logic.parser.CliSyntax;
import unibook.model.Model;
import unibook.model.person.Person;

/**
 * Adds a person to the UniBook.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the UniBook. "
        + "Parameters: "
        + CliSyntax.PREFIX_NAME + "NAME "
        + CliSyntax.PREFIX_PHONE + "PHONE "
        + CliSyntax.PREFIX_EMAIL + "EMAIL "
        + "[" + CliSyntax.PREFIX_TAG + "TAG]...\n"
        + "Example: " + COMMAND_WORD + " "
        + CliSyntax.PREFIX_NAME + "John Doe "
        + CliSyntax.PREFIX_PHONE + "98765432 "
        + CliSyntax.PREFIX_EMAIL + "johnd@example.com "
        + CliSyntax.PREFIX_TAG + "friends "
        + CliSyntax.PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the UniBook";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddCommand // instanceof handles nulls
            && toAdd.equals(((AddCommand) other).toAdd));
    }
}
