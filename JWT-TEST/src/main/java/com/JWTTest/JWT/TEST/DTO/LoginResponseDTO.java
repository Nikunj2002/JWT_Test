package com.JWTTest.JWT.TEST.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDTO {

	String token;
	Long userId;
}
