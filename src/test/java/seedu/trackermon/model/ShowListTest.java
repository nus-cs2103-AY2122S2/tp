package seedu.trackermon.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackermon.logic.commands.CommandTestUtil.VALID_TAG_HENTAI;
import static seedu.trackermon.testutil.Assert.assertThrows;
import static seedu.trackermon.testutil.TypicalShows.ALICE_IN_WONDERLAND;
import static seedu.trackermon.testutil.TypicalShows.getTypicalShowList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.trackermon.model.show.Show;
import seedu.trackermon.model.show.exceptions.DuplicatedShowException;
import seedu.trackermon.testutil.ShowBuilder;


public class ShowListTest {

    private final ShowList showList = new ShowList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), showList.getShows());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> showList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyShowList_replacesData() {
        ShowList newData = getTypicalShowList();
        showList.resetData(newData);
        assertEquals(newData, showList);
    }

    @Test
    public void resetData_withDuplicateShows_throwsDuplicateShowException() {
        // Two shows with the same identity fields
        Show editedAlice = new ShowBuilder(ALICE_IN_WONDERLAND).withComment().withTags(VALID_TAG_HENTAI)
                .build();
        List<Show> newShows = Arrays.asList(ALICE_IN_WONDERLAND, editedAlice);
        ShowListStub newData = new ShowListStub(newShows);

        assertThrows(DuplicatedShowException.class, () -> showList.resetData(newData));
    }

    @Test
    public void hasShow_nullShow_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> showList.hasShow(null));
    }

    @Test
    public void hasShow_showNotInShowList_returnsFalse() {
        assertFalse(showList.hasShow(ALICE_IN_WONDERLAND));
    }

    @Test
    public void hasPerson_personInShowList_returnsTrue() {
        showList.addShow(ALICE_IN_WONDERLAND);
        assertTrue(showList.hasShow(ALICE_IN_WONDERLAND));
    }

    @Test
    public void hasShow_showWithSameIdentityFieldsInShowList_returnsTrue() {
        showList.addShow(ALICE_IN_WONDERLAND);
        Show editedAlice = new ShowBuilder(ALICE_IN_WONDERLAND).withComment().withTags(VALID_TAG_HENTAI)
                .build();
        assertTrue(showList.hasShow(editedAlice));
    }

    @Test
    public void getShowList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> showList.getShows().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class ShowListStub implements ReadOnlyShowList {

        private final ObservableList<Show> shows = FXCollections.observableArrayList();

        ShowListStub(Collection<Show> shows) {
            this.shows.setAll(shows);
        }

        @Override
        public ObservableList<Show> getShows() {
            return shows;
        }
    }
}
