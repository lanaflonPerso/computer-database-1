/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.page.creator;

import java.util.Locale;

import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.dto.mapper.impl.DateMapper;
import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.page.model.ComputerPage;
import com.excilys.computerdatabase.util.DateFormat;

@Service
public abstract class AbstractPageCreator {
	
	protected static void convertDto(ComputerDto computerDto, DateFormat dateFormatFrom, DateFormat dateFormatTo) {
		computerDto.setIntroduced(DateMapper.convertString(computerDto.getIntroduced(), dateFormatFrom, dateFormatTo));
		computerDto.setDiscontinued(DateMapper.convertString(computerDto.getDiscontinued(), dateFormatFrom, dateFormatTo));
	}
	
	public static void pageConverter(ComputerPage page, Locale LocaleFrom, Locale LocaleTo) {
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
