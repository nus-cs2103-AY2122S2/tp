package woofareyou.testutil;

import static woofareyou.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static woofareyou.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static woofareyou.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static woofareyou.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static woofareyou.logic.commands.CommandTestUtil.VALID_OWNER_NAME_AMY;
import static woofareyou.logic.commands.CommandTestUtil.VALID_OWNER_NAME_BOB;
import static woofareyou.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static woofareyou.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static woofareyou.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static woofareyou.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import woofareyou.model.PetBook;
import woofareyou.model.pet.Pet;

/**
 * A utility class containing a list of {@code Pet} objects to be used in tests.
 */
public class TypicalPets {

    public static final String DATE_TODAY = LocalDate.now().toString();

    public static final Pet BOBA = new PetBuilder().withName("Boba")
            .withAddress("123, Jurong West Ave 6, #08-111").withOwnerName("Alice Pauline")
            .withPhone("94351253")
            .withTags("friends").withDiet("").withAppointment().build();
    public static final Pet PIZZA = new PetBuilder().withName("Pizza")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withOwnerName("Benson Meier").withPhone("98765432")
            .withTags("owesMoney", "friends").withDiet("").withAppointment()
            .withPresentAttendanceEntry(DATE_TODAY, "09:00", "17:30").build();
    public static final Pet BAGEL = new PetBuilder().withName("Bagel").withPhone("95352563")
            .withOwnerName("Heinz Ketchup").withAddress("wall street").withDiet("").withAppointment()
            .withAbsentAttendanceEntry(DATE_TODAY).build();
    public static final Pet DANIEL = new PetBuilder().withName("Peepee").withPhone("87652533")
            .withOwnerName("Daniel Meier").withAddress("10th street").withTags("friends")
            .withDiet("").withAppointment().withPresentAttendanceEntry(DATE_TODAY).build();
    public static final Pet PANCAKE = new PetBuilder().withName("Pancake").withPhone("94822242")
            .withOwnerName("Elle Meyer").withAddress("michegan ave").withDiet("").withAppointment().build();
    public static final Pet WAFFLE = new PetBuilder().withName("Waffle").withPhone("94824272")
            .withOwnerName("Fiona Kunz").withAddress("little tokyo").withDiet("").withAppointment().build();
    public static final Pet TOFU = new PetBuilder().withName("Tofu").withPhone("94824422")
            .withOwnerName("George Best").withAddress("4th street").withDiet("").withAppointment().build();

    // Manually added
    public static final Pet HOON = new PetBuilder().withName("Hoon Meier").withPhone("84824242")
            .withOwnerName("Stefan Sim").withAddress("little india").build();
    public static final Pet IDA = new PetBuilder().withName("Ida Mueller").withPhone("84821312")
            .withOwnerName("Han Solo").withAddress("chicago ave").build();

    // Manually added - Pet's details found in {@code CommandTestUtil}
    public static final Pet AMY = new PetBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withOwnerName(VALID_OWNER_NAME_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Pet BOB = new PetBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withOwnerName(VALID_OWNER_NAME_BOB).withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPets() {} // prevents instantiation

    /**
     * Returns a {@code PetBook} with all the typical pets.
     */
    public static PetBook getTypicalPetBook() {
        PetBook ab = new PetBook();
        for (Pet pet : getTypicalPets()) {
            ab.addPet(pet);
        }
        return ab;
    }

    public static List<Pet> getTypicalPets() {
        return new ArrayList<>(Arrays.asList(BOBA, PIZZA, BAGEL, DANIEL, PANCAKE, WAFFLE, TOFU));
    }
}
