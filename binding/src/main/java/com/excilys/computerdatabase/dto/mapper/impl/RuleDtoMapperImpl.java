/**
 * @Author Vincent Galloy
 * 
 */
package com.excilys.computerdatabase.dto.mapper.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.dto.mapper.RuleDtoMapper;
import com.excilys.computerdatabase.dto.model.RuleDto;
import com.excilys.computerdatabase.model.Role;
import com.excilys.computerdatabase.model.Rule;

/**
 * The Class RuleDtoMapperImpl.
 */
@Service
public class RuleDtoMapperImpl implements RuleDtoMapper {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.dto.mapper.DtoMapper#mapFromModel(java.lang.Object)
	 */
	@Override
	public RuleDto mapFromModel(Rule model) {
		log.warn("mapFromModel : unsupportedOperation");
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.dto.mapper.DtoMapper#mapToModel(java.lang.Object)
	 */
	@Override
	public Rule mapToModel(RuleDto dto) {
		Rule rule = new Rule();
		rule.setAuthorized(dto.isAuthorized());
		rule.setRole(Role.build(dto.getRole()));
		rule.setUserName(dto.getUserName());
		return rule;
	}

}
