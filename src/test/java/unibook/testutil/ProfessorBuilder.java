package unibook.testutil;

import static unibook.logic.commands.CommandTestUtil.VALID_EMAIL_PROFESSOR_CHARLES;
import static unibook.logic.commands.CommandTestUtil.VALID_NAME_PROFESSOR_CHARLES;
import static unibook.logic.commands.CommandTestUtil.VALID_OFFICE_2;
import static unibook.logic.commands.CommandTestUtil.VALID_PHONE_PROFESSOR_CHARLES;

import java.util.HashSet;
import java.util.Set;

import unibook.model.module.Module;
import unibook.model.person.Email;
import unibook.model.person.Name;
import unibook.model.person.Office;
import unibook.model.person.Phone;
import unibook.model.person.Professor;
import unibook.model.tag.Tag;
import unibook.model.util.SampleDataUtil;

/**
 * Used to instantiate a {@code Professor} object for testing.
 * Recommended to use default fields where possible.
 */
public class ProfessorBuilder {
    public static final String DEFAULT_NAME = VALID_NAME_PROFESSOR_CHARLES;
    public static final String DEFAULT_PHONE = VALID_PHONE_PROFESSOR_CHARLES;
    public static final String DEFAULT_EMAIL = VALID_EMAIL_PROFESSOR_CHARLES;
    public static final String DEFAULT_OFFICE = VALID_OFFICE_2;

    private String option;
    private Name name;
    private Phone phone;
    private Email email;
    private Office office;
    private Set<Tag> tags;
    private Set<Module> modules;

    /**
     * Creates a {@code ProfessorBuilder} with the default details.
     */
    public ProfessorBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        office = new Office(DEFAULT_OFFICE);
        tags = new HashSet<>();
        modules = new HashSet<>();
    }

    /**
     * Initializes the ProfessorBuilder with the data of {@code personToCopy}.
     */
    public ProfessorBuilder(Professor professorToCopy) {
        name = professorToCopy.getName();
        phone = professorToCopy.getPhone();
        email = professorToCopy.getEmail();
        office = professorToCopy.getOffice();
        tags = new HashSet<>(professorToCopy.getTags());
        modules = new HashSet<>(professorToCopy.getModules());
    }

    /**
     * Sets the {@code Name} of the {@code Professor} that we are building.
     */
    public ProfessorBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Professor} that we are building.
     */
    public ProfessorBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Professor} that we are building.
     */
    public ProfessorBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Professor} that we are building.
     */
    public ProfessorBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Office} of the {@code Office} that we are building.
     * @return
     */
    public ProfessorBuilder withOffice(String officeStr) {
        this.office = new Office(officeStr);
        return this;
    }


    public Professor build() {
        return new Professor(name, phone, email, tags, office, modules);
    }
}
