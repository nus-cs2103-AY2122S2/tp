package seedu.address.model.person.insights;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.LAUREN;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.PersonBuilder;

public class NumEventsInsightTest {

    private final Model validModelWithEvents;
    private final PersonBuilder personBuilder = new PersonBuilder();
    private final EventBuilder eventBuilder = new EventBuilder();

    public NumEventsInsightTest() {
        this.validModelWithEvents = new ModelManager();

        // friend with one event
        this.validModelWithEvents.addPerson(AMY); // has one event
        this.validModelWithEvents.addEvent(eventBuilder.withName("some")
                .withNames(AMY.getName().toString()).build()); // add one default event

        // friend with no events
        this.validModelWithEvents.addPerson(BOB);

        // friend with two events
        this.validModelWithEvents.addPerson(LAUREN);
        this.validModelWithEvents.addEvent(eventBuilder.withName("first")
                .withNames(LAUREN.getName().toString()).build());
        this.validModelWithEvents.addEvent(eventBuilder.withName("second")
                .withNames(LAUREN.getName().toString())
                .withDateTime("2-1-2022 1500")
                .build());

    }

    // ===== UNIT TESTS =====

    @Test
    public void getInsight_nulls_failure() {
        assertThrows(NullPointerException.class, () -> Insights.createNumEventsInsight(null, null));
    }

    @Test
    public void equals() {

        NumEventsInsight insight;
        NumEventsInsight other;

        // same object -> true
        insight = Insights.createNumEventsInsight(BOB, this.validModelWithEvents);
        assertEquals(insight, insight);

        // same values -> true
        other = Insights.createNumEventsInsight(BOB, this.validModelWithEvents);
        assertEquals(insight, other);

        // different values -> false
        other = Insights.createNumEventsInsight(AMY, this.validModelWithEvents);
        assertNotEquals(insight, other);

    }

    // ===== INTEGRATION TESTS =====
    @Test
    public void getInsight_valid_success() {

        NumEventsInsight insight;

        // no events -> correct output
        insight = Insights.createNumEventsInsight(BOB, this.validModelWithEvents);
        assertEquals(insight.getAsString(), NumEventsInsight.DEFAULT_HAS_EVENT_PREFIX + "0");

        // have events -> correct output
        insight = Insights.createNumEventsInsight(AMY, this.validModelWithEvents);
        assertEquals(NumEventsInsight.DEFAULT_HAS_EVENT_PREFIX + "1", insight.getAsString());

        insight = Insights.createNumEventsInsight(LAUREN, this.validModelWithEvents);
        assertEquals(NumEventsInsight.DEFAULT_HAS_EVENT_PREFIX + "2", insight.getAsString());

    }

}
