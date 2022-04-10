package seedu.unite.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_MATRICCARD;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.Set;

import seedu.unite.logic.commands.exceptions.CommandException;
import seedu.unite.model.Model;
import seedu.unite.model.person.Person;
import seedu.unite.model.tag.Tag;



/**
 * Adds a person to the UNite.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the UNite. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + PREFIX_COURSE + "COURSE "
            + PREFIX_MATRICCARD + "MATRIC CARD "
            + PREFIX_TELEGRAM + "TELEGRAM "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "Duplicate person in the UNite. \n"
            + "Person are considered as duplicated if \n"
            + "i) They have the same name (case-insensitive)\n"
            + "ii) At least one of these fields (phone, email, address, \n"
            + "    matric card, telegram) is the same \n"
            + "Consider using the edit command to edit the fields instead";


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
        Set<Tag> newTags = toAdd.getTags();
        for (Tag newTag : newTags) {
            if (!model.hasTag(newTag)) {
                model.addTag(newTag);
            }
        }
        model.showProfile(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
