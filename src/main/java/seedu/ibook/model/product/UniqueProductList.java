package seedu.ibook.model.product;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.ibook.commons.core.UniqueList;
import seedu.ibook.commons.core.exceptions.DuplicateElementException;
import seedu.ibook.commons.core.exceptions.ElementNotFoundException;

/**
 * A list of products that enforces uniqueness between its elements and does not allow nulls.
 * A product is considered unique by comparing using {@code Product#isSameProduct(Product)}. As such, adding and
 * updating of products uses Product#isSameProduct(Product) for equality as to ensure that the product being added or
 * updated is unique in terms of identity in the UniqueProductList. However, the removal of a product uses
 * Product#equals(Object) as to ensure that the product with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Product#isSame(Product)
 */
public class UniqueProductList extends UniqueList<Product> {

    /**
     * Replaces the product {@code target} in the list with {@code editedProduct}.
     * {@code target} must exist in the list.
     * The product identity of {@code editedProduct} must not be the same as another existing product in the list.
     */
    public void setProduct(Product target, Product editedProduct) {
        requireAllNonNull(target, editedProduct);

        int index = asObservableList().indexOf(target);
        if (index == -1) {
            throw new ElementNotFoundException();
        }

        if (!target.isSame(editedProduct) && contains(editedProduct)) {
            throw new DuplicateElementException();
        }

        set(index, editedProduct);
    }

    public void setProducts(UniqueProductList replacement) {
        requireNonNull(replacement);
        setAll(replacement.asObservableList());
    }

    /**
     * Replaces the contents of this list with {@code products}.
     * {@code products} must not contain duplicate products.
     */
    public void setProducts(List<Product> products) {
        requireAllNonNull(products);
        if (!productsAreUnique(products)) {
            throw new DuplicateElementException();
        }

        setAll(products);
    }

    /**
     * Returns true if {@code products} contains only unique products.
     */
    private boolean productsAreUnique(List<Product> products) {
        for (int i = 0; i < products.size() - 1; i++) {
            for (int j = i + 1; j < products.size(); j++) {
                if (products.get(i).isSame(products.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
