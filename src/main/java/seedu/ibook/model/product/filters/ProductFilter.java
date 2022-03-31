package seedu.ibook.model.product.filters;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.ibook.model.product.Product;
import seedu.ibook.model.product.exceptions.FilterNotFoundException;

public class ProductFilter implements Predicate<Product> {
    private final ObservableList<AttributeFilter> filters = FXCollections.observableArrayList();

    /**
     * Instantiate with no conditions. Matches all products.
     */
    public ProductFilter() {}

    /**
     * Instantiate with a specific product.
     */
    public ProductFilter(Product product) {
        addFilter(new NameFilter(product.getName()));
        addFilter(new CategoryFilter(product.getCategory()));
        addFilter(new DescriptionFilter(product.getDescription()));
        addFilter(new PriceFilter(product.getPrice()));
    }

    /**
     * Adds a filter to the predicate.
     */
    public void addFilter(AttributeFilter filter) {
        requireNonNull(filter);
        filters.add(filter);
    }

    /**
     * Removes a filter to the predicate.
     */
    public void removeFilter(AttributeFilter filter) {
        requireNonNull(filter);
        if (!filters.remove(filter)) {
            throw new FilterNotFoundException();
        }
    }

    /**
     * Clear all filters.
     */
    public void clearFilters() {
        filters.clear();
    }

    /**
     * Returns a list of {@code AttributeFilters}.
     */
    public ObservableList<AttributeFilter> getFilters() {
        return filters;
    }

    @Override
    public boolean test(Product otherProduct) {
        return filters
            .stream()
            .allMatch(filter -> filter.test(otherProduct));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ProductFilter)) {
            return false;
        }

        ProductFilter otherPredicate = (ProductFilter) other;
        return filters.equals(otherPredicate.getFilters());
    }
}
