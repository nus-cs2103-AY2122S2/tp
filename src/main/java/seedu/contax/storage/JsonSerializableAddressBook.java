package seedu.contax.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.contax.commons.core.LogsCenter;
import seedu.contax.commons.exceptions.IllegalValueException;
import seedu.contax.model.AddressBook;
import seedu.contax.model.ReadOnlyAddressBook;
import seedu.contax.model.person.Person;
import seedu.contax.model.tag.Tag;

/**
 * Represents an Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_TAG = "Tag list contains duplicate tag(s).";
    private static final Logger logger = LogsCenter.getLogger(JsonSerializableAddressBook.class);

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        tags.addAll(source.getTagList().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedTag jsonAdaptedTag: tags) {
            try {
                Tag tag = jsonAdaptedTag.toModelType();
                if (addressBook.hasTag(tag)) {
                    throw new IllegalValueException(MESSAGE_DUPLICATE_TAG);
                }
                addressBook.addTag(tag);
            } catch (IllegalValueException e) {
                logger.info("Skipped Tag: " + jsonAdaptedTag.getTagNameString());
            }
        }

        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            try {
                Person person = jsonAdaptedPerson.toModelType();
                if (addressBook.hasPerson(person)) {
                    throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
                }
                // Load tags that were not added from tag list
                addMissingTags(person, addressBook);
                addressBook.addPerson(person);
            } catch (IllegalValueException e) {
                logger.info("Skipped Person: " + jsonAdaptedPerson.getPersonNameString());
            }
        }
        return addressBook;
    }

    public void addMissingTags(Person person, AddressBook addressbook) {
        for (Tag personTag : person.getTags()) {
            boolean isTagMissing = !addressbook.hasTag(personTag);
            if (isTagMissing) {
                addressbook.addTag(new Tag(personTag.getTagNameString()));
            }
        }
    }
}
