package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

@Controller
public class DiceController {


    @GetMapping("/roll-dice")
    public String diceRoller(){
        return "/dice/dice-selector";
        //This is returning the html file dice selector for the file we will view
        //It essentially the same as the servlet when we use req.getdispather
    }

    @GetMapping("/roll-dice/{n}")
    //Above we are getting the n variable from roll dice.
    public String diceRevealer(@PathVariable int n, Model model){
        Random random = new Random();
        //Above we are instantiating the random class and then using it below to get a random number between 1 and 6 representing the side of the dice
        int rando = random.nextInt(6) + 1;

        model.addAttribute("guess", n);
        //Above we are setting an attribute for the n varibale that comes from roll-dice
        model.addAttribute("randomNum", rando);
        //Above we are setting an attribute for the rando above for a random number
        //These attributes are what will be used inside the html files
        return "/dice/dice-revealer";
        //this return statement will take us to the dice selector page.
    }
}
