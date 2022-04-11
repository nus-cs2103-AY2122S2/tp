package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Group;
import seedu.address.model.person.Person;

/**
 * Adds a person to the class group.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the class group. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_STUDENT_NUMBER + "STUDENT_NUMBER "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_MOD + "MOD "
            + PREFIX_GROUP + "GROUP "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_STUDENT_NUMBER + "A2345678Q "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_MOD + "CS2030S "
            + PREFIX_GROUP + "W12-1 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesmoney";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the class group";
    public static final String MESSAGE_NO_DEFAULT_GROUP = "This module doesn't have a default group set";

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

        if (toAdd.getGroup().equals(Group.EMPTY_GROUP)) {
            if (model.doesModExistInList(toAdd.getMod()) && model.isDefaultGroupOfModPresent(toAdd.getMod())) {
                toAdd.setGroup(model.getDefaultGroupOfMod(toAdd.getMod()));
            } else {
                throw new CommandException(MESSAGE_NO_DEFAULT_GROUP);
            }
        }
        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        model.commitContent();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
