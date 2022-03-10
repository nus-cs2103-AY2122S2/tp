package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.pet.Address;
import seedu.address.model.pet.Name;
import seedu.address.model.pet.OwnerName;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Pet[] getSamplePets() {
        return new Pet[] {
            new Pet(new Name("Bagel"), new OwnerName("Alex Yeo"), new Phone("87438807"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Pet(new Name("Tofu"), new OwnerName("Bernice Yu"), new Phone("99272758"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Pet(new Name("PeePee"), new OwnerName("Charlotte Oliveiro"), new Phone("93210283"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Pet(new Name("Waffle"), new OwnerName("David Li"), new Phone("91031282"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Pet(new Name("Sesame"), new OwnerName("Irfan Ibrahim"), new Phone("92492021"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Pet(new Name("Avocado"), new OwnerName("Roy Balakrishnan"), new Phone("92624417"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
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
