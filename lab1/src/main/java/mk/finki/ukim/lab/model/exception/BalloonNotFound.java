package mk.finki.ukim.lab.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BalloonNotFound extends RuntimeException {
    public BalloonNotFound(Long id) {
        super(String.format("Balloon with id: %d not found!", id));
    }
}
