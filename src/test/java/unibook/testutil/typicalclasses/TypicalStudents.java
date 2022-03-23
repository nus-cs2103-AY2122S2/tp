package unibook.testutil.typicalclasses;

import unibook.model.person.Student;
import unibook.testutil.builders.StudentBuilder;

public class TypicalStudents {
    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
        .withPhone("94351253").withEmail("alice@example.com")
        .withTags("friends").build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
        .withEmail("johnd@example.com").withPhone("98765432")
        .withTags("owesMoney", "friends").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz").withPhone("95352563")
        .withEmail("heinz@example.com").build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier").withPhone("87652533")
        .withEmail("cornelia@example.com").withTags("friends").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer").withPhone("9482224")
        .withEmail("werner@example.com").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withPhone("9482427")
        .withEmail("lydia@example.com").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best").withPhone("9482442")
        .withEmail("anna@example.com").build();
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withPhone("8482424")
        .withEmail("stefan@example.com").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withPhone("8482131")
        .withEmail("hans@example.com").build();

    private TypicalStudents() {
    } //prevents instantiation
}
