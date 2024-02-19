package ma.youcode.aftas.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        String timeStamp,
        Integer status,
        String error,
        String message,
        String path
) {

}
