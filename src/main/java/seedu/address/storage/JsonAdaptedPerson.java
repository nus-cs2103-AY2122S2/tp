package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.Task;
import seedu.address.model.person.TaskList;
import seedu.address.model.person.TelegramHandle;

import java.util.ArrayList;
import java.util.List;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String studentId;
    private final String name;
    private final String moduleCode;
    private final String phone;
    private final String telegramHandle;
    private final String email;
    private final TaskList taskList = new TaskList();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("studentId") String studentId, @JsonProperty("name") String name,
                             @JsonProperty("moduleCode") String moduleCode, @JsonProperty("phone") String phone,
                             @JsonProperty("telegramHandle") String telegramHandle,
                             @JsonProperty("email") String email, @JsonProperty("taskList") TaskList taskList) {
        this.studentId = studentId;
        this.name = name;
        this.moduleCode = moduleCode;
        this.phone = phone;
        this.telegramHandle = telegramHandle;
        this.email = email;
        if (taskList != null) {
            this.taskList.addAllTask(taskList);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        studentId = source.getStudentId().id;
        name = source.getName().fullName;
        moduleCode = source.getModuleCode().moduleCode;
        Phone currPhone = source.getPhone();
        if (currPhone == null) {
            phone = null;
        } else {
            phone = currPhone.value;
        }

        TelegramHandle currTelegramHandle = source.getTelegramHandle();
        if (currTelegramHandle == null) {
            telegramHandle = null;
        } else {
            telegramHandle = currTelegramHandle.telegramHandle;
        }

        Email currEmail = source.getEmail();
        if (currEmail == null) {
            email = null;
        } else {
            email = currEmail.value;
        }

        taskList.addAllTask(source.getTaskList());
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        if (studentId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StudentId.class.getSimpleName()));
        }
        if (!StudentId.isValidId(studentId)) {
            throw new IllegalValueException(StudentId.MESSAGE_CONSTRAINTS);
        }
        final StudentId modelStudentId = new StudentId(studentId);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (moduleCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleCode.class.getSimpleName()));
        }
        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        final ModuleCode modelModuleCode = new ModuleCode(moduleCode);

        final Phone modelPhone;
        if (phone == null) {
            modelPhone = new Phone(null);
        } else if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        } else {
            modelPhone = new Phone(phone);
        }

        final TelegramHandle modelTelegramHandle;
        if (telegramHandle == null) {
            modelTelegramHandle = new TelegramHandle(null);
        } else if (!TelegramHandle.isValidTelegramHandle(telegramHandle)) {
            throw new IllegalValueException(TelegramHandle.MESSAGE_CONSTRAINTS);
        } else {
            modelTelegramHandle = new TelegramHandle(telegramHandle);
        }

        final Email modelEmail;
        if (email == null) {
            modelEmail = new Email(null);
        } else if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        } else {
            modelEmail = new Email(email);
        }

        final TaskList modelTaskList;
        for (Task i : taskList.getTaskList()) {
            if (!Task.isValidTaskName(i.getTaskName())) {
                throw new IllegalValueException(Task.MESSAGE_CONSTRAINTS);
            }
        }
        modelTaskList = taskList;

        return new Person(modelStudentId, modelName, modelModuleCode,
                modelPhone, modelTelegramHandle, modelEmail, modelTaskList);
    }

}
