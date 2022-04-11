package seedu.address.model.recipe;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a searchable values of a recipe for {@code FindCommand}.
 */
public class SearchSet {
    private final Set<String> searchValues = new HashSet<>();
    private final StringBuilder stringRepresentation = new StringBuilder();

    public Set<String> getSearchValues() {
        return searchValues;
    }

    /**
     * Adds the {@code value} into {@code searchValues} and only appends to {@code stringRepresentation} if the
     * element is successfully added into {@code searchValues}.
     * @param value the value that wants to be added into {@code searchValues} and {@code stringRepresentation}.
     */
    public void add(String value) {
        if (this.searchValues.add(value)) {
            this.stringRepresentation.append(value).append(" ");
        }
    }

    @Override
    public String toString() {
        return stringRepresentation.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SearchSet)) {
            return false;
        }

        if (o == this) {
            return true;
        }

        SearchSet other = (SearchSet) o;
        return this.searchValues.equals(other.searchValues);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(searchValues);
    }
}
