package com.zhang.controller.admin;

import com.zhang.pojo.Link;
import com.zhang.pojo.PageBean;
import com.zhang.service.LinkService;
import com.zhang.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 友情链接管理
 */
@Controller
@RequestMapping("/admin/link")
public class LinkAdminController {
    @Resource
    private LinkService linkService;

    @RequestMapping("/listLink")
    public String listLink(@RequestParam(value = "page",required = false) String page,
                           @RequestParam(value = "rows",required = false) String rows,
                           HttpServletResponse response) throws IOException {

        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        // 查询友情链接
        List<Link> linkList = linkService.listLink(map);

        // 查询总共有多少条数据
        Long total = linkService.getTotal(map);

        //将数据写入response
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(linkList);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }

    // 保存友情链接
    @RequestMapping("/saveLink")
    public String save(Link link, HttpServletResponse response) throws IOException {
        int resultTotal = 0;
        //添加
        if(link.getId() == null){
            resultTotal = linkService.addLink(link).intValue();
        }else{
            //修改
            resultTotal = linkService.updateLink(link).intValue();
        }

        JSONObject result = new JSONObject();
        if(resultTotal>0){
            result.put("success", Boolean.valueOf(true));
        }else{
            result.put("success", Boolean.valueOf(false));
        }
        ResponseUtil.write(response, result);
        return null;
    }

    @RequestMapping("/deleteLink")
    public String delete(@RequestParam("ids") String ids, HttpServletResponse response) throws IOException {
        String[] idsStr = ids.split(",");
        JSONObject result = new JSONObject();
        for(int i = 0; i < idsStr.length; i++){
            linkService.deleteLink(Integer.valueOf(idsStr[i]));
        }
        result.put("success", Boolean.valueOf(true));
        ResponseUtil.write(response, result);
        return null;
    }
}
