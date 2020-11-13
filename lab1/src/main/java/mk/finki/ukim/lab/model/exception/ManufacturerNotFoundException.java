package mk.finki.ukim.lab.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ManufacturerNotFoundException extends RuntimeException{
    public ManufacturerNotFoundException(Long manufacturerId){
        super(String.format("Manufacturer with id: %d not found!", manufacturerId));
    }
}
