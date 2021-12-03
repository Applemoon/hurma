package ru.hurma.data;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ItemCategorySerializer extends JsonSerializer<Category> {

    @Override
    public void serialize(Category value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeObject(value.getName());
    }
}
