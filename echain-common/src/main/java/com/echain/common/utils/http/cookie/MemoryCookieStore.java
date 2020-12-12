package com.echain.common.utils.http.cookie;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * 内存缓存Cookie
 */
public class MemoryCookieStore implements CookieStore {
    private final Map<String, Map<String,Cookie>> allCookies = new ConcurrentHashMap<>();

    @Override
    public void add(HttpUrl uri, List<Cookie> cookies) {
        if (uri == null) {
            throw new NullPointerException("Uri must not be null.");
        }
        if (CollectionUtils.isEmpty(cookies)) {
            throw new NullPointerException("Cookies must not be null.");
        }
        Map<String,Cookie> urlCookies = allCookies.get(uri.host());
        if(MapUtils.isEmpty(urlCookies)) {
        	urlCookies = Maps.newConcurrentMap();
        }
        
        for(Cookie cookie : cookies){
        	urlCookies.put(cookie.name(), cookie);
        }
        allCookies.put(uri.host(),urlCookies);
    }
    
    boolean isSameDomain(String myHost,String targetHost) {
    	return targetHost.endsWith(myHost.substring(myHost.length()-6));
    }

    
    @Override
    public List<Cookie> get(HttpUrl uri) {
        if (uri == null) {
            throw new NullPointerException("Uri must not be null.");
        }
        List<Cookie> myCookie = Lists.newArrayList();
        for(String key : allCookies.keySet()) {
        	if(!isSameDomain(uri.host() , key)) {
        		continue;
        	}
        	Collection<Cookie> tmpList = allCookies.get(key).values();
        	if(CollectionUtils.isNotEmpty(tmpList)) {
        		myCookie.addAll(tmpList);
        	}
        }
        return myCookie.stream().filter(e->{return !isCookieExpired(e);}).collect(Collectors.toList());
    }

    @Override
    public Map<String,List<Cookie>> getCookies() {
        Map<String,List<Cookie>> cookies = Maps.newHashMap();
        allCookies.entrySet().forEach(entry->{
        	cookies.put(entry.getKey(), Lists.newArrayList(entry.getValue().values().stream().filter(e->{ return !isCookieExpired(e);}).collect(Collectors.toList())));
        });
        return cookies;
    }

    @Override
    public boolean remove(HttpUrl uri, Cookie cookie) {
        if (uri == null) {
            throw new NullPointerException("Uri must not be null.");
        }
        if (cookie == null) {
            throw new NullPointerException("Cookie must not be null.");
        }
        return allCookies.remove(uri.host()) != null;
    }

    @Override
    public boolean removeAll() {
        allCookies.clear();
        return true;
    }
    
    /**
     * 是否相同的域名
     * @param cookie
     * @return
     */
    private boolean isCookieExpired(Cookie cookie) {
        return cookie.expiresAt() < System.currentTimeMillis();
    }

	@Override
	public void initCookies(Map<String, List<Cookie>> cookies) {
		cookies.entrySet().forEach(e->{
			Map<String,Cookie> cMap = Maps.newConcurrentMap();
			e.getValue().forEach(x->{
				cMap.put(x.name(), x);
			});
			allCookies.put(e.getKey(), cMap);	
		});
	}
}
