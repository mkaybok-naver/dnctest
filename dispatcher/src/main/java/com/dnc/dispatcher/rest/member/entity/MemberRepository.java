/**
 * 
 */
package com.dnc.dispatcher.rest.member.entity;

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
public interface MemberRepository extends JpaRepository<Member, Integer> {
	Optional<Member> findByEmail(String email);
	boolean existsByEmail(String email);
}
