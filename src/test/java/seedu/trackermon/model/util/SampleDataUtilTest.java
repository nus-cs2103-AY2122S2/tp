package seedu.trackermon.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SampleDataUtilTest {

    @Test
    void getSampleShowList() {
        // check first sample show
        assertEquals("Attack on Titan; Status: watching; Rating: 3; Comment: Levi; Tags: [Anime];",
                SampleDataUtil.getSampleShowList().getShows().get(0).toString());

        // check second sample show
        assertEquals("Another; Status: completed; Rating: 3; Comment: WTF; Tags: [Horror][Anime];",
                SampleDataUtil.getSampleShowList().getShows().get(1).toString());
    }
}
