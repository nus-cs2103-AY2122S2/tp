package seedu.trackermon.model.show;

import java.util.function.Predicate;

public class SuggestPredicate implements Predicate<Show> {

    private final Show testShow;

    public SuggestPredicate(Show show) {
        testShow = show;
    }

    @Override
    public boolean test(Show show) {
        return testShow.isSameShow(show);
    }
}
