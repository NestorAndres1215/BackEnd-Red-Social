package com.na.backend.security.serializer;

import java.io.IOException;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class GrantedAuthorityJacksonDeserializer extends StdDeserializer<GrantedAuthority> {

    private static final long serialVersionUID = 1L;

    public GrantedAuthorityJacksonDeserializer() {
        super(GrantedAuthority.class);
    }

    @Override
    public GrantedAuthority deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String authority = node.get("authority").asText();
        return new SimpleGrantedAuthority(authority);
    }
}