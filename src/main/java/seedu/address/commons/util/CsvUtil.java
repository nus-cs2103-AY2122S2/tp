package seedu.address.commons.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import seedu.address.model.person.Person;
import seedu.address.storage.CsvAdaptedPerson;

public class CsvUtil {

    public static String headers = "Name,Phone Number,Email,Insurance Package,Address,Tags";

    public static void saveCsvFile(List<CsvAdaptedPerson> persons, Path filePath) {

        try {
            FileWriter fw = new FileWriter(filePath.toString());
            fw.write(headers + "\n");
            for (CsvAdaptedPerson p : persons) {
               fw.write(p.toCsvString() + "\n");
            }
            fw.close();
            System.out.println("Done");
        } catch (IOException err) {
            System.out.println("Error");
        }
    }

    public static List<Person> loadCsvFile(Path filePath) {

        ArrayList<Person> persons = new ArrayList<>();
        Scanner s;
        String personString;
        Person p;

        try {
            s = new Scanner(new File(filePath.toString())); // create a Scanner using the File as the source
        } catch (IOException err) {
            System.out.println("Error");
            return persons;
        }

        s.nextLine();  // headers
        System.out.println("hello");
        while (s.hasNext()) {
            personString = s.nextLine();
            p = CsvAdaptedPerson.fromCsvString(personString);
            persons.add(p);
        }

        return persons;
    }

}
