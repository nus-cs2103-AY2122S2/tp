package seedu.address.logic.commands.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.module.Module;

/**
 * Edits a module in LinkyTime.
 */
public class EditModuleCommand extends Command {
    public static final String COMMAND_WORD = "medit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the name of the module identified "
            + "by the index number used in the displayed list. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NAME + "MODULE_NAME "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "CS2103T";

    public static final String MESSAGE_EDIT_MODULE_SUCCESS = "Edited module: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in LinkyTime.";

    private final Index index;
    private final EditModuleDescriptor editModuleDescriptor;

    /**
     * @param index of the module in the filtered module list to edit
     * @param editModuleDescriptor details to edit the module with
     */
    public EditModuleCommand(Index index, EditModuleDescriptor editModuleDescriptor) {
        requireNonNull(index);
        requireNonNull(editModuleDescriptor);

        this.index = index;
        this.editModuleDescriptor = new EditModuleDescriptor(editModuleDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        final List<Module> lastShownList = model.getFilteredModuleList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        final Module moduleToEdit = lastShownList.get(index.getZeroBased());
        final Module editedModule = createEditedModule(moduleToEdit, editModuleDescriptor);

        if (!moduleToEdit.equals(editedModule) && model.hasModule(editedModule)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        final List<Meeting> lastShownMeetingList = model.getMeetingList();
        if (hasDependentMeetings(lastShownMeetingList, moduleToEdit)) {
            editAssociatedMeetings(model, moduleToEdit, editedModule);
        }
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        model.setModule(moduleToEdit, editedModule);

        return new CommandResult(String.format(MESSAGE_EDIT_MODULE_SUCCESS, editedModule));
    }

    /**
     * Creates and returns a {@code Module} with the details of {@code moduleToEdit}
     * edited with {@code editModuleDescriptor}.
     */
    private static Module createEditedModule(Module moduleToEdit,
            EditModuleDescriptor editModuleDescriptor) {
        assert moduleToEdit != null;

        final String updatedCode = editModuleDescriptor.getCode().orElse(moduleToEdit.getCode());

        return new Module(updatedCode);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditModuleCommand)) {
            return false;
        }

        // state check
        final EditModuleCommand e = (EditModuleCommand) other;
        return index.equals(e.index) && editModuleDescriptor.equals(e.editModuleDescriptor);
    }

    /**
     * Checks if the given module has meetings assigned to it.
     *
     * @param meetings The list of meetings to check.
     * @param module   The module to search for.
     * @return True, if there are dependent meetings in the list.
     */
    protected boolean hasDependentMeetings(List<Meeting> meetings, Module module) {
        return meetings.stream().anyMatch(meeting -> meeting.getModule().equals(module));
    }

    protected void editAssociatedMeetings(Model model, Module toEdit, Module edited) {
        model.getMeetingList()
                .stream()
                .filter(meeting -> meeting.getModule().equals(toEdit))
                .forEach((meeting) -> changeModule(model, meeting, edited));
    }

    protected void changeModule(Model model, Meeting meeting, Module module) {
        Meeting newMeeting = new Meeting(meeting.getName(), meeting.getUrl(), meeting.getStartDateTime(),
                meeting.getDuration(), module, meeting.getIsRecurring(), meeting.getTags());
        model.setMeeting(meeting, newMeeting);
    }

    /**
     * Stores the details to edit the module with. Each non-empty field value will replace the
     * corresponding field value of the module.
     */
    public static class EditModuleDescriptor {
        private String code;

        public EditModuleDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditModuleDescriptor(EditModuleDescriptor toCopy) {
            setCode(toCopy.code);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(code);
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Optional<String> getCode() {
            return Optional.ofNullable(code);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditModuleDescriptor)) {
                return false;
            }

            // state check
            final EditModuleDescriptor e = (EditModuleDescriptor) other;

            return getCode().equals(e.getCode());
        }
    }

}
