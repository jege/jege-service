package org.jege.util;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.READ_ENUMS_USING_TO_STRING;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_ENUMS_USING_TO_STRING;

import java.io.IOException;
import java.util.Map;

import org.jege.user.UserModule;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    static {
        objectMapper.setSerializationInclusion(NON_NULL);
        objectMapper.enable(WRITE_ENUMS_USING_TO_STRING);
        objectMapper.enable(READ_ENUMS_USING_TO_STRING);
        
        objectMapper.registerModule(new UserModule());
    }
    
    public static <T> T jsonToObject(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
        return objectMapper.readValue(json, clazz);
    }
    
    @SuppressWarnings("unchecked")
    public static Map<String, Object> jsonToMap(String json) throws JsonParseException, JsonMappingException, IOException {
        return JsonMapper.jsonToObject(json, Map.class);
    }
    
    public static String objectToJson(Object object) throws JsonGenerationException, JsonMappingException, IOException {
        return objectMapper.writeValueAsString(object);
    }
}
