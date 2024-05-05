package com.example.jwt.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class JwtInfo {
	@NonNull
	private String token;
	@NonNull
	private String subject;
	@NonNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss.SS")
	private Date issuedAt;
	@NonNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss.SS")
	private Date expiration;
	@NonNull
	private Integer age;
}
