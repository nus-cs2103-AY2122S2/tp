package woofareyou.testutil;

import woofareyou.model.PetBook;
import woofareyou.model.pet.Pet;

/**
 * A utility class to help with building AddressBook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPet("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private PetBook petBook;

    public AddressBookBuilder() {
        petBook = new PetBook();
    }

    public AddressBookBuilder(PetBook petBook) {
        this.petBook = petBook;
    }

    /**
     * Adds a new {@code Pet} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPet(Pet pet) {
        petBook.addPet(pet);
        return this;
    }

    public PetBook build() {
        return petBook;
    }
}
