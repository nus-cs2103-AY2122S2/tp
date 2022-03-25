package seedu.address.testutil;

import seedu.address.model.person.Email;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.Task;
import seedu.address.model.person.TaskList;
import seedu.address.model.person.TelegramHandle;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_ID = "A0000000Z";
    public static final String DEFAULT_NAME = "Default Name";
    public static final String DEFAULT_MODULE_CODE = "CS0000";
    public static final String DEFAULT_PHONE = "00000000";
    public static final String DEFAULT_TELEGRAM_HANDLE = "default";
    public static final String DEFAULT_EMAIL = "default@gmail.com";

    private StudentId studentId;
    private Name name;
    private ModuleCode moduleCode;
    private Phone phone;
    private TelegramHandle telegramHandle;
    private Email email;
    private TaskList taskList;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        studentId = new StudentId(DEFAULT_ID);
        name = new Name(DEFAULT_NAME);
        moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
        phone = new Phone(DEFAULT_PHONE);
        telegramHandle = new TelegramHandle(DEFAULT_TELEGRAM_HANDLE);
        email = new Email(DEFAULT_EMAIL);
        taskList = new TaskList();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        studentId = personToCopy.getStudentId();
        name = personToCopy.getName();
        moduleCode = personToCopy.getModuleCode();
        phone = personToCopy.getPhone();
        telegramHandle = personToCopy.getTelegramHandle();
        email = personToCopy.getEmail();
        taskList = personToCopy.getTaskList();
    }

    /**
     * Sets the {@code StudentId} of the {@code Person} that we are building.
     */
    public PersonBuilder withStudentId(String studentId) {
        this.studentId = new StudentId(studentId);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code ModuleCode} of the {@code Person} that we are building.
     */
    public PersonBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        if (phone == null) {
            this.phone = null;
        } else {
            this.phone = new Phone(phone);
        }
        return this;
    }

    /**
     * Sets the {@code TelegramHandle} of the {@code Person} that we are building.
     */
    public PersonBuilder withTelegramHandle(String telegramHandle) {
        if (telegramHandle == null) {
            this.telegramHandle = null;
        } else {
            this.telegramHandle = new TelegramHandle(telegramHandle);
        }
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        if (email == null) {
            this.email = null;
        } else {
            this.email = new Email(email);
        }
        return this;
    }

    /**
     * Sets the {@code TaskList} of the {@code Person} that we are building.
     */
    public PersonBuilder withTaskList(String task, boolean isCompleted) {
        if (taskList == null) { // First addition to the PersonBuilder's taskList.
            taskList = new TaskList();
        }

        Task newTask = new Task(task);
        if (isCompleted) {
            newTask.markComplete();
        } else {
            newTask.markNotComplete();
        }
        taskList.addTask(newTask);

        return this;
    }

    public Person build() {
        return new Person(studentId, name, moduleCode, phone, telegramHandle, email, taskList);
    }

}
