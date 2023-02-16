package util;


import java.util.ArrayList;

public class LRUCache<T> {

    private ArrayList<T> array = new ArrayList<>();

    private final int maxSize;

    public LRUCache(int maxSize) {
        if (maxSize <= 0)
            throw new IllegalArgumentException("Illegal capacity: %d".formatted(maxSize));
        this.maxSize = maxSize;
    }

    public void add(T element) {
        if (array.size() == maxSize)
            array.remove(0);
        array.add(element);
    }

    public void clear() {
        this.array = new ArrayList<>();
    }

    public ArrayList<T> getArray() {
        return this.array;
    }

    public int size() {
        return this.array.size();
    }
}
