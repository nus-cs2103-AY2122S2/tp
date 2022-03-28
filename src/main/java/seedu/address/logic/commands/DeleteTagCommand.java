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
 * Adds a person to the address book.
 */
public class DeleteTagCommand extends Command {

    public static final String COMMAND_WORD = "addTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete a tag "
            + "(identified by the tag's index number) "
            + "of the person identified "
            + "by the index number used in the displayed person list. "
            + "Only one tag can be deleted at a time. "
            + "Parameters: PERSON_INDEX (must be a positive integer) "
            + "TAG_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + "3 " + "2";

    public static final String MESSAGE_SUCCESS = "Deleted tag in Person: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Index index;
    private final int tagNumber;

    /**
     * Creates an AddTagCommand to add the specified {@code Tag}
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
        Person tagAddedPerson = deleteTagFromPerson(personToEdit, tagNumber);

        model.setPerson(personToEdit, tagAddedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, tagAddedPerson));
    }

    private Person deleteTagFromPerson(Person personToEdit, int tagNumber) {
        Person newPerson = Person.copyPerson(personToEdit);
        ArrayList<Tag> tagList = newPerson.getTags();
        tagList.remove(tagNumber-1);

        newPerson.setTags(tagList);
        return newPerson;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTagCommand // instanceof handles nulls
                && tagNumber  == ((DeleteTagCommand) other).tagNumber);
    }
}
