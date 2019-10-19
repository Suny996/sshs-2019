package com.sshs.core.util;

/**
 * Created by daisy.fu on 2018/10/22.
 */
public class ThreeTuple<A, B, C> extends com.sshs.core.util.TwoTuple<A, B> {

    public final Object third;

    public ThreeTuple(Object a, Object b, Object c) {
        super(a, b);
        this.third = c;
    }

    public String tostring(){
        return first.toString()+second.toString()+third.toString();
    }
}
