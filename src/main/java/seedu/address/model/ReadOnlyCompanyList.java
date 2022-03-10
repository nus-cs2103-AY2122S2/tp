package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.company.Company;

/**
 * Unmodifiable view of an address book
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
