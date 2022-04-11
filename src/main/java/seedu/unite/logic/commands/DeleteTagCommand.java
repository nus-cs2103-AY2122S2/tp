package seedu.unite.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.unite.commons.core.Messages;
import seedu.unite.commons.core.index.Index;
import seedu.unite.logic.commands.exceptions.CommandException;
import seedu.unite.model.Model;
import seedu.unite.model.person.Person;
import seedu.unite.model.tag.Tag;

/**
 * Deletes a tag identified using it's displayed index from the unite.
 */
public class DeleteTagCommand extends Command {

    public static final String COMMAND_WORD = "delete_tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the tag identified by the index number used in the displayed tag list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted Tag: %1$s";

    private final Index targetIndex;

    public DeleteTagCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tag> lastShownList = model.getTagList();
        List<Person> personList = model.getFullPersonList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
        }
        // delete the tag from model
        Tag tagToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTag(tagToDelete);

        // remove tag from each person's tags
        for (Person currPerson : personList) {
            Set<Tag> tempTags = currPerson.getTags();
            Set<Tag> tagCopy = new HashSet<>(tempTags);
            tagCopy.removeIf(t -> t.isSameTag(tagToDelete));
            Person newPerson = new Person(currPerson.getName(), currPerson.getPhone(),
                    currPerson.getEmail(), currPerson.getAddress(), tagCopy,
                    currPerson.getCourse(), currPerson.getMatricCard(), currPerson.getTelegram());
            model.setPerson(currPerson, newPerson);
        }

        model.showTagList();
        return new CommandResult(String.format(MESSAGE_DELETE_TAG_SUCCESS, tagToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTagCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTagCommand) other).targetIndex)); // state check
    }
}

