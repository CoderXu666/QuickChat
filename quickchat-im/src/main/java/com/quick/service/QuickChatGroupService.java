package com.quick.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quick.pojo.po.QuickChatGroup;

import java.util.List;

/**
 * <p>
 * 群聊 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2024-01-08
 */
public interface QuickChatGroupService extends IService<QuickChatGroup> {

    List<QuickChatGroup> getGroupList();

    /**
     * 根据 group_id 解散群聊
     *
     * @param groupId 群聊id
     * @return 执行结果
     */
    Boolean dismiss(String groupId);
}
