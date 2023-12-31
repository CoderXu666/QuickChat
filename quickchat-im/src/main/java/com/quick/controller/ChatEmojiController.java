package com.quick.controller;

import com.quick.enums.ResponseEnum;
import com.quick.pojo.po.QuickChatEmoji;
import com.quick.response.R;
import com.quick.service.QuickChatEmojiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-11-29  09:18
 * @Description: 表情包功能
 * @Version: 1.0
 */
@RestController
@RequestMapping("/emoji")
public class ChatEmojiController {
    @Autowired
    private QuickChatEmojiService emojiService;

    /**
     * 查询表情包列表
     */
    @GetMapping("/list/{accountId}")
    public R getEmojiList(@PathVariable String accountId) {
        List<QuickChatEmoji> emojiList = emojiService.getEmojiList(accountId);
        return R.out(ResponseEnum.SUCCESS, emojiList);
    }

    /**
     * 添加表情包
     */
    @GetMapping("/add/{url}")
    public R addEmoji(@PathVariable String url) {
        emojiService.addEmoji(url);
        return R.out(ResponseEnum.SUCCESS);
    }

    /**
     * 删除表情包
     */
    @GetMapping("/delete/{emojiId}")
    public R deleteEmoji(@PathVariable Long emojiId) {
        emojiService.deleteEmoji(emojiId);
        return R.out(ResponseEnum.SUCCESS);
    }
}
