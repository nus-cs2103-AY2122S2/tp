package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collection;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

public class RemoveCommand extends Command {
    public static final String COMMAND_WORD = "remove";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Appends fields to a person in the address book.";
    public static final String MESSAGE_SUCCESS = "Success";
    public static final String MESSAGE_DUPLICATE_PERSON = "Another person is already using this email.";

    private final Index index;
    private final Collection<Prefix> fieldPrefixes;
    private final Collection<Tag> tags;

    /**
     * Creates a RemoveCommand to remove {@code Field}s from the specified {@code Person}.
     * @param index the index of the person
     * @param prefixes the prefixes of the fields to remove
     * @param tags the tags to remove
     */
    public RemoveCommand(Index index, Collection<Prefix> prefixes, Collection<Tag> tags) {
        this.index = index;
        this.fieldPrefixes = prefixes;
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = personToEdit.removeFields(this.fieldPrefixes).removeTags(this.tags);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, editedPerson));
    }
}
