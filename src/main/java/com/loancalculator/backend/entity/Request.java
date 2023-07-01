package com.loancalculator.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.*;
import java.util.*;
import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;

import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "request")
@RequiredArgsConstructor
public class Request implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "request_id")
	private Integer id;
	@Column(name = "request_time")
	private String requestTime;
	@Column(name = "request_method")
	private String requestMethod;
	@Column(name = "request_path")
	private String requestPath;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Request request = (Request) o;
		return id.equals(request.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	public static Request fromHttpReq(HttpServletRequest httpServletRequest){
		Request request = new Request();
		request.setRequestTime(new Date().toString());
		request.setRequestMethod(httpServletRequest.getMethod());
		request.setRequestPath(httpServletRequest.getServletPath());
		return request;
	}

}