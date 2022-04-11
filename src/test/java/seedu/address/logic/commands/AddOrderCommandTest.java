package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.util.SampleDataUtil.getDetailsList;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ModelStub;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.order.CollectionType;
import seedu.address.model.order.DeliveryDateTime;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.testutil.OrderBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddOrderCommandTest {
    @Test
    public void constructor_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddOrderCommand(null, null,
                null, null, null));
    }

    @Test
    public void execute_orderAcceptedByModel_addSuccessful() throws Exception {
        AddOrderCommandTest.ModelStubAcceptingOrderAdded modelStub = new AddOrderCommandTest
                .ModelStubAcceptingOrderAdded();
        Order validOrder = new OrderBuilder().build();

        CommandResult commandResult = new AddOrderCommand(new Phone(OrderBuilder.DEFAULT_PHONE),
                new Remark(OrderBuilder.DEFAULT_REMARK), getDetailsList(OrderBuilder.DEFAULT_DETAILS),
                new DeliveryDateTime(OrderBuilder.DEFAULT_DELIVERYDATETIME), CollectionType.DELIVERY)
                .execute(modelStub);

        assertEquals(String.format(AddOrderCommand.MESSAGE_ORDER_SUCCESS, validOrder),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validOrder).toString(), modelStub.ordersAdded.toString());
    }

    @Test
    public void execute_duplicateOrder_throwsCommandException() {
        Order validOrder = new OrderBuilder().withUuid(PersonBuilder.DEFAULT_UUID).build();
        AddOrderCommand addOrderCommand = new AddOrderCommand((new Phone(OrderBuilder.DEFAULT_PHONE)),
                new Remark(OrderBuilder.DEFAULT_REMARK), getDetailsList(OrderBuilder.DEFAULT_DETAILS),
                new DeliveryDateTime(OrderBuilder.DEFAULT_DELIVERYDATETIME), CollectionType.DELIVERY);

        ModelStub modelStub = new AddOrderCommandTest.ModelStubWithOrder(validOrder);

        assertThrows(CommandException.class,
                AddOrderCommand.MESSAGE_DUPLICATE_ORDER, () -> addOrderCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        AddOrderCommand addAliceOrderCommand = new AddOrderCommand((new Phone(OrderBuilder.DEFAULT_PHONE)),
                new Remark(OrderBuilder.DEFAULT_REMARK), getDetailsList(OrderBuilder.DEFAULT_DETAILS),
                new DeliveryDateTime(OrderBuilder.DEFAULT_DELIVERYDATETIME), CollectionType.DELIVERY);
        AddOrderCommand addBobOrderCommand = new AddOrderCommand((new Phone("12345")),
                new Remark(OrderBuilder.DEFAULT_REMARK), getDetailsList(OrderBuilder.DEFAULT_DETAILS),
                new DeliveryDateTime(OrderBuilder.DEFAULT_DELIVERYDATETIME), CollectionType.DELIVERY);

        // same object -> returns true
        assertTrue(addAliceOrderCommand.equals(addAliceOrderCommand));

        // same values -> returns true
        AddOrderCommand addAliceOrderCommandCopy = new AddOrderCommand((new Phone(OrderBuilder.DEFAULT_PHONE)),
                new Remark(OrderBuilder.DEFAULT_REMARK), getDetailsList(OrderBuilder.DEFAULT_DETAILS),
                new DeliveryDateTime(OrderBuilder.DEFAULT_DELIVERYDATETIME), CollectionType.DELIVERY);
        assertTrue(addAliceOrderCommand.equals(addAliceOrderCommandCopy));

        // different types -> returns false
        assertFalse(addAliceOrderCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceOrderCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceOrderCommand.equals(addBobOrderCommand));
    }

    /**
     * A Model stub that contains a single Order.
     */
    private class ModelStubWithOrder extends ModelStub {
        private final Order order;

        ModelStubWithOrder(Order order) {
            requireNonNull(order);
            this.order = order;
        }

        @Override
        public boolean hasOrder(Order order) {
            requireNonNull(order);
            return this.order.equals(order);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            ObservableList<Person> list = FXCollections.observableArrayList();
            list.add(new PersonBuilder().build());
            FilteredList<Person> filteredPersons = new FilteredList<Person>(list);
            return filteredPersons;
        }
    }

    /**
     * A Model stub that always accept the order being added.
     */
    private class ModelStubAcceptingOrderAdded extends ModelStub {
        final ArrayList<Order> ordersAdded = new ArrayList<>();

        @Override
        public boolean hasOrder(Order order) {
            requireNonNull(order);
            return ordersAdded.stream().anyMatch(order::equals);
        }

        @Override
        public void addOrder(Order order) {
            requireNonNull(order);
            ordersAdded.add(order);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public ObservableList<Person> getPersonList() {
            ObservableList<Person> list = FXCollections.observableArrayList();
            list.add(new PersonBuilder().build());
            FilteredList<Person> filteredPersons = new FilteredList<Person>(list);
            return filteredPersons;
        }
    }

}
