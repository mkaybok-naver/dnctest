package com.dnc.dispatcher.rest.common.response.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dnc.dispatcher.rest.common.response.model.CommonResult;
import com.dnc.dispatcher.rest.common.response.model.ListResult;
import com.dnc.dispatcher.rest.common.response.model.SingleResult;

/**
 * <pre>
 * com.dnc.dispatcher.rest.common.response.service
 * ResponseService.java
 * </pre>
 *
 * @author : mkbok
 * @date    : 2019. 9. 4.
 *
 */
@Service
public class ResponseService {

	public enum CommonResponse {
		SUCCESS(0, "성공"),
		FAIL(-1, "실패"),
		USER_NOT_FOUND_FAIL(-2,"사용자를 찾을 수 없습니다"),
		EMAIL_SIGNUP_EXIST_EMAIL_FAIL(-3,"동일한 이메일 사용자가 존재합니다"),
		EMAIL_SIGNIN_FAIL(-4,"이메일정보나 비밀번호가 일치하지 않습니다"),
		REQUEST_BY_DISPATCHER_EXIST_FAIL(-5, "기사님이 배차한 요청이 이미 존재합니다. 배차 취소후 실행해 주십시오"),
		REQUEST_DISPATCH_FAIL(-6, "배차 가능한 차량이 아닙니다. 다시 시도해 주십시오"),
		REQUEST_EXIST_REQUEST_FAIL(-7, "이미 배차신청이 존재합니다. 취소후 재시도 해 주십시오.");
		
		int code;
		String msg;
		
		CommonResponse(int code, String msg) {
			this.setCode(code);
			this.setMsg(msg);
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}
	
	// 단일건 결과를 처리하는 메소드
    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }
    // 다중건 결과를 처리하는 메소드
    public <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        result.setList(list);
        setSuccessResult(result);
        return result;
    }
    // 성공 결과만 처리하는 메소드
    public CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }
    // 실패 결과만 처리하는 메소드
    public CommonResult getFailResult() {
        CommonResult result = new CommonResult();
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
        return result;
    }
    // 실패 결과만 처리하는 메소드
    public CommonResult getUserNotFoundFailResult() {
    	CommonResult result = new CommonResult();
    	result.setSuccess(false);
    	result.setCode(CommonResponse.USER_NOT_FOUND_FAIL.getCode());
    	result.setMsg(CommonResponse.USER_NOT_FOUND_FAIL.getMsg());
    	return result;
    }
    // 실패 결과만 처리하는 메소드
    public CommonResult getSignUserExistFailResult() {
    	CommonResult result = new CommonResult();
    	result.setSuccess(false);
    	result.setCode(CommonResponse.EMAIL_SIGNUP_EXIST_EMAIL_FAIL.getCode());
    	result.setMsg(CommonResponse.EMAIL_SIGNUP_EXIST_EMAIL_FAIL.getMsg());
    	return result;
    }
    // 실패 결과만 처리하는 메소드
    public CommonResult getSigninUserNotFoundFailResult() {
    	CommonResult result = new CommonResult();
    	result.setSuccess(false);
    	result.setCode(CommonResponse.EMAIL_SIGNIN_FAIL.getCode());
    	result.setMsg(CommonResponse.EMAIL_SIGNIN_FAIL.getMsg());
    	return result;
    }
    // 실패 결과만 처리하는 메소드
    public CommonResult getRequestByDispatcherExistFailResult() {
    	CommonResult result = new CommonResult();
    	result.setSuccess(false);
    	result.setCode(CommonResponse.REQUEST_BY_DISPATCHER_EXIST_FAIL.getCode());
    	result.setMsg(CommonResponse.REQUEST_BY_DISPATCHER_EXIST_FAIL.getMsg());
    	return result;
    }
    // 실패 결과만 처리하는 메소드
    public CommonResult getRequestDispatchFailResult() {
    	CommonResult result = new CommonResult();
    	result.setSuccess(false);
    	result.setCode(CommonResponse.REQUEST_DISPATCH_FAIL.getCode());
    	result.setMsg(CommonResponse.REQUEST_DISPATCH_FAIL.getMsg());
    	return result;
    }
    // 실패 결과만 처리하는 메소드
    public CommonResult getExistRequestFailResult() {
    	CommonResult result = new CommonResult();
    	result.setSuccess(false);
    	result.setCode(CommonResponse.REQUEST_EXIST_REQUEST_FAIL.getCode());
    	result.setMsg(CommonResponse.REQUEST_EXIST_REQUEST_FAIL.getMsg());
    	return result;
    }
    // 결과 모델에 api 요청 성공 데이터를 세팅해주는 메소드
    private void setSuccessResult(CommonResult result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }
}
