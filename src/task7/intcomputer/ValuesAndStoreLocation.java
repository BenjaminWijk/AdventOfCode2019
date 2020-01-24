package task7.intcomputer;

import java.util.ArrayList;

public class ValuesAndStoreLocation<T> extends ArrayList<T> {
    int storeLocation;


    public void setStoreLocation(int storeLocation) {
        this.storeLocation = storeLocation;
    }


    public T first(){
        return get(0);
    }

    public T last(){
        return get(size()-1);
    }
}
