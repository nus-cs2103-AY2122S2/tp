package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.image.ImageDetailsList;
import seedu.address.model.person.Address;
import seedu.address.model.person.DeadlineList;
import seedu.address.model.person.Email;
import seedu.address.model.person.Favourite;
import seedu.address.model.person.HighImportance;
import seedu.address.model.person.Name;
import seedu.address.model.person.Notes;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Deletes tags that contain any of the argument keywords, and unassigns the tag from any contacts.
 * Keyword matching is case insensitive.
 */
public class DeleteTagCommand extends Command {

    public static final String COMMAND_WORD = "deltag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes tags that contain any of "
            + "the specified keywords (case-insensitive), automatically unassigns the tag from any contacts "
            + "and displays a list of contacts who had the tags unassigned from them."
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " friends colleague";

    public static final String MESSAGE_TAG_NOT_EXIST = "One or more of the specified tag(s) does not exist: %s";
    public static final String MESSAGE_DELETE_SUCCESS = "Deleted tags: %s";
    public static final String MESSAGE_DELETE_FAIL = "All specified tag(s) do not exist.";

    private final List<String> keywords;

    public DeleteTagCommand(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.clearActivatedTagList();
        model.clearDetailedContactView();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        ObservableList<Person> allPersons = model.getFilteredPersonList();

        Set<Tag> tagsToDelete = new HashSet<>();
        Set<String> tagsNotExist = new HashSet<>();

        boolean hasUncreatedTags = false;

        for (String keyword : keywords) {
            Tag toDelete = new Tag(keyword);
            if (!model.hasTag(toDelete)) {
                hasUncreatedTags = true;
                tagsNotExist.add(keyword);
                continue;
            }

            for (Person person : allPersons) {
                if (canRemoveTag(person, toDelete)) {
                    Person editedPerson = removeTagFromNewPerson(person, toDelete);
                    model.setPerson(person, editedPerson);
                }
            }

            tagsToDelete.add(toDelete);
        }

        for (Tag tag : tagsToDelete) {
            model.deleteTag(tag);
        }



        Set<String> tagNamesToDelete = tagsToDelete.stream().map(tag -> tag.tagName).collect(Collectors.toSet());

        if (hasUncreatedTags) {
            if (tagNamesToDelete.size() >= 1) {
                String result = MESSAGE_TAG_NOT_EXIST + "\n" + MESSAGE_DELETE_SUCCESS;
                return new CommandResult(String.format(result, tagsNotExist, tagNamesToDelete));
            } else {
                throw new CommandException(MESSAGE_DELETE_FAIL);
            }
        }

        return new CommandResult(String.format(MESSAGE_DELETE_SUCCESS, tagNamesToDelete));
    }


    /**
     * Removes a specific {@code Tag} from the {@code Set<Tag>} of a {@code Person}.
     * @param personToEdit the person to remove the tag from.
     * @param newTag the tag to remove.
     * @return the person with the removed tag.
     */
    private static Person removeTagFromNewPerson(Person personToEdit, Tag newTag) {
        requireNonNull(personToEdit);
        requireNonNull(newTag);

        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Address address = personToEdit.getAddress();
        DeadlineList deadlines = personToEdit.getDeadlines();
        Notes notes = personToEdit.getNotes();
        Set<Tag> tags = personToEdit.getTags();
        Favourite favourite = personToEdit.getFavouriteStatus();
        HighImportance highImportance = personToEdit.getHighImportanceStatus();
        ImageDetailsList images = personToEdit.getImageDetailsList();

        Set<Tag> newTags = new HashSet<>(tags);
        newTags.remove(newTag);

        return new Person(name, phone, email, address, deadlines, notes, newTags, favourite, highImportance, images);
    }

    /**
     * Checks if a specific {@code Tag} can be removed from the {@code Set<Tag>} of a {@code Person}.
     * @param personToEdit the person to remove the tag from.
     * @param newTag the tag to remove.
     * @return boolean value of whether the tag can be removed.
     */
    private static boolean canRemoveTag(Person personToEdit, Tag newTag) {
        requireNonNull(personToEdit);
        requireNonNull(newTag);
        Set<Tag> tags = personToEdit.getTags();
        Set<Tag> newTags = new HashSet<>(tags);
        return newTags.contains(newTag);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTagCommand // instanceof handles nulls
                && keywords.equals(((DeleteTagCommand) other).keywords)); // state check
    }
}
