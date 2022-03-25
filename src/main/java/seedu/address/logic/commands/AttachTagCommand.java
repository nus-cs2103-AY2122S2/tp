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
public class AttachTagCommand extends Command {

    public static final String COMMAND_WORD = "attach";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Attaches a tag to the person. \n"
            + "Parameters: "
            + "[" + PREFIX_TAG + "TAG]\n"
            + "Parameters: "
            + PREFIX_INDEX + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "friends " + PREFIX_INDEX + "1";

    public static final String MESSAGE_SUCCESS = "Tag attached: %1$s to Person : %2$s";
    public static final String MESSAGE_MISSING_TAG = "This tag does not exist, you need to create it first";
    public static final String MESSAGE_DUPLICATE_TAG = "This tag already exists in the person";

    private static Logger logger = Logger.getLogger("AttachTag");

    private final Tag toAttach;
    private final Index targetIndex;

    /**
     * Creates an AddTagCommand to add the specified {@code Tag}
     */
    public AttachTagCommand(Tag tag, Index targetIndex) {
        requireNonNull(tag);
        toAttach = tag;
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (!model.hasTag(toAttach)) {
            throw new CommandException(MESSAGE_MISSING_TAG);
        }

        Person personToAttachNewTag = lastShownList.get(targetIndex.getZeroBased());
        logger.log(Level.INFO, personToAttachNewTag.getName().toString());
        Set<Tag> tempTags = personToAttachNewTag.getTags();
        Set<Tag> tagCopy = new HashSet<>(tempTags);
        tagCopy.add(toAttach);

        Person personWithNewTag = new Person(personToAttachNewTag.getName(), personToAttachNewTag.getPhone(),
                    personToAttachNewTag.getEmail(), personToAttachNewTag.getAddress(), tagCopy,
                    personToAttachNewTag.getCourse(), personToAttachNewTag.getMatricCard(),
                    personToAttachNewTag.getTelegram());

        if (personToAttachNewTag.equals(personWithNewTag)) {
            throw new CommandException(MESSAGE_DUPLICATE_TAG);
        }
        model.setPerson(personToAttachNewTag, personWithNewTag);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAttach, personToAttachNewTag.getName()));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AttachTagCommand // instanceof handles nulls
                && toAttach.equals(((AttachTagCommand) other).toAttach));
    }
}
