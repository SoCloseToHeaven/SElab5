package collectionmanagers;

import common.Dragon;
import exceptions.InvalidFieldValueException;

import java.util.ArrayList;

public interface CollectionManager {

    ArrayList<Dragon> getCollection();
    boolean add() throws InvalidFieldValueException;
    boolean remove(int index);
    void clear();
    void open();
    void sort();

    boolean removeIf(long id);
}
