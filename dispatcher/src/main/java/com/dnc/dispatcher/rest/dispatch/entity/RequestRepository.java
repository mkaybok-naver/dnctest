/**
 * 
 */
package com.dnc.dispatcher.rest.dispatch.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <pre>
 * com.dnc.dispatcher.rest.member.entity
 * MemberRepository.java
 * </pre>
 *
 * @author : mkbok
 * @date    : 2019. 9. 4.
 *
 */
public interface RequestRepository extends JpaRepository<Request, Integer> {
	boolean existsByDispatcherAndStatusIn(String dispatcher, List<String> status);
	boolean existsByRequesterAndStatusIn(String requester, List<String> status);
}
