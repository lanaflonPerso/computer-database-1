/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.page.creator;

import java.util.Locale;

import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.mapper.impl.DateMapper;
import com.excilys.computerdatabase.page.model.ComputerPage;
import com.excilys.computerdatabase.util.DateFormat;
import com.excilys.computerdatabase.validation.Validator;

/**
 * The Class AbstractPageCreator.
 */
@Service
public abstract class AbstractPageCreator {

	/**
	 * Convert dto.
	 *
	 * @param computerDto
	 *          the computer dto
	 * @param dateFormatFrom
	 *          the date format from
	 * @param dateFormatTo
	 *          the date format to
	 */
	protected static void convertDto(ComputerDto computerDto, DateFormat dateFormatFrom, DateFormat dateFormatTo) {
		if (Validator.isDateValid(computerDto.getIntroduced(), dateFormatFrom)) {
			computerDto.setIntroduced(DateMapper.convertString(computerDto.getIntroduced(), dateFormatFrom, dateFormatTo));
		} else {
			computerDto.setIntroduced(computerDto.getIntroduced());
		}
		if (Validator.isDateValid(computerDto.getDiscontinued(), dateFormatFrom)) {
			computerDto.setDiscontinued(DateMapper.convertString(computerDto.getDiscontinued(), dateFormatFrom, dateFormatTo));
		} else {
			computerDto.setDiscontinued(computerDto.getDiscontinued());
		}
	}

	/**
	 * Page converter.
	 *
	 * @param page
	 *          the page
	 * @param LocaleFrom
	 *          the locale from
	 * @param LocaleTo
	 *          the locale to
	 */
	public static void pageConverter(ComputerPage page, Locale LocaleFrom, Locale LocaleTo) {
		DateFormat dateFormatFrom = DateFormat.ENGLISH;
		DateFormat dateFormatTo = DateFormat.ENGLISH;
		if (LocaleFrom.equals(Locale.FRENCH)) {
			dateFormatFrom = DateFormat.FRENCH;
		}
		if (LocaleTo.equals(Locale.FRENCH)) {
			dateFormatTo = DateFormat.FRENCH;
		}
		convertDto(page.getComputerDto(), dateFormatFrom, dateFormatTo);
	}

	/**
	 * Map computer dto.
	 *
	 * @param computerDto
	 *          the computer dto
	 */
	protected void mapComputerDto(ComputerDto computerDto) {
		if (computerDto.getIntroduced() == null || "".equals(computerDto.getIntroduced().trim())) {
			computerDto.setIntroduced(null);
		}
		if (computerDto.getDiscontinued() == null || "".equals(computerDto.getDiscontinued().trim())) {
			computerDto.setDiscontinued(null);
		}
		if (computerDto.getCompanyId() == null || "".equals(computerDto.getCompanyId().trim())) {
			computerDto.setCompanyId(null);
		}
	}
}
