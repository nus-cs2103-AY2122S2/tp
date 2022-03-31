package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ScheduleNameContainsKeywordsPredicateTest {

    @Test
    public void samePred() {
        List<String> keywords = new ArrayList<>();
        keywords.add("P/");
        ScheduleNameContainsKeywordsPredicate p1 = new ScheduleNameContainsKeywordsPredicate(keywords);
        ScheduleNameContainsKeywordsPredicate p2 = new ScheduleNameContainsKeywordsPredicate(keywords);
        assertTrue(p1.equals(p1));
        assertTrue(p1.equals(p2));
    }
}
