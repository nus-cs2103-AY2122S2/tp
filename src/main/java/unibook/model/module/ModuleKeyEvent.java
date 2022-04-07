package unibook.model.module;

import static unibook.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class ModuleKeyEvent {
    public static final String MESSAGE_CONSTRAINTS_TYPE = "Key Event type can only take in values from 1 to 4, "
        + "and cannot be empty.\n"
        + "1 = EXAM\n2 = QUIZ\n3 = ASSIGNMENT_RELEASE\n4 = ASSIGNMENT_DUE";
    public static final String MESSAGE_CONSTRAINTS_MISSINGDT = "Missing datetime field in your input. "
        + "Please specify the date and time of the key event in the following format!\n"
        + "dt/yyyy-MM-dd HH:mm";
    private LocalDateTime keyEventTiming;
    private KeyEventType keyEventType;

    private Module module;

    /**
     * Constructor for a ModuleKeyEvent.
     *
     * @param keyEventType
     * @param localDateTime
     * @param module
     */
    public ModuleKeyEvent(KeyEventType keyEventType, LocalDateTime localDateTime, Module module) {
        requireAllNonNull(keyEventType, localDateTime, module);
        this.keyEventType = keyEventType;
        this.keyEventTiming = localDateTime;
        this.module = module;
    }
    /**
     * Constructor for a empty ModuleKeyEvent.
     */
    public ModuleKeyEvent() {
        this.keyEventType = null;
        this.keyEventTiming = null;
        this.module = null;
    }

    public void setDateTime(LocalDateTime localDateTime) {
        this.keyEventTiming = localDateTime;
    }

    public void setKeyDateType(KeyEventType keyEventType) {
        this.keyEventType = keyEventType;
    }

    public KeyEventType getKeyEventType() {
        return this.keyEventType;
    }

    public LocalDateTime getKeyEventTiming() {
        return this.keyEventTiming;
    }

    public LocalDate getKeyEventDate() {
        int year = keyEventTiming.getYear();
        int month = keyEventTiming.getMonthValue();
        int dayOfMonth = keyEventTiming.getDayOfMonth();
        return LocalDate.of(year, month, dayOfMonth);
    }

    public Module getModule() {
        return this.module;
    }

    @Override
    public String toString() {
        return this.keyEventType + " (" + this.keyEventTiming + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ModuleKeyEvent)) {
            return false;
        }
        ModuleKeyEvent other = (ModuleKeyEvent) o;

        return other.getKeyEventTiming().equals(this.keyEventTiming)
            && other.getKeyEventType().equals(this.keyEventType);
    }

    public enum KeyEventType {
        //More can be added later
        EXAM, QUIZ, ASSIGNMENT_RELEASE, ASSIGNMENT_DUE
    }

}
