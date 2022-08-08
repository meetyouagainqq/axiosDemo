package com.javasm.demo.dao;

import com.javasm.demo.basedao.BaseDAO;

import com.javasm.demo.entity.Area;
import com.javasm.demo.util.DBHelper;
import org.springframework.jca.cci.connection.ConnectionFactoryUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaDaoImpl  implements AreaDao {
    @Override
    public List<Area> getAreaList(Integer parentId) {

        Connection conn = DBHelper.getConn();
        String sql = "select * from tb_area ta where ta.parent_id = ?";
        List<Area> areaList = new ArrayList<Area>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,parentId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                /*
                 * 封装结果集
                 * 程序员手动写
                 * ORM映射工具  实体关系映射
                 * */

                int areaId = resultSet.getInt("area_id");
                String areaName = resultSet.getString("area_name");
                Area area = new Area(areaId,areaName);
                areaList.add(area);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {

              DBHelper.closeConn(conn,preparedStatement,resultSet);
        }
        return areaList;
    }
}
