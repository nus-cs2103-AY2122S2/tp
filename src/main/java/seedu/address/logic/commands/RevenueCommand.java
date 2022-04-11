package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import java.time.LocalDate;
import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.order.OrderDeliveredAndFromDatePredicate;

/**
 * Finds and lists all orders in FoodOnWheels, and displays the total
 * revenue generated in the current day based on the date on the operating system.
 *
 * The total revenue is calculated based on all the delivered orders in the current
 * day.
 */
public class RevenueCommand extends Command {
    public static final String COMMAND_WORD = "revenue";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the total revenue "
            + "of the current day "
            + "and displays all orders as a list with index numbers.\n";

    public static final String MESSAGE_SUCCESS = "Total revenue: $";

    private final OrderDeliveredAndFromDatePredicate predicate =
            new OrderDeliveredAndFromDatePredicate(List.of(LocalDate.now()));

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        double revenue = getRevenueForTheDay(model);
        return new CommandResult(MESSAGE_SUCCESS
                + String.format("%.2f", revenue), false, false, false, false, true);
    }

    public double getRevenueForTheDay(Model model) {
        requireNonNull(model);
        model.updateFilteredOrderList(predicate);
        double revenue = model.getFilteredOrderList().stream()
                .mapToDouble(x -> x.getTotalPrice()).sum();
        model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
        return revenue;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RevenueCommand // instanceof handles nulls
                && predicate.equals(((RevenueCommand) other).predicate)); // state check
    }
}
