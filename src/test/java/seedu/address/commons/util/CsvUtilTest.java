package seedu.address.commons.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.opencsv.exceptions.CsvValidationException;

public class CsvUtilTest {
    private static final String PATH = "src/test/data/ImportCsvTest/color_srgb.csv";

    @Test
    public void fileConformationTest_success() throws CsvValidationException, IOException {
        String expectedHeaders = "Name,HEX,RGB Values";
        Path p = Path.of(PATH);
        boolean isFileConforming = CsvUtil.isTheFileConformingToHeaders(p, expectedHeaders);

        assertTrue(isFileConforming);
    }

    @Test
    public void extractColumnTest_success() throws CsvValidationException, IOException {
        Path p = Path.of(PATH);
        List<String> nameColumnAsExtracted = CsvUtil.extractColumn("Name", p);
        List<String> hexColumnAsExtracted = CsvUtil.extractColumn("HEX", p);
        List<String> rgbColumnAsExtracted = CsvUtil.extractColumn("RGB Values", p);

        List<String> expectedNameColumnExtraction = new ArrayList<>(Arrays.asList("White", "Silver", "Gray", "Black",
                "Red", "Maroon", "Yellow", "Olive", "Lime", "Green", "Aqua", "Teal", "Blue", "Navy", "Fuchsia",
                "Purple"));

        List<String> expectedHexColumnExtraction = new ArrayList<>(Arrays.asList("#FFFFFF", "#C0C0C0", "#808080",
                "#000000", "#FF0000", "#800000", "#FFFF00", "#808000", "#00FF00", "#008000", "#00FFFF", "#008080",
                "#0000FF", "#000080", "#FF00FF", "#800080"));

        List<String> expectedRgbColumnExtraction = new ArrayList<>(Arrays.asList("rgb(100,100,100)", "rgb(75,75,75)",
                "rgb(50,50,50)", "rgb(0,0,0)", "rgb(100,0,0)", "rgb(50,0,0)", "rgb(100,100,0)", "rgb(50,50,0)",
                "rgb(0,100,0)", "rgb(0,50,0)", "rgb(0,100,100)", "rgb(0,50,50)", "rgb(0,0,100)", "rgb(0,0,50)",
                "rgb(100,0,100)", "rgb(50,0,50)"));

        assertThat(nameColumnAsExtracted).isEqualTo(expectedNameColumnExtraction);
        assertThat(hexColumnAsExtracted).isEqualTo(expectedHexColumnExtraction);
        assertThat(rgbColumnAsExtracted).isEqualTo(expectedRgbColumnExtraction);
    }
}
