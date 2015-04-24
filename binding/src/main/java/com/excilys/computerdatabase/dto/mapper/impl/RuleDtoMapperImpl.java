package com.excilys.computerdatabase.dto.mapper.impl;

import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.dto.mapper.RuleDtoMapper;
import com.excilys.computerdatabase.dto.model.RuleDto;
import com.excilys.computerdatabase.model.Role;
import com.excilys.computerdatabase.model.Rule;

@Service
public class RuleDtoMapperImpl implements RuleDtoMapper {

	@Override
	public RuleDto mapFromModel(Rule model) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Rule mapToModel(RuleDto dto) {
		Rule rule = new Rule();
		rule.setAuthorized(dto.isAuthorized());
		rule.setRole(Role.build(dto.getRole()));
		rule.setUserName(dto.getUserName());
		return rule;
	}

}
