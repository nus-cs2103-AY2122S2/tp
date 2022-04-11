package seedu.address.model.summarybar;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a SummaryBox in InternApply that keeps track of the number of applications with a specified tagName.
 */
public class TagSummaryBox extends SummaryBox {
    private int currApplications;

    /**
     * Constructs a TagSummaryBox.
     *
     * @param name Given name of the TagSummaryBox.
     * @param currApplications Number of applications this TagSummaryBox is tracking.
     * @param totalApplications Total number of applications in InternApply.
     */
    TagSummaryBox(String name, int currApplications, int totalApplications) {
        super(name, totalApplications);
        this.currApplications = currApplications;
    }

    @Override
    public Optional<Integer> getCurrApplications() {
        return Optional.of(currApplications);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        TagSummaryBox that = (TagSummaryBox) o;
        return currApplications == that.currApplications;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), currApplications);
    }
}
