package com.dnc.dispatcher.rest.dispatch.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * com.dnc.dispatcher.rest.emeber.entity
 * Member.java
 * </pre>
 *
 * @author : mkbok
 * @date    : 2019. 9. 4.
 *
 */
@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Request{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(position = 0, value = "요청 id, 기사 배차시 해당 요청건 id", example = "1234")
	private int id;
	@Column(nullable = false, length = 50)
	@ApiModelProperty(position = 1, value = "요청자 id, 입력불필요", required = false)
    String requester;
	@Column(nullable = false, length = 100)
	@ApiModelProperty(position = 3, value = "주소, 최대 100, 배차 요청시 주소", example = "서울시 송파구 송파동 123")
    String addr;
	@Column(nullable = false, length = 256)
	@ApiModelProperty(position = 4, value = "요청상태, 대기 : \"W\", 배차 : \"D\", 운행 : \"R\", 운행완료 : \"E\", 배차취소 : \"C\", 요청취소 : \"X\"", required = false, example = "W")
    String status;
	@Column(nullable = false, length = 256)
	@ApiModelProperty(position = 5, value = "요청일시, 입력불필요", example = "password")
    Date requestDate;
	@Column(nullable = true, length = 256)
	@ApiModelProperty(position = 6, value = "배차기사 id, 입력불필요", required = false, example = "driver@driver.com")
    String dispatcher;
	@Column(nullable = true, length = 256)
	@ApiModelProperty(position = 7, value = "업데이트일시, 입력불필요", required = false, example = "password")
    Date modifyDate;
}
