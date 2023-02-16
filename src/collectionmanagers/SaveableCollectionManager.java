package collectionmanagers;

import java.io.FileNotFoundException;


public interface SaveableCollectionManager extends CollectionManager{
    boolean save() throws FileNotFoundException;
}
