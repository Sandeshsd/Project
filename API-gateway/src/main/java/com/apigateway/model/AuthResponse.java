package com.apigateway.model;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

	private String Id;
	private String accessToken;
	private String refreshToken;
	private long expireAt;
	private Collection<String> authorities;
}
