package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.lab.Lab;
import seedu.address.model.lab.LabList;
import seedu.address.testutil.TypicalStudents;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsAddressBook.json");
    private static final Path INVALID_STUDENT_FILE = TEST_DATA_FOLDER.resolve("invalidStudentAddressBook.json");
    private static final Path DUPLICATE_STUDENT_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentAddressBook.json");
    private static final Path INVALID_LABSTATUS_FILE = TEST_DATA_FOLDER.resolve("invalidLabStatusAddressBook.json");
    private static final Path ALIGN_LAB_FILE = TEST_DATA_FOLDER.resolve("alignLabAddressBook.json");
    private static final Path EXPECTED_ALIGNED_FIlE = TEST_DATA_FOLDER.resolve("expectedAlignedLabAddressBook.json");
    private static final Path DUPLICATE_LAB_MASTERLIST_FILE =
            TEST_DATA_FOLDER.resolve("duplicateLabsInMasterListAddressBook.json");
    private static final Path DUPLICATE_LAB_FILE = TEST_DATA_FOLDER.resolve("duplicateLabAddressBook.json");

    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalStudentsAddressBook = TypicalStudents.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalStudentsAddressBook);
    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENT_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidLabStatusFile_defaultToUnsubmitted() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_LABSTATUS_FILE,
                JsonSerializableAddressBook.class).get();
        LabList expectedLabList = new LabList();
        expectedLabList.add(new Lab("1"));
        assertEquals(expectedLabList, dataFromFile.toModelType().getStudentList().get(0).getLabs());
    }

    @Test
    public void toModelType_alignLabFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(ALIGN_LAB_FILE,
                JsonSerializableAddressBook.class).get();
        JsonSerializableAddressBook expectedDataFromFile = JsonUtil.readJsonFile(EXPECTED_ALIGNED_FIlE,
                JsonSerializableAddressBook.class).get();
        assertEquals(expectedDataFromFile.toModelType(), dataFromFile.toModelType());
    }

    @Test
    public void toModelType_duplicateLabInMasterList_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_LAB_MASTERLIST_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_LAB,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateLab_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_LAB_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonAdaptedStudent.MESSAGE_DUPLICATE_LABS,
                dataFromFile::toModelType);
    }

}
