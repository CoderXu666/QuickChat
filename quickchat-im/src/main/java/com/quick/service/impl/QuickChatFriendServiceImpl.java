package com.quick.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quick.mapper.QuickChatFriendMapper;
import com.quick.pojo.po.QuickChatFriend;
import com.quick.service.QuickChatFriendService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 聊天好友 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-11-30
 */
@Service
public class QuickChatFriendServiceImpl extends ServiceImpl<QuickChatFriendMapper, QuickChatFriend> implements QuickChatFriendService {

    @Override
    public List<QuickChatFriend> getFriendList() {
        return null;
    }

    @Override
    public Boolean addFriend(String accountId) {
        return null;
    }

    @Override
    public Boolean deleteFriend(String accountId) {
        return null;
    }

    @Override
    public Boolean blockFriend(String accountId) {
        return null;
    }

    @Override
    public Boolean agreeApply(String accountId) {
        return null;
    }

    @Override
    public QuickChatFriend getByFromIdAndToId(String fromId, String toId) {
        return null;
    }
}
