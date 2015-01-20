package com.jittrack.gts.web.rest;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.jittrack.gts.domain.Account;
import com.jittrack.gts.repository.AccountRepository;

/**
 * REST controller for managing Account.
 */
@RestController
@RequestMapping("/app")
public class GTSAccountResource {

    private final Logger log = LoggerFactory.getLogger(GTSAccountResource.class);

    @Inject
    private AccountRepository accountRepository;

    /**
     * POST  /rest/accounts -> Create a new account.
     */
    @RequestMapping(value = "/rest/accounts",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Account account) {
        log.debug("REST request to save Account : {}", account);
        accountRepository.save(account);
    }

    /**
     * GET  /rest/accounts -> get all the accounts.
     */
    @RequestMapping(value = "/rest/accounts",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @PreAuthorize("hasPermission(null, 'allowDoSomething')")
    public List<Account> getAll() {
        log.debug("REST request to get all Accounts");
        return accountRepository.findAll();
    }

    /**
     * GET  /rest/accounts/:id -> get the "id" account.
     */
    @RequestMapping(value = "/rest/accounts/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Account> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Account : {}", id);
        Account account = accountRepository.findOne(id);
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/accounts/:id -> delete the "id" account.
     */
    @RequestMapping(value = "/rest/accounts/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Account : {}", id);
        accountRepository.delete(id);
    }
}
