package seedu.address.model.dish;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DISH_NAME_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DISH_PRICE_DONUT;
import static seedu.address.testutil.TypicalAddressBook.APPLE;
import static seedu.address.testutil.TypicalAddressBook.BURGER;
import static seedu.address.testutil.TypicalAddressBook.DONUT;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DishBuilder;

public class DishTest {


    @Test
    public void isSameDish() {
        // same object -> returns true
        assertTrue(APPLE.isSameDish(APPLE));

        // null -> returns false
        assertFalse(APPLE.isSameDish(null));

        // same name, price different -> returns true
        Dish editedApple =
                new DishBuilder(APPLE).withPrice(VALID_DISH_PRICE_DONUT)
                                .build();
        assertTrue(APPLE.isSameDish(editedApple));

        // different name, same price -> returns false
        editedApple = new DishBuilder(APPLE).withName(VALID_DISH_NAME_DONUT).build();
        assertFalse(APPLE.isSameDish(editedApple));

        // name differs in case -> returns true
        Dish editedDonut = new DishBuilder(DONUT).withName(VALID_DISH_NAME_DONUT.toLowerCase())
                .build();
        assertTrue(DONUT.isSameDish(editedDonut));

        // name has trailing spaces -> returns false
        String nameWithTrailingSpaces = VALID_DISH_NAME_DONUT + " ";
        editedDonut = new DishBuilder(DONUT).withName(nameWithTrailingSpaces).build();
        assertFalse(DONUT.isSameDish(editedDonut));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Dish appleCopy = new DishBuilder(APPLE).build();
        assertTrue(APPLE.equals(appleCopy));

        // same object -> returns true
        assertTrue(APPLE.equals(APPLE));

        // null -> returns false
        assertFalse(APPLE.equals(null));

        // different type -> returns false
        assertFalse(APPLE.equals(5));

        // different customer -> returns false
        assertFalse(APPLE.equals(BURGER));

        // different name -> returns false
        Dish editedApple = new DishBuilder(APPLE).withName(VALID_DISH_NAME_DONUT).build();
        assertFalse(APPLE.equals(editedApple));

        // different price -> returns false
        editedApple = new DishBuilder(APPLE).withPrice(VALID_DISH_PRICE_DONUT).build();
        assertFalse(APPLE.equals(editedApple));
    }
}
