/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.dto.page.creator;

import java.util.Locale;

import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.dto.page.model.ComputerPage;
import com.excilys.computerdatabase.mapper.impl.DateMapper;
import com.excilys.computerdatabase.util.DateFormat;
import com.excilys.computerdatabase.validation.CorrectField;
import com.excilys.computerdatabase.validation.Validator;

@Service
public abstract class AbstractPageCreator {

	protected CorrectField checkComputerDto(ComputerDto computerDto, DateFormat dateFormat) {
		CorrectField incorrectField = new CorrectField(true, true, true, true);
		if (computerDto.getName() == null) {
			incorrectField.setComputerNameTrue(false);
		} else if (computerDto.getName().trim().isEmpty()) {
			incorrectField.setComputerNameTrue(false);
		}
		if (computerDto.getIntroduced() != null
				&& !Validator.isDateValid(computerDto.getIntroduced(), dateFormat)) {
			incorrectField.setIntroducedDateTrue(false);
		}
		if (computerDto.getDiscontinued() != null
				&& !Validator.isDateValid(computerDto.getDiscontinued(), dateFormat)) {
			incorrectField.setDiscontinuedDateTrue(false);
		}
		return incorrectField;
	}
	
	protected void convertDto(ComputerDto computerDto, DateFormat dateFormatFrom, DateFormat dateFormatTo) {
		computerDto.setIntroduced(DateMapper.convertString(computerDto.getIntroduced(), dateFormatFrom, dateFormatTo));
		computerDto.setDiscontinued(DateMapper.convertString(computerDto.getDiscontinued(), dateFormatFrom, dateFormatTo));
	}
	
	public void pageConverter(ComputerPage page, Locale LocaleFrom, Locale LocaleTo) {
		DateFormat dateFormatFrom 	= DateFormat.ENGLISH;
		DateFormat dateFormatTo 	= DateFormat.ENGLISH;	
		if(LocaleFrom.equals(Locale.FRENCH)) {
			dateFormatFrom = DateFormat.FRENCH;
		}
		if(LocaleTo.equals(Locale.FRENCH)) {
			dateFormatTo = DateFormat.FRENCH;
		}	
		convertDto(page.getComputerDto(), dateFormatFrom, dateFormatTo);
	}
	

	protected void pageValidation(ComputerPage page, Locale locale) {
		DateFormat dateFormat = DateFormat.ENGLISH;
		if(locale.equals(Locale.FRENCH)){
			dateFormat = DateFormat.FRENCH;
		}
		page.setCorrectField(checkComputerDto(page.getComputerDto(), dateFormat));
	}
	
	protected void mapComputerDto(ComputerDto computerDto) {
		if(computerDto.getIntroduced() == null || "".equals(computerDto.getIntroduced().trim())){
			computerDto.setIntroduced(null);
		}
		if(computerDto.getDiscontinued() == null || "".equals(computerDto.getDiscontinued().trim())){
			computerDto.setDiscontinued(null);
		}
		if(computerDto.getCompanyId() == null || "".equals(computerDto.getCompanyId().trim())){
			computerDto.setCompanyId(null);
		}
	}
}
