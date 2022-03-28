package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class GrabCommand extends Command {

    public static final String COMMAND_WORD = "grab";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Grab certain attributes of person with certain index "
            + "Parameters: PREFIX/index \n"
            + "NOTE: This works for all PREFIX except the tag prefix"
            + "Example: " + COMMAND_WORD + " tele/1";

    public static final String MESSAGE_SUCCESS = "Grabbed succesfully";

    public static final String MESSAGE_TOO_MANY_ARGUMENT = "Please insert only maximum one attribute to be grabbed";

    public static final String NAME = "n/";
    public static final String PHONE = "p/";
    public static final String ADDRESS = "a/";
    public static final String EMAIL = "e/";
    public static final String COURSE = "c/";
    public static final String TELEGRAM = "tele/";
    public static final String MATRICCARD = "m/";

    private final Prefix attribute;
    private final String index;

    /**
     * Creates an GrabCommand to grab the specified {@code Prefix} attributes
     */
    public GrabCommand(Prefix attribute, String index) {
        this.attribute = attribute;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Person> currAddressBook = model.getFilteredPersonList();

        try {
            if (!index.equals("")) {
                int personIndex = Integer.parseInt(index);
            }
        } catch (NumberFormatException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (!index.equals("") && (Integer.parseInt(index) > currAddressBook.size() || Integer.parseInt(index) < 1)) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        final StringBuilder builder = new StringBuilder();

        if (index.equals("")) {
            for (Person p : currAddressBook) {
                switch (attribute.toString()) {
                case NAME:
                    builder.append(p.getName() + "\n");
                    break;
                case PHONE:
                    builder.append(p.getPhone() + "\n");
                    break;
                case EMAIL:
                    builder.append(p.getEmail() + "\n");
                    break;
                case ADDRESS:
                    builder.append(p.getAddress() + "\n");
                    break;
                case COURSE:
                    builder.append(p.getCourse() + "\n");
                    break;
                case MATRICCARD:
                    builder.append(p.getMatricCard() + "\n");
                    break;
                case TELEGRAM:
                    builder.append(p.getTelegram() + "\n");
                    break;
                default:
                }
            }
        } else {
            int personIndex = Integer.parseInt(index);
            Person personToBeGrabbed = currAddressBook.get(personIndex - 1);
            switch (attribute.toString()) {
            case NAME:
                builder.append(personToBeGrabbed.getName());
                break;
            case PHONE:
                builder.append(personToBeGrabbed.getPhone());
                break;
            case EMAIL:
                builder.append(personToBeGrabbed.getEmail());
                break;
            case ADDRESS:
                builder.append(personToBeGrabbed.getAddress());
                break;
            case COURSE:
                builder.append(personToBeGrabbed.getCourse());
                break;
            case MATRICCARD:
                builder.append(personToBeGrabbed.getMatricCard());
                break;
            case TELEGRAM:
                builder.append(personToBeGrabbed.getTelegram());
                break;
            default:
            }
        }
        return new CommandResult(MESSAGE_SUCCESS + '\n' + builder);
    }
}
