package collectionmanagers;


import java.util.ArrayList;

public interface CollectionManager<T> {

    ArrayList<T> getCollection();
    void add(T t);
    boolean remove(int index);
    void clear();
    void open();
    void sort();

    boolean removeIf(long id);
}
