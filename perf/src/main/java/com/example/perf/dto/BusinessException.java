package com.example.perf.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * This is a generic class of exception that can be thrown during the normal operation
 * of the api.
 *
 * @author Robin.Garg
 */

@Getter
@Setter
public class BusinessException extends BaseSystemException {

    private static final long serialVersionUID = -2476325012186237845L;

    private final HttpStatus httpstatus;

    private final ServiceStatus serviceResponseStatus;

    public BusinessException(ErrorMessage errorMessage) {
        super(errorMessage);
        this.httpstatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.serviceResponseStatus = ServiceStatus.ERROR;
    }

    public BusinessException(ErrorMessage errorMessage, HttpStatus httpstatus) {
        super(errorMessage);
        this.httpstatus = httpstatus;
        this.serviceResponseStatus = ServiceStatus.ERROR;
    }


    public BusinessException(ErrorMessage errorMessage, HttpStatus httpstatus, ServiceStatus serviceResponseStatus) {
        super(errorMessage);
        this.httpstatus = httpstatus;
        this.serviceResponseStatus = serviceResponseStatus;
    }

    public BusinessException(String errorMessage) {
        super(errorMessage);
        this.httpstatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.serviceResponseStatus = ServiceStatus.ERROR;
    }

    public BusinessException(List<ErrorMessage> errorMessage) {
        super(errorMessage);
        this.httpstatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.serviceResponseStatus = ServiceStatus.ERROR;
    }
}
