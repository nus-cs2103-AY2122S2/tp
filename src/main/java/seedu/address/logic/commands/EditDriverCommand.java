package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DRIVERS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.driver.Driver;
import seedu.address.model.driver.DriverStatus;
import seedu.address.model.driver.NameDriver;
import seedu.address.model.driver.PhoneDriver;


/**
 * Edits the details of an existing driver in the database.
 */
public class EditDriverCommand extends Command {
    public static final String COMMAND_WORD = "editdriver";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the driver identified "
            + "by the index number used in the displayed driver list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Driver's status can only be changed when the driver is free.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_STATUS + "STATUS]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 ";

    public static final String MESSAGE_EDIT_DRIVER_SUCCESS = "Edited Driver: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_DRIVER = "This driver already exists in the database.";
    public static final String MESSAGE_UPDATE_TO_BUSY_FAIL = "Editing Driver: %1$s "
            + "failed because cannot update the status of driver to BUSY";
    public static final String MESSAGE_DELETE_DRIVER_FAIL_BUSY = "Editing Driver: %1$s "
            + "failed because driver is not free";

    private final Index index;
    private final EditDriverDescriptor editDriverDescriptor;

    /**
     * @param index of the driver in the filtered driver list to edit
     * @param editDriverDescriptor details to edit the driver with
     */
    public EditDriverCommand(Index index, EditDriverDescriptor editDriverDescriptor) {
        requireNonNull(index);
        requireNonNull(editDriverDescriptor);

        this.index = index;
        this.editDriverDescriptor = new EditDriverDescriptor(editDriverDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Driver> lastShownList = model.getFilteredDriverList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DRIVER_DISPLAYED_INDEX);
        }

        Driver driverToEdit = lastShownList.get(index.getZeroBased());
        Driver editedDriver = createEditedDriver(driverToEdit, editDriverDescriptor);


        if (model.hasRepeatedNameOrPhoneDriver(driverToEdit, editedDriver)) {
            throw new CommandException(MESSAGE_DUPLICATE_DRIVER);
        }

        if (driverToEdit.isBusy()) {
            throw new CommandException(String.format(MESSAGE_DELETE_DRIVER_FAIL_BUSY, driverToEdit));
        }
        if (editedDriver.isBusy()) {
            throw new CommandException(String.format(MESSAGE_UPDATE_TO_BUSY_FAIL, driverToEdit));
        }


        model.setDriver(driverToEdit, editedDriver);
        model.updateFilteredDriverList(PREDICATE_SHOW_ALL_DRIVERS);
        return new CommandResult(String.format(MESSAGE_EDIT_DRIVER_SUCCESS, editedDriver), false,
                false, false, true, false);
    }

    /**
     * Creates and returns a {@code Driver} with the details of {@code driverToEdit}
     * edited with {@code editDriverDescriptor}.
     */
    private static Driver createEditedDriver(Driver driverToEdit,
                                               EditDriverDescriptor editDriverDescriptor) {
        assert driverToEdit != null;

        NameDriver updatedNameDriver = editDriverDescriptor.getName().orElse(driverToEdit.getName());
        PhoneDriver updatedPhoneDriver = editDriverDescriptor.getPhone().orElse(driverToEdit.getPhone());
        DriverStatus updatedStatusDriver = editDriverDescriptor
                .getStatus().orElse(driverToEdit.getStatus());

        return new Driver(updatedNameDriver, updatedPhoneDriver, updatedStatusDriver);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditDriverCommand)) {
            return false;
        }

        // state check
        EditDriverCommand e = (EditDriverCommand) other;
        return index.equals(e.index)
                && editDriverDescriptor.equals(e.editDriverDescriptor);
    }


    /**
     * Stores the details to edit the driver with each non-empty field value will replace the
     * corresponding field value of the driver.
     */
    public static class EditDriverDescriptor {
        private NameDriver nameDriver;
        private PhoneDriver phoneDriver;
        private DriverStatus driverStatus;

        public EditDriverDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditDriverDescriptor(EditDriverDescriptor toCopy) {
            setName(toCopy.nameDriver);
            setPhone(toCopy.phoneDriver);
            setStatus(toCopy.driverStatus);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(nameDriver, phoneDriver, driverStatus);
        }

        public void setName(NameDriver nameDriver) {
            this.nameDriver = nameDriver;
        }

        public Optional<NameDriver> getName() {
            return Optional.ofNullable(nameDriver);
        }

        public void setPhone(PhoneDriver phoneDriver) {
            this.phoneDriver = phoneDriver;
        }

        public Optional<PhoneDriver> getPhone() {
            return Optional.ofNullable(phoneDriver);
        }

        public void setStatus(DriverStatus driverStatus) {
            this.driverStatus = driverStatus;
        }

        public Optional<DriverStatus> getStatus() {
            return Optional.ofNullable(driverStatus);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditDriverDescriptor)) {
                return false;
            }

            // state check
            EditDriverDescriptor e = (EditDriverDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getStatus().equals(e.getStatus());
        }
    }
}
