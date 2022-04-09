package seedu.unite.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.unite.commons.exceptions.IllegalValueException;
import seedu.unite.model.ReadOnlyUnite;
import seedu.unite.model.Unite;
import seedu.unite.model.person.Person;
import seedu.unite.model.tag.Tag;

/**
 * An Immutable Unite that is serializable to JSON format.
 */
@JsonRootName(value = "unite")
class JsonSerializableUnite {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    private static final String MESSAGE_DUPLICATE_TAG = "Tags list contains duplicate tag(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableUnite} with the given persons.
     */
    @JsonCreator
    public JsonSerializableUnite(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyUnite} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableUnite}.
     */
    public JsonSerializableUnite(ReadOnlyUnite source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        tags.addAll(source.getTagList().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
    }

    /**
     * Converts this unite into the model's {@code Unite} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Unite toModelType() throws IllegalValueException {
        Unite unite = new Unite();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (unite.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            unite.addPerson(person);
        }

        for (JsonAdaptedTag jsonAdaptedTag : tags) {
            Tag tag = jsonAdaptedTag.toModelType();
            if (unite.hasTag(tag)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TAG);
            }
            unite.addTag(tag);
        }
        return unite;
    }

}
