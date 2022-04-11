package seedu.address.testutil;

import static seedu.address.model.util.SampleDataUtil.getDetailsList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import seedu.address.model.order.CollectionType;
import seedu.address.model.order.Complete;
import seedu.address.model.order.DeliveryDateTime;
import seedu.address.model.order.Details;
import seedu.address.model.order.Order;
import seedu.address.model.person.Remark;

public class OrderBuilder {

    public static final String DEFAULT_DETAILS = "1 : chocolatemuffin";

    public static final String DEFAULT_COMPLETE = "false";
    public static final String DEFAULT_UUID = "c6a8669e-ee95-4c42-9ef6-4a9b61380164";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_REMARK = "Add more chocolate";
    public static final String DEFAULT_DELIVERYDATETIME = "10-12-2022 17:00";
    public static final CollectionType DEFAULT_COLLECTIONTYPE = CollectionType.DELIVERY;


    private Remark remark;
    private List<Details> details;
    private DeliveryDateTime deliveryDateTime;
    private CollectionType collectionType;
    private Complete complete;
    private UUID uuid;

    /**
     * Creates a {@code OrderBuilder} with the default details.
     */
    public OrderBuilder() {

        remark = new Remark(DEFAULT_REMARK);
        details = getDetailsList(DEFAULT_DETAILS);
        deliveryDateTime = new DeliveryDateTime(DEFAULT_DELIVERYDATETIME);
        collectionType = DEFAULT_COLLECTIONTYPE;
        complete = new Complete(DEFAULT_COMPLETE);
        uuid = UUID.fromString(DEFAULT_UUID);
    }

    /**
     * Initializes the OrderBuilder with the data of {@code personToCopy}.
     */
    public OrderBuilder(Order orderToCopy) {
        uuid = orderToCopy.getUuid();
        remark = orderToCopy.getRemark();
        details = new ArrayList<>(orderToCopy.getDetails());
        deliveryDateTime = orderToCopy.getDeliveryDateTime();
        collectionType = orderToCopy.getCollectionType();
        complete = orderToCopy.getComplete();
    }


    /**
     * Sets the {@code UUID} of the {@code Order} that we are building.
     */
    public OrderBuilder withUuid(String uuid) {
        this.uuid = UUID.fromString(uuid);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Order} that we are building.
     */
    public OrderBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Sets the {@code Details} of the {@code Order} that we are building.
     */
    public OrderBuilder withDetails(String... details) {
        this.details = getDetailsList(details);
        return this;
    }

    /**
     * Sets the {@code deliveryDateTime} of the {@code Order} that we are building.
     */
    public OrderBuilder withDeliveryDateTime(String deliveryDateTime) {
        this.deliveryDateTime = new DeliveryDateTime(deliveryDateTime);
        return this;
    }

    /**
     * Sets the {@code collectionType} of the {@code Order} that we are building.
     */
    public OrderBuilder withCollectionType(CollectionType collectionType) {
        this.collectionType = collectionType;
        return this;
    }

    /**
     * Sets the {@code Complete} of the {@code Order} that we are building.
     */
    public OrderBuilder withComplete(Boolean complete) {
        this.complete = new Complete(complete);
        return this;
    }

    /**
     * Builds the {@code Order} according to the attributes given
     */
    public Order build() {
        return new Order(remark, details, deliveryDateTime, collectionType, complete, uuid);

    }
}
