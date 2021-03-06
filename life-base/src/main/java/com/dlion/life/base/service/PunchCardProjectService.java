package com.dlion.life.base.service;

import com.dlion.life.base.dao.PunchCardProjectMapper;
import com.dlion.life.base.entity.PunchCardProject;
import com.dlion.life.common.bo.ProjectSearchPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author 李正元
 * @date 2019/9/11
 */
@Service
public class PunchCardProjectService {

    @Autowired
    private PunchCardProjectMapper punchCardProjectMapper;

    public Integer addPunchCardProject(PunchCardProject punchCardProject) {

        punchCardProjectMapper.insertSelective(punchCardProject);

        return punchCardProject.getId();
    }

    public void updatePunchCardProject(PunchCardProject punchCardProject) {

        punchCardProjectMapper.updateByPrimaryKeySelective(punchCardProject);
    }

    public void delete(Integer id) {

        punchCardProjectMapper.deleteByPrimaryKey(id);
    }

    public List<PunchCardProject> getByUserId(Integer userId) {

        return punchCardProjectMapper.selectByUserId(userId);
    }


    public PunchCardProject getById(Integer id) {

        return punchCardProjectMapper.selectByPrimaryKey(id);
    }

    public List<PunchCardProject> getMorePunchCard(Integer num, Integer pageNo, Integer dataNum) {

        pageNo = (pageNo - 1) * dataNum;
        return punchCardProjectMapper.getMorePunchCard(num, pageNo, dataNum);
    }

    public List<PunchCardProject> search(String keyword, Integer pageNo, Integer dataNum) {

        pageNo = (pageNo - 1) * dataNum;
        return punchCardProjectMapper.search(keyword, pageNo, dataNum);
    }

    public List<PunchCardProject> getProjectListByType(ProjectSearchPo projectSearchPo) {

        if (Objects.nonNull(projectSearchPo.getPageNo())) {
            projectSearchPo.setPageNo((projectSearchPo.getPageNo() - 1) * projectSearchPo.getPageNo());
        }
        return punchCardProjectMapper.getProjectListByType(projectSearchPo);
    }

    public void refreshTodayPunchCardNum() {

        punchCardProjectMapper.refreshTodayPunchCardNum();
    }
}
