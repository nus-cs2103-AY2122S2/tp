package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.patron.Email;
import seedu.address.model.patron.Id;
import seedu.address.model.patron.Name;
import seedu.address.model.patron.Patron;
import seedu.address.model.patron.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Patron}.
 */
class JsonAdaptedPatron {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Patron's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String id;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPatron} with the given patron details.
     */
    @JsonCreator
    public JsonAdaptedPatron(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("id") String id,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.id = id;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Patron} into this class for Jackson use.
     */
    public JsonAdaptedPatron(Patron source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        id = source.getId().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted patron object into the model's {@code Patron} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted patron.
     */
    public Patron toModelType() throws IllegalValueException {
        final List<Tag> patronTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            patronTags.add(tag.toModelType());
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

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName()));
        }
        if (!Id.isValidId(id)) {
            throw new IllegalValueException(Id.MESSAGE_CONSTRAINTS);
        }
        final Id modelAddress = new Id(id);

        final Set<Tag> modelTags = new HashSet<>(patronTags);
        return new Patron(modelName, modelPhone, modelEmail, modelAddress, modelTags);
    }

}
