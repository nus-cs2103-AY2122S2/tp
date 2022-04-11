package seedu.trackermon.testutil;

import seedu.trackermon.model.ShowList;
import seedu.trackermon.model.show.Show;

/**
 * A utility class to help with building ShowList objects.
 * Example usage: <br>
 *     {@code ShowList ab = new ShowListBuilder().withShow(ALICE_IN_WONDERLAND).build();}
 */
public class ShowListBuilder {
    private ShowList showList;

    public ShowListBuilder() {
        showList = new ShowList();
    }

    /**
     * Adds a new {@code Show} to the {@code ShowList} that we are building.
     */
    public ShowListBuilder withShow(Show show) {
        showList.addShow(show);
        return this;
    }

    public ShowList build() {
        return showList;
    }
}
