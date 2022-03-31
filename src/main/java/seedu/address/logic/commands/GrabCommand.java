package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

public class GrabCommand extends Command {

    public static final String COMMAND_WORD = "grab";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Grab certain attributes of person "
            + "with certain index \n"
            + "Parameters: PREFIX/index \n"
            + "NOTE: This works for all PREFIX except the tag prefix \n"
            + "Example to grab one person: " + COMMAND_WORD + " tele/1\n"
            + "Example to grab all person: " + COMMAND_WORD + " tele/ \n"
            + "Parameters: PREFIX/index t/TAG_NAME or t/TAG_NAME PREFIX/index\n"
            + "Example to grab by tag: " + COMMAND_WORD + " tele/ t/TAG_NAME \n"
            + "Example to grab by tag: " + COMMAND_WORD + " t/TAG_NAME tele/ ";

    public static final String MESSAGE_SUCCESS = "Grabbed succesfully";

    public static final String MESSAGE_TOO_MANY_ARGUMENT = "Please insert only maximum "
            + "one attribute to be grabbed";
    public static final String MESSAGE_MISSING_TAG = "This tag does not exist, "
            + "you need to create it first";
    public static final String MESSAGE_GRAB_ONLY_BY_TAG = "Please remove the index after PREFIX, "
            + "we can only grab info of all people with this tag";
    public static final String NAME = "n/";
    public static final String PHONE = "p/";
    public static final String ADDRESS = "a/";
    public static final String EMAIL = "e/";
    public static final String COURSE = "c/";
    public static final String TELEGRAM = "tele/";
    public static final String MATRICCARD = "m/";

    private final Prefix attribute;
    private final String index;
    private final Tag tag;

    /**
     * Creates an GrabCommand to grab the specified {@code Prefix} attributes
     */
    public GrabCommand(Prefix attribute, String index, Tag tag) {
        this.attribute = attribute;
        this.index = index;
        this.tag = tag;
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

        if (tag == null) {
            // no tag situation
            if (index.equals("")) {
                builder.append(grabAllPerson(currAddressBook));
            } else {
                int personIndex = Integer.parseInt(index);
                Person personToBeGrabbed = currAddressBook.get(personIndex - 1);
                builder.append(grabOnePerson(personToBeGrabbed));
            }
            return new CommandResult(MESSAGE_SUCCESS, true, builder.toString());
        } else {
            if (index.equals("")) {
                ObservableList<Person> newPersonList =
                        currAddressBook.filtered(t->t.getTags().contains(tag));
                if (newPersonList.size() == 0) {
                    throw new CommandException(MESSAGE_MISSING_TAG);
                }
                builder.append(grabAllPerson(newPersonList));
            } else {
                // extra index is found
                throw new CommandException(MESSAGE_GRAB_ONLY_BY_TAG);
            }
            return new CommandResult(MESSAGE_SUCCESS + '\n', true, builder.toString());
        }
    }

    /**
     * Helper function to grab the person
     * @param personGrabbed the person that get grabbed
     * @return the grabbed result
     */
    public String grabOnePerson(Person personGrabbed) {
        final StringBuilder builder = new StringBuilder();
        switch (attribute.toString()) {
        case NAME:
            builder.append(personGrabbed.getName());
            break;
        case PHONE:
            builder.append(personGrabbed.getPhone());
            break;
        case EMAIL:
            builder.append(personGrabbed.getEmail());
            break;
        case ADDRESS:
            builder.append(personGrabbed.getAddress());
            break;
        case COURSE:
            builder.append(personGrabbed.getCourse());
            break;
        case MATRICCARD:
            builder.append(personGrabbed.getMatricCard());
            break;
        case TELEGRAM:
            builder.append(personGrabbed.getTelegram());
            break;
        default:
            return "";
        }
        return builder.toString();
    }

    /**
     * Helper function to grab all the person
     * @param personList the persons in contact list
     * @return the grabbed result
     */
    public String grabAllPerson(ObservableList<Person> personList) {
        final StringBuilder builder = new StringBuilder();
        for (Person p : personList) {
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
                return "";
            }
        }
        return builder.toString();
    }
}
