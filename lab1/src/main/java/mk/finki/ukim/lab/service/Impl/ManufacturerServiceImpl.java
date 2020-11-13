package mk.finki.ukim.lab.service.Impl;

import mk.finki.ukim.lab.model.Manufacturer;
import mk.finki.ukim.lab.repository.ManufacturerRepository;
import mk.finki.ukim.lab.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return this.manufacturerRepository.findAll();
    }
}
