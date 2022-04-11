package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalAddressBook.FIRST;
import static seedu.address.testutil.TypicalAddressBook.FOURTH;
import static seedu.address.testutil.TypicalAddressBook.SECOND;
import static seedu.address.testutil.TypicalAddressBook.THIRD;

import org.junit.jupiter.api.Test;

import seedu.address.model.dish.Dish;

public class OrderTest {
    @Test
    public void totalPrice() {
        double price = 0;
        for (Dish dish : FIRST.getDishes()) {
            price += dish.getPrice().value;
        }
        assertTrue(FIRST.getTotalPrice() == price);

        price = 0;
        for (Dish dish : SECOND.getDishes()) {
            price += dish.getPrice().value;
        }
        assertTrue(SECOND.getTotalPrice() == price);

        price = 0;
        for (Dish dish : THIRD.getDishes()) {
            price += dish.getPrice().value;
        }
        assertTrue(THIRD.getTotalPrice() == price);

        price = 0;
        for (Dish dish : FOURTH.getDishes()) {
            price += dish.getPrice().value;
        }
        assertTrue(FOURTH.getTotalPrice() == price);
    }

}
