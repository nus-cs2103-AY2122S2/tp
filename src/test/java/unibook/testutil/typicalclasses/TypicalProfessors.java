package unibook.testutil.typicalclasses;

import unibook.model.person.Professor;
import unibook.testutil.builders.ProfessorBuilder;

/**
 * A utility class containing a list of {@code Professor} objects to be used in tests.
 */
public class TypicalProfessors {
    public static final Professor AMANDA = new ProfessorBuilder().withName("Amanda Ginsburg")
        .withPhone("94351223").withEmail("amanda@example.com")
        .withTags("helpful").withOffice("COM1 10-2").build();
    public static final Professor BENJAMIN = new ProfessorBuilder().withName("Benjamin Mcdonald")
        .withEmail("ben@example.com").withPhone("94365423").withOffice("FASS1 1-23").build();
    public static final Professor CATHERINE =
        new ProfessorBuilder().withName("Catherine Keratusa").withPhone("92352903")
            .withTags("friendly").withEmail("catherine@example.com").build();
    public static final Professor DAVID = new ProfessorBuilder().withName("David Melner").withPhone("87654533")
        .withEmail("david@example.com").withTags("stern").build();

    private TypicalProfessors() {
    } //prevents instantiation
}
