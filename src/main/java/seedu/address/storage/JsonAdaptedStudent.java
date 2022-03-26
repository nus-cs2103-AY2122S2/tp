package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lab.Lab;
import seedu.address.model.student.Email;
import seedu.address.model.student.GithubUsername;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.Telegram;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";
    private static final Logger logger = LogsCenter.getLogger(JsonAdaptedStudent.class);

    private final String name;
    private final String email;
    private final String githubUsername;
    private final String telegram;
    private final String studentId;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedLab> labs = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("email") String email,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged, @JsonProperty("github") String githubUsername,
            @JsonProperty("telegram") String telegram, @JsonProperty("studentId") String studentId,
            @JsonProperty("labs") List<JsonAdaptedLab> labs) {
        this.name = name;
        this.email = email;
        this.githubUsername = githubUsername;
        this.telegram = telegram;
        this.studentId = studentId;

        if (tagged != null) {
            this.tagged.addAll(tagged);
        }

        if (labs != null) {
            this.labs.addAll(labs);
        }
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        email = source.getEmail().value;
        githubUsername = source.getGithubUsername().username;
        telegram = source.getTelegram().handle;
        studentId = source.getStudentId().id;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        labs.addAll(source.getLabs().asUnmodifiableObservableList().stream()
                .map(JsonAdaptedLab::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalValueException {
        final List<Tag> personTags = deserializeTags();

        final List<Lab> personLabs = deserializeLabs();

        final Name modelName = deserializeName();

        final Email modelEmail = deserializeEmail();

        final GithubUsername modelUsername = deserializeGithub();

        final Telegram modelTelegram = deserializeTelegram();

        final StudentId modelId = deserializeStudentId();

        final Set<Tag> modelTags = new HashSet<>(personTags);

        Student s = new Student(modelName, modelEmail, modelTags, modelUsername, modelTelegram, modelId);
        s.getLabs().setLabs(personLabs);

        return s;
    }

    private List<Lab> deserializeLabs() throws IllegalValueException {
        List<Lab> personLabs = new ArrayList<>();
        for (int i = 0; i < labs.size(); i++) {
            // Incase of wrong lab status format we will just add the lab with LabStatus.UNSUBMITTED
            try {
                personLabs.add(labs.get(i).toModelType());
            } catch (IllegalArgumentException e) {
                logger.info("Illegal lab attributes found when converting labs " + e);
                personLabs.add(new Lab(labs.get(i).getLabNumber()));
            }
        }
        return personLabs;
    }

    private List<Tag> deserializeTags() throws IllegalValueException {
        List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }
        return personTags;
    }

    private Name deserializeName() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(name);
    }

    private Email deserializeEmail() throws IllegalValueException {
        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(email);
    }

    private GithubUsername deserializeGithub() throws IllegalValueException {
        if (githubUsername == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GithubUsername.class.getSimpleName()));
        }
        if (!GithubUsername.isValidGithubUsername(githubUsername)) {
            throw new IllegalValueException(GithubUsername.MESSAGE_CONSTRAINTS);
        }
        return new GithubUsername(githubUsername);
    }

    private Telegram deserializeTelegram() throws IllegalValueException {
        if (telegram == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Telegram.class.getSimpleName()));
        }
        if (!Telegram.isValidTelegram(telegram)) {
            throw new IllegalValueException(Telegram.MESSAGE_CONSTRAINTS);
        }
        return new Telegram(telegram);
    }

    private StudentId deserializeStudentId() throws IllegalValueException {
        if (studentId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StudentId.class.getSimpleName()));
        }
        if (!StudentId.isValidStudentId(studentId)) {
            throw new IllegalValueException(StudentId.MESSAGE_CONSTRAINTS);
        }
        return new StudentId(studentId);
    }

}
