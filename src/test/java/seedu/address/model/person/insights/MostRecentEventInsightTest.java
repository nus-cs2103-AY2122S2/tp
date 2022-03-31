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
import seedu.address.model.event.DateTime;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.PersonBuilder;

public class MostRecentEventInsightTest {

    private final Model validModelWithEvents;
    private final PersonBuilder personBuilder = new PersonBuilder();
    private final EventBuilder eventBuilder = new EventBuilder();

    public MostRecentEventInsightTest() {
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
        assertThrows(NullPointerException.class, () -> Insights.createMostRecentEventInsight(null, null));
    }

    @Test
    public void equals() {

        MostRecentEventInsight insight;
        MostRecentEventInsight other;

        // same object -> true
        insight = Insights.createMostRecentEventInsight(BOB, this.validModelWithEvents);
        assertEquals(insight, insight);

        // same values -> true
        other = Insights.createMostRecentEventInsight(BOB, this.validModelWithEvents);
        assertEquals(insight, other);

        // different values -> false
        other = Insights.createMostRecentEventInsight(AMY, this.validModelWithEvents);
        assertNotEquals(insight, other);

    }

    // ===== INTEGRATION TESTS =====
    @Test
    public void getInsight_valid_success() {

        MostRecentEventInsight insight;

        // no events -> correct output
        insight = Insights.createMostRecentEventInsight(BOB, this.validModelWithEvents);
        assertEquals(insight.getAsString(), MostRecentEventInsight.DEFAULT_NO_EVENT_MESSAGE);

        // have events -> correct output
        insight = Insights.createMostRecentEventInsight(AMY, this.validModelWithEvents);
        assertEquals(MostRecentEventInsight.DEFAULT_HAS_EVENT_PREFIX
                + new DateTime(eventBuilder.DEFAULT_DATE_TIME).toString(), insight.getAsString());

    }

}
