package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Email;
import seedu.address.model.student.GitHub;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.Telegram;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";
    public static final String NO_TUTORIAL_GROUP_MESSAGE_FORMAT = "Student has no tutorial group!";

    private final String name;
    private final String telegram;
    private final String email;
    private final String gitHub;
    private final List<JsonAdaptedTutorialGroup> inTutorialGroups = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("telegram") String telegram,
                              @JsonProperty("email") String email, @JsonProperty("gitHub") String gitHub,
                              @JsonProperty("tutorialGroup") List<JsonAdaptedTutorialGroup> tutorialGroup) {
        this.name = name;
        this.telegram = telegram;
        this.email = email;
        this.gitHub = gitHub;
        if (tutorialGroup != null) {
            this.inTutorialGroups.addAll(tutorialGroup);
        }
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        email = source.getEmail().value;
        telegram = source.getTelegram().value;
        gitHub = source.getGitHub().value;
        inTutorialGroups.addAll(source.getTutorialGroups().stream()
                .map(JsonAdaptedTutorialGroup::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalValueException {
        final List<TutorialGroup> personTutorialGroups = new ArrayList<>();
        if (inTutorialGroups.isEmpty()) {
            throw new IllegalValueException(NO_TUTORIAL_GROUP_MESSAGE_FORMAT);
        }
        for (JsonAdaptedTutorialGroup tag : inTutorialGroups) {
            personTutorialGroups.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (telegram == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Telegram.class.getSimpleName()));
        }
        if (!telegram.equals("") && !Telegram.isValidTelegram(telegram)) {
            throw new IllegalValueException(Telegram.MESSAGE_CONSTRAINTS);
        }
        final Telegram modelTelegram = telegram.equals("") ? new Telegram(null) : new Telegram(telegram);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (gitHub == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, GitHub.class.getSimpleName()));
        }
        if (!gitHub.equals("") && !GitHub.isValidGitHub(gitHub)) {
            throw new IllegalValueException(GitHub.MESSAGE_CONSTRAINTS);
        }
        final GitHub modelGitHub = gitHub.equals("") ? new GitHub(null) : new GitHub(gitHub);

        final Set<TutorialGroup> modelTutorialGroups = new HashSet<>(personTutorialGroups);
        return new Student(modelName, modelTelegram, modelEmail, modelGitHub, modelTutorialGroups);
    }

}
