package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/** A list of unique tags found in the task list */
public class TagList {

    /**
     * Hashmap to store tags, where the key is the Tag and the value is an Integer that counts
     * the number of tasks in the task list which contain the Tag.
     */
    private static HashMap<Tag, Integer> tags;

    /**
     * Creates a tag list, initialised with tags from the existing tasks in the task list.
     *
     * @param taskList Task list to retrieve tags from.
     */
    public TagList(TaskList taskList) {
        tags = new HashMap<>();
        initialiseTagList(taskList);
    }

    /**
     * Initialises the tag list with tags found in the given task list.
     *
     * @param taskList TaskList to retrieve tags from.
     */
    public void initialiseTagList(TaskList taskList) {
        requireNonNull(taskList);
        for (Task task : taskList.getTaskList()) {
            Set<Tag> tagsInTask = task.getTags();
            tagsInTask.forEach(this::addTag);
        }
    }

    /**
     * Adds a tag to the tag list.
     */
    private void addTag(Tag tag) {
        requireNonNull(tag);
        if (tags.containsKey(tag)) {
            int numOfTasksWithTag = tags.get(tag);
            tags.put(tag, numOfTasksWithTag + 1);
        } else {
            tags.put(tag, 1);
        }
    }

    /**
     * Adds all tags of a given task to the tag list.
     */
    public void addTagsOfTask(Task task) {
        Set<Tag> tagsInTask = task.getTags();
        tagsInTask.forEach(this::addTag);
    }

    /**
     * Removes a tag from the tag list.
     */
    private void removeTag(Tag tag) {
        requireNonNull(tag);
        if (tags.containsKey(tag)) {
            int numTasksWithTag = tags.get(tag);
            if (numTasksWithTag > 1) {
                tags.put(tag, numTasksWithTag - 1);
            } else if (numTasksWithTag == 1) {
                tags.remove(tag);
            }
        }
    }

    /**
     * Removes all tags from a given task from the tag list.
     */
    public void removeTagsOfTask(Task task) {
        Set<Tag> tagsInTask = task.getTags();
        tagsInTask.forEach(this::removeTag);
    }

    /**
     * Removes all tags from the target task from the tag list and
     * adds the tags from the edited task to the tag list.
     *
     * @param target The task to be replaced.
     * @param editedTask The edited task to replace the target.
     */
    public void setTag(Task target, Task editedTask) {
        Set<Tag> tagsInTarget = target.getTags();
        tagsInTarget.forEach(this::removeTag);

        Set<Tag> tagsInEditedTask = editedTask.getTags();
        tagsInEditedTask.forEach(this::addTag);
    }

    /**
     * Returns an ordered tag list
     */
    public Set<Tag> getTagList() {
        return new TreeSet<>(tags.keySet());
    }

    /**
     * Resets the existing tags of this {@code TagList} with tags from {@code newTaskList}.
     */
    public void resetTags(ReadOnlyTaskList newTaskList) {
        requireNonNull(newTaskList);

        tags = new HashMap<>();
        for (Task task : newTaskList.getTaskList()) {
            addTagsOfTask(task);
        }
    }

    @Override
    public String toString() {
        final StringJoiner joiner = new StringJoiner(" ");
        for (Tag tag : getTagList()) {
            joiner.add(tag.toString());
        }
        return joiner.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagList // instanceof handles nulls
                && tags.equals(((TagList) other).tags));
    }

    @Override
    public int hashCode() {
        return tags.hashCode();
    }
}
