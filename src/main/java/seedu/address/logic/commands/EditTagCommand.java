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
 * Edits the tag of a person in the address book.
 */
public class EditTagCommand extends Command {

    public static final String COMMAND_WORD = "editTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edit a tag "
            + "(identified by the tag's index number) "
            + "of the client identified "
            + "by the index number used in the displayed person list. "
            + "Only one tag can edited at a time. \n"
            + "Parameters: CLIENT_INDEX (must be a positive integer) "
            + "TAG_NUMBER (must be a positive integer) "
            + "TAG\n"
            + "Example: " + COMMAND_WORD + " 1 2 "
            + "owesMoney :p2";

    public static final String MESSAGE_SUCCESS = "Edited tag in Client: %1$s";
    public static final String MESSAGE_DUPLICATE_TAG = "New version of tag specified is already present.";

    private final Index index;
    private final int tagNumber;
    private final Tag toAdd;

    /**
     * Creates an EditTagCommand to replace the tag in the index with the specified {@code Tag}
     *
     * @param index of the person in the filtered person list to edit
     * @param tagNumber of the person's tag list to edit
     * @param tag to be added to the person identified
     */
    public EditTagCommand(Index index, int tagNumber, Tag tag) {
        requireNonNull(index);
        requireNonNull(tag);
        this.index = index;
        this.tagNumber = tagNumber;
        toAdd = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person tagAddedPerson = editTagOfPerson(personToEdit, tagNumber, toAdd);

        model.setPerson(personToEdit, tagAddedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, tagAddedPerson));
    }

    /**
     * Edits {@code Tag} of {@code Person}
     * @param personToEdit person whose tag should be edited
     * @param tagNumber serial number of tag to be edited
     * @param tag tag to replace original tag
     * @return Person who is identical to personToEdit except for edited tag
     * @throws CommandException if the new tag already exists in personToEdit's tag list
     */
    private Person editTagOfPerson(Person personToEdit, int tagNumber, Tag tag) throws CommandException {
        Person newPerson = Person.copyPerson(personToEdit);
        ArrayList<Tag> tagList = newPerson.getTags();
        if (tagNumber < 1 || tagNumber > tagList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TAG_NUMBER);
        }
        if (tagList.contains(tag)) {
            throw new CommandException(MESSAGE_DUPLICATE_TAG);
        }

        tagList.set(tagNumber - 1, tag);
        newPerson.setTags(tagList);
        return newPerson;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditTagCommand // instanceof handles nulls
                && index.equals(((EditTagCommand) other).index)
                && tagNumber == ((EditTagCommand) other).tagNumber
                && toAdd.equals(((EditTagCommand) other).toAdd));
    }
}
