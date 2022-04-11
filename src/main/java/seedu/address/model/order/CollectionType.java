package seedu.address.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an order's CollectionType in ReadyBakey.
 */
public enum CollectionType {
    DELIVERY("Delivery"),
    PICKUP("Pickup");

    public static final String MESSAGE_CONSTRAINTS = "CollectionType can only be either Delivery or Pickup";

    /*
     * Must either be Delivery or Pickup
     */
    public static final String VALIDATION_REGEX = "^(Delivery|Pickup)$";

    public final String value;

    /**
     * Constructs an {@code CollectionType}.
     *
     * @param collectionType A valid CollectionType
     */
    CollectionType(String collectionType) {
        requireNonNull(collectionType);
        checkArgument(isValidCollectionTypeValue(collectionType), MESSAGE_CONSTRAINTS);
        value = collectionType;
    }

    /**
     * Returns true if a given string is a valid CollectionTypeValue.
     */
    public static boolean isValidCollectionTypeValue(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getValue() {
        return value;
    }


}
