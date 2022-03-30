package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a client.\n"
            + "Required Parameters: "
            + Name.PREFIX + "NAME "
            + Phone.PREFIX + "PHONE "
            + Email.PREFIX + "EMAIL "
            + Address.PREFIX + "ADDRESS \n"
            + "Optional Parameters: "
            + "[" + Birthday.PREFIX + "BIRTHDAY] "
            + "[" + Remark.PREFIX + "REMARK] "
            + "[" + Tag.PREFIX + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + Name.PREFIX + "John Doe "
            + Phone.PREFIX + "98765432 "
            + Email.PREFIX + "johnd@example.com "
            + Address.PREFIX + "311, Clementi Ave 2, #02-25 "
            + Tag.PREFIX + "vendor "
            + Tag.PREFIX + "owesMoney";

    public static final String MESSAGE_SUCCESS = "Added %s.";
    public static final String MESSAGE_DUPLICATE_PERSON = "Another client is already using this email.";

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
