package org.currency.starter.service;

import org.currency.starter.domain.Authority;
import org.currency.starter.repository.AuthorityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author atwa Jul 22, 2018
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

	private Logger LOGGER = LoggerFactory.getLogger(AuthorityServiceImpl.class);

	@Autowired
	private AuthorityRepository authorityRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dam.pharm.starter.service.CompanyService#saveUser(com.dam.pharm.starter.
	 * entities.User)
	 */
	@Override
	public Authority saveAuthority(Authority authority) {

		LOGGER.info(">> save company :{}", authority);
		Authority auth = authorityRepository.save(authority);
		LOGGER.info(">> saved company :{}", authority);
		return authority;
	}

}
