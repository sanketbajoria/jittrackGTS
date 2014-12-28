package com.jittrack.gts.repository;

import com.jittrack.gts.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Account entity.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

}
