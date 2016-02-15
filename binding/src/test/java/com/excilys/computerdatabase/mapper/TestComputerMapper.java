package com.excilys.computerdatabase.mapper;

import com.excilys.computerdatabase.dto.mapper.ComputerDtoMapper;
import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.mapper.impl.DateMapper;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.util.DateFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Vincent Galloy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test-binding-context.xml"})
public class TestComputerMapper {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DateFormat.ENGLISH.toString());
    private final String currentStringDate = LocalDateTime.now().format(dateTimeFormatter);
    private final LocalDateTime currentDate = DateMapper.extractFromString(currentStringDate, DateFormat.ENGLISH);
    @Autowired
    private ComputerDtoMapper computerDtoMapper;

    @Test
    public void testMapperModelToDtoOk2() {
        Computer computer = new Computer(0L, "name", currentDate, null, new Company(0L, "name"));
        ComputerDto computerDto = computerDtoMapper.mapFromModel(computer);
        assertEquals(computerDto.getIntroduced(), currentStringDate);
    }

    @Test
    public void testMapperModelToDtoOk3() {
        Computer computer = new Computer(null, "name", null, currentDate, new Company(null, "name"));
        ComputerDto computerDto = computerDtoMapper.mapFromModel(computer);
        assertEquals(computerDto.getIntroduced(), null);
        assertEquals(computerDto.getDiscontinued(), currentStringDate);
    }

    @Test
    public void testMapperListModelToDtoOk() {
        Computer computer = new Computer(null, "name", null, currentDate, new Company(null, "name"));
        List<Computer> computers = new ArrayList<>();
        computers.add(computer);
        computers.add(new Computer());

        List<ComputerDto> computerDtos = computerDtoMapper.mapListFromModel(computers);

        assertEquals(computerDtos.size(), 2);
        assertEquals(computerDtos.get(1).getIntroduced(), null);

        ComputerDto computerDto = computerDtoMapper.mapFromModel(computer);
        assertEquals(computerDtos.get(0), computerDto);
    }

    @Test(expected = NullPointerException.class)
    public void testMapperDaoListWrong() {
        computerDtoMapper.mapListFromModel(null);
    }

    @Test
    public void testMapperDtoToModelOk() {
        Computer computer2 = new Computer(5L, "name", currentDate, null, new Company(4L, null));
        Computer computer = computerDtoMapper.mapToModel(new ComputerDto("5", "name", currentStringDate, "", "4", null));
        assertEquals(computer.hashCode(), computer2.hashCode());
        assertEquals(computer, computer2);
    }
}
