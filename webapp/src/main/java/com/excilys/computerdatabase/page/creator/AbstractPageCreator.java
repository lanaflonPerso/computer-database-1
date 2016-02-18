package com.excilys.computerdatabase.page.creator;

import com.excilys.computerdatabase.dto.mapper.CompanyDtoMapper;
import com.excilys.computerdatabase.dto.model.CompanyDto;
import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.mapper.impl.DateMapper;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.page.model.ComputerPage;
import com.excilys.computerdatabase.service.services.CompanyService;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.util.DateFormat;
import com.excilys.computerdatabase.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

/**
 * @author Vincent Galloy
 *         The Class AbstractPageCreator.
 */
@Service
public abstract class AbstractPageCreator {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyDtoMapper companyDtoMapper;

    /**
     * Convert dto.
     *
     * @param computerDto    the computer dto
     * @param dateFormatFrom the date format from
     * @param dateFormatTo   the date format to
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
     * @param page       the page
     * @param localeFrom the locale from
     * @param localeTo   the locale to
     */
    public static void pageConverter(ComputerPage page, Locale localeFrom, Locale localeTo) {
        DateFormat dateFormatFrom = DateFormat.ENGLISH;
        DateFormat dateFormatTo = DateFormat.ENGLISH;
        if (localeFrom.equals(Locale.FRENCH)) {
            dateFormatFrom = DateFormat.FRENCH;
        }
        if (localeTo.equals(Locale.FRENCH)) {
            dateFormatTo = DateFormat.FRENCH;
        }
        convertDto(page.getComputerDto(), dateFormatFrom, dateFormatTo);
    }

    /**
     * Map computer dto.
     *
     * @param computerDto the computer dto
     */
    protected void convertEmptyFieldIntoNull(ComputerDto computerDto) {
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

    /**
     * Page post.
     *
     * @param computerDto the computer dto
     * @return the computer page
     */
    protected ComputerPage pagePost(ComputerDto computerDto) {
        ComputerPage page = new ComputerPage();
        List<Company> companies = companyService.list(new SortCriteria());
        List<CompanyDto> companyDtos = companyDtoMapper.mapListFromModel(companies);
        page.setCompanies(companyDtos);
        page.setComputerDto(computerDto);
        return page;
    }
}
