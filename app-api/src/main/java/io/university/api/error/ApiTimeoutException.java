package io.university.api.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 07.04.2019
 */
@ResponseStatus(code = HttpStatus.REQUEST_TIMEOUT, reason = "Aggregator Connection Timeout")
public class ApiTimeoutException extends RuntimeException {

    public ApiTimeoutException() {
        super();
    }

    public ApiTimeoutException(String message) {
        super(message);
    }

    public ApiTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
