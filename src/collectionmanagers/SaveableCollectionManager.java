package collectionmanagers;

import java.io.FileNotFoundException;


public interface SaveableCollectionManager<T> extends CollectionManager<T>{
    boolean save() throws FileNotFoundException;
}
