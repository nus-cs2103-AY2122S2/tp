package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;
import seedu.address.model.person.UuidContainsKeywordsPredicate;

/**
 * Panel containing the list of orders.
 */
public class OrderListPanel extends UiPart<Region> {
    private static final String FXML = "OrderListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(OrderListPanel.class);

    @FXML
    private ListView<Order> orderListView;

    /**
     * Creates a {@code OrderListPanel} with the given {@code ObservableList}.
     */
    public OrderListPanel(ObservableList<Order> orderList, ObservableList<Person> personList) {
        super(FXML);
        orderListView.setItems(orderList);
        orderListView.setCellFactory(listView -> new OrderListViewCell(personList));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Order} using a {@code OrderCard}.
     */
    class OrderListViewCell extends ListCell<Order> {
        private final ObservableList<Person> personList;

        OrderListViewCell(ObservableList<Person> personList) {
            this.personList = personList;
        }
        @Override
        protected void updateItem(Order order, boolean empty) {
            super.updateItem(order, empty);

            if (empty || order == null) {
                setGraphic(null);
                setText(null);
            } else {
                ArrayList<String> list = new ArrayList<>(1);
                list.add(order.getUuid().toString());
                List<Person> singlePersonList = personList.filtered(new UuidContainsKeywordsPredicate(list));
                if (!singlePersonList.isEmpty()) {
                    Person person = singlePersonList.get(0);
                    setGraphic(new OrderCard(order, getIndex() + 1, person).getRoot());
                } else {
                    setGraphic(new OrderCard(order, getIndex() + 1).getRoot());
                }
            }
        }
    }

}
