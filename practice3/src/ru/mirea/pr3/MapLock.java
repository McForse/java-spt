package ru.mirea.pr3;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MapLock<K, V> implements Map<K, V> {
    private volatile Map<K, V> map = new HashMap<>();
    private static final Lock lock = new ReentrantLock();

    @Override
    public int size() {
        lock.lock();
        int s = map.size();
        lock.unlock();
        return s;
    }

    @Override
    public boolean isEmpty() {
        lock.lock();
        boolean r = map.isEmpty();
        lock.unlock();
        return r;
    }

    @Override
    public boolean containsKey(Object key) {
        lock.lock();
        boolean r = map.containsKey(key);
        lock.unlock();
        return r;
    }

    @Override
    public boolean containsValue(Object value) {
        lock.lock();
        boolean r = map.containsValue(value);
        lock.unlock();
        return r;
    }

    @Override
    public V get(Object key) {
        lock.lock();
        V r = map.get(key);
        lock.unlock();
        return r;
    }

    @Override
    public V put(K key, V value) {
        lock.lock();
        V r = map.put(key, value);
        lock.unlock();
        return r;
    }

    @Override
    public V remove(Object key) {
        lock.lock();
        V r = map.remove(key);
        lock.unlock();
        return r;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        lock.lock();
        map.putAll(m);
        lock.unlock();
    }

    @Override
    public void clear() {
        lock.lock();
        map.clear();
        lock.unlock();
    }

    @Override
    public Set<K> keySet() {
        lock.lock();
        Set<K> r = map.keySet();
        lock.unlock();
        return r;
    }

    @Override
    public Collection<V> values() {
        lock.lock();
        Collection<V> r = map.values();
        lock.unlock();
        return r;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        lock.lock();
        Set<Entry<K, V>> r = map.entrySet();
        lock.unlock();
        return r;
    }

    @Override
    public boolean equals(Object o) {
        lock.lock();
        boolean r = map.equals(o);
        lock.unlock();
        return r;
    }

    @Override
    public int hashCode() {
        lock.lock();
        int r = map.hashCode();
        lock.unlock();
        return r;
    }
}
