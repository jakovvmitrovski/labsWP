package mk.finki.ukim.lab.repository;

import mk.finki.ukim.lab.bootstrap.DataHolder;
import mk.finki.ukim.lab.model.Balloon;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BalloonRepository {


    public List<Balloon> findAllBalloons(){
        return DataHolder.balloons;
    }

    public List<Balloon> findAllByNameOrDescription(String text){
        return DataHolder.balloons.stream()
                .filter(b -> b.getName().contains(text) || b.getDescription().contains(text))
                .collect(Collectors.toList());
    }

    public Balloon save(Balloon balloon){
        DataHolder.balloons.removeIf(x -> x.getId().equals(balloon.getId()));
        DataHolder.balloons.add(balloon);
        return balloon;
    }

    public Optional<Balloon> searchById(Long balloonId){
        return DataHolder.balloons.stream().filter(x -> x.getId().equals(balloonId)).findFirst();
    }

    public void deleteById(Long balloonId){
        DataHolder.balloons.removeIf(x -> x.getId().equals(balloonId));
    }
}
