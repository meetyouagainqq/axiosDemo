package com.javasm.demo.service;

import com.javasm.demo.entity.Area;

import java.util.List;

public interface AreaService {
    public List<Area> getAreaList(Integer parentId);
}
