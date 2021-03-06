package com.dlion.life.base.api;

import com.dlion.life.base.entity.UserProjectRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李正元
 * @date 2019-09-20
 */
@FeignClient(value = "life-base")
public interface UserProjectRecordApi {

    @GetMapping("/life-base-userProjectRecord/getByProjectId")
    List<UserProjectRecord> listByProjectId(@RequestParam("projectId") Integer projectId);

    @GetMapping("/life-base-userProjectRecord/getByUserId")
    UserProjectRecord getByUserId(@RequestParam("userId") Integer userId,
                                  @RequestParam("projectId") Integer projectId);

    @PostMapping("/life-base-userProjectRecord/add")
    void add(@RequestBody UserProjectRecord userProjectRecord);

    /**
     * 查询用户参与的圈子
     *
     * @param userId 用户ID
     * @return
     */
    @GetMapping("/life-base-userProjectRecord/listByUserId")
    List<UserProjectRecord> listByUserId(@RequestParam("userId") Integer userId);

    @PutMapping("/life-base-userProjectRecord/update")
    void update(@RequestBody UserProjectRecord newUserProjectRecord);
}
