package com.dnc.dispatcher.rest.member.entity;

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
public class Member implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(hidden = true)
	@JsonIgnore
	private int id;
	@Column(nullable = false, unique = true, length = 50)
	@ApiModelProperty(position = 0, value = "이메일 아이디, 최대 50", required = true, example = "test@company.com")
	private String email;
	@Column(nullable = false, length = 256)
	@ApiModelProperty(position = 1, value = "비밀번호, 최대 256", required = true, example = "password")
	private String password;
	@Column(nullable = false, length = 50)
	@ApiModelProperty(position = 2, value = "이름", required = true, example = "홍길동")
    private String name;
	@Column(nullable = false, length = 50)
	@ApiModelProperty(position = 3, value = "휴대폰번호", required = true, example="010-1111-2222")
    private String mobile;
	@Transient
	@ApiModelProperty(position = 4, value = "사용자 유형, 일반회원 : \"C\", 기사 : \"D\", 관리자 : \"A\"", required = true, example="C")
    private String usertype;
	@Column(nullable = true, length = 50)
	@ApiModelProperty(position = 5, value = "차량번호, 기사에만 해당", required = false, example="00가1111")
	private String carno;
	@Column(nullable = false, length = 50, updatable=false)
	@ApiModelProperty(position = 6, value = "생성자, 입력불필요", required = false)
    private String createuser;
	@Column(nullable = true,  length = 50, updatable=false)
	@ApiModelProperty(position = 7, value = "수정자, 입력불필요", required = false)
    private String modifyuser;
	@Column(nullable = false, updatable=false)
	@ApiModelProperty(position = 8, value = "생성일시, 입력불필요", required = false, dataType = "Date")
    Date createdate;
	@Column(nullable = true, updatable=false)
	@ApiModelProperty(position = 9, value = "수정일시, 입력불필요", required = false, dataType = "Date")
    Date modifydate;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@Builder.Default
	@ApiModelProperty(hidden = true)
	private List<String> roles = new ArrayList<>();
	
	@Override
	@ApiModelProperty(hidden = true)
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public String getUsername() {
		return this.email;
	}
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isEnabled() {
		return true;
	}
}
