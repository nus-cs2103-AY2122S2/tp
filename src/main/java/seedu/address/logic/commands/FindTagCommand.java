package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.TagContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

/**
 * Finds and lists all contacts in address book whose tag contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTagCommand extends Command {

    public static final String COMMAND_WORD = "findtag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts whose tags contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " friends colleague";

    public static final String TAG_NOT_EXIST = "One or more tags do not exist";

    public static final String TAG_ALREADY_ACTIVATED = "One or more tags have already been selected";

    private final List<String> keywords;

    public FindTagCommand(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.clearDetailedContactView();

        boolean hasAllTag = true;
        List<Tag> tagsToAdd = new ArrayList<>();
        for (String keyword : keywords) {
            Tag tagToAdd = new Tag(keyword);
            hasAllTag = hasAllTag && model.hasTag(new Tag(keyword));
            boolean tagActivated = model.getActivatedTagList().contains(tagToAdd);

            if (!hasAllTag) {
                throw new CommandException(TAG_NOT_EXIST);
            }

            if (tagActivated) {
                throw new CommandException(TAG_ALREADY_ACTIVATED);
            }
            tagsToAdd.add(tagToAdd);
        }

        for (Tag tag : tagsToAdd) {
            model.addActivatedTag(tag);
        }

        if (keywords.size() == 0) {
            throw new CommandException(MESSAGE_USAGE);
        }
        ObservableList<Tag> activatedTagList = model.getActivatedTagList();

        TagContainsKeywordsPredicate predicate =
                new TagContainsKeywordsPredicate(activatedTagList.stream().map(tag -> tag.tagName)
                        .collect(Collectors.toList()));
        model.updateFilteredPersonList(predicate);

        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTagCommand // instanceof handles nulls
                // && predicate.equals(((FindTagCommand) other).predicate)); // state check
                && keywords.equals(((FindTagCommand) other).keywords)); // state check
    }
}
