package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.lineup.LineupPlayersList;
import seedu.address.model.person.Email;
import seedu.address.model.person.Height;
import seedu.address.model.person.JerseyNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Weight;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleDateTime;
import seedu.address.model.schedule.ScheduleDescription;
import seedu.address.model.schedule.ScheduleName;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Height("172"), new JerseyNumber("23"),
                getTagSet("PG", "SG"), new Weight("60")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Height("181"), new JerseyNumber("11"),
                getTagSet("SG"), new Weight("85")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Height("175"), new JerseyNumber("0"),
                getTagSet("PG", "SG"), new Weight("60")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Height("190"), new JerseyNumber("15"),
                getTagSet("PF", "C"), new Weight("90")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Height("213"), new JerseyNumber("1"),
                getTagSet("C"), new Weight("110")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Height("188"), new JerseyNumber("3"),
                getTagSet("SF", "PF"), new Weight("90")),
            new Person(new Name("Kevin Durantula"), new Phone("95326789"), new Email("durantula@example.com"),
                    new Height("211"), new JerseyNumber("7"),
                    getTagSet("SG", "SF", "PF"), new Weight("105"), getLineupSet("snake", "all star")),
            new Person(new Name("Lebron LeGM"), new Phone("92623662"), new Email("legm@example.com"),
                    new Height("206"), new JerseyNumber("6"),
                    getTagSet("SF", "PF", "C"), new Weight("120"), getLineupSet("all star")),
            new Person(new Name("Stephen Spice"), new Phone("96564417"), new Email("steph@example.com"),
                    new Height("188"), new JerseyNumber("30"),
                    getTagSet("PG", "SG"), new Weight("90"), getLineupSet("all star")),
            new Person(new Name("Joel EnBig"), new Phone("92683747"), new Email("enbigb@example.com"),
                    new Height("213"), new JerseyNumber("21"),
                    getTagSet("C"), new Weight("90"), getLineupSet("all star")),
            new Person(new Name("Greek Koumpo"), new Phone("85624417"), new Email("greek@example.com"),
                    new Height("208"), new JerseyNumber("34"),
                    getTagSet("SF", "PF"), new Weight("110"), getLineupSet("all star", "freak"))
        };
    }

    public static Lineup[] getSampleLineups() {
        return new Lineup[] {
                new Lineup(new LineupName("snake")),
                new Lineup(new LineupName("all star")),
                new Lineup(new LineupName("freak"))
        };
    }

    public static Schedule[] getSampleSchedules() {
        return new Schedule[] {
            new Schedule(new ScheduleName("Championship Match"),
                new ScheduleDescription("Against Clippers LeGM needs to stop passing"),
                new ScheduleDateTime("06/01/2022 1200")),
            new Schedule(new ScheduleName("All star game"),
                new ScheduleDescription("LBJ and KD participating"),
                new ScheduleDateTime("02/01/2020 1200")),
            new Schedule(new ScheduleName("Skills challenge"),
                new ScheduleDescription("Practice for this"),
                new ScheduleDateTime("18/01/2021 1200")),
            new Schedule(new ScheduleName("Three point shoot out"),
                new ScheduleDescription("Steph practice 3 pointer"),
                new ScheduleDateTime("19/02/2020 1200")),
            new Schedule(new ScheduleName("Free throw practice"),
                new ScheduleDescription("Dwight needs to improve free throws"),
                new ScheduleDateTime("19/06/2021 1200"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Lineup sampleLineup : getSampleLineups()) {
            sampleAb.addLineup(sampleLineup);
        }
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.initalizePerson(samplePerson);
        }
        for (Schedule sampleSchedule : getSampleSchedules()) {
            System.out.println(sampleSchedule);
            sampleAb.addSchedule(sampleSchedule);
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
     * Returns a lineup set containing the list of strings given.
     */
    public static Set<LineupName> getLineupSet(String... strings) {
        return Arrays.stream(strings)
                .map(LineupName::new)
                .collect(Collectors.toSet());
    }

}
