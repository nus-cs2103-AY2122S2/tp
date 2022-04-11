package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.MedBook;
import seedu.address.model.ReadOnlyMedBook;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.ConsultationDiagnosis;
import seedu.address.model.consultation.ConsultationFee;
import seedu.address.model.consultation.ConsultationNotes;
import seedu.address.model.consultation.Date;
import seedu.address.model.consultation.Time;
import seedu.address.model.contact.Address;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;
import seedu.address.model.medical.Age;
import seedu.address.model.medical.BloodType;
import seedu.address.model.medical.Ethnicity;
import seedu.address.model.medical.FamilyHistory;
import seedu.address.model.medical.Gender;
import seedu.address.model.medical.Height;
import seedu.address.model.medical.Illnesses;
import seedu.address.model.medical.ImmunizationHistory;
import seedu.address.model.medical.Medical;
import seedu.address.model.medical.Medication;
import seedu.address.model.medical.Surgeries;
import seedu.address.model.medical.Weight;
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

    public static Medical[] getSampleMedicals() {
        return new Medical[] {
            new Medical(new Nric("S1234567L"), new Age("25"), new BloodType("O"),
                    new Medication("Paracetamol 500mg twice a day; Atarvastatin 20mg once a day"),
                    new Height("185 cm"), new Weight("70 cm"),
                    new Illnesses("Mild fever"), new Surgeries("Appendectomy"),
                    new FamilyHistory("Has family history of high blood pressure"),
                    new ImmunizationHistory("MMR; 6 in 1; Hepatitis B"),
                    new Gender("Male"), new Ethnicity("Chinese")),
            new Medical(new Nric("S1234568L"), new Age("65"), new BloodType("AB"),
                    new Medication("Lisinopril 10 mg once a day"),
                    new Height("185 cm"), new Weight("70 cm"),
                    new Illnesses("Mononucleosis"), new Surgeries("NIL"),
                    new FamilyHistory("NIL"), new ImmunizationHistory("NIL"),
                    new Gender("Male"), new Ethnicity("Chinese")),
            new Medical(new Nric("S1234569L"), new Age("37"), new BloodType("O+"),
                    new Medication("NIL"),
                    new Height("165 cm"), new Weight("65 cm"),
                    new Illnesses("Stomach Aches"), new Surgeries("NIL"),
                    new FamilyHistory("Albinism"), new ImmunizationHistory("NIL"),
                    new Gender("Non-binary"), new Ethnicity("Caucasian")),
            new Medical(new Nric("S1234560L"), new Age("26"), new BloodType("B"),
                    new Medication("Aripiprazole 2 mg once a day"),
                    new Height("168 cm"), new Weight("70 cm"),
                    new Illnesses("Mild fever"), new Surgeries("NIL"),
                    new FamilyHistory("NIL"), new ImmunizationHistory("NIL"),
                    new Gender("Female"), new Ethnicity("Pacific Islander")),
            new Medical(new Nric("S1234562L"), new Age("16"), new BloodType("A"),
                    new Medication("Paracetamol 250 mg twice a day"),
                    new Height("170 cm"), new Weight("65 cm"),
                    new Illnesses("Mild fever"), new Surgeries("NIL"),
                    new FamilyHistory("NIL"), new ImmunizationHistory("NIL"),
                    new Gender("Male"), new Ethnicity("Chinese"))
        };
    }

    public static TestResult[] getSampleTestResults() {
        return new TestResult[] {
            new TestResult(new Nric("S1234567L"), new TestDate("2020-03-01"), new MedicalTest("X-Ray Scan"),
                    new Result("Fractured wrist")),
            new TestResult(new Nric("S1234568L"), new TestDate("2022-12-01"), new MedicalTest("CT Scan"),
                    new Result("Stroke")),
            new TestResult(new Nric("S1234569L"), new TestDate("2021-06-09"), new MedicalTest("Blood Pressure"),
                    new Result("Systolic: 154; Diastolic: 99")),
            new TestResult(new Nric("S1234560L"), new TestDate("2022-07-11"), new MedicalTest("MRI"),
                    new Result("Extensive tissue loss in the right cerebral hemisphere"))
        };
    }

    public static Consultation[] getSampleConsultations() {
        return new Consultation[]{
            new Consultation(new Nric("S1234567L"), new Date("2020-03-01"), new Time("19-00"),
                    new ConsultationDiagnosis("Patient is suffering from Upper respiratory infection. "
                            + "Is to have plenty of rest and take cough drops."),
                    new ConsultationFee("56"), new ConsultationNotes("NIL")),
            new Consultation(new Nric("S1234561L"), new Date("2020-04-12"), new Time("12-00"),
                    new ConsultationDiagnosis("Patient has caught H1N1 flu. "
                            + "Is to take antibiotics to completion."),
                    new ConsultationFee("50.50"), new ConsultationNotes("NIL")),
            new Consultation(new Nric("S1234568L"), new Date("2020-03-10"), new Time("10-00"),
                    new ConsultationDiagnosis("Patient is suffering from Allergic reaction. "
                            + "A shot of adrenaline has been prescribed, along with anti-allergic medication."),
                    new ConsultationFee("46.50"), new ConsultationNotes("NIL")),
            new Consultation(new Nric("S1234569L"), new Date("2020-08-21"), new Time("09-00"),
                    new ConsultationDiagnosis("Patient is suffering from Upper respiratory infection. "
                            + "Is to have plenty of rest and take cough drops."),
                    new ConsultationFee("20"), new ConsultationNotes("NIL")),
            new Consultation(new Nric("S1234562L"), new Date("2020-01-10"), new Time("13-00"),
                    new ConsultationDiagnosis("Patient is suffering from Outer Ear Infection. "
                            + "Is to have plenty of rest and apply ear antibiotics."),
                    new ConsultationFee("96"), new ConsultationNotes("NIL")),
        };
    }

    public static ReadOnlyMedBook getSampleMedBook() {
        MedBook sampleMedBook = new MedBook();
        for (Patient samplePatient : getSamplePatients()) {
            sampleMedBook.addPatient(samplePatient);
        }
        for (Contact sampleContact : getSampleContacts()) {
            sampleMedBook.addContact(sampleContact);
        }
        for (TestResult sampleTestResult : getSampleTestResults()) {
            sampleMedBook.addTestResult(sampleTestResult);
        }
        for (Consultation sampleConsultation : getSampleConsultations()) {
            sampleMedBook.addConsultation(sampleConsultation);
        }
        for (Medical sampleMedical: getSampleMedicals()) {
            sampleMedBook.addMedical(sampleMedical);
        }
        return sampleMedBook;
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
