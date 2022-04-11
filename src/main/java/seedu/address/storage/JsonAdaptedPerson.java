package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.image.ImageDetails;
import seedu.address.model.image.ImageDetailsList;
import seedu.address.model.person.Address;
import seedu.address.model.person.Deadline;
import seedu.address.model.person.DeadlineList;
import seedu.address.model.person.Email;
import seedu.address.model.person.Favourite;
import seedu.address.model.person.HighImportance;
import seedu.address.model.person.Name;
import seedu.address.model.person.Notes;
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
    private final List<JsonAdaptedDeadline> deadlines = new ArrayList<>();
    private final List<String> notes = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String isFavourite;
    private final List<JsonAdaptedImageDetails> images = new ArrayList<>();
    private final String hasHighImportance;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("deadlines") List<JsonAdaptedDeadline> deadlines,
                             @JsonProperty("notes") List<String> notes,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("isFavourite") String isFavourite,
                             @JsonProperty("images") List<JsonAdaptedImageDetails> images,
                             @JsonProperty("hasHighImportance") String hasHighImportance) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (deadlines != null) {
            this.deadlines.addAll(deadlines);
        }
        if (notes != null) {
            this.notes.addAll(notes);
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.isFavourite = isFavourite;
        if (images != null) {
            this.images.addAll(images);
        }
        this.hasHighImportance = hasHighImportance;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        deadlines.addAll(source.getDeadlines().getDeadlines().stream()
                .map(JsonAdaptedDeadline::new)
                .collect(Collectors.toList()));
        notes.addAll(source.getNotes().value);
        isFavourite = source.getFavouriteStatus().value;
        hasHighImportance = source.getHighImportanceStatus().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        images.addAll(source.getImageDetailsList().getImages().stream()
                .map(JsonAdaptedImageDetails::new)
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

        final List<Deadline> personDeadlines = new ArrayList<>();
        for (JsonAdaptedDeadline deadline : deadlines) {
            personDeadlines.add(deadline.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Name.class.getSimpleName()));
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

        final Notes modelNotes = Notes.loadNotesFromList(notes);

        if (isFavourite == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Favourite.class.getSimpleName()));
        }
        if (!Favourite.isValidFavourite(isFavourite)) {
            throw new IllegalValueException(Favourite.MESSAGE_CONSTRAINTS);
        }
        final Favourite modelFavourite = Favourite.valueOf(isFavourite);

        if (hasHighImportance == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, HighImportance.class.getSimpleName()));
        }
        if (!HighImportance.isValidHighImportance(hasHighImportance)) {
            throw new IllegalValueException(HighImportance.MESSAGE_CONSTRAINTS);
        }
        final HighImportance modelHighImportance = HighImportance.valueOf(hasHighImportance);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        final DeadlineList modelDeadlines = new DeadlineList(personDeadlines);

        final List<ImageDetails> personImages = new ArrayList<>();
        for (JsonAdaptedImageDetails image : images) {
            personImages.add(image.toModelType());
        }
        final ImageDetailsList modelImages = new ImageDetailsList(personImages);

        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelDeadlines,
                modelNotes, modelTags, modelFavourite, modelHighImportance, modelImages);
    }

}
