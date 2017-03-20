package com.example.think.toutiao.util.event;

/**
 * Created by Think on 2017/3/15.
 */

public class FlushContentFragmentEvent {
    public int currentPage;

    public FlushContentFragmentEvent(int currentPage) {
        this.currentPage = currentPage;
    }
}
