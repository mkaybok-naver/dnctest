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
 * SingleResult.java
 * </pre>
 *
 * @author : mkbok
 * @date    : 2019. 9. 4.
 *
 */
@Getter
@Setter
public class SingleResult<T> extends CommonResult {
	private T data;
}
