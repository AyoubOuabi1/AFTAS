package com.ayoub.aftas.aftas.helpers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CustomTimeDeserializer extends StdDeserializer<Time> {

    private final SimpleDateFormat inputFormatter = new SimpleDateFormat("HH:mm");
    private final SimpleDateFormat outputFormatter = new SimpleDateFormat("HH:mm:ss");

    public CustomTimeDeserializer() {
        this(null);
    }

    public CustomTimeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Time deserialize(JsonParser jp, DeserializationContext context) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        String timeString = node.asText();
        try {
            java.util.Date parsedDate = inputFormatter.parse(timeString);
            String formattedTime = outputFormatter.format(parsedDate);
            return Time.valueOf(formattedTime);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid time format", e);
        }
    }
}
