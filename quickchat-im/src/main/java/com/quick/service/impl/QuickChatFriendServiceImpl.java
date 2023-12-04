package com.quick.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quick.mapper.QuickChatFriendMapper;
import com.quick.pojo.po.QuickChatFriend;
import com.quick.service.QuickChatFriendService;
import com.quick.store.QuickChatFriendStore;
import com.quick.utils.RequestHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private QuickChatFriendStore friendStore;

    /**
     * 查询好友列表
     */
    @Override
    public void getFriendList() {
        String accountId = (String) RequestHolderUtil.get().get("account_id");
        List<QuickChatFriend> friendList = friendStore.getListByAccountId(accountId);
    }
}
