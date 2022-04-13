package seedu.tinner.model;

import javafx.collections.ObservableList;
import seedu.tinner.model.company.Company;

/**
 * Unmodifiable view of a company list.
 */
public interface ReadOnlyCompanyList {

    /**
     * Returns an unmodifiable view of the companies list.
     * This list will not contain any duplicate companies.
     */
    ObservableList<Company> getCompanyList();

    /**
     * Returns the size of the company list.
     *
     * @return the size of the internal company list.
     */
    int size();
}
