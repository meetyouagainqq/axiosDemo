package com.javasm.demo.dao;

import com.javasm.demo.entity.Area;

import java.util.List;

public interface AreaDao {
 public List<Area> getAreaList(Integer parentId);
}
