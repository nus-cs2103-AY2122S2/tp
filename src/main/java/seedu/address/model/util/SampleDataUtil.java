package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.client.Plan;
import seedu.address.model.procedure.Completion;
import seedu.address.model.procedure.Cost;
import seedu.address.model.procedure.Date;
import seedu.address.model.procedure.Information;
import seedu.address.model.procedure.Procedure;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Client[] getSampleClients() {
        return new Client[] {
            new Client(new Name("MINISO"), new Phone("65705231"), new Email("miniso@example.com"),
                new Address("3155 Commonwealth Ave W, #03-56-58"), new Plan("EXPRESS 200MBps"),
                getTagSet("maintenance", "periodic"), getSampleProceduresList1()),

            new Client(new Name("Master Fix Services"), new Phone("96724552"), new Email("masterfix@example.com"),
                new Address("3155 Commonwealth Ave W, #B1-10"), new Plan("NORMAL 100MBps"),
                getTagSet("maintenance", "SME"), getSampleProceduresList2()),

            new Client(new Name("Mr Bean"), new Phone("66594724"), new Email("mrbean@example.com"),
                new Address("3155 Commonwealth Ave W, #B1-K13"), new Plan("VALUE 50MBps"),
                getTagSet("household"), getSampleProceduresList3()),

            new Client(new Name("Optical 88"), new Phone("66595327"), new Email("optical88@example.com"),
                new Address("3155 Commonwealth Ave W, #05-27"), new Plan("EXPRESS 200MBps"),
                getTagSet("new"), getSampleProceduresList4()),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Client sampleClient : getSampleClients()) {
            sampleAb.addClient(sampleClient);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a list of sample procedures.
     */
    public static List<Procedure> getSampleProceduresList1() {
        Procedure sampleProcedure1 = new Procedure(new Information("Fix Modem"), new Date("01/01/2021 15:55"),
            new Cost("50"), new Completion("true"));

        Procedure sampleProcedure2 = new Procedure(new Information("Troubleshoot their router"),
            new Date("03/03/2022 15:35"), new Cost("60"), new Completion("true"));

        Procedure sampleProcedure3 = new Procedure(new Information("Run a network diagnostic test"),
            new Date("05/04/2022 11:55"), new Cost("10"), new Completion("true"));

        Procedure sampleProcedure4 = new Procedure(new Information("Extend international warranty of modem"),
            new Date("07/05/2022 12:25"), new Cost("125"), new Completion("false"));

        Procedure sampleProcedure5 = new Procedure(
            new Information("Inspect the network performance of their cash register"),
            new Date("07/08/2022 13:15"), new Cost("100"), new Completion("false"));

        Procedure sampleProcedure6 = new Procedure(
            new Information("Back up their employee data to the cloud database"),
            new Date("10/09/2022 09:55"), new Cost("48"), new Completion("false"));

        ArrayList<Procedure> sampleProcedures = new ArrayList<>();
        sampleProcedures.add(sampleProcedure1);
        sampleProcedures.add(sampleProcedure2);
        sampleProcedures.add(sampleProcedure3);
        sampleProcedures.add(sampleProcedure4);
        sampleProcedures.add(sampleProcedure5);
        sampleProcedures.add(sampleProcedure6);
        return sampleProcedures;
    }

    /**
     * Returns a list of sample procedures.
     */
    public static List<Procedure> getSampleProceduresList2() {
        Procedure sampleProcedure1 = new Procedure(new Information("Run a network diagnostic test"),
            new Date("30/05/2022 11:55"), new Cost("20"), new Completion("false"));

        Procedure sampleProcedure2 = new Procedure(new Information("Troubleshoot their router"),
            new Date("31/05/2022 15:35"), new Cost("65"), new Completion("false"));

        Procedure sampleProcedure3 = new Procedure(new Information("Extend international warranty of modem"),
            new Date("01/06/2022 09:25"), new Cost("195"), new Completion("false"));

        Procedure sampleProcedure4 = new Procedure(
            new Information("Back up their employee data to the cloud database"),
            new Date("06/06/2022 09:55"), new Cost("230"), new Completion("false"));

        ArrayList<Procedure> sampleProcedures = new ArrayList<>();
        sampleProcedures.add(sampleProcedure1);
        sampleProcedures.add(sampleProcedure2);
        sampleProcedures.add(sampleProcedure3);
        sampleProcedures.add(sampleProcedure4);
        return sampleProcedures;
    }

    /**
     * Returns a list of sample procedures.
     */
    public static List<Procedure> getSampleProceduresList3() {
        Procedure sampleProcedure1 = new Procedure(new Information("Extend international warranty of modem"),
            new Date("01/06/2022 12:25"), new Cost("195"), new Completion("false"));

        Procedure sampleProcedure2 = new Procedure(
            new Information("Back up their employee data to the cloud database"),
            new Date("06/06/2022 09:55"), new Cost("230"), new Completion("false"));

        ArrayList<Procedure> sampleProcedures = new ArrayList<>();
        sampleProcedures.add(sampleProcedure1);
        sampleProcedures.add(sampleProcedure2);

        return sampleProcedures;
    }

    /**
     * Returns a list of sample procedures.
     */
    public static List<Procedure> getSampleProceduresList4() {
        Procedure sampleProcedure1 = new Procedure(new Information("Extend international warranty of modem"),
            new Date("06/06/2022 12:25"), new Cost("195"), new Completion("false"));

        Procedure sampleProcedure2 = new Procedure(
            new Information("Back up their employee data to the cloud database"),
            new Date("20/06/2022 09:55"), new Cost("230"), new Completion("false"));

        Procedure sampleProcedure3 = new Procedure(
            new Information("Inspect the network performance of their cash register"),
            new Date("23/08/2022 13:15"), new Cost("50"), new Completion("false"));

        ArrayList<Procedure> sampleProcedures = new ArrayList<>();
        sampleProcedures.add(sampleProcedure1);
        sampleProcedures.add(sampleProcedure2);
        sampleProcedures.add(sampleProcedure3);

        return sampleProcedures;
    }
}
