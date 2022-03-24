package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Clears the address book.
 */
public class DetachTagCommand extends Command {

    public static final String COMMAND_WORD = "detach";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Detaches a tag from the person. \n"
            + "Parameters: "
            + "[" + PREFIX_TAG + "TAG]\n"
            + "Parameters: "
            + PREFIX_INDEX + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "friends " + PREFIX_INDEX + "1";

    public static final String MESSAGE_SUCCESS = "Tag detached: %1$s from Person : %2$s";
    public static final String MESSAGE_MISSING_TAG = "This tag does not exist, you need to create it first";
    public static final String MESSAGE_MISSING_TAG_PERSON = "The person does not have this tag";

    private static Logger logger = Logger.getLogger("DetachTag");

    private final Tag toDetach;
    private final Index targetIndex;

    /**
     * Creates an AddTagCommand to add the specified {@code Tag}
     */
    public DetachTagCommand(Tag tag, Index targetIndex) {
        requireNonNull(tag);
        toDetach = tag;
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (!model.hasTag(toDetach)) {
            throw new CommandException(MESSAGE_MISSING_TAG);
        }

        Person personToDetach = lastShownList.get(targetIndex.getZeroBased());
        logger.log(Level.INFO, personToDetach.getName().toString());
        Set<Tag> tempTags = personToDetach.getTags();
        Set<Tag> tagCopy = new HashSet<>(tempTags);

        if (!tagCopy.contains(toDetach)) {
            throw new CommandException(MESSAGE_MISSING_TAG_PERSON);
        }
        tagCopy.removeIf(t -> t.isSameTag(toDetach));

        Person newPerson = new Person(personToDetach.getName(), personToDetach.getPhone(),
                personToDetach.getEmail(), personToDetach.getAddress(), tagCopy);

        model.setPerson(personToDetach, newPerson);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toDetach, personToDetach.getName()));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DetachTagCommand // instanceof handles nulls
                && toDetach.equals(((DetachTagCommand) other).toDetach));
    }
}
