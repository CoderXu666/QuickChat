package com.quick.store.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quick.constant.RedisConstant;
import com.quick.mapper.QuickChatUserMapper;
import com.quick.pojo.po.QuickChatUser;
import com.quick.store.QuickChatUserStore;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-11-13
 */
@Service
public class QuickChatUserStoreImpl extends ServiceImpl<QuickChatUserMapper, QuickChatUser> implements QuickChatUserStore {
    @Override
    @Cacheable(value = RedisConstant.QUICK_CHAT_USER, key = "#p0", unless = "#result == null")
    public QuickChatUser getByAccountId(String accountId) {
        return this.lambdaQuery()
                .eq(QuickChatUser::getAccountId, accountId)
                .one();
    }

    @Override
    public Boolean saveUser(QuickChatUser userPO) {
        return this.save(userPO);
    }

    @Override
    public List<QuickChatUser> getListByAccountIds(List<String> accountIds) {
        return this.lambdaQuery()
                .in(QuickChatUser::getAccountId, accountIds)
                .list();
    }

    @Override
    @CacheEvict(value = RedisConstant.QUICK_CHAT_USER, key = "#p0.accountId")
    public Boolean updateInfo(QuickChatUser userPO) {
        return this.updateById(userPO);
    }
}
