package seedu.address.model.person.comparators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;


class PersonNameComparatorTest {

    private static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withFlag("true")
            .withTags("friends")
            .withPrevDateMet("2022-01-12")
            .withSalary("5300")
            .withScheduledMeeting("2022-01-03", "1230")
            .withInfo("Salary of $5300").build();
    private static final Person PETER = new PersonBuilder().withName("Peter Wilts")
            .withPhone("96201021")
            .withEmail("peter@example.com")
            .withAddress("20th street")
            .withFlag("true")
            .withPrevDateMet("2021-02-28")
            .withSalary("4200")
            .withInfo("Risky investor")
            .withScheduledMeeting("2022-01-05", "1430")
            .build();
    private static final Person PETER_TWO = new PersonBuilder().withName("Peter Wilts")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withAddress("10th street")
            .withFlag("true")
            .withTags("friends")
            .withPrevDateMet("2021-12-12")
            .withSalary("3500")
            .withScheduledMeeting("2022-01-05", "1430")
            .withInfo("Salary of $3500").build();

    private PersonNameComparator comparator = new PersonNameComparator();

    @Test
    public void compare_letterA_letterP() {
        assertEquals(comparator.compare(ALICE, PETER), -15);
    }

    @Test
    public void compare_letterP_letterA() {
        assertEquals(comparator.compare(PETER, ALICE), 15);
    }

    @Test
    public void compare_sameName() {
        assertEquals(comparator.compare(PETER_TWO, PETER), 0);
    }
}
