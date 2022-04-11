package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Email;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.team.SkillSet;
import seedu.address.model.team.Team;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Alex Teo"), new Phone("87438807"), new Email("alexteo@example.com"),
                    new GithubUsername("alexteo98"), getTeamSet("Google CodeJam"),
                    getSkillSet("C_50"), false),
            new Person(new Name("Junha Park"), new Phone("91031282"), new Email("billpark@example.com"),
                    new GithubUsername("B1LLP4RK"), getTeamSet("CTF 2022"),
                    getSkillSet("Java_70"), true),
            new Person(new Name("Lye Jia Yang"), new Phone("99272758"), new Email("lyejy@example.com"),
                    new GithubUsername("Jiaaa-yang"), getTeamSet("Shopee League", "Kickstart"),
                    getSkillSet("C_90"), false),
            new Person(new Name("Melvin Chan"), new Phone("93210283"), new Email("mchan@example.com"),
                    new GithubUsername("MelvinCZJ"), getTeamSet("CS2103T"),
                    getSkillSet("Bash_50"), false),
            new Person(new Name("Toh Zhan Qing"), new Phone("92492021"), new Email("tohzq@example.com"),
                    new GithubUsername("tzhan98"), getTeamSet("CS2100"),
                    getSkillSet("C_50"), false),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a team set containing the list of strings given.
     */
    public static Set<Team> getTeamSet(String... strings) {
        return Arrays.stream(strings)
                .map(Team::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a skill set containing the list of strings given.
     */
    public static SkillSet getSkillSet(String... strings) {
        return new SkillSet(Arrays.stream(strings)
                .map(x -> {
                    try {
                        return ParserUtil.parseSkill(x);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .collect(Collectors.toSet()));
    }

}
