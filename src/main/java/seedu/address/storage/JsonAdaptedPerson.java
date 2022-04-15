package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.name.Name;
import seedu.address.model.note.Note;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedNote> strengths = new ArrayList<>();
    private final List<JsonAdaptedNote> weaknesses = new ArrayList<>();
    private final List<JsonAdaptedNote> misc = new ArrayList<>();
    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("strengths") List<JsonAdaptedNote> strengths,
            @JsonProperty("weaknesses") List<JsonAdaptedNote> weaknesses,
            @JsonProperty("misc") List<JsonAdaptedNote> misc) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (strengths != null) {
            this.strengths.addAll(strengths);
        }
        if (weaknesses != null) {
            this.weaknesses.addAll(weaknesses);
        }
        if (misc != null) {
            this.misc.addAll(misc);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        strengths.addAll(source.getStrengths().stream()
                .map(JsonAdaptedNote::new)
                .collect(Collectors.toList()));
        weaknesses.addAll(source.getWeaknesses().stream()
                .map(JsonAdaptedNote::new)
                .collect(Collectors.toList()));
        misc.addAll(source.getMiscellaneous().stream()
                .map(JsonAdaptedNote::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        final List<Note> personStrengths = new ArrayList<>();
        final List<Note> personWeaknesses = new ArrayList<>();
        final List<Note> personMisc = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }
        for (JsonAdaptedNote note : strengths) {
            personStrengths.add(note.toModelType());
        }
        for (JsonAdaptedNote note : weaknesses) {
            personWeaknesses.add(note.toModelType());
        }
        for (JsonAdaptedNote note : misc) {
            personMisc.add(note.toModelType());
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

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        final List<Note> modelStrengths = new ArrayList<>(personStrengths);
        final List<Note> modelWeaknesses = new ArrayList<>(personWeaknesses);
        final List<Note> modelMisc = new ArrayList<>(personMisc);
        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelTags, modelStrengths, modelWeaknesses,
                modelMisc);
    }

}
