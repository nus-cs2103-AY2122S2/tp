package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.StringJoiner;

import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 * Lists all tasks in the task list to the user.
 * Can also list all unique tags used in Harmonia in the command result box.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": If no parameters are specified, list all tasks in task list. "
            + "If parameter " + PREFIX_TAG + " is specified, list all existing tags in the command result box.\n"
            + "Parameters: [" + PREFIX_TAG + "]";

    public static final String LIST_TASKS_MESSAGE_SUCCESS = "Listed all tasks";
    public static final String LIST_TAGS_MESSAGE_SUCCESS = "All tags found: %1$s";
    public static final String LIST_TAGS_NO_TAGS_FOUND_MESSAGE = "No tags found.";

    private final Boolean isListTag;

    /**
     * Creates a ListCommand to list all tasks or tags.
     *
     * @param isListTag True if it is a list tag command, false otherwise.
     */
    public ListCommand(Boolean isListTag) {
        this.isListTag = isListTag;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (isListTag) {
            Set<Tag> tagList = model.getTagList();

            if (tagList.isEmpty()) {
                return new CommandResult(LIST_TAGS_NO_TAGS_FOUND_MESSAGE);
            }

            String allTags = tagListToString(tagList);
            return new CommandResult(String.format(LIST_TAGS_MESSAGE_SUCCESS, allTags));

        } else {
            model.updateToDefaultSortedTaskList();
            model.updateToDefaultFilteredTaskList();
            return new CommandResult(LIST_TASKS_MESSAGE_SUCCESS);
        }
    }

    /**
     * Formats tag list as text for viewing.
     *
     * @param tagList Tag list to be formatted.
     * @return Tag list as a {@code String} with a whitespace between each tag.
     */
    public String tagListToString(Set<Tag> tagList) {
        final StringJoiner joiner = new StringJoiner(" ");
        for (Tag tag : tagList) {
            joiner.add(tag.toString());
        }
        return joiner.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCommand // instanceof handles nulls
                && isListTag.equals(((ListCommand) other).isListTag));
    }
}
