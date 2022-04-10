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
 * Adds tag to specified person in the address book.
 */
public class AddTagCommand extends Command {

    public static final String COMMAND_WORD = "addTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds tag to the client identified "
            + "by the index number used in the displayed person list. "
            + "Only one tag can be added at a time. Duplicates cannot be added. "
            + "Parameters: INDEX (must be a positive integer) + TAG\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "owes money :p3 ";

    public static final String MESSAGE_SUCCESS = "Added tag(s) to Person: %1$s";
    public static final String MESSAGE_DUPLICATE_TAG = "The tag you want to add is already present.";

    private final Index index;
    private final Tag tagToAdd;

    /**
     * Creates an AddTagCommand to add the specified {@code Tag}.
     *
     * @param index of the person in the filtered person list to edit
     * @param tag to be added to the person identified
     */
    public AddTagCommand(Index index, Tag tag) {
        requireNonNull(index);
        requireNonNull(tag);
        this.index = index;
        tagToAdd = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person tagAddedPerson = addTagToPerson(personToEdit, tagToAdd);

        model.setPerson(personToEdit, tagAddedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, tagAddedPerson));
    }

    /**
     * Adds {@code Tag} to taglist of {@code Person} specified.
     * @param personToEdit Person to add tag to
     * @param tagToAdd Tag to be added
     * @return Person with the tag added
     * @throws CommandException if there is a duplicate tag already existing
     */
    private Person addTagToPerson(Person personToEdit, Tag tagToAdd) throws CommandException {
        Person newPerson = Person.copyPerson(personToEdit);
        ArrayList<Tag> tagList = newPerson.getTags();

        if (!tagList.contains(tagToAdd)) {
            tagList.add(tagToAdd);
        } else {
            throw new CommandException(MESSAGE_DUPLICATE_TAG);
        }

        newPerson.setTags(tagList);
        return newPerson;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTagCommand // instanceof handles nulls
                && index.equals(((AddTagCommand) other).index)
                && tagToAdd.equals(((AddTagCommand) other).tagToAdd));
    }
}
