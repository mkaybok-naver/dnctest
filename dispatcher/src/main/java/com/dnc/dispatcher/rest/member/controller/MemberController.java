package com.dnc.dispatcher.rest.member.controller;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnc.dispatcher.common.config.security.JwtTokenProvider;
import com.dnc.dispatcher.rest.common.exception.CEmailSigninFailedException;
import com.dnc.dispatcher.rest.common.exception.CEmailSignupFailedException;
import com.dnc.dispatcher.rest.common.exception.CUserNotFoundException;
import com.dnc.dispatcher.rest.common.response.model.ListResult;
import com.dnc.dispatcher.rest.common.response.model.SingleResult;
import com.dnc.dispatcher.rest.common.response.service.ResponseService;
import com.dnc.dispatcher.rest.member.entity.Member;
import com.dnc.dispatcher.rest.member.entity.MemberRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <pre>
 * com.dnc.dispatcher.rest.member.controller
 * MemberController.java
 * </pre>
 *
 * @author : mkbok
 * @date    : 2019. 9. 4.
 *
 */
@Api(tags = {"1. Member"})
@RestController
@RequestMapping(value = "/member")
public class MemberController {

	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	ResponseService responseService;

	@ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "회원 조회", notes = "모든 회원을 조회한다")
	@GetMapping(value="/list")
	public ListResult<Member> findAllMember() {
		return responseService.getListResult(memberRepository.findAll(new Sort(Direction.DESC,"id")));
	}
	
	@ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "로그인 회원 정보 조회", notes = "userId로 회원을 조회한다")
    @GetMapping(value = "/get")
    public SingleResult<Member> findUserById()throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        return responseService.getSingleResult(memberRepository.findByEmail(id).orElseThrow(CUserNotFoundException::new));
    }

	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "로그인 회원 정보 수정", notes = "회원정보를 수정한다.")
	@PutMapping(value = "/put")
	public SingleResult<Member> put(@RequestBody Member member)throws Exception {
		Date now = new Date();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String id = authentication.getName();
		member.setEmail(id);
		member.setModifyuser(id);
		member.setModifydate(now);
		Member oldMemberData = memberRepository.findByEmail(id).orElseThrow(CUserNotFoundException::new);
		member.setId(oldMemberData.getId());
		member.setCreateuser(oldMemberData.getCreateuser());
		member.setCreatedate(oldMemberData.getCreatedate());
		member.setRoles(Collections.singletonList(member.getUsertype()));
		Member memberData = memberRepository.save(member);
		return responseService.getSingleResult(memberData);
	}
	
	@ApiOperation(value = "회원 가입", notes = "이메일 아이디로 회원에 가입한다")
	@PostMapping(value="/signup")
	public SingleResult<Member> signup(@RequestBody Member member) {
		if (memberRepository.existsByEmail(member.getEmail()) )
			throw new CEmailSignupFailedException();
		Date now = new Date();
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		member.setCreateuser(member.getEmail());
		member.setCreatedate(now);
		member.setRoles(Collections.singletonList(member.getUsertype()));
		Member memberData = memberRepository.save(member);
		return responseService.getSingleResult(memberData);
	}
	
	@ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @PostMapping(value = "/signin")
    public SingleResult<String> signin(@RequestBody Member member) {
 
        Member memberData = memberRepository.findByEmail(member.getEmail()).orElseThrow(CEmailSigninFailedException::new);
        if (!passwordEncoder.matches(member.getPassword(), memberData.getPassword()))
            throw new CEmailSigninFailedException();
 
        return responseService.getSingleResult(jwtTokenProvider.createToken(member.getUsername(), member.getRoles()));
    }
	
}
