package com.dlion.life.base.api;

import com.dlion.life.base.entity.PunchCardProject;
import com.dlion.life.common.bo.ProjectSearchPo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/11
 */
@FeignClient(value = "life-base")
public interface PunchCardProjectApi {

    @PostMapping("/life-base-punchCardProject/add")
    Integer add(@RequestBody PunchCardProject punchCardProject);

    @PutMapping("/life-base-punchCardProject/update")
    void update(@RequestBody PunchCardProject punchCardProject);

    @DeleteMapping("/life-base-punchCardProject/deleteById")
    void deleteById(@RequestParam("id") Integer id);

    @GetMapping("/life-base-punchCardProject/getByUserId")
    List<PunchCardProject> getByUserId(@RequestParam("userId") Integer userId);

    @GetMapping("/life-base-punchCardProject/getById")
    PunchCardProject getById(@RequestParam("id") Integer id);

    /**
     * 获取打卡数超过一定数量的圈子
     *
     * @param num     打卡数
     * @param dataNum 页码
     * @param pageNo  条数
     * @return
     */
    @GetMapping("/life-base-punchCardProject/getMorePunchCard")
    List<PunchCardProject> getMorePunchCard(@RequestParam Integer num,
                                            @RequestParam Integer pageNo,
                                            @RequestParam Integer dataNum);

    /**
     * 根据关键字查询
     *
     * @param keyword 关键字
     * @param dataNum 页码
     * @param pageNo  条数
     * @return
     */
    @GetMapping("/life-base-punchCardProject/search")
    List<PunchCardProject> search(@RequestParam String keyword, @RequestParam Integer pageNo,
                                  @RequestParam Integer dataNum);

    @GetMapping(value = "/life-base-punchCardProject/getProjectListByType", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<PunchCardProject> getProjectListByType(@RequestBody ProjectSearchPo projectSearchPo);

    @PutMapping("/life-base-punchCardProject/refreshTodayPunchCardNum")
    void refreshTodayPunchCardNum();
}
