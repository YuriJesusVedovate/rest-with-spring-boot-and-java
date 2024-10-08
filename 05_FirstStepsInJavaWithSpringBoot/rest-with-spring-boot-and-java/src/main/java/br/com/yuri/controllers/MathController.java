package br.com.yuri.controllers;

import br.com.yuri.services.math.MathService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/math")
public class MathController {

    private final MathService mathService = new MathService();

    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable String numberOne, @PathVariable String numberTwo) throws Exception {
        return mathService.Sum(numberOne, numberTwo);
    }

    @GetMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(@PathVariable String numberOne, @PathVariable String numberTwo) throws Exception {
        return mathService.Subtraction(numberOne, numberTwo);
    }

    @GetMapping("/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(@PathVariable String numberOne, @PathVariable String numberTwo) throws Exception {
        return mathService.Multiplication(numberOne, numberTwo);
    }

    @GetMapping("/division/{numberOne}/{numberTwo}")
    public Double division(@PathVariable String numberOne, @PathVariable String numberTwo) throws Exception {
        return mathService.Division(numberOne, numberTwo);
    }

    @GetMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(@PathVariable String numberOne, @PathVariable String numberTwo) throws Exception {
        return mathService.Mean(numberOne, numberTwo);
    }

    @GetMapping("/square-root/{number}")
    public Double squareRoot(@PathVariable String number) throws Exception {
        return mathService.SquareRoot(number);
    }
}
