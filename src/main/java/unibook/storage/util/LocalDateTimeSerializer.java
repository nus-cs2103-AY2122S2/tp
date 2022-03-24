package unibook.storage.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Jackson serializer for LocalDateTime objects.
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofLocalizedDateTime(FormatStyle.MEDIUM);

    @Override
    public void serialize(LocalDateTime dateTime, JsonGenerator generator, SerializerProvider provider) throws
        IOException {
        String s = dateTime.format(DATE_TIME_FORMATTER);
        generator.writeString(s);
    }
}
