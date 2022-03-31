package seedu.trackbeau.logic.commands.service;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_PRICE;

import seedu.trackbeau.commons.core.Messages;
import seedu.trackbeau.logic.commands.Command;
import seedu.trackbeau.logic.commands.CommandResult;
import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.service.ServiceSearchContainsKeywordsPredicate;
import seedu.trackbeau.ui.Panel;

/**
 * Finds and lists all services in trackBeau whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindServiceCommand extends Command {

    public static final String COMMAND_WORD = "finds";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all service whose details match "
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PRICE + "PRICE] "
            + "[" + PREFIX_DURATION + "DURATION]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Relaxing Eye Treatment "
            + PREFIX_PRICE + "58 "
            + PREFIX_DURATION + "30";

    private final ServiceSearchContainsKeywordsPredicate predicate;

    public FindServiceCommand(ServiceSearchContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredServiceList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_SERVICES_LISTED_OVERVIEW, model.getFilteredServiceList().size()),
                Panel.SERVICE_PANEL);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindServiceCommand // instanceof handles nulls
                && predicate.equals(((FindServiceCommand) other).predicate)); // state check
    }
}
