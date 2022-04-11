package seedu.address.model.summarybar;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a SummaryBox in InternApply.
 */
public abstract class SummaryBox {

    private String name;
    private int totalApplications;

    /**
     * Constructs a {@Code SummaryBox}.
     *
     * @param name Name of the SummaryBox.
     * @param totalApplications Total number of applications in InternApply.
     */
    public SummaryBox(String name, int totalApplications) {
        this.name = name;
        this.totalApplications = totalApplications;
    }

    public String getName() {
        return name;
    }

    public int getTotalApplications() {
        return totalApplications;
    }

    public void setTotalApplications(int totalApplications) {
        this.totalApplications = totalApplications;
    }

    /**
     * Gets the number of applications this SummaryBox is tracking.
     * Returns Optional.empty() for SummaryBoxes that are not tracking any applications.
     *
     * @return The number of applications this SummaryBox is tracking.
     */
    public abstract Optional<Integer> getCurrApplications();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SummaryBox that = (SummaryBox) o;
        return totalApplications == that.totalApplications
                && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, totalApplications);
    }
}
