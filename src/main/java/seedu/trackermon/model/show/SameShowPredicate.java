package seedu.trackermon.model.show;

import java.util.function.Predicate;

/**
 * Tests that {@code Show} is the same show as {@code testShow}.
 */
public class SameShowPredicate implements Predicate<Show> {

    private final Show testShow;

    /**
     * Constructs a {@code SuggestPredicate} with the provided {@code Show}.
     * @param show provided {@code Show}.
     */
    public SameShowPredicate(Show show) {
        testShow = show;
    }

    /**
     * Tests if it is the same {@code Show} as the provided {@code Show}.
     * @param show provided {@code Show}.
     */
    @Override
    public boolean test(Show show) {
        return testShow.isSameShow(show);
    }
}
