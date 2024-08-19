package br.com.yuri.Math;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable String numberOne, @PathVariable String numberTwo) {
        try {
            Double num1 = convertToDouble(numberOne);
            Double num2 = convertToDouble(numberTwo);
            return num1 + num2;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("Invalid input: both parameters should be valid numbers. Error: %s", e.getMessage()));
        }
    }

    private Double convertToDouble(String strNumber) {
        String number = strNumber.replaceAll(",", ".");
        return Double.parseDouble(number);
    }
}
