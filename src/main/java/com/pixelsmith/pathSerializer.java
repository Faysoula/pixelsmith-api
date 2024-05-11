package com.pixelsmith;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

// Custom serializer class
public class pathSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null) {
            // Replace single backslashes with double backslashes
            String sanitizedValue = value.replace("\\", "\\\\");
            gen.writeString(sanitizedValue);
        }
    }
}
