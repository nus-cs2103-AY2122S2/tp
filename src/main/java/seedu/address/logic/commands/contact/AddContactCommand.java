package seedu.address.logic.commands.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactWithNricPredicate;
import seedu.address.model.person.Nric;
import seedu.address.model.person.NricPredicate;

public class AddContactCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final CommandType COMMAND_TYPE = CommandType.CONTACT;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a contact to patient in Medbook. "
            + "Parameters: "
            + PREFIX_NRIC + "OWNER_NRIC "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John S. Smith "
            + PREFIX_PHONE + "88888888 "
            + PREFIX_EMAIL + "johns@example.com "
            + PREFIX_ADDRESS + "21 Lower Kent Ridge Road, Singapore 119077 ";

    public static final String MESSAGE_SUCCESS = "New contact added: %1$s";
    public static final String MESSAGE_DUPLICATE_CONTACT = "This contact already exists in patient contact list";
    public static final String MESSAGE_MISSING_PATIENT = "This patient does not exists in Medbook";

    // Identifier
    private final Nric ownerNric;

    private final Contact toAdd;

    /**
     * Creates an AddContactCommand to add the specified {@code Patient}
     */
    public AddContactCommand(Nric ownerNric, Contact contact) {
        requireNonNull(ownerNric);
        requireNonNull(contact);
        toAdd = contact;
        this.ownerNric = ownerNric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPerson(new NricPredicate(ownerNric))) {
            throw new CommandException(MESSAGE_MISSING_PATIENT);
        }

        if (model.hasContact(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONTACT);
        }

        model.addContact(toAdd);
        model.updateFilteredContactList(new ContactWithNricPredicate(ownerNric));

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), COMMAND_TYPE);
    }
}
