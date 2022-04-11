package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Group;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentNumber;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String studentNumber;
    private final String email;
    private final String group;
    private final String mod;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("student number") String studentNumber,
            @JsonProperty("email") String email, @JsonProperty("mod") String mod,
                             @JsonProperty("group") String group,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.studentNumber = studentNumber;
        this.email = email;
        this.group = group;
        this.mod = mod;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        studentNumber = source.getStudentNumber().value;
        email = source.getEmail().value;
        group = source.getGroup().value;
        mod = source.getMod().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (studentNumber == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentNumber.class.getSimpleName()));
        }
        if (!StudentNumber.isValidStudentNumber(studentNumber)) {
            throw new IllegalValueException(StudentNumber.MESSAGE_CONSTRAINTS);
        }
        final StudentNumber modelStudentNumber = new StudentNumber(studentNumber);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (mod == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Mod.class.getSimpleName()));
        }
        if (!Mod.isValidMod(mod)) {
            throw new IllegalValueException(Mod.MESSAGE_CONSTRAINTS);
        }
        final Mod modelMod = new Mod(mod);

        if (group == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Group.class.getSimpleName()));
        }
        if (!Group.isValidGroup(group)) {
            throw new IllegalValueException(Group.MESSAGE_CONSTRAINTS);
        }

        final Group modelGroupId = new Group(group);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName, modelStudentNumber, modelEmail, modelMod, modelGroupId, modelTags);

    }

}
