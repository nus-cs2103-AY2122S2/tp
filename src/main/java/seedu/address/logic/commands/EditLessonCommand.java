package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_OOB_LESSON_DISPLAYED_INDEX;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION_HOURS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION_MINUTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_LESSONS;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.misc.InfoPanelTypes;
import seedu.address.logic.commands.misc.ViewTab;
import seedu.address.model.Model;
import seedu.address.model.lesson.ConflictingLessonsPredicate;
import seedu.address.model.lesson.DateTimeSlot;
import seedu.address.model.lesson.EnrolledStudents;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonAddress;
import seedu.address.model.lesson.LessonName;
import seedu.address.model.lesson.RecurringLesson;
import seedu.address.model.lesson.Subject;
import seedu.address.model.lesson.TemporaryLesson;

public class EditLessonCommand extends Command {

    public static final String COMMAND_WORD = "editlesson";
    public static final String SHORTENED_COMMAND_WORD = "el";
    public static final String COMMAND_DESCRIPTION = "Edit a lesson";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a lesson from the list of lessons "
            + "\n"
            + "Parameters: LESSON_ID ["
            + PREFIX_LESSON_NAME + " NAME] ["
            + PREFIX_SUBJECT + " SUBJECT] ["
            + PREFIX_LESSON_ADDRESS + " ADDRESS] "
            + "\n     ["
            + PREFIX_DATE + " DATE] ["
            + PREFIX_START_TIME + " START_TIME] "
            + "\n     ["
            + PREFIX_DURATION_HOURS + " DURATION IN HOURS] ["
            + PREFIX_DURATION_MINUTES + " DURATION IN MINUTES] "
            + "\n     "
            + "Example: "
            + "\n     "
            + COMMAND_WORD + " "
            + "2 "
            + PREFIX_DATE + " 19-12-2022 "
            + PREFIX_START_TIME + " 18:00 "
            + PREFIX_DURATION_HOURS + " 2 "
            + PREFIX_DURATION_MINUTES + " 15 "
            + "\nNote: You cannot change recurring lessons to be temporary and vice-versa.";

    public static final String MESSAGE_SUCCESS = "%1$s successfully edited!";
    public static final String MESSAGE_CONFLICTING_LESSON = "WARNING: Cannot edit lesson as new date and time"
            + " conflicts with these existing lessons in your schedule:";
    public static final String MESSAGE_DID_NOT_EDIT = "No details were provided to edit %1$s!";

    private final Index lessonId;
    private final EditLessonDescriptor editLessonDescriptor;

    /**
     * @param lessonId of the lesson to be edited in the filtered lesson list
     * @param editLessonDescriptor details to be edited
     */
    public EditLessonCommand(Index lessonId, EditLessonDescriptor editLessonDescriptor) {
        requireAllNonNull(lessonId, editLessonDescriptor);
        this.lessonId = lessonId;
        this.editLessonDescriptor = editLessonDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Lesson> lastShownList = model.getFilteredLessonList();

        boolean isIndexOutOfBounds = lastShownList.size() < lessonId.getOneBased();
        if (isIndexOutOfBounds) {
            throw new CommandException(MESSAGE_OOB_LESSON_DISPLAYED_INDEX);
        }

        Lesson lessonToEdit = lastShownList.get(lessonId.getZeroBased());
        Lesson editedLesson = createEditedLesson(lessonToEdit, editLessonDescriptor);
        requireNonNull(editedLesson);

        model.deleteLesson(lessonToEdit);
        if (model.hasConflictingLesson(editedLesson)) {
            model.addLesson(lessonToEdit);
            model.updateFilteredLessonList(new ConflictingLessonsPredicate(editedLesson));
            throw new CommandException(MESSAGE_CONFLICTING_LESSON, ViewTab.LESSON);
        }

        model.addLesson(editedLesson);
        model.setSelectedLesson(editedLesson);
        model.updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);

