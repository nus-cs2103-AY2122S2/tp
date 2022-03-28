package seedu.trackbeau.logic.commands.service;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.trackbeau.model.Model.PREDICATE_SHOW_ALL_SERVICES;

import java.util.List;
import java.util.Optional;

import seedu.trackbeau.commons.core.Messages;
import seedu.trackbeau.commons.core.index.Index;
import seedu.trackbeau.commons.util.CollectionUtil;
import seedu.trackbeau.logic.commands.Command;
import seedu.trackbeau.logic.commands.CommandResult;
import seedu.trackbeau.logic.commands.exceptions.CommandException;
import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.service.Duration;
import seedu.trackbeau.model.service.Price;
import seedu.trackbeau.model.service.Service;
import seedu.trackbeau.model.service.ServiceName;

/**
 * Edits the details of an existing service in trackBeau.
 */
public class EditServiceCommand extends Command {

    public static final String COMMAND_WORD = "edits";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the service identified "
        + "by the index number used in the displayed service list. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_NAME + "NAME] "
        + "[" + PREFIX_PRICE + "PRICE] "
        + "[" + PREFIX_DURATION + "DURATION]\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_PRICE + "138.00 "
        + PREFIX_DURATION + "90";

    public static final String MESSAGE_EDIT_SERVICE_SUCCESS = "Edited Service: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_SERVICE = "This service already exists in trackBeau.";

    private final Index index;
    private final EditServiceDescriptor editServiceDescriptor;

    /**
     * @param index                 of the service in the service list to edit
     * @param editServiceDescriptor details to edit the service with
     */
    public EditServiceCommand(Index index, EditServiceDescriptor editServiceDescriptor) {
        requireNonNull(index);
        requireNonNull(editServiceDescriptor);

        this.index = index;
        this.editServiceDescriptor = new EditServiceDescriptor(editServiceDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Service> lastShownList = model.getServiceList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SERVICE_DISPLAYED_INDEX);
        }

        Service serviceToEdit = lastShownList.get(index.getZeroBased());
        Service editedService = createEditedService(serviceToEdit, editServiceDescriptor);

        if (!serviceToEdit.isSameItem(editedService) && model.hasService(editedService)) {
            throw new CommandException(MESSAGE_DUPLICATE_SERVICE);
        }

        model.setService(serviceToEdit, editedService);
        model.updateServiceList(PREDICATE_SHOW_ALL_SERVICES);
        return new CommandResult(String.format(MESSAGE_EDIT_SERVICE_SUCCESS, editedService));
    }

    /**
     * Creates and returns a {@code Service} with the details of {@code serviceToEdit}
     * edited with {@code editServiceDescriptor}.
     */
    private static Service createEditedService(Service serviceToEdit, EditServiceDescriptor editServiceDescriptor) {
        assert serviceToEdit != null;

        ServiceName updatedName = editServiceDescriptor.getName().orElse(serviceToEdit.getName());
        Price updatePrice = editServiceDescriptor.getPrice().orElse(serviceToEdit.getPrice());
        Duration updatedDuration = editServiceDescriptor.getDuration().orElse(serviceToEdit.getDuration());
        return new Service(updatedName, updatePrice, updatedDuration);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditServiceCommand)) {
            return false;
        }

        // state check
        EditServiceCommand e = (EditServiceCommand) other;
        return index.equals(e.index) && editServiceDescriptor.equals(e.editServiceDescriptor);
    }

    /**
     * Stores the details to edit the service with. Each non-empty field value will replace the
     * corresponding field value of the service.
     */
    public static class EditServiceDescriptor {
        private ServiceName name;
        private Price price;
        private Duration duration;

        public EditServiceDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditServiceDescriptor(EditServiceDescriptor toCopy) {
            setName(toCopy.name);
            setPrice(toCopy.price);
            setDuration(toCopy.duration);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, price, duration);
        }

        public void setName(ServiceName name) {
            this.name = name;
        }

        public Optional<ServiceName> getName() {
            return Optional.ofNullable(name);
        }

        public void setPrice(Price price) {
            this.price = price;
        }

        public Optional<Price> getPrice() {
            return Optional.ofNullable(price);
        }

        public void setDuration(Duration duration) {
            this.duration = duration;
        }

        public Optional<Duration> getDuration() {
            return Optional.ofNullable(duration);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditServiceDescriptor)) {
                return false;
            }

            // state check
            EditServiceDescriptor e = (EditServiceDescriptor) other;

            return getName().equals(e.getName())
                && getPrice().equals(e.getPrice())
                && getDuration().equals(e.getDuration());
        }
    }
}
