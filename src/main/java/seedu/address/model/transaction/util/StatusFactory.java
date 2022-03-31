package seedu.address.model.transaction.util;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.PayCommand;
import seedu.address.logic.commands.UnpayCommand;
import seedu.address.model.transaction.Status;

public class StatusFactory implements StatusFactoryInterface {
    @Override
    public Status getStatus(Class<? extends Command> command) {
        if (command.equals(PayCommand.class)) {
            return new Status(Status.STATUS_PAID);
        } else if (command.equals(UnpayCommand.class)) {
            return new Status(Status.STATUS_NOTPAID);
        } else {
            throw new InternalError(Messages.MESSAGE_INTERNAL_ERROR);
        }
    }
}
