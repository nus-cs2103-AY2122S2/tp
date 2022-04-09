package woofareyou.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import woofareyou.commons.exceptions.IllegalValueException;
import woofareyou.model.PetBook;
import woofareyou.model.ReadOnlyPetBook;
import woofareyou.model.pet.Pet;

/**
 * An Immutable PetBook that is serializable to JSON format.
 */
@JsonRootName(value = "petbook")
class JsonSerializablePetBook {

    public static final String MESSAGE_DUPLICATE_PET = "Pets list contains duplicate pet(s).";

    private final List<JsonAdaptedPet> pets = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePetBook} with the given pets.
     */
    @JsonCreator
    public JsonSerializablePetBook(@JsonProperty("pets") List<JsonAdaptedPet> pets) {
        this.pets.addAll(pets);
    }

    /**
     * Converts a given {@code ReadOnlyPetBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePetBook}.
     */
    public JsonSerializablePetBook(ReadOnlyPetBook source) {
        pets.addAll(source.getPetList().stream().map(JsonAdaptedPet::new).collect(Collectors.toList()));
    }

    /**
     * Converts this pet book into the model's {@code PetBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PetBook toModelType() throws IllegalValueException {
        PetBook petBook = new PetBook();
        for (JsonAdaptedPet jsonAdaptedPet : pets) {
            Pet pet = jsonAdaptedPet.toModelType();
            if (petBook.hasPet(pet)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PET);
            }
            petBook.addPet(pet);
        }
        return petBook;
    }

}
