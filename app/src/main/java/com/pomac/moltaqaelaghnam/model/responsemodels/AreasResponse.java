package com.pomac.moltaqaelaghnam.model.responsemodels;

import com.pomac.moltaqaelaghnam.model.Area;

import java.util.List;

public class AreasResponse {

    private int status;

    private List<Area> data;

    public int getStatus() {
        return status;
    }

    public List<Area> getData() {
        return data;
    }
}
