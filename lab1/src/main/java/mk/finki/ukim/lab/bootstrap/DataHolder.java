package mk.finki.ukim.lab.bootstrap;

import mk.finki.ukim.lab.model.Balloon;
import mk.finki.ukim.lab.model.Manufacturer;
import mk.finki.ukim.lab.model.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Balloon> balloons = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();

    @PostConstruct
    public void init(){
        Manufacturer manufacturer1 = new Manufacturer("Nike", "NY", "NY NY 12");
        Manufacturer manufacturer2 = new Manufacturer("Apple", "LA", "LA LA 21");
        balloons.add(new Balloon("balon1", "desc1", manufacturer1));
        balloons.add(new Balloon("Jakovs balloon", "desc2", manufacturer2));
        balloons.add(new Balloon("Nanananan", "desc3", manufacturer1));
        balloons.add(new Balloon("Jakov", "desc4", manufacturer2));
        balloons.add(new Balloon("Jak", "desc5", manufacturer1));
        balloons.add(new Balloon("balon6", "desc6", manufacturer2));
        balloons.add(new Balloon("balon7", "desc7", manufacturer1));
        balloons.add(new Balloon("balon8", "desc8", manufacturer2));

        manufacturers.add(manufacturer1);
        manufacturers.add(manufacturer2);
    }
}
