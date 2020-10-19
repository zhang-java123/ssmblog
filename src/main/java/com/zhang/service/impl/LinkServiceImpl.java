package com.zhang.service.impl;

import com.zhang.dao.LinkDao;
import com.zhang.pojo.Link;
import com.zhang.service.LinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("linkService")
public class LinkServiceImpl implements LinkService {

    @Resource
    private LinkDao linkDao;

    public Link findById(Integer id) {
        return linkDao.findById(id);
    }

    public List<Link> listLink(Map<String, Object> paramMap) {
        return linkDao.listLink(paramMap);
    }

    public Long getTotal(Map<String, Object> paramMap) {
        return linkDao.getTotal(paramMap);
    }

    public Integer addLink(Link link) {
        return linkDao.addLink(link);
    }

    public Integer updateLink(Link link) {
        return linkDao.updateLink(link);
    }

    public Integer deleteLink(Integer id) {
        return linkDao.deleteLink(id);
    }
}
