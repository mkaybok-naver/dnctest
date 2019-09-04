/**
 * 
 */
package com.dnc.dispatcher.rest.common.response.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * com.dnc.dispatcher.rest.common.response.model
 * ListResult.java
 * </pre>
 *
 * @author : mkbok
 * @date    : 2019. 9. 4.
 *
 */
@Getter
@Setter
public class ListResult<T> extends CommonResult {
	private List<T> list;
}
