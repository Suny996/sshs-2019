package com.sshs.core.util;

/**
 * Created by daisy.fu on 2018/10/22.
 */
public class TwoTuple<A, B> {

    public final Object first;
    public final Object second;

    public TwoTuple(Object a, Object b){
        first = a;
        second = b;
    }

    public String toString(){
        return first.toString()+second.toString();
    }
}
