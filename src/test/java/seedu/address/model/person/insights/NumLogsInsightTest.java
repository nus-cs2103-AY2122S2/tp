package seedu.address.model.person.insights;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.MAVIS;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;


public class NumLogsInsightTest {

    private final Model validModelWithEvents;

    public NumLogsInsightTest() {
        this.validModelWithEvents = new ModelManager();
        this.validModelWithEvents.addPerson(AMY); // no logs
        this.validModelWithEvents.addPerson(MAVIS); // 3 logs
    }

    // ===== UNIT TESTS =====

    @Test
    public void getInsight_nulls_failure() {
        assertThrows(NullPointerException.class, () -> Insights.createNumLogsInsight(null, null));
    }

    @Test
    public void equals() {

        NumLogsInsight insight;
        NumLogsInsight other;

        // same object -> true
        insight = Insights.createNumLogsInsight(AMY, this.validModelWithEvents);
        assertEquals(insight, insight);

        // same values -> true
        other = Insights.createNumLogsInsight(AMY, this.validModelWithEvents);
        assertEquals(insight, other);

        // different values -> false
        other = Insights.createNumLogsInsight(MAVIS, this.validModelWithEvents);
        assertNotEquals(insight, other);

    }

    // ===== INTEGRATION TESTS =====
    @Test
    public void getInsight_valid_success() {

        NumLogsInsight insight;

        // no events -> correct output
        insight = Insights.createNumLogsInsight(AMY, this.validModelWithEvents);
        assertEquals(NumLogsInsight.DEFAULT_HAS_LOG_PREFIX + "0", insight.getAsString());

        // have events -> correct output
        insight = Insights.createNumLogsInsight(MAVIS, this.validModelWithEvents);
        assertEquals(NumLogsInsight.DEFAULT_HAS_LOG_PREFIX + "3", insight.getAsString());

    }

}
