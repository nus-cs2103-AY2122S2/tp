package seedu.address.testutil;

import seedu.address.model.client.Note;

public class NoteBuilder {
    public static final String DEFAULT_NOTE = "Studies at NUS";

    private String note;

    /**
     * Creates a {@code NoteBuilder} with the default details.
     */
    public NoteBuilder() {
        note = DEFAULT_NOTE;
    }

    /**
     * Sets the {@code note} of the {@code Note} that we are building.
     */
    public NoteBuilder withNote(String note) {
        this.note = note;
        return this;
    }

    /**
     * Returns a {@code Note} from this {@code NoteBuilder}
     */
    public Note build() {
        return new Note(note);
    }


}
