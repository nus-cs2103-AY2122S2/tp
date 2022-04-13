package seedu.tinner.model.util;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.tinner.model.CompanyList;
import seedu.tinner.model.ReadOnlyCompanyList;
import seedu.tinner.model.company.Address;
import seedu.tinner.model.company.Company;
import seedu.tinner.model.company.CompanyName;
import seedu.tinner.model.company.Email;
import seedu.tinner.model.company.FavouriteStatus;
import seedu.tinner.model.company.Phone;
import seedu.tinner.model.company.RoleList;
import seedu.tinner.model.role.Description;
import seedu.tinner.model.role.ReminderDate;
import seedu.tinner.model.role.Role;
import seedu.tinner.model.role.Status;
import seedu.tinner.model.role.Stipend;

/**
 * Contains utility methods for populating {@code CompanyList} with sample data.
 */
public class SampleDataUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final LocalDate TODAY = LocalDate.now();

    private static final Role SAMPLE_ROLE_1 = new Role(
            new seedu.tinner.model.role.RoleName("Backend engineer"),
            new Status("interview and assessments"), new ReminderDate(TODAY.plusDays(2).format(FORMATTER) + " 18:00"),
            new Description("Backend engineer"), new Stipend("1200"));
    private static final Role SAMPLE_ROLE_2 = new Role(
            new seedu.tinner.model.role.RoleName("Frontend engineer"),
            new Status("pending"), new ReminderDate(TODAY.plusDays(3).format(FORMATTER) + " 22:00"),
            new Description("Frontend engineer"), new Stipend("1500"));
    private static final Role SAMPLE_ROLE_3 = new Role(
            new seedu.tinner.model.role.RoleName("Data analyst"),
            new Status("applying"), new ReminderDate(TODAY.plusDays(5).format(FORMATTER) + " 10:00"),
            new Description("Data analyst"), new Stipend("1400"));

    private static final RoleList SAMPLE_ROLES_1 = new RoleList();
    private static final RoleList SAMPLE_ROLES_2 = new RoleList();
    private static final RoleList SAMPLE_ROLES_3 = new RoleList();

    public static Company[] getSampleCompanies() {
        SAMPLE_ROLES_1.setRoles(Arrays.asList(SAMPLE_ROLE_1));
        SAMPLE_ROLES_2.setRoles(Arrays.asList(SAMPLE_ROLE_2));
        SAMPLE_ROLES_3.setRoles(Arrays.asList(SAMPLE_ROLE_2, SAMPLE_ROLE_3));

        return new Company[]{
                new Company(new CompanyName("Meta"), new Phone("87438807"), new Email("hr@meta.com"),
                        new Address("Blk 30 Geylang Street 29, #06-40"), SAMPLE_ROLES_1,
                        new FavouriteStatus(false)),
                new Company(new CompanyName("Amazon"), new Phone("99272758"), new Email("recruitment@amazon.com"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), SAMPLE_ROLES_2,
                        new FavouriteStatus(false)),
                new Company(new CompanyName("Apple"), new Phone("92492021"), new Email("careers@apple.com"),
                        new Address("Blk 47 Tampines Street 20, #17-35"), SAMPLE_ROLES_3,
                        new FavouriteStatus(false)),
        };
    }

    public static ReadOnlyCompanyList getSampleCompanyList() {
        CompanyList sampleAb = new CompanyList();
        for (Company sampleCompany : getSampleCompanies()) {
            sampleAb.addCompany(sampleCompany);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static List<Role> getRoleList(Role... roles) {
        return Arrays.stream(roles)
                .collect(Collectors.toList());
    }

}
