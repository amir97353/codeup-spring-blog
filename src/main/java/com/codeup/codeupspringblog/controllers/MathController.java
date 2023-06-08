package com.codeup.codeupspringblog.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

public class MathController {

    @GetMapping("/add/{num1}/{num2}")
    //Get mapping is like the get request in a servlet
    @ResponseBody
    //tells Spring that whatever is returned from this method should be the body of our response
    public double add(@PathVariable double num1, @PathVariable double num2) {
        //num1 and num2 are the variables allows you to acces the varibles from the URI
        return num1 + num2;
    }

    @GetMapping("/subtract/{num1}/{num2}")
    @ResponseBody
    public double subtract(@PathVariable double num1, @PathVariable double num2) {
        return num1 - num2;
    }

    @GetMapping("/multiply/{num1}/{num2}")
    @ResponseBody
    public double multiply(@PathVariable double num1, @PathVariable double num2) {
        return num1 * num2;
    }

    @GetMapping("/divide/{num1}/{num2}")
    @ResponseBody
    public double divide(@PathVariable double num1, @PathVariable double num2) {
        return num1 / num2;
    }
}
