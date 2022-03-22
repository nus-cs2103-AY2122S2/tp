package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.pet.Pet;

public class SimilarPets {

    public static final Pet WAFFLE = new PetBuilder().withName("Waffle").withAddress("123, Jurong West Ave 6, #08-111")
            .withOwnerName("Alice Pauline")
            .withPhone("94351253")
            .withTags("friends")
            .withDiet("cute oranges")
            .withAppointment()
            .build();
    public static final Pet ANOTHER_WAFFLE = new PetBuilder().withName("Waffle").withAddress("4th street")
            .withOwnerName("Alice Pauline")
            .withPhone("88887778")
            .withDiet("cute apples")
            .withAppointment()
            .build();

    private SimilarPets() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the similar pets.
     */
    public static AddressBook getSimilarAddressBook() {
        AddressBook ab = new AddressBook();
        for (Pet pet : getSimilarPets()) {
            ab.addPet(pet);
        }
        return ab;
    }

    public static List<Pet> getSimilarPets() {
        return new ArrayList<>(Arrays.asList(WAFFLE, ANOTHER_WAFFLE));
    }
}
