package com.echain.common.utils;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Json {

	private static ObjectMapper mapper = new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	
	/**
	 * 将对象转成JSON
	 * @param o
	 * @return
	 */
	public static String toJSON(Object o) {
		if(o == null) {
			return null;
		}
		try {
			return mapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			log.error("toJson {}",o,e);
			return null;
		}
	}
	
	/**
	 * 美化输出
	 * @param o
	 * @return
	 */
	public static String formatJSON(Object o) {
		if(o == null) {
			return null;
		}
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
		} catch (JsonProcessingException e) {
			log.error("formatJSON {}",o,e);
			return null;
		}
	}
	
	/**
	 * 类型转换
	 * @param <T>
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T parse(String json,Class<T> clazz) {
		return readValue(json, mapper.getTypeFactory().constructType(clazz));
	}
	
	/**
	 *  转换List
	 * @param <T>
	 * @param json
	 * @param beanType
	 * @return
	 */
    public static <T> List<T> toList(String json, Class<T> beanType) {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, beanType);
        return readValue(json, javaType);
    }

    /**
     * 	转换map
     * @param <K>
     * @param <V>
     * @param json
     * @param keyType
     * @param valueType
     * @return
     */
    public static <K, V> Map<K, V> toMap(String json, Class<K> keyType, Class<V> valueType) {
        JavaType javaType = mapper.getTypeFactory().constructMapType(Map.class, keyType, valueType);
        return readValue(json, javaType);
    }
    
    /**
     * json to Object
     * @param <T>
     * @param json
     * @param javaType
     * @return
     */
    public static <T> T readValue(String json,JavaType javaType) {
    	try {
    		return mapper.readValue(json, javaType);
    	}catch(Exception ex) {
//    		log.error("无法解析JSON! \n {} ",json);
    		return null;
    	}
    }
    
    /**
     * json to Object
     * @param <T>
     * @param json
     * @param referenceType
     * @return
     */
    public static <T> T readValue(String json,TypeReference<T> typeReference) {
    	try {
    		return mapper.readValue(json, typeReference);
    	}catch(Exception ex) {
//    		log.error("无法解析JSON! \n {} ",json);
    		return null;
    	}
    }
}
