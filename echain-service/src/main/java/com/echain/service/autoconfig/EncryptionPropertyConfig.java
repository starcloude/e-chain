package com.echain.service.autoconfig;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;

import com.echain.common.utils.AesEncodeUtil;
import com.google.common.collect.Sets;
import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyFilter;
import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyResolver;

import lombok.Builder;
import lombok.Data;

@Profile("!dev")
@Configuration
public class EncryptionPropertyConfig {

	@Autowired  
	private Environment env;  
	
	private final Pattern envPattern = Pattern.compile("\\$\\{(.*?)\\}");
	
	/**
	 * 需要解密的key
	 */
	private final Set<String> keySets = Sets.newHashSet(
			//Redis
			"spring.redis.host",
			"spring.redis.port",
			"spring.redis.password",
			
			//通用
			"spring.datasource.username",
			"spring.datasource.password",
			"spring.datasource.url",
			
			//hikari
			"spring.datasource.hikari.username",
			"spring.datasource.hikari.password",
			"spring.datasource.hikari.url",
			
			//druid
			"spring.datasource.druid.username",
			"spring.datasource.druid.password",
			"spring.datasource.druid.url"
			
			);
	
	//解密
	@Bean(name = "encryptablePropertyResolver")
    public EncryptablePropertyResolver encryptablePropertyResolver() {
        return new EncryptionPropertyResolver();
    }
 
	//过滤
	@Bean(name="encryptablePropertyFilter")
    EncryptablePropertyFilter encryptablePropertyFilter() {
        return new EncryptionPropertyFilter();
    }
	
	//过滤器
	class EncryptionPropertyFilter implements EncryptablePropertyFilter {
        public boolean shouldInclude(PropertySource<?> source, String name) {
        	return keySets.contains(name);
        }
    }
	
	//解密器
    class EncryptionPropertyResolver implements EncryptablePropertyResolver {
        @Override
        public String resolvePropertyValue(String value) {
            if (StringUtils.isBlank(value)) {
                return value;
            }
            Matcher matcher = envPattern.matcher(value);
            if(matcher.find()) {
            	EnvParam param = getEnvParam(matcher.group(1));
            	value = env.getProperty(param.getKey(),param.getDefaultValue());
            }
            return resolveDESValue(value);
        }
 
        private String resolveDESValue(String value) {
            // 自定义DES密文解密
            return AesEncodeUtil.decrypt(value);
        }
    }
    
    private EnvParam getEnvParam(String value) {
    	String key = value,defaultValue = null;
    	if(value.indexOf(":")>0) {
    		key = value.substring(0,value.indexOf(":"));
    		defaultValue = value.substring(value.indexOf(":")+1);
    	}
    	return EnvParam.builder().key(key).defaultValue(defaultValue).build();
    }
}

@Data
@Builder
class EnvParam{
	private String key;
	private String defaultValue;
}
