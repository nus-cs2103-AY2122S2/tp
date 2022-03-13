package seedu.address.logic.commands.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.stream.IntStream;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;


public class AddContactCommand extends Command {
    public static final String COMMAND_WORD = "create";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a contact to patient in Medbook. "
            + "Parameters: "
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
    private final Nric nric;

    private final Contact toAdd;

    /**
     * Creates an AddContactCommand to add the specified {@code Patient}
     */
    public AddContactCommand(Nric nric, Contact contact) {
        requireNonNull(nric);
        requireNonNull(contact);
        toAdd = contact;
        this.nric = nric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> personList = model.getAddressBook().getPersonList();

        int index = IntStream.range(0, personList.size())
                .filter(i -> personList.get(i).getNric().equals(nric))
                .findFirst()
                .orElse(-1);

        if (index == -1) {
            throw new CommandException(MESSAGE_MISSING_PATIENT);
        }

        Person person = personList.get(index);

        if (person.hasContact(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONTACT);
        }

        person.setContact(toAdd);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
