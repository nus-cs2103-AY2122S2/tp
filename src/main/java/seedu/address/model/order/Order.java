package seedu.address.model.order;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import seedu.address.model.person.Remark;


/**
 * Represents an Order in the ReadyBakey.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Order {
    // Limits for list-type fields
    public static final int MAX_DETAIL_SIZE = 5;

    // Identity fields
    private final UUID uuid;

    // Data fields
    private final Remark remark;
    private final List<Details> details = new ArrayList<>();
    private final DeliveryDateTime deliveryDateTime;
    private final Complete complete;
    private final CollectionType collectionType;

    /**
     * Every field must be present and not null.
     */
    public Order(Remark remark, List<Details> details,
                 DeliveryDateTime deliveryDateTime, CollectionType collectionType, UUID uuid) {
        requireAllNonNull(remark, details, deliveryDateTime, collectionType, uuid);

        this.remark = remark;
        this.details.addAll(details);
        this.complete = new Complete(false);
        this.deliveryDateTime = deliveryDateTime;
        this.collectionType = collectionType;
        this.uuid = uuid;
    }

    /**
     * Every field must be present and not null.
     */
    public Order(Remark remark, List<Details> details,
                 DeliveryDateTime deliveryDateTime, CollectionType collectionType, Complete complete, UUID uuid) {
        requireAllNonNull(remark, details, deliveryDateTime, collectionType, complete, uuid);

        this.remark = remark;
        this.details.addAll(details);
        this.deliveryDateTime = deliveryDateTime;
        this.collectionType = collectionType;
        this.complete = complete;
        this.uuid = uuid;
    }

    public List<Details> getDetails() {
        return Collections.unmodifiableList(details);
    }

    public Remark getRemark() {
        return remark;
    }

    public DeliveryDateTime getDeliveryDateTime() {
        return deliveryDateTime;
    }

    public LocalDateTime getDeliveryDateTimeValue() {
        return deliveryDateTime.getValue();
    }

    public Complete getComplete() {
        return complete;
    }

    public boolean isComplete() {
        return complete.isComplete();
    }

    public UUID getUuid() {
        return uuid;
    }

    public CollectionType getCollectionType() {
        return collectionType;
    }

    /**
     * Returns true if both orders have the same identity and data fields.
     * This defines a stronger notion of equality between two orders.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Order)) {
            return false;
        }

        Order otherOrder = (Order) other;

        return otherOrder.getUuid().equals(getUuid())
                && new HashSet<>(otherOrder.getDetails()).equals(new HashSet<>(getDetails()))
                && otherOrder.getDeliveryDateTime().equals(getDeliveryDateTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(remark, details, deliveryDateTime, collectionType, uuid);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append("Remark: ")
                .append(getRemark())
                .append("; Details: ")
                .append(getDetails())
                .append("; Delivery Date: ")
                .append(getDeliveryDateTime())
                .append("; Collection Type: ")
                .append(getCollectionType())
                .append("; Complete: ")
                .append(getComplete());

        return builder.toString();
    }

}