        String commandResultMessage = String.format(MESSAGE_SUCCESS, editedLesson.getName());
        return new CommandResult(commandResultMessage, InfoPanelTypes.LESSON);
    }

    private Lesson createEditedLesson(Lesson toEdit, EditLessonDescriptor editLessonDescriptor)
            throws CommandException {
        requireAllNonNull(toEdit, editLessonDescriptor);

        LessonName updatedName = getUpdatedName(toEdit, editLessonDescriptor);
        Subject updatedSubject = getUpdatedSubject(toEdit, editLessonDescriptor);
        LessonAddress updatedAddress = getUpdatedAddress(toEdit, editLessonDescriptor);
        LocalTime updatedStartTime = getUpdatedStartTime(toEdit, editLessonDescriptor);
        LocalDate updatedStartDate = getUpdatedStartDate(toEdit, editLessonDescriptor);
        Duration updatedDuration = getUpdatedDuration(toEdit, editLessonDescriptor);

        int durationHours = updatedDuration.getHour();
        int durationMinutes = updatedDuration.getMinutes();

        DateTimeSlot updatedDateTimeSlot = DateTimeSlot.makeDateTimeSlot(updatedStartDate.atTime(updatedStartTime),
                durationHours, durationMinutes);

        EnrolledStudents enrolledStudents = toEdit.getEnrolledStudents();

        if (toEdit instanceof RecurringLesson) {
            return Lesson.makeRecurringLesson(updatedName, updatedSubject,
                    updatedAddress, updatedDateTimeSlot, enrolledStudents);
        } else if (toEdit instanceof TemporaryLesson) {
            return Lesson.makeTemporaryLesson(updatedName, updatedSubject,
                    updatedAddress, updatedDateTimeSlot, enrolledStudents);
        }
        // Code shouldn't get to this point, as a Lesson can only be Recurring or Temporary
        assert false;
        return null;
    }

    /**
     * Gets the Updated Name.
     * If there's no new Name, return the original.
     */
    private LessonName getUpdatedName(Lesson toEdit, EditLessonDescriptor editLessonDescriptor) {
        return editLessonDescriptor.getName().orElse(toEdit.getName());
    }

    /**
     * Gets the Updated Subject.
     * If there's no new Subject, return the original.
     */
    private Subject getUpdatedSubject(Lesson toEdit, EditLessonDescriptor editLessonDescriptor) {
        return editLessonDescriptor.getSubject().orElse(toEdit.getSubject());
    }

    /**
     * Gets the Updated Address.
     * If there's no new Address, return the original.
     */
    private LessonAddress getUpdatedAddress(Lesson toEdit, EditLessonDescriptor editLessonDescriptor) {
        return editLessonDescriptor.getAddress().orElse(toEdit.getLessonAddress());
    }

    /**
     * Gets the Updated StartTime.
     * If there's no new StartTime, return the original.
     */
    private LocalTime getUpdatedStartTime(Lesson toEdit, EditLessonDescriptor editLessonDescriptor) {
        return editLessonDescriptor.getStartTime().orElse(toEdit.getDateTimeSlot().getDateOfLesson().toLocalTime());
    }

    /**
     * Gets the Updated StartDate.
     * If there's no new StartDate, return the original.
     */
    private LocalDate getUpdatedStartDate(Lesson toEdit, EditLessonDescriptor editLessonDescriptor) {
        return editLessonDescriptor.getStartDate().orElse(toEdit.getDateTimeSlot().getDateOfLesson().toLocalDate());
    }

    private Duration getUpdatedDuration(Lesson toEdit, EditLessonDescriptor editLessonDescriptor) {
        int durationHours;
        int durationMinutes;

        boolean isHourPrefixPresent = editLessonDescriptor.getDurationHours().isPresent();
        boolean isMinutesPrefixPresent = editLessonDescriptor.getDurationMinutes().isPresent();

        if (isHourPrefixPresent || isMinutesPrefixPresent) {
            durationHours = editLessonDescriptor.getDurationHours().orElse(0);
            durationMinutes = editLessonDescriptor.getDurationMinutes().orElse(0);
        } else {
            durationHours = toEdit.getDateTimeSlot().getHours();
            durationMinutes = toEdit.getDateTimeSlot().getMinutes();
        }

        return new Duration(durationHours, durationMinutes);
    }

    /**
     * Simple class to store hours and minutes in int.
     */
    protected static class Duration {
        private final int hour;
        private final int minutes;

        /**
         * Creates a {@code Duration object} with hours and minutes.
         * @param hour Hours in int.
         * @param minutes Minutes in int.
         */
        public Duration(int hour, int minutes) {
            this.hour = hour;
            this.minutes = minutes;
        }

        public int getHour() {
            return hour;
        }

        public int getMinutes() {
            return minutes;
        }
    }

    public static class EditLessonDescriptor {
        private LessonName name;
        private Subject subject;
        private LessonAddress address;
        private LocalDate startDate;
        private LocalTime startTime;
        private Integer durationHours;
        private Integer durationMinutes;

        public EditLessonDescriptor() {}

        public void setName(LessonName name) {
            this.name = name;
        }

        public void setSubject(Subject subject) {
            this.subject = subject;
        }

        public void setAddress(LessonAddress address) {
            this.address = address;
        }

        public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
        }

        public void setStartTime(LocalTime startTime) {
            this.startTime = startTime;
        }

        public void setDurationHours(Integer durationHours) {
            this.durationHours = durationHours;
        }

        public void setDurationMinutes(Integer durationMinutes) {
            this.durationMinutes = durationMinutes;
        }

        public Optional<LessonName> getName() {
            return Optional.ofNullable(name);
        }

        public Optional<Subject> getSubject() {
            return Optional.ofNullable(subject);
        }

        public Optional<LessonAddress> getAddress() {
            return Optional.ofNullable(address);
        }

        public Optional<LocalDate> getStartDate() {
            return Optional.ofNullable(startDate);
        }

        public Optional<LocalTime> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public Optional<Integer> getDurationHours() {
            return Optional.ofNullable(durationHours);
        }

        public Optional<Integer> getDurationMinutes() {
            return Optional.ofNullable(durationMinutes);
        }


    }
}
