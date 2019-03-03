package com.xiaozhu.repocket.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author haoshijing
 * @version 2018年06月19日 13:11
 **/
@Slf4j
public abstract class ThreadContext {

    public static final String CURRENT_AGENT_VO_KEY = ThreadContext.class.getName() + "_CURRENT_AGENT_VO_KEY";

    public static final String CURRENT_OPEN_ID_KEY = ThreadContext.class.getName() + "_CURRENT_OPEN_ID_KEY";

    public static final String CURRENT_USER_ID_KEY = ThreadContext.class.getName() + "_CURRENT_USER_ID_KEY";
    
    public static final String CURRENT_SYSUSER_VO_KEY = ThreadContext.class.getName() + "_CURRENT_SYSUSER_VO_KEY";
    
    private static final ThreadLocal<Map<Object, Object>> resources = new ThreadLocal<>();

    /**
     * Default no-argument constructor.
     */
    protected ThreadContext() {
    }

    /**
     * Returns the ThreadLocal Map. This Map is used internally to bind objects
     * to the current thread by storing each object under a unique key.
     *
     * @return the map of bound resources
     */
    public static Map<Object, Object> getResources() {
        if (resources.get() == null) {
            return Collections.emptyMap();
        } else {
            return new HashMap<Object, Object>(resources.get());
        }
    }

    public static void setResources(Map<Object, Object> newResources) {
        if (CollectionUtils.isEmpty(newResources)) {
            return;
        }
        ensureResourcesInitialized();
        resources.get().clear();
        resources.get().putAll(newResources);
    }

    private static void ensureResourcesInitialized() {
        if (resources.get() == null) {
            resources.set(new HashMap<Object, Object>());
        }
    }

    public static Object get(Object key) {
        if (log.isTraceEnabled()) {
            String msg = "get() - in thread [" + Thread.currentThread().getName() + "]";
            log.trace(msg);
        }

        Object value = getValue(key);
        if ((value != null) && log.isTraceEnabled()) {
            String msg = "Retrieved value of type [" + value.getClass().getName() + "] for key [" + key + "] " + "bound to thread [" + Thread.currentThread().getName() + "]";
            log.trace(msg);
        }
        return value;
    }

    public static void put(Object key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        if (value == null) {
            remove(key);
            return;
        }

        ensureResourcesInitialized();
        resources.get().put(key, value);

        if (log.isTraceEnabled()) {
            String msg = "Bound value of type [" + value.getClass().getName() + "] for key [" + key + "] to thread " + "[" + Thread.currentThread().getName() + "]";
            log.trace(msg);
        }
    }

    public static Object remove(Object key) {
        Map<Object, Object> perThreadResources = resources.get();
        Object value = perThreadResources != null ? perThreadResources.remove(key) : null;

        if ((value != null) && log.isTraceEnabled()) {
            String msg = "Removed value of type [" + value.getClass().getName() + "] for key [" + key + "]" + "from thread [" + Thread.currentThread().getName() + "]";
            log.trace(msg);
        }

        return value;
    }

    public static void remove() {
        resources.remove();
    }
    public static Object getCurrentAdmin() {
        return get(CURRENT_AGENT_VO_KEY);
    }


    public static void bindOpenId(String openId) {
        put(CURRENT_OPEN_ID_KEY, openId);
    }

    public static String getOpenId() {
      return (String) get(CURRENT_OPEN_ID_KEY);
    }

    public static Integer getCurrentUserId() {
        return (Integer) get(CURRENT_USER_ID_KEY);
    }

    public static void bindUserId(Integer userId) {
        put(CURRENT_USER_ID_KEY, userId);
    }

    public static void bindAdmin(Object agentVo) {
        if (agentVo != null) {
            put(CURRENT_AGENT_VO_KEY, agentVo);
        }
    }

    public static void unBindAdmin() {
        remove(CURRENT_AGENT_VO_KEY);
    }

    
    public static Object getCurrentSysUser() {
        return get(CURRENT_SYSUSER_VO_KEY);
    }
    
    public static void bindSysUser(Object sysUser) {
        if (sysUser != null) {
            put(CURRENT_SYSUSER_VO_KEY, sysUser);
        }
    }

    public static void unBindSysUser() {
        remove(CURRENT_SYSUSER_VO_KEY);
    }
    
    private static Object getValue(Object key) {
        Map<Object, Object> perThreadResources = resources.get();
        return perThreadResources != null ? perThreadResources.get(key) : null;
    }
}
