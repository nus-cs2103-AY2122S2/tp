package seedu.address.testutil;

import seedu.address.model.dish.Dish;
import seedu.address.model.dish.NameDish;
import seedu.address.model.dish.PriceDish;

/**
 * A utility class to help with building Dish objects.
 */
public class DishBuilder {

    public static final String DEFAULT_NAME = "Fried Rice";
    public static final String DEFAULT_PRICE = "20.00";

    private NameDish nameDish;
    private PriceDish priceDish;

    /**
     * Creates a {@code DishBuilder} with the default details.
     */
    public DishBuilder() {
        nameDish = new NameDish(DEFAULT_NAME);
        priceDish = new PriceDish(DEFAULT_PRICE);
    }

    /**
     * Initializes the DishBuilder with the data of {@code dishToCopy}.
     */
    public DishBuilder(Dish dishToCopy) {
        nameDish = dishToCopy.getName();
        priceDish = dishToCopy.getPrice();
    }

    /**
     * Sets the {@code NameDish} of the {@code Dish} that we are building.
     */
    public DishBuilder withName(String name) {
        this.nameDish = new NameDish(name);
        return this;
    }

    /**
     * Sets the {@code PriceDish} of the {@code Dish} that we are building.
     */
    public DishBuilder withPrice(String price) {
        this.priceDish = new PriceDish(price);
        return this;
    }


    public Dish build() {
        return new Dish(nameDish, priceDish);
    }

}

