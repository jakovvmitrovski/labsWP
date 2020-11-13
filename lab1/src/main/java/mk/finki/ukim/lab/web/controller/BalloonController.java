package mk.finki.ukim.lab.web.controller;

import mk.finki.ukim.lab.model.Balloon;
import mk.finki.ukim.lab.model.Manufacturer;
import mk.finki.ukim.lab.model.exception.BalloonNotFound;
import mk.finki.ukim.lab.service.BalloonService;
import mk.finki.ukim.lab.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/balloons")
public class BalloonController {

    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getBalloonsPAge(@RequestParam(required = false) String error, Model model){

        if (error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("balloons", this.balloonService.listAll());
        model.addAttribute("manufacturers", this.manufacturerService.findAll());
        return "listBalloons";
    }

    @GetMapping("/add")
    public String getAddBalloonPage(Model model){
        model.addAttribute("manufacturers", this.manufacturerService.findAll());
        return "add-balloon";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditBalloonPage(@PathVariable Long id, Model model){
        try {
            Balloon balloon = this.balloonService.searchById(id);
            model.addAttribute("balloon", balloon);
            this.balloonService.deleteById(id);
            model.addAttribute("manufacturers", this.manufacturerService.findAll());
            return "add-balloon";
        }catch (RuntimeException e){
            return "redirect:/balloons?error=" + e.getMessage();
        }
    }

    @PostMapping("/add")
    public String saveBalloon(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Long manufacturerId){
        try{
            this.balloonService.save(name, description, manufacturerId);
            return "redirect:/balloons";
        }catch(RuntimeException e){
            return "redirect:/balloons?error=" + e.getMessage();
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id){
        this.balloonService.deleteById(id);
        return "redirect:/balloons";
    }
}
