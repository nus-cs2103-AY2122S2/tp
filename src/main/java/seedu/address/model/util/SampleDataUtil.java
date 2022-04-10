package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.MedBook;
import seedu.address.model.ReadOnlyMedBook;
import seedu.address.model.contact.Address;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;
import seedu.address.model.tag.Tag;
import seedu.address.model.testresult.MedicalTest;
import seedu.address.model.testresult.Result;
import seedu.address.model.testresult.TestDate;
import seedu.address.model.testresult.TestResult;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Patient[] getSamplePatients() {
        return new Patient[] {
            new Patient(new Nric("S1234567L"), new Name("Alex Yeoh"),
                    new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("Cancer")),
            new Patient(new Nric("S1234568L"), new Name("Bernice Yu"),
                    new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("ICU", "Surgery")),
            new Patient(new Nric("S1234569L"), new Name("Charlotte Oliveiro"),
                    new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("Diabetic", "Medication")),
            new Patient(new Nric("S1234560L"), new Name("David Li"),
                    new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("Critical", "ICU")),
            new Patient(new Nric("S1234561L"), new Name("Irfan Ibrahim"), new Phone("92492021"),
                    new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("Room2A")),
            new Patient(new Nric("S1234562L"), new Name("Roy Balakrishnan"), new Phone("92624417"),
                    new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("Room3B"))
        };
    }

    public static Contact[] getSampleContacts() {
        return new Contact[] {
            // Patient - S1234567L
            new Contact(new Nric("S1234567L"), new Name("Abigail Yeh Xi Ling"), new Phone("64976804"),
                    new Email("brad66@yahoo.com"),
                    new Address("Blk 352 Hoppe Alley Way 02 SINGAPORE 214180"),
                    getTagSet("Primary", "Mother")),
            new Contact(new Nric("S1234567L"), new Name("Bridget Yeh Xi Ling"), new Phone("90596224"),
                    new Email("qbrekke@hotmail.com"),
                    new Address("70 Jalan Barton Hill SINGAPORE 508372"),
                    getTagSet("Sister")),
            new Contact(new Nric("S1234567L"), new Name("Cindy Zhuo Shu Hui"), new Phone("91349569"),
                    new Email("finn.mclaughlin@gmail.com"),
                    new Address("Blk 429D Jalan Kuphal Lane 01 22 SINGAPORE 864493"),
                    getTagSet("Secondary", "Wife")),
            new Contact(new Nric("S1234567L"), new Name("Darice Ye Hui Wen"), new Phone("62077189"),
                    new Email("cedrick85@hotmail.com"),
                    new Address("Blk 163B Kunze Link Bridge 01 44 SINGAPORE 487813"),
                    getTagSet("Friend")),
            // Patient - S1234568L
            new Contact(new Nric("S1234568L"), new Name("Aaron Fan Zheng Min"), new Phone("68342163"),
                    new Email("enrique.schmeler@oconnell.org"),
                    new Address("1 Grange Road 01-00 Orchard Building"),
                    getTagSet("Primary", "Dad")),
            new Contact(new Nric("S1234568L"), new Name("Bryan Fan Ming De "), new Phone("90596224"),
                    new Email("ilene.graham@hotmail.com"),
                    new Address("71 Jalan Barton Hill SINGAPORE 508372"),
                    getTagSet("Brother")),
            new Contact(new Nric("S1234568L"), new Name("Cindy Tan Shu Huan"), new Phone("91349569"),
                    new Email("finn.mclaughlin@gmail.com"),
                    new Address("Blk 429D Jalan Kuphal Lane 01 22 SINGAPORE 864493"),
                    getTagSet("Secondary", "Wife")),
            new Contact(new Nric("S1234568L"), new Name("Darice Lim Hui Wan"), new Phone("63823030"),
                    new Email("cedrick85@hotmail.com"),
                    new Address("12 Tampines Rd"),
                    getTagSet("Friend")),
            // Patient - S1234569L
            new Contact(new Nric("S1234569L"), new Name("Abigail Yeh Xi Ling"), new Phone("64976804"),
                        new Email("brad66@yahoo.com"),
                        new Address("Blk 352 Hoppe Alley Way 02 SINGAPORE 214180"),
                    getTagSet("Primary", "Mother")),
            new Contact(new Nric("S1234569L"), new Name("Bridget Yeh Xi Ling"), new Phone("90596224"),
                    new Email("qbrekke@hotmail.com"),
                    new Address("70 Jalan Barton Hill SINGAPORE 508372"),
                    getTagSet("Sister")),
            new Contact(new Nric("S1234569L"), new Name("Cindy Zhuo Shu Hui"), new Phone("91349569"),
                    new Email("finn.mclaughlin@gmail.com"),
                    new Address("Blk 429D Jalan Kuphal Lane 01 22 SINGAPORE 864493"),
                    getTagSet("Secondary", "Wife")),
            new Contact(new Nric("S1234569L"), new Name("Darice Ye Hui Wen"), new Phone("62077189"),
                    new Email("cedrick85@hotmail.com"),
                    new Address("Blk 163B Kunze Link Bridge 01 44 SINGAPORE 487813"),
                    getTagSet("Friend")),
            // Patient - S1234560L
            new Contact(new Nric("S1234560L"), new Name("Abigail Yeh Xi Ling"), new Phone("64976804"),
                        new Email("brad66@yahoo.com"),
                        new Address("Blk 352 Hoppe Alley Way 02 SINGAPORE 214180"),
                    getTagSet("Primary", "Mother")),
            new Contact(new Nric("S1234560L"), new Name("Bridget Yeh Xi Ling"), new Phone("90596224"),
                    new Email("qbrekke@hotmail.com"),
                    new Address("70 Jalan Barton Hill SINGAPORE 508372"),
                    getTagSet("Sister")),
            new Contact(new Nric("S1234560L"), new Name("Cindy Zhuo Shu Hui"), new Phone("91349569"),
                    new Email("finn.mclaughlin@gmail.com"),
                    new Address("Blk 429D Jalan Kuphal Lane 01 22 SINGAPORE 864493"),
                    getTagSet("Secondary", "Wife")),
            new Contact(new Nric("S1234560L"), new Name("Darice Ye Hui Wen"), new Phone("62077189"),
                    new Email("cedrick85@hotmail.com"),
                    new Address("Blk 163B Kunze Link Bridge 01 44 SINGAPORE 487813"),
                    getTagSet("Friend")),
            // Patient - S1234561L
            new Contact(new Nric("S1234561L"), new Name("Abigail Yeh Xi Ling"), new Phone("64976804"),
                        new Email("brad66@yahoo.com"),
                        new Address("Blk 352 Hoppe Alley Way 02 SINGAPORE 214180"),
                    getTagSet("Primary", "Mother")),
            new Contact(new Nric("S1234561L"), new Name("Bridget Yeh Xi Ling"), new Phone("90596224"),
                    new Email("qbrekke@hotmail.com"),
                    new Address("70 Jalan Barton Hill SINGAPORE 508372"),
                    getTagSet("Sister")),
            new Contact(new Nric("S1234561L"), new Name("Cindy Zhuo Shu Hui"), new Phone("91349569"),
                    new Email("finn.mclaughlin@gmail.com"),
                    new Address("Blk 429D Jalan Kuphal Lane 01 22 SINGAPORE 864493"),
                    getTagSet("Secondary", "Wife")),
            new Contact(new Nric("S1234561L"), new Name("Darice Ye Hui Wen"), new Phone("62077189"),
                    new Email("cedrick85@hotmail.com"),
                    new Address("Blk 163B Kunze Link Bridge 01 44 SINGAPORE 487813"),
                    getTagSet("Friend")),
            // Patient - S1234562L
            new Contact(new Nric("S1234562L"), new Name("Abigail Yeh Xi Ling"), new Phone("64976804"),
                        new Email("brad66@yahoo.com"),
                        new Address("Blk 352 Hoppe Alley Way 02 SINGAPORE 214180"),
                    getTagSet("Primary", "Mother")),
            new Contact(new Nric("S1234562L"), new Name("Bridget Yeh Xi Ling"), new Phone("90596224"),
                    new Email("qbrekke@hotmail.com"),
                    new Address("70 Jalan Barton Hill SINGAPORE 508372"),
                    getTagSet("Sister")),
            new Contact(new Nric("S1234562L"), new Name("Cindy Zhuo Shu Hui"), new Phone("91349569"),
                    new Email("finn.mclaughlin@gmail.com"),
                    new Address("Blk 429D Jalan Kuphal Lane 01 22 SINGAPORE 864493"),
                    getTagSet("Secondary", "Wife")),
            new Contact(new Nric("S1234562L"), new Name("Darice Ye Hui Wen"), new Phone("62077189"),
                    new Email("cedrick85@hotmail.com"),
                    new Address("Blk 163B Kunze Link Bridge 01 44 SINGAPORE 487813"),
                    getTagSet("Friend")),
        };
    }

    public static TestResult[] getSampleTestResults() {
        return new TestResult[] {
            new TestResult(new Nric("S1234567L"), new TestDate("2020-03-01"), new MedicalTest("X-Ray Scan"),
                    new Result("Fractured wrist")),
            new TestResult(new Nric("S1234568L"), new TestDate("2022-12-01"), new MedicalTest("CT Scan"),
                    new Result("Stroke")),
            new TestResult(new Nric("S1234569L"), new TestDate("2021-06-09"), new MedicalTest("Blood Pressure"),
                    new Result("Systolic: 154; Diastolic: 99"))
        };
    }

    public static ReadOnlyMedBook getSampleMedBook() {
        MedBook sampleAb = new MedBook();
        for (Patient samplePatient : getSamplePatients()) {
            sampleAb.addPatient(samplePatient);
        }
        for (Contact sampleContact : getSampleContacts()) {
            sampleAb.addContact(sampleContact);
        }
        for (TestResult sampleTestResult : getSampleTestResults()) {
            sampleAb.addTestResult(sampleTestResult);
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
