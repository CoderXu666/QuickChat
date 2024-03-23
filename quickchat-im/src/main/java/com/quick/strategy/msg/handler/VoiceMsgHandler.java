package com.quick.strategy.msg.handler;

import cn.hutool.json.JSONUtil;
import com.quick.adapter.ChatMsgAdapter;
import com.quick.constant.MQConstant;
import com.quick.enums.BucketEnum;
import com.quick.enums.ChatMsgEnum;
import com.quick.enums.ChatTypeEnum;
import com.quick.kafka.KafkaProducer;
import com.quick.pojo.dto.ChatMsgDTO;
import com.quick.pojo.po.QuickChatMsg;
import com.quick.pojo.po.QuickChatSession;
import com.quick.store.QuickChatMsgStore;
import com.quick.strategy.msg.AbstractChatMsgStrategy;
import com.quick.utils.MinioUtil;
import com.quick.utils.RedissonLockUtil;
import com.quick.utils.RelationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-11-17  17:05
 * @Description: 语音消息
 * @Version: 1.0
 */
@Component
public class VoiceMsgHandler extends AbstractChatMsgStrategy {
    @Autowired
    private MinioUtil minioUtil;
    @Autowired
    private RedissonLockUtil lockUtil;
    @Autowired
    private QuickChatMsgStore msgStore;
    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    protected ChatMsgEnum getEnum() {
        return ChatMsgEnum.VOICE;
    }

    @Override
    public void sendChatMsg(ChatMsgDTO msgDTO) throws Throwable {
        // 上传语音文件
        String url = minioUtil.upload(msgDTO.getFile(), BucketEnum.VOICE.getBucketName());

        // 保存聊天信息
        String fromId = msgDTO.getFromId();
        String toId = msgDTO.getToId();
        QuickChatMsg chatMsg = ChatMsgAdapter.buildChatMsgPO(fromId, toId, url, null, this.getEnum().getCode());
        msgStore.saveMsg(chatMsg);

        // 处理双方会话信息
        String relationId = RelationUtil.generate(fromId, toId);
        QuickChatSession chatSession = lockUtil.executeWithLock(relationId, 15, TimeUnit.SECONDS,
                () -> this.handleSession(fromId, toId)
        );

        // 通过Channel推送消息（单聊、群聊）
        if (ChatTypeEnum.SINGLE.getType().equals(chatSession.getType())) {
            kafkaProducer.send(MQConstant.SEND_CHAT_SINGLE_MSG, JSONUtil.toJsonStr(chatMsg));
        }
        if (ChatTypeEnum.GROUP.getType().equals(chatSession.getType())) {
            kafkaProducer.send(MQConstant.SEND_CHAT_GROUP_MSG, JSONUtil.toJsonStr(chatMsg));
        }
    }
}
