package mk.finki.ukim.lab.service;


import mk.finki.ukim.lab.model.Balloon;

import java.util.List;
import java.util.Optional;

public interface BalloonService {
    List<Balloon> listAll();
    List<Balloon> searchByNameOrDescription(String text);
    Balloon save(String name, String description, Long manufacturerId);
    void deleteById(Long balloonId);
    Balloon searchById(Long balloonId);
}
