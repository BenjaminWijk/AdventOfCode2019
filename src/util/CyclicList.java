package util;

import java.util.ArrayList;

public class CyclicList<T> extends ArrayList<T>{

    private int index;

    public T getNext(){
        if(index == size()){
            reset();
        }

        return get(index++);
    }

    public int getIndex(){
        return index;
    }

    public void reset(){
        index = 0;
    }
}
