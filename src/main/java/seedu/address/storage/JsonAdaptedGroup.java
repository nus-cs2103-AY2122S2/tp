package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link Group}.
 */
public class JsonAdaptedGroup {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Group's %s field is missing!";

    private final String groupName;
    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedGroup} with the given group details.
     */
    @JsonCreator
    public JsonAdaptedGroup(@JsonProperty("groupName") String groupName,
                            @JsonProperty("persons") List<JsonAdaptedPerson> persons ,
                            @JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.groupName = groupName;
        if (!persons.isEmpty()) {
            this.persons.addAll(persons);
        }
        if (tasks != null) {
            this.tasks.addAll(tasks);
        }
    }

    /**
     * Converts a given {@code Group} into this class for Jackson use.
     */
    public JsonAdaptedGroup(Group source) {
        groupName = String.valueOf(source.getGroupName());
        persons.addAll(source.getPersons().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));

        List<String> taskNames = new ArrayList<>();

        //map all the tasks into a stream of strings
        taskNames.addAll(source.getTaskList().getInternalList().stream()
                .map(task -> String.valueOf(task.taskName))
                .collect(Collectors.toList()));
        tasks.addAll(taskNames.stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted group object into the model's {@code Group} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted group.
     */
    public Group toModelType() throws IllegalValueException {
        final List<Task> groupTasks = new ArrayList<>();
        for (JsonAdaptedTask task : tasks) {
            groupTasks.add(task.toModelType());
        }

        if (groupName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GroupName.class.getSimpleName()));
        }

        if (!GroupName.isValidGroupName(groupName)) {
            throw new IllegalValueException(GroupName.MESSAGE_CONSTRAINTS);
        }
        final GroupName modelGroupName = new GroupName(groupName);

        Group modelGroup = new Group(modelGroupName);

        for (JsonAdaptedPerson person: persons) {
            modelGroup.assignPerson(person.toModelType());
        }

        for (Task task : groupTasks) {
            if (!modelGroup.getTaskList().contains(task)) {
                modelGroup.addTask(task);
            }
        }

        return modelGroup;
    }
}
