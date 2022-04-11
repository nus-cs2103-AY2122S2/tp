package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.core.index.Index;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final StudentId studentId;
    private final Name name;

    // Data fields
    private final ModuleCode moduleCode;
    private final Phone phone;
    private final TelegramHandle telegramHandle;
    private final Email email;
    private final TaskList taskList;

    /**
     * Constructs a new Person.
     * Only the studentId, name, moduleCode are compulsory fields.
     */
    public Person(StudentId studentId, Name name, ModuleCode moduleCode, Phone phone,
                  TelegramHandle telegramHandle, Email email) {
        requireAllNonNull(studentId, name, moduleCode); // Compulsory fields
        this.studentId = studentId;
        this.name = name;
        this.moduleCode = moduleCode;
        this.phone = phone;
        this.telegramHandle = telegramHandle;
        this.email = email;
        this.taskList = new TaskList();
    }

    /**
     * Constructs a new Person, with an existing taskList.
     * Only the studentId, name, moduleCode are compulsory fields.
     */
    public Person(StudentId studentId, Name name, ModuleCode moduleCode, Phone phone,
                  TelegramHandle telegramHandle, Email email, TaskList taskList) {
        requireAllNonNull(studentId, name, moduleCode); // Compulsory fields
        this.studentId = studentId;
        this.name = name;
        this.moduleCode = moduleCode;
        this.phone = phone;
        this.telegramHandle = telegramHandle;
        this.email = email;
        this.taskList = taskList;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public Name getName() {
        return name;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    public Phone getPhone() {
        return phone;
    }

    public TelegramHandle getTelegramHandle() {
        return telegramHandle;
    }

    public Email getEmail() {
        return email;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Adds a {@code task} to list of tasks.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        taskList.addTask(task);
    }

    /**
     * Deletes the task at {@code index} from the list of tasks.
     *
     * @param index the index of the task to be deleted.
     */
    public void deleteTask(Index index) {
        taskList.deleteTask(index);
    }

    /**
     * Deletes the {@code task} from the list of tasks.
     *
     * @param task the task to be deleted.
     */
    public void deleteTask(Task task) {
        taskList.deleteTask(task);
    }

    /**
     * Marks the task at {@code index} from the list of tasks as complete.
     *
     * @param index the index of the task to be marked complete.
     */
    public void markTaskAsComplete(int index) {
        taskList.markTaskAsComplete(index);
    }

    /**
     * Checks if the {@code task} is already present in the list of tasks.
     *
     * @param task the task name to be checked.
     * @return true if the task is already present ;false otherwise.
     */
    public boolean isTaskAlreadyPresent(Task task) {
        return taskList.isTaskAlreadyPresent(task);
    }

    /**
     * Returns true if both persons have the same student id.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getStudentId().equals(getStudentId());
    }

    /**
     * Returns a copy of the person.
     *
     * @return a copy of the person object.
     */
    public Person getCopy() {
        TaskList copyOfTaskList = new TaskList();
        for (Task task : getTaskList().getTaskList()) {
            Task copyOfTask = new Task(task.getTaskName());
            if (task.isTaskComplete()) {
                copyOfTask.markComplete();
            }
            copyOfTaskList.addTask(copyOfTask);
        }

        return new Person(getStudentId(), getName(), getModuleCode(), getPhone(),
                getTelegramHandle(), getEmail(), copyOfTaskList);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;

        boolean isPhoneEqual;

        if (otherPerson.getPhone() == null && getPhone() == null) {
            isPhoneEqual = true;
        } else if (otherPerson.getPhone() == null) {
            isPhoneEqual = false;
        } else if (getPhone() == null) {
            isPhoneEqual = false;
        } else {
            isPhoneEqual = otherPerson.getPhone().equals(getPhone());
        }

        boolean isTelegramHandleEqual;

        if (otherPerson.getTelegramHandle() == null && getTelegramHandle() == null) {
            isTelegramHandleEqual = true;
        } else if (otherPerson.getTelegramHandle() == null) {
            isTelegramHandleEqual = false;
        } else if (getTelegramHandle() == null) {
            isTelegramHandleEqual = false;
        } else {
            isTelegramHandleEqual = otherPerson.getTelegramHandle().equals(getTelegramHandle());
        }

        boolean isEmailEqual;

        if (otherPerson.getEmail() == null && getEmail() == null) {
            isEmailEqual = true;
        } else if (otherPerson.getEmail() == null) {
            isEmailEqual = false;
        } else if (getEmail() == null) {
            isEmailEqual = false;
        } else {
            isEmailEqual = otherPerson.getEmail().equals(getEmail());
        }

        boolean isTaskListEqual;

        if (otherPerson.getTaskList() == null && getTaskList() == null) {
            isTaskListEqual = true;
        } else if (getTaskList() == null || otherPerson.getTaskList() == null) {
            isTaskListEqual = false;
        } else {
            isTaskListEqual = otherPerson.getTaskList().equals(getTaskList());
        }

        return otherPerson.getStudentId().equals(getStudentId())
                && otherPerson.getName().equals(getName())
                && otherPerson.getModuleCode().equals(getModuleCode())
                && isPhoneEqual
                && isTelegramHandleEqual
                && isEmailEqual
                && isTaskListEqual;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(studentId, name, moduleCode, phone, telegramHandle, email);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Student ID: ")
                .append(getStudentId())
                .append("; Name: ")
                .append(getName())
                .append("; Module Code: ")
                .append(getModuleCode());

        Phone currentPhone = getPhone();
        if (currentPhone == null) {
            builder.append("; Phone Number: ");
        } else {
            builder.append("; Phone Number: ").append(currentPhone);
        }

        TelegramHandle currentTelegramHandle = getTelegramHandle();
        if (currentTelegramHandle == null) {
            builder.append("; Telegram Handle: ");
        } else {
            builder.append("; Telegram Handle: @").append(currentTelegramHandle);
        }

        Email currentEmail = getEmail();
        if (currentEmail == null) {
            builder.append("; Email: ");
        } else {
            builder.append("; Email: ").append(currentEmail);
        }

        TaskList currentTaskList = getTaskList();
        builder.append("; Tasks: ").append(currentTaskList);


        return builder.toString();
    }

}
