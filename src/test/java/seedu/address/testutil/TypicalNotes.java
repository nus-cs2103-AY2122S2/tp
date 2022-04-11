package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.client.Note;

public class TypicalNotes {

    public static final Note SCHOOL = new NoteBuilder()
            .withNote("Studies at NUS")
            .build();

    public static final Note OVERSEAS = new NoteBuilder()
            .withNote("Is travelling to Japan in June")
            .build();

    private TypicalNotes() {} // prevents instantiation

    public static List<Note> getTypicalPreferenceMaps() {
        return new ArrayList<>(Arrays.asList(SCHOOL, OVERSEAS));
    }
}
