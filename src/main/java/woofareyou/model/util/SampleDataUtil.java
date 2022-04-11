package woofareyou.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import woofareyou.model.PetBook;
import woofareyou.model.ReadOnlyPetBook;
import woofareyou.model.pet.Address;
import woofareyou.model.pet.Appointment;
import woofareyou.model.pet.AttendanceHashMap;
import woofareyou.model.pet.Diet;
import woofareyou.model.pet.Name;
import woofareyou.model.pet.OwnerName;
import woofareyou.model.pet.Pet;
import woofareyou.model.pet.Phone;
import woofareyou.model.tag.Tag;

/**
 * Contains utility methods for populating {@code PetBook} with sample data.
 */
public class SampleDataUtil {

    public static final Diet EMPTY_DIET = new Diet("");
    public static final Appointment EMPTY_APPOINTMENT = new Appointment();

    public static Pet[] getSamplePets() {
        return new Pet[] {
            new Pet(new Name("Bagel"), new OwnerName("Alex Yeo"), new Phone("87438807"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("Japanese Spitz"), EMPTY_DIET, EMPTY_APPOINTMENT, new AttendanceHashMap()),
            new Pet(new Name("Tofu"), new OwnerName("Bernice Yu"), new Phone("99272758"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("Golden Retriever"), EMPTY_DIET, EMPTY_APPOINTMENT, new AttendanceHashMap()),
            new Pet(new Name("PeePee"), new OwnerName("Charlotte Oliveiro"), new Phone("93210283"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("Bulldog"), EMPTY_DIET, EMPTY_APPOINTMENT, new AttendanceHashMap()),
            new Pet(new Name("Waffle"), new OwnerName("David Li"), new Phone("91031282"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet("British Shorthair"), EMPTY_DIET, EMPTY_APPOINTMENT, new AttendanceHashMap()),
            new Pet(new Name("Sesame"), new OwnerName("Irfan Ibrahim"), new Phone("92492021"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("Poodle"), EMPTY_DIET, EMPTY_APPOINTMENT, new AttendanceHashMap()),
            new Pet(new Name("Avocado"), new OwnerName("Roy Balakrishnan"), new Phone("92624417"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("Dachshund"), EMPTY_DIET, EMPTY_APPOINTMENT, new AttendanceHashMap())
        };
    }

    public static ReadOnlyPetBook getSamplePetBook() {
        PetBook sampleAb = new PetBook();
        for (Pet samplePet : getSamplePets()) {
            sampleAb.addPet(samplePet);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
