package seedu.address.model.person.comparators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class PersonFlagComparatorTest {

    private static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withFlag("true")
            .withTags("friends")
            .withPrevDateMet("2022-01-12")
            .withSalary("5300").build();
    private static final Person PETER = new PersonBuilder().withName("Peter Wilts")
            .withPhone("96201021")
            .withEmail("peter@example.com")
            .withAddress("20th street")
            .withPrevDateMet("2021-02-28")
            .withSalary("4200")
            .withInfo("Risky investor")
            .withScheduledMeeting("2022-01-05", "1430")
            .build();
    private static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withAddress("10th street")
            .withFlag("true")
            .withTags("friends")
            .withPrevDateMet("2021-12-12")
            .withSalary("3500")
            .withInfo("Salary of $3500").build();
    private static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com")
            .withAddress("michegan ave")
            .withPrevDateMet("2021-11-02")
            .withSalary("6200")
            .withInfo("Salary of $6200").build();

    private PersonFlagComparator comparator = new PersonFlagComparator();

    @Test
    public void compare_flag_unflag() {
        assertEquals(comparator.compare(ALICE, PETER), -1);
    }

    @Test
    public void compare_unflag_flag() {
        assertEquals(comparator.compare(PETER, ALICE), 1);
    }

    @Test
    public void compare_both_flagged() {
        assertEquals(comparator.compare(DANIEL, ALICE), 0);
    }

    @Test
    public void compare_both_unflagged() {
        assertEquals(comparator.compare(ELLE, PETER), 0);
    }
}
