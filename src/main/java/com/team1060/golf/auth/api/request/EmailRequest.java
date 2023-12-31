package com.team1060.golf.auth.api.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * <pre>
 * 메일 
 * </pre>
 * 
 * @author KJY
 * @since 2023.12.27
 */

@Data
public class EmailRequest {

    @NotEmpty(message = "이메일을 입력해주세요")
    public String email;
}