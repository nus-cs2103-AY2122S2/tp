package seedu.address.model.transaction.util;

import seedu.address.logic.commands.Command;
import seedu.address.model.transaction.Status;

public interface StatusFactoryInterface {
    Status getStatus(Class<? extends Command> command);
}
