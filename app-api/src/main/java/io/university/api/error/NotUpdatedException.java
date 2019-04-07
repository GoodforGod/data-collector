package io.university.api.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 07.04.2019
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Model Not Updated")
public class NotUpdatedException extends RuntimeException {

}
