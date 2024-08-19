package br.com.yuri.Math;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable String numberOne, @PathVariable String numberTwo) {
        try {
            Double num1 = Double.parseDouble(numberOne);
            Double num2 = Double.parseDouble(numberTwo);
            return num1 + num2;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input: both parameters should be valid numbers.");
        }
    }
}
