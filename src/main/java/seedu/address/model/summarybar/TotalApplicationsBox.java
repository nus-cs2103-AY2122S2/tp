package seedu.address.model.summarybar;

import java.util.Optional;

/**
 * Represents a SummaryBox that tracks the total number of applications in InternApply.
 */
public class TotalApplicationsBox extends SummaryBox {

    /**
     * Constructs a TotalApplicationsBox.
     *
     * @param name The name of this TotalApplicationsBox.
     * @param totalApplications Total number of applications in InternApply.
     */
    public TotalApplicationsBox(String name, int totalApplications) {
        super(name, totalApplications);
    }

    @Override
    public Optional<Integer> getCurrApplications() {
        return Optional.empty();
    }
}
