package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.person.Email;
import seedu.address.model.person.Height;
import seedu.address.model.person.JerseyNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Weight;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String height;
    private final String jerseyNumber;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String weight;
    private final List<JsonAdaptedLineupName> lineups = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name,
                             @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email,
                             @JsonProperty("height") String height,
                             @JsonProperty("jerseyNumber") String jerseyNumber,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("lineups") List<JsonAdaptedLineupName> lineups,
                             @JsonProperty("weight") String weight
                             ) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.height = height;
        this.jerseyNumber = jerseyNumber;
        this.weight = weight;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (lineups != null) {
            this.lineups.addAll(lineups);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        height = source.getHeight().value;
        jerseyNumber = source.getJerseyNumber().value;
        weight = source.getWeight().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        lineups.addAll(source.getLineupNames().stream()
                .map(JsonAdaptedLineupName::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        final List<LineupName> personLineups = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }
        for (JsonAdaptedLineupName lineupName : lineups) {
            personLineups.add(lineupName.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        // height
        if (height == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Height.class.getSimpleName()));
        }
        if (!Height.isValidHeight(height)) {
            throw new IllegalValueException(Height.MESSAGE_CONSTRAINTS);
        }
        final Height modelHeight = new Height(height);

        // jersey
        if (jerseyNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    JerseyNumber.class.getSimpleName()));
        }
        if (!JerseyNumber.isValidJerseyNumber(jerseyNumber)) {
            throw new IllegalValueException(JerseyNumber.MESSAGE_CONSTRAINTS);
        }
        final JerseyNumber modelJerseyNumber = new JerseyNumber(jerseyNumber);

        // weight
        if (weight == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName()));
        }
        if (!Weight.isValidWeight(weight)) {
            throw new IllegalValueException(Weight.MESSAGE_CONSTRAINTS);
        }
        final Weight modelWeight = new Weight(weight);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        final Set<LineupName> modelLineups = new HashSet<>(personLineups);

        return new Person(modelName, modelPhone, modelEmail,
                modelHeight, modelJerseyNumber, modelTags, modelWeight, modelLineups);
    }

}
