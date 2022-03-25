package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION_HOURS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION_MINUTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
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
import seedu.address.model.Model;
import seedu.address.model.lesson.DateTimeSlot;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonAddress;
import seedu.address.model.lesson.LessonName;
import seedu.address.model.lesson.RecurringLesson;
import seedu.address.model.lesson.Subject;

public class EditLessonCommand extends Command {
    public static final String COMMAND_WORD = "editlesson";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a lesson to the schedule"
            + "\n"
            + "Parameters: "
            + PREFIX_LESSON + " LESSON_ID "
            + PREFIX_LESSON_NAME + " NAME "
            + PREFIX_SUBJECT + " SUBJECT "
            + PREFIX_LESSON_ADDRESS + " ADDRESS "
            + "\n     "
            + PREFIX_DATE + " DATE "
            + PREFIX_START_TIME + " START_TIME "
            + "\n     "
            + PREFIX_DURATION_HOURS + " DURATION IN HOURS "
            + PREFIX_DURATION_MINUTES + " DURATION IN MINUTES "
            + "\n     "
            + "[EXAMPLE]: "
            + "\n     "
            + COMMAND_WORD + " "
            + PREFIX_LESSON + " 2 "
            + PREFIX_DATE + " 19-12-2022 "
            + PREFIX_START_TIME + " 18:00 "
            + PREFIX_DURATION_HOURS + " 2 "
            + PREFIX_DURATION_MINUTES + " 15 "
            + "\nNote: You cannot change recurring lessons to be temporary and vice-versa.";

    public static final String MESSAGE_SUCCESS = "%1$s successfully edited!";
    public static final String MESSAGE_CONFLICTING_LESSON = "Editing the time/date of this lesson conflicts "
            + "with an existing lesson in the schedule";
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
        if (lastShownList.size() < lessonId.getOneBased()) {
            throw new CommandException(MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }
        Lesson lessonToEdit = lastShownList.get(lessonId.getZeroBased());
        Lesson editedLesson = createEditedLesson(lessonToEdit, editLessonDescriptor);
        if (lessonToEdit.equals(editedLesson)) {
            throw new CommandException(String.format(MESSAGE_DID_NOT_EDIT, lessonToEdit.getName()));
        }
        model.deleteLesson(lessonToEdit);
        if (model.hasConflictingLesson(editedLesson)) {
            model.addLesson(lessonToEdit);
            model.updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
            throw new CommandException(MESSAGE_CONFLICTING_LESSON);
        }
        model.addLesson(editedLesson);
        model.setSelectedLesson(editedLesson);
        model.updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedLesson.getName()), true,
                InfoPanelTypes.LESSON, ViewTab.NONE);
    }

    private Lesson createEditedLesson(Lesson toEdit, EditLessonDescriptor editLessonDescriptor) {
        requireAllNonNull(toEdit, editLessonDescriptor);
        LessonName updatedName = editLessonDescriptor.getName().orElse(toEdit.getName());
        Subject updatedSubject = editLessonDescriptor.getSubject().orElse(toEdit.getSubject());
        LessonAddress updatedAddress = editLessonDescriptor.getAddress().orElse(toEdit.getLessonAddress());
        LocalTime updatedStartTime = editLessonDescriptor.getStartTime()
                .orElse(toEdit.getDateTimeSlot().getDateOfLesson().toLocalTime());
        LocalDate updatedStartDate = editLessonDescriptor.getStartDate()
                .orElse(toEdit.getDateTimeSlot().getDateOfLesson().toLocalDate());
        Integer durationHours = editLessonDescriptor.getDurationHours()
                .orElse(toEdit.getDateTimeSlot().getHours());
        Integer durationMinutes = editLessonDescriptor.getDurationMinutes()
                .orElse(toEdit.getDateTimeSlot().getMinutes());
        DateTimeSlot updatedDateTimeSlot = new DateTimeSlot(updatedStartDate.atTime(updatedStartTime),
                durationHours, durationMinutes);

        return toEdit instanceof RecurringLesson
                ? Lesson.makeRecurringLesson(updatedName, updatedSubject,
                updatedAddress, updatedDateTimeSlot, toEdit.getEnrolledStudents())
                : Lesson.makeTemporaryLesson(updatedName, updatedSubject,
                updatedAddress, updatedDateTimeSlot, toEdit.getEnrolledStudents());
    }

    public static class EditLessonDescriptor {
        private LessonName name;
        private Subject subject;
        private LessonAddress address;
        private LocalDate startDate;
        private LocalTime startTime;
        private Integer durationHours;
        private Integer durationMinutes;

        public EditLessonDescriptor () {}

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
