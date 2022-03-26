package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.filter.AppointmentContainsFilterWordPredicate;
import seedu.address.model.filter.DateContainsFilterDatePredicate;
import seedu.address.model.filter.OwnerNameContainsFilterWordPredicate;
import seedu.address.model.filter.TagContainsFilterWordPredicate;
import seedu.address.model.tag.Tag;

public class FilterUtilTest {
    public static String ownerName = "ABC";
    public static String dateWord = "today";
    public static String tagWord = "def";
    public static OwnerNameContainsFilterWordPredicate ownerPredicate;
    public static DateContainsFilterDatePredicate datePredicate;
    public static AppointmentContainsFilterWordPredicate appPredicate;
    public static TagContainsFilterWordPredicate tagPredicate;
    public static Set<Tag> tags = new HashSet<>();

    //---------------- Tests for isValidFilterArg --------------------------------------
    @Test
    public void isValidFilterArg_valid_input() {
        assertTrue(FilterUtil.isValidFilterArg("byDate/18-09-2022"));
        assertTrue(FilterUtil.isValidFilterArg("byApp/today"));
        assertTrue(FilterUtil.isValidFilterArg("byOwner/abcd"));
        assertTrue(FilterUtil.isValidFilterArg("byTags/abc"));


    }

    @Test
    public void isValidFilterArg_invalid_input() {
        assertFalse(FilterUtil.isValidFilterArg("byDate/09-03-2022 byOwner/abcd"));
        assertFalse(FilterUtil.isValidFilterArg("byTags/abc byApp/today"));
    }

    //---------------- Tests for successMessageMatch --------------------------------------
    @BeforeAll
    public static void createPredicates() {
        try {
            datePredicate = new DateContainsFilterDatePredicate(dateWord);
            appPredicate = new AppointmentContainsFilterWordPredicate(dateWord);
        } catch (ParseException pe) {
            fail("Should not have thrown parse exception.");
        }
        ownerPredicate = new OwnerNameContainsFilterWordPredicate(ownerName);
        tagPredicate = new TagContainsFilterWordPredicate(tagWord);
    }
    @Test
    public void successMessageMatch_test() {
        assertEquals("date", FilterUtil.successMessageMatch(datePredicate));
        assertEquals("appointment date", FilterUtil.successMessageMatch(appPredicate));
        assertEquals("owner's name", FilterUtil.successMessageMatch(ownerPredicate));
        assertEquals("tags", FilterUtil.successMessageMatch(tagPredicate));
    }

    //---------------- Tests for tagContainsFilterWord --------------------------------------
    @BeforeAll
    public static void createTags() {
        Tag tagOne = new Tag("ABC");
        Tag tagTwo = new Tag("DEF");
        Tag tagThree = new Tag("GHI JKL");
        tags.add(tagOne);
        tags.add(tagTwo);
        tags.add(tagThree);
    }

    @Test
    public void tagContainsFilterWord_success() {
        //Lower case match "ABC"
        assertTrue(FilterUtil.tagContainFilterWord(tags, "abc"));

        //Upper case match "DEF"
        assertTrue(FilterUtil.tagContainFilterWord(tags, "DEF"));

        //Lower case partial match "GHI JKL"
        assertTrue(FilterUtil.tagContainFilterWord(tags, "gh jk"));


    }
}
