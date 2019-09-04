package com.dnc.dispatcher.rest.dispatch.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnc.dispatcher.common.config.security.JwtTokenProvider;
import com.dnc.dispatcher.rest.common.exception.CDispatchException;
import com.dnc.dispatcher.rest.common.exception.CRequestExistByDispatcher;
import com.dnc.dispatcher.rest.common.exception.CRequestExistByRequester;
import com.dnc.dispatcher.rest.common.response.model.ListResult;
import com.dnc.dispatcher.rest.common.response.model.SingleResult;
import com.dnc.dispatcher.rest.common.response.service.ResponseService;
import com.dnc.dispatcher.rest.dispatch.entity.Request;
import com.dnc.dispatcher.rest.dispatch.entity.RequestRepository;

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
@Api(tags = {"2. Request"})
@RestController
@RequestMapping(value = "/request")
public class RequestController {

	@Autowired
	RequestRepository requestRepository;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	ResponseService responseService;

	@ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "차량배차요청", notes = "차량 배차 요청을 한다.")
	@PostMapping(value = "/regist")
    public SingleResult<Request> regist(@RequestBody Request request) {
		Date now = new Date();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();
		
		List<String> chkStatusList = new ArrayList<String>();
		chkStatusList.add("W");
		chkStatusList.add("D");
		chkStatusList.add("R");
		
		if ( requestRepository.existsByRequesterAndStatusIn(userId, chkStatusList) )
			throw new CRequestExistByRequester();
		
		request.setRequester(userId);
		request.setRequestDate(now);
		request.setStatus("W");
		Request requestData = requestRepository.save(request);
        return responseService.getSingleResult(requestData);
    }
	
	@ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "요청 목록 조회", notes = "모든 요청 목록 조회한다")
	@GetMapping(value="/list")
	public ListResult<Request> findAllRequest() {
		return responseService.getListResult(requestRepository.findAll(new Sort(Direction.DESC,"id")));
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "배차", notes = "승객요청건에 대한 배차를 실행한다.")
	@GetMapping(value = "/dispatch/{id}")
	public SingleResult<Request> dispatch(@ApiParam(value = "요청목록에서 가져온 요청 id", required = true) @PathVariable int id)throws Exception {
		Date now = new Date();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String dispatcherId = authentication.getName();
		
		List<String> chkStatusList = new ArrayList<String>();
		chkStatusList.add("D");
		chkStatusList.add("R");
		
		if ( requestRepository.existsByDispatcherAndStatusIn(dispatcherId, chkStatusList) )
			throw new CRequestExistByDispatcher();
		
		Request existRequest = requestRepository.findById(id).orElseThrow(CDispatchException::new);
		if ( !"W".equalsIgnoreCase(existRequest.getStatus()) )
			throw new CDispatchException();
		Request request = new Request();
		request.setId(id);
		request.setDispatcher(dispatcherId);
		request.setModifyDate(now);
		request.setAddr(existRequest.getAddr());
		request.setRequestDate(existRequest.getRequestDate());
		request.setStatus("D");
		request.setRequester(existRequest.getRequester());
		return responseService.getSingleResult(requestRepository.save(request));
	}
	
}
