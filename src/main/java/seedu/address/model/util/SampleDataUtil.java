package seedu.address.model.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.HireLah;
import seedu.address.model.ReadOnlyHireLah;
import seedu.address.model.applicant.Address;
import seedu.address.model.applicant.Age;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Email;
import seedu.address.model.applicant.Gender;
import seedu.address.model.applicant.Name;
import seedu.address.model.applicant.Phone;
import seedu.address.model.interview.Interview;
import seedu.address.model.position.Description;
import seedu.address.model.position.Position;
import seedu.address.model.position.PositionName;
import seedu.address.model.position.PositionOffers;
import seedu.address.model.position.PositionOpenings;
import seedu.address.model.position.Requirement;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code HireLah} with sample data.
 */
public class SampleDataUtil {
    public static Applicant[] getSampleApplicants() {
        return new Applicant[]{
            new Applicant(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Age("21"), new Address("Blk 30 Geylang Street 29, #06-40"), new Gender("M"),
                    getTagSet(">=5 years of exp", "1st Class Honours", "Computer Science")),
            new Applicant(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Age("22"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Gender("F"),
                    getTagSet("Marketing", "Referred by manager")),
            new Applicant(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Age("23"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Gender("F"),
                    getTagSet("Ex-Google")),
            new Applicant(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Age("24"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Gender("M"),
                    getTagSet("NUS Alumni", "National Athlete")),
            new Applicant(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Age("25"), new Address("Blk 47 Tampines Street 20, #17-35"), new Gender("M"),
                    getTagSet("Ex Senior Engineer @ Amazon")),
            new Applicant(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Age("26"), new Address("Blk 45 Aljunied Street 85, #11-31"), new Gender("M"),
                    getTagSet("ICPC Winner"))
        };
    }

    public static Position[] getSamplePositions() {
        PositionOffers samplePos = new PositionOffers();
        samplePos.increment();
        return new Position[]{
            new Position(new PositionName("Senior Software Developer"),
                    new Description("The highest paying job in the company. More than 5 years experience."),
                    new PositionOpenings("3"), getRequirementSet("Java", "C++")),
            new Position(new PositionName("IT Intern"),
                    new Description("Work on internal tools. Must be willing to learn."),
                    new PositionOpenings("1"),
                    getRequirementSet("Source Academy", "C")),
            new Position(new PositionName("Janitor"),
                    new Description("Arguably the most important job"),
                    new PositionOpenings("2"),
                    getRequirementSet("Sweep Floor", "Wipe Window", "Wash Toilet")),
            new Position(new PositionName("Admin Officer"),
                    new Description("Degree or Postgraduate holder with Major in Information Technology, "
                            + "Computer Science, or other similar areas, and a cumulative GPA of 3.5 and above."),
                    new PositionOpenings("2"), samplePos, getRequirementSet("Hardworking", "Good with people"))
        };
    }

    public static Interview[] getSampleInterviews() {
        Applicant[] samplePersons = getSampleApplicants();
        Position[] samplePositions = getSamplePositions();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return new Interview[]{
            new Interview(samplePersons[0],
                    LocalDateTime.parse("2021-01-01 12:00", formatter),
                    samplePositions[0]),
            new Interview(samplePersons[1],
                    LocalDateTime.parse("2021-05-05 05:00", formatter),
                    samplePositions[1]),
            new Interview(samplePersons[2],
                    LocalDateTime.parse("2021-09-09 18:00", formatter),
                    samplePositions[2]),
            new Interview(samplePersons[3],
                    LocalDateTime.parse("2021-12-20 19:00", formatter),
                    samplePositions[3])
        };
    }

    public static ReadOnlyHireLah getSampleHireLah() {
        HireLah sampleAb = new HireLah();
        for (Applicant sampleApplicant : getSampleApplicants()) {
            sampleAb.addApplicant(sampleApplicant);
        }
        for (Position samplePosition : getSamplePositions()) {
            sampleAb.addPosition(samplePosition);
        }
        for (Interview sampleInterview : getSampleInterviews()) {
            sampleAb.addInterview(sampleInterview);
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

    /**
     * Returns a requirement set containing the list of strings given.
     */
    public static Set<Requirement> getRequirementSet(String... strings) {
        return Arrays.stream(strings)
                .map(Requirement::new)
                .collect(Collectors.toSet());
    }
}
