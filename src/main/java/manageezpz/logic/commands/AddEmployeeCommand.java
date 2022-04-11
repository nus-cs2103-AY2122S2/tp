package manageezpz.logic.commands;

import static java.util.Objects.requireNonNull;
import static manageezpz.logic.parser.CliSyntax.PREFIX_EMAIL;
import static manageezpz.logic.parser.CliSyntax.PREFIX_NAME;
import static manageezpz.logic.parser.CliSyntax.PREFIX_PHONE;

import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.model.Model;
import manageezpz.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddEmployeeCommand extends Command {

    public static final String COMMAND_WORD = "addEmployee";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an Employee to ManageEZPZ.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com";

    public static final String MESSAGE_SUCCESS = "New Employee added: %1$s";

    public static final String MESSAGE_DUPLICATE_PERSON = "Employee, phone number or "
            + "email already exists in manageEZPZ! "
            + "Please check again!\n";

    private final Person toAdd;

    /**
     * Creates an AddEmployeeCommand to add the specified {@code Person}
     */
    public AddEmployeeCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON + "\n" + MESSAGE_USAGE);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddEmployeeCommand // instanceof handles nulls
                && toAdd.equals(((AddEmployeeCommand) other).toAdd));
    }
}
