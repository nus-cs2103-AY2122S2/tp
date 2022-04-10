package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Deletes a tag from a person in the address book.
 */
public class DeleteTagCommand extends Command {

    public static final String COMMAND_WORD = "deleteTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete a tag "
            + "(identified by the tag's index number) "
            + "of the client identified "
            + "by the index number used in the displayed client list. "
            + "Only one tag can be deleted at a time. \n"
            + "Parameters: CLIENT_INDEX (must be a positive integer) "
            + "TAG_NUMBER (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " 3 " + "2";

    public static final String MESSAGE_SUCCESS = "Deleted tag in Client: %1$s";

    private final Index index;
    private final int tagNumber;

    /**
     * Creates an DeleteTagCommand to delete the {@code Tag} specified by the index and tag number
     *
     * @param index of the person in the filtered person list to edit
     * @param tagNumber of the tag to be deleted
     */
    public DeleteTagCommand(Index index, int tagNumber) {
        requireNonNull(index);
        this.index = index;
        this.tagNumber = tagNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person tagDeletedPerson = deleteTagFromPerson(personToEdit, tagNumber);

        model.setPerson(personToEdit, tagDeletedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, tagDeletedPerson));
    }

    /**
     * Deletes {@code Tag} from {@code Person}
     * @param personToEdit person whose tag should be deleted
     * @param tagNumber serial number of tag to be deleted
     * @return Person who is identical to personToEdit except for the deleted tag
     * @throws CommandException if an invalid tag number is specified
     */
    private Person deleteTagFromPerson(Person personToEdit, int tagNumber) throws CommandException {
        Person newPerson = Person.copyPerson(personToEdit);
        ArrayList<Tag> tagList = newPerson.getTags();
        if (tagNumber < 1 || tagNumber > tagList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TAG_NUMBER);
        }

        tagList.remove(tagNumber - 1);

        newPerson.setTags(tagList);
        return newPerson;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTagCommand // instanceof handles nulls
                && index.equals(((DeleteTagCommand) other).index)
                && tagNumber == ((DeleteTagCommand) other).tagNumber);
    }
}
