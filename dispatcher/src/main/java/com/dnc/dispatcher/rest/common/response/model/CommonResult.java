/**
 * 
 */
package com.dnc.dispatcher.rest.common.response.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * com.dnc.dispatcher.rest.common.response.model
 * CommonResult.java
 * </pre>
 *
 * @author : mkbok
 * @date    : 2019. 9. 4.
 *
 */
@Getter
@Setter
public class CommonResult {

	@ApiModelProperty(value = "처리 성공여부 : true/false")
	private boolean success;
	
	@ApiModelProperty(value = "응답코드 : >= 0 정상, < 0 비정상")
	private int code;
	
	@ApiModelProperty(value = "응답 메시지")
	private String msg;
}
