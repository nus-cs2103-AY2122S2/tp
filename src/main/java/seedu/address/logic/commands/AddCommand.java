package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB_USERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to HackNet.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to HackNet. "
        + "Multiple teams or skills should be separated by a comma.\n"
        + "Parameters: "
        + PREFIX_NAME + "NAME "
        + PREFIX_PHONE + "PHONE "
        + PREFIX_EMAIL + "EMAIL "
        + PREFIX_GITHUB_USERNAME + "GITHUB USERNAME "
        + "[" + PREFIX_TEAM + "TEAM...]"
        + "[" + PREFIX_SKILL + "SKILLNAME_SKILLPROFICENCY...]\n"
        + "Example1: " + COMMAND_WORD + " "
        + PREFIX_NAME + "John Doe "
        + PREFIX_PHONE + "98765432 "
        + PREFIX_EMAIL + "johnd@example.com "
        + PREFIX_GITHUB_USERNAME + "johndoe-123 "
        + PREFIX_TEAM + "HackNet, WhiteHackers"
        + PREFIX_SKILL + "Java_90\n"
        + "Example2: " + COMMAND_WORD + " "
        + PREFIX_NAME + "Joe King "
        + PREFIX_PHONE + "98765432 "
        + PREFIX_EMAIL + "joking@example.com "
        + PREFIX_GITHUB_USERNAME + "joking-123 "
        + PREFIX_TEAM + "Google Serach Engine, Vim plugin";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in HackNet.";

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
            String duplicatedField = model.getDuplicateField(toAdd);
            throw new CommandException("Error: Operation would result in persons with same " + duplicatedField + ".");
        }


        model.addPerson(toAdd);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddCommand // instanceof handles nulls
            && toAdd.equals(((AddCommand) other).toAdd));
    }
}
