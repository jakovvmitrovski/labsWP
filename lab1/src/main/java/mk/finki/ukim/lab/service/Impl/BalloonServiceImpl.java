package mk.finki.ukim.lab.service.Impl;

import mk.finki.ukim.lab.model.Balloon;
import mk.finki.ukim.lab.model.Manufacturer;
import mk.finki.ukim.lab.model.exception.BalloonNotFound;
import mk.finki.ukim.lab.model.exception.ManufacturerNotFoundException;
import mk.finki.ukim.lab.repository.BalloonRepository;
import mk.finki.ukim.lab.repository.ManufacturerRepository;
import mk.finki.ukim.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {

    private final BalloonRepository balloonRepository;
    private final ManufacturerRepository manufacturerRepository;

    public BalloonServiceImpl(BalloonRepository balloonRepository, ManufacturerRepository manufacturerRepository) {
        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return this.balloonRepository.findAllBalloons();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return this.balloonRepository.findAllByNameOrDescription(text);
    }

    @Override
    public Balloon save(String name, String description, Long manufacturerId) {
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
        Balloon balloon = new Balloon(name, description, manufacturer);
        return this.balloonRepository.save(balloon);
    }

    @Override
    public void deleteById(Long balloonId) {
        this.balloonRepository.deleteById(balloonId);
    }

    @Override
    public Balloon searchById(Long balloonId) {
        return this.balloonRepository.searchById(balloonId).orElseThrow(() -> new BalloonNotFound(balloonId));
    }
}
