package com.quick.strategy.msg.handler;

import com.quick.enums.ChatMsgEnum;
import com.quick.pojo.dto.ChatMsgDTO;
import com.quick.pojo.po.QuickChatMsg;
import com.quick.strategy.msg.AbstractChatMsgStrategy;
import org.springframework.stereotype.Component;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-11-17  17:08
 * @Description: 视频通话消息
 * @Version: 1.0
 */
@Component
public class VideoCallHandler extends AbstractChatMsgStrategy {

    @Override
    protected ChatMsgEnum getEnum() {
        return ChatMsgEnum.VIDEO_CALL;
    }

    // TODO 视频电话
    @Override
    public QuickChatMsg sendMsg(ChatMsgDTO msgDTO) {
        return null;
    }
}
