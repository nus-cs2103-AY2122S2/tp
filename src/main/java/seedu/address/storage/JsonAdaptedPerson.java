package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Block;
import seedu.address.model.person.CovidStatus;
import seedu.address.model.person.Email;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.MatriculationNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String block;
    private final String faculty;
    private final String phone;
    private final String email;
    private final String address;
    private final String number;
    private final String status;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name,
                             @JsonProperty("block") String block,
                             @JsonProperty("faculty") String faculty,
                             @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("number") String number, @JsonProperty("status") String status,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.block = block;
        this.faculty = faculty;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.number = number;
        this.status = status;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        block = source.getBlock().studentBlock;
        faculty = source.getFaculty().studentFaculty;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        number = source.getMatriculationNumber().value;
        status = source.getStatus().covidStatus;
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
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS_FORMAT);
        }
        if (!Name.isValidNameLength(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS_CHARACTER_LENGTH);
        }
        final Name modelName = new Name(name);

        if (block == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Block.class.getSimpleName()));
        }
        if (!Block.isValidBlock(block)) {
            throw new IllegalValueException(Block.MESSAGE_CONSTRAINTS);
        }
        final Block modelBlock = new Block(block);

        if (faculty == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Faculty.class.getSimpleName()));
        }

        if (!Faculty.isValidFaculty(faculty)) {
            throw new IllegalValueException(Faculty.MESSAGE_CONSTRAINTS);
        }
        final Faculty modelFaculty = new Faculty(faculty);

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

        if (number == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MatriculationNumber.class.getSimpleName()));
        }
        if (!MatriculationNumber.isValidMatriculationNumber(number)) {
            throw new IllegalValueException(MatriculationNumber.MESSAGE_CONSTRAINTS);
        }
        final MatriculationNumber matriculationNumber = new MatriculationNumber(number);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CovidStatus.class.getSimpleName()));
        }
        if (!CovidStatus.isValidCovidStatus(status)) {
            throw new IllegalValueException(CovidStatus.MESSAGE_CONSTRAINTS);
        }
        final CovidStatus covidStatus = new CovidStatus(status);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName, modelBlock, modelFaculty, modelPhone, modelEmail, modelAddress,
                matriculationNumber, covidStatus, modelTags);
    }

}
