package com.javasm.demo.service.impl;

import com.javasm.demo.dao.AreaDao;
import com.javasm.demo.dao.AreaDaoImpl;
import com.javasm.demo.entity.Area;
import com.javasm.demo.service.AreaService;

import java.util.List;

public class AreaServiceImpl implements AreaService {
  private   AreaDao areaDao=new AreaDaoImpl();
    @Override
    public List<Area> getAreaList(Integer parentId) {
        List<Area> areaList = areaDao.getAreaList(parentId);
        return areaList;
    }
}
