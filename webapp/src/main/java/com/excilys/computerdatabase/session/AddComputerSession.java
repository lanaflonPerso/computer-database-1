package com.excilys.computerdatabase.session;

import com.excilys.computerdatabase.dto.model.ComputerDto;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.io.Serializable;

/**
 * @author Vincent Galloy
 *         The Class AddComputerSession.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AddComputerSession implements Serializable {
    private static final long serialVersionUID = -1774961026585136517L;
    private ComputerDto computerDto;
    private BindingResult bindingResult;

    public ComputerDto getComputerDto() {
        return computerDto;
    }

    public void setComputerDto(ComputerDto computerDto) {
        this.computerDto = computerDto;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }

    public void setBindingResult(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((bindingResult == null) ? 0 : bindingResult.hashCode());
        result = prime * result
                + ((computerDto == null) ? 0 : computerDto.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AddComputerSession other = (AddComputerSession) obj;
        if (bindingResult == null) {
            if (other.bindingResult != null)
                return false;
        } else if (!bindingResult.equals(other.bindingResult))
            return false;
        if (computerDto == null) {
            if (other.computerDto != null)
                return false;
        } else if (!computerDto.equals(other.computerDto))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "AddComputerSession [computerDto=" + computerDto
                + ", bindingResult=" + bindingResult + "]";
    }
}
