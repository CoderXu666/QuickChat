package com.quick.strategy.chatmsg.handler;

import com.quick.enums.ChatMsgEnum;
import com.quick.pojo.dto.ChatMsgDTO;
import com.quick.strategy.chatmsg.AbstractChatMsgStrategy;
import org.springframework.stereotype.Component;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-11-17  17:07
 * @Description: 语音通话消息
 * @Version: 1.0
 */
@Component
public class VoiceCallHandler extends AbstractChatMsgStrategy {

    @Override
    protected ChatMsgEnum getEnum() {
        return ChatMsgEnum.VOICE_CALL;
    }

    /**
     * 发送语音通话消息
     */
    @Override
    public void sendChatMsg(ChatMsgDTO msgDTO) {

    }
}
