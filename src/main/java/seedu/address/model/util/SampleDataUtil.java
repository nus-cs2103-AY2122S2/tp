package seedu.address.model.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.image.ImageDetails;
import seedu.address.model.image.ImageDetailsList;
import seedu.address.model.person.Address;
import seedu.address.model.person.Deadline;
import seedu.address.model.person.DeadlineList;
import seedu.address.model.person.Email;
import seedu.address.model.person.Favourite;
import seedu.address.model.person.HighImportance;
import seedu.address.model.person.Name;
import seedu.address.model.person.Notes;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new DeadlineList(), Notes.getNewNotes(),
                getTagSet("friends"), Favourite.NOT_FAVOURITE, HighImportance.HIGH_IMPORTANCE,
                new ImageDetailsList()),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new DeadlineList(),
                Notes.getNewNotes(), getTagSet("colleagues", "friends"), Favourite.NOT_FAVOURITE,
                HighImportance.NOT_HIGH_IMPORTANCE, new ImageDetailsList()),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new DeadlineList(), Notes.getNewNotes(),
                getTagSet("neighbours"), Favourite.NOT_FAVOURITE, HighImportance.NOT_HIGH_IMPORTANCE,
                new ImageDetailsList()),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new DeadlineList(),
                Notes.getNewNotes(), getTagSet("family"), Favourite.NOT_FAVOURITE,
                HighImportance.NOT_HIGH_IMPORTANCE, new ImageDetailsList()),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new DeadlineList(), Notes.getNewNotes(),
                getTagSet("classmates"), Favourite.NOT_FAVOURITE, HighImportance.NOT_HIGH_IMPORTANCE,
                new ImageDetailsList()),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new DeadlineList(), Notes.getNewNotes(),
                getTagSet("colleagues"), Favourite.NOT_FAVOURITE, HighImportance.NOT_HIGH_IMPORTANCE,
                new ImageDetailsList())
        };
    }

    public static Tag[] getSampleTags() {
        return new Tag[]{
            new Tag("friends"),
            new Tag("colleagues"),
            new Tag("neighbours"),
            new Tag("classmates"),
            new Tag("family")
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }

        for (Tag sampleTag : getSampleTags()) {
            sampleAb.addTag(sampleTag);
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

    public static DeadlineList getDeadlineList(String[] strings) {
        ArrayList<Deadline> deadlines = new ArrayList<>();
        for (String string : strings) {
            String trimmedDeadline = string.trim();
            StringBuilder originalInput = new StringBuilder(trimmedDeadline);
            originalInput.reverse();
            String[] reversedAndSplit = originalInput.toString().split("\\s+", 2);
            String description = new StringBuilder(reversedAndSplit[1]).reverse().toString();
            String date = new StringBuilder(reversedAndSplit[0]).reverse().toString();
            deadlines.add(new Deadline(description, date));
        }
        return new DeadlineList(deadlines);
    }

    /**
     * Returns an image details list containing the list of image details given
     */
    public static ImageDetailsList getImageDetailsList(String... imageDetails) {
        return new ImageDetailsList(Arrays.stream(imageDetails)
                .map(File::new)
                .map(ImageDetails::new)
                .collect(Collectors.toList()));
    }

}
