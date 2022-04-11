package woofareyou.testutil;

import woofareyou.model.PetBook;
import woofareyou.model.pet.Pet;

/**
 * A utility class to help with building PetBook objects.
 * Example usage: <br>
 *     {@code PetBook ab = new PetBookBuilder().withPet("John", "Doe").build();}
 */
public class PetBookBuilder {

    private PetBook petBook;

    public PetBookBuilder() {
        petBook = new PetBook();
    }

    public PetBookBuilder(PetBook petBook) {
        this.petBook = petBook;
    }

    /**
     * Adds a new {@code Pet} to the {@code PetBook} that we are building.
     */
    public PetBookBuilder withPet(Pet pet) {
        petBook.addPet(pet);
        return this;
    }

    public PetBook build() {
        return petBook;
    }
}
