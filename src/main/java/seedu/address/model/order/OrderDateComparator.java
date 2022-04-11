package seedu.address.model.order;

import java.util.Comparator;

public class OrderDateComparator implements Comparator<Order> {
    @Override
    public int compare(Order firstOrder, Order secondOrder) {
        return firstOrder.getDeliveryDateTimeValue().compareTo(secondOrder.getDeliveryDateTimeValue());
    }
}
