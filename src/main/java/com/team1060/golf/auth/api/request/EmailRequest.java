package com.team1060.golf.auth.api.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * 메일 
 * </pre>
 * 
 * @author KJY
 * @since 2023.12.27
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {

    @NotEmpty(message = "이메일을 입력해주세요")
    public String email;
}