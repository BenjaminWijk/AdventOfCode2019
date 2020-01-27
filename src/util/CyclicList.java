package util;

import java.util.ArrayList;

public class CyclicList<T> extends ArrayList<T>{

    private int index;
    public int loopCounter;

    public T getNext(){
        if(index == size()){
            reset();
        }

        return get(index++);
    }

    public int getIndex(){
        return index;
    }

    public T getLast(){
        return get(size()-1);
    }

    public void reset(){
        index = 0;
        loopCounter++;
    }
}
