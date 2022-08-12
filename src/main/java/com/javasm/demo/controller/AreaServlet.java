package com.javasm.demo.controller;


import cn.hutool.core.util.StrUtil;
import com.google.gson.Gson;
import com.javasm.demo.entity.Area;
import com.javasm.demo.entity.R;
import com.javasm.demo.entity.ResponseEnum;
import com.javasm.demo.service.AreaService;
import com.javasm.demo.service.impl.AreaServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

@WebServlet("/area")
public class AreaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          resp.setHeader("Access-Control-Allow-Origin","http://localhost:8100");
        /* 允许跨域的请求⽅法GET, POST, HEAD 等 */
        resp.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新预检验跨域的缓存时间 (s) */
        resp.setHeader("Access-Control-Max-Age", "3600");
        /* 允许跨域的请求头 */
        resp.setHeader("Access-Control-Allow-Headers", "*");
        /* 是否携带cookie */
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        req.setCharacterEncoding("UTF-8");
        String parentIdStr = req.getParameter("parentId");
        Integer parentId = 0;
        if (!StrUtil.isBlank(parentIdStr)) {
            parentId = Integer.parseInt(parentIdStr);
        }
        AreaService areaService = new AreaServiceImpl();
        List<Area> areaList = areaService.getAreaList(parentId);
        R r = new R();
        if (areaList.size() > 0) {
            r.setCode(ResponseEnum.SUCCESS.getCode());
            r.setMsg(ResponseEnum.SUCCESS.getMsg());
            r.setData(areaList);
        }else {
            r.setCode(ResponseEnum.ERROR.getCode());
            r.setMsg(ResponseEnum.ERROR.getMsg());
        }
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        Gson gson = new Gson();
        writer.println(gson.toJson(r));
        writer.flush();
        writer.close();

    }
}
