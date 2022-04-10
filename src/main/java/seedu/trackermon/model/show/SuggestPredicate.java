package seedu.trackermon.model.show;

import java.util.function.Predicate;

/**
 * Tests that {@code Show} is the same show as {@code testShow}.
 */
public class SuggestPredicate implements Predicate<Show> {

    private final Show testShow;

    /**
     * Constructs a {@code SuggestPredicate} with the provided {@code Show}.
     * @param show provided {@code Show}
     */
    public SuggestPredicate(Show show) {
        testShow = show;
    }

    @Override
    public boolean test(Show show) {
        return testShow.isSameShow(show);
    }
}
