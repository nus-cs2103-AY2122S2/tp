package unibook.model.module;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import unibook.testutil.Assert;

public class ModuleKeyEventTest {
    private LocalDateTime currentDateTime = LocalDateTime.now();
    private Module validModule = new Module(new ModuleName("Software Engineering"), new ModuleCode("CS2103"));

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ModuleKeyEvent(null, null, null));
    }
}
