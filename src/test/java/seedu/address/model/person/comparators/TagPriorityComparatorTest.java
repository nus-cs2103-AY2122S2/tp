package seedu.address.model.person.comparators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INSURANCE_PACKAGE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MULTIPLE_TAGS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NO_TAGS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NULL_PRIO;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;


/**
 * Integration tests for TagPriorityComparator class.
 */
class TagPriorityComparatorTest {
    private static Person personWithPrio4 = new PersonBuilder().withTags(VALID_TAG_FRIEND).build();
    private static PersonBuilder unfinishedPerson = new PersonBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
            .withInsurancePackage(VALID_INSURANCE_PACKAGE_BOB)
            .withAddress(VALID_ADDRESS_BOB);
    private static TagPriorityComparator comparator = new TagPriorityComparator();

    /**
     * Tests that for a person with multiple tags, his/her priority level is the highest
     * priority level out of all of his/her tags.
     * This test also checks that the comparator compares priority levels between two tags correctly.
    */
    @Test
    public void compareWithOtherTags() {
        Person personWithPrio3 = unfinishedPerson.withTags(VALID_MULTIPLE_TAGS).build();
        int res = comparator.compare(personWithPrio4, personWithPrio3);

        assertEquals(1, res);
    }

    /**
     * Tests that the comparator can handle tags with null priority.
     */
    @Test
    public void compareWithNullPrio() {
        Person personWithNullPrioTag = unfinishedPerson.withTags(VALID_TAG_NULL_PRIO).build();
        int res = comparator.compare(personWithPrio4, personWithNullPrioTag);

        assertEquals(-1, res);
    }

    /**
     * Tests that a person with no tags is given the lowest priority.
     */
    @Test
    public void compareWithNoTags() {
        Person personWithNoTags = unfinishedPerson.withTags(VALID_NO_TAGS).build();
        int res = comparator.compare(personWithPrio4, personWithNoTags);

        assertEquals(-1, res);
    }

}
