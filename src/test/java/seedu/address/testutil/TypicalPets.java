package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.pet.Pet;

import static seedu.address.logic.commands.CommandTestUtil.*;

/**
 * A utility class containing a list of {@code Pet} objects to be used in tests.
 */
public class TypicalPets {

    public static final Pet ALICE = new PetBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withOwnerName("Alicia Siew")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Pet BENSON = new PetBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withOwnerName("John Again").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Pet CARL = new PetBuilder().withName("Carl Kurz").withPhone("95352563")
            .withOwnerName("Heinz Ketchup").withAddress("wall street").build();
    public static final Pet DANIEL = new PetBuilder().withName("Daniel Meier").withPhone("87652533")
            .withOwnerName("Cornelia Li").withAddress("10th street").withTags("friends").build();
    public static final Pet ELLE = new PetBuilder().withName("Elle Meyer").withPhone("9482224")
            .withOwnerName("Werner Wright").withAddress("michegan ave").build();
    public static final Pet FIONA = new PetBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withOwnerName("Lydia Poon").withAddress("little tokyo").build();
    public static final Pet GEORGE = new PetBuilder().withName("George Best").withPhone("9482442")
            .withOwnerName("Anna Lee").withAddress("4th street").build();

    // Manually added
    public static final Pet HOON = new PetBuilder().withName("Hoon Meier").withPhone("8482424")
            .withOwnerName("Stefan Sim").withAddress("little india").build();
    public static final Pet IDA = new PetBuilder().withName("Ida Mueller").withPhone("8482131")
            .withOwnerName("Han Solo").withAddress("chicago ave").build();

    // Manually added - Pet's details found in {@code CommandTestUtil}
    public static final Pet AMY = new PetBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withOwnerName(VALID_OWNERNAME_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Pet BOB = new PetBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withOwnerName(VALID_OWNERNAME_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPets() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical pets.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Pet pet : getTypicalPets()) {
            ab.addPet(pet);
        }
        return ab;
    }

    public static List<Pet> getTypicalPets() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
