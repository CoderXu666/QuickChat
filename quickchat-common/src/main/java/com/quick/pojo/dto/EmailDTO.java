package com.quick.pojo.dto;

import lombok.Data;

/**
 * @Author 徐志斌
 * @Date: 2023/11/25 15:05
 * @Version 1.0
 * @Description: 邮箱DTO
 */
@Data
public class EmailDTO {
    /**
     * 邮件类型
     *
     * @see com.quick.enums.EmailEnum
     */
    private Integer type;

    /**
     * 接收方邮箱
     */
    private String toEmail;
}
