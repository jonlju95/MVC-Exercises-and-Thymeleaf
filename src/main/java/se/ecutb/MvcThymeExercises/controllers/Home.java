package se.ecutb.MvcThymeExercises.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Home {
    private List<String> nameList = new ArrayList<>();

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        contactList(model);
        return "contact";
    }

    @PostMapping("/contact")
    public String contact(@RequestParam String name) {
        if (!name.isEmpty()) {
            nameList.add(name);
        }
        return "redirect:/contact";
    }

    public void contactList(Model model) {
        model.addAttribute("names", nameList);
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/temperature")
    public String temperature(@RequestParam(value = "name", required = false) String reading, Model model) {
        String givenTemp = null;
        try {
            if(reading != null) {
                if (reading.isEmpty()) {
                    givenTemp = "Not temp entered";
                } else if(Double.parseDouble(reading)<=35) {
                    givenTemp = "You are hypothermic";
                } else if(Double.parseDouble(reading)>=38) {
                    givenTemp = "You have a fever";
                } else {
                    givenTemp = "You're within normal temperatures";
                }
            }
        } catch (NumberFormatException e) {
            givenTemp = "Not a number";
        }
        model.addAttribute("temp", givenTemp);
        return "temperature";
    }
}
