package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
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
public class AddTagCommand extends Command {

    public static final String COMMAND_WORD = "addTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds tags to the person identified "
            + "by the index number used in the displayed person list. "
            + "At least one tag should be specified. "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "owes money :p3 "
            + PREFIX_TAG + "friends and family";;

    public static final String MESSAGE_SUCCESS = "Added tag(s) to Person: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Index index;
    private final ArrayList<Tag> tagsToAdd;

    /**
     * Creates an AddTagCommand to add the specified {@code Tag}s
     *
     * @param index of the person in the filtered person list to edit
     * @param tags to be added to the person identified
     */
    public AddTagCommand(Index index, ArrayList<Tag> tags) {
        requireNonNull(index);
        this.index = index;
        tagsToAdd = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person tagAddedPerson = addTagsToPerson(personToEdit, tagsToAdd);

        model.setPerson(personToEdit, tagAddedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, tagAddedPerson));
    }

    private Person addTagsToPerson(Person personToEdit, ArrayList<Tag> tagsToAdd) {
        Person newPerson = Person.copyPerson(personToEdit);
        ArrayList<Tag> tagList = newPerson.getTags();
        for (Tag tag: tagsToAdd) {
            tagList.add(tag);
        }

        System.out.println(tagList);
        newPerson.setTags(tagList);
        return newPerson;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTagCommand // instanceof handles nulls
                && tagsToAdd.equals(((AddTagCommand) other).tagsToAdd));
    }
}
