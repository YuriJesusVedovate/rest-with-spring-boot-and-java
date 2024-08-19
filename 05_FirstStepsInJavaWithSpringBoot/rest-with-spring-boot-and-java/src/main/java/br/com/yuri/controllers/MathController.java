package br.com.yuri.controllers;

import br.com.yuri.services.math.SimpleMathService;
import br.com.yuri.exceptions.UnsupportedMathOperationException;
import br.com.yuri.services.math.numberConverter.NumberConverterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    private SimpleMathService math = new SimpleMathService();

    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable String numberOne, @PathVariable String numberTwo) throws Exception {
        try {
            Double num1 = NumberConverterService.convertToDouble(numberOne);
            Double num2 = NumberConverterService.convertToDouble(numberTwo);
            return math.sum(num1, num2);
        } catch (UnsupportedMathOperationException e) {
            throw new UnsupportedMathOperationException(String.format("Invalid input: both parameters should be valid numbers. Error: %s", e.getMessage()));
        }
        catch (Exception e) {
            throw new Exception("An error occurred while trying to sum the numbers. Error: " + e.getMessage());
        }
    }

    @GetMapping("/sub/{numberOne}/{numberTwo}")
    public Double subtraction(@PathVariable String numberOne, @PathVariable String numberTwo) throws Exception {
        try {
            Double num1 = NumberConverterService.convertToDouble(numberOne);
            Double num2 = NumberConverterService.convertToDouble(numberTwo);
            return math.subtraction(num1, num2);
        } catch (UnsupportedMathOperationException e) {
            throw new UnsupportedMathOperationException(String.format("Invalid input: both parameters should be valid numbers. Error: %s", e.getMessage()));
        }
        catch (Exception e) {
            throw new Exception("An error occurred while trying to subtract the numbers. Error: " + e.getMessage());
        }
    }

    @GetMapping("/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(@PathVariable String numberOne, @PathVariable String numberTwo) throws Exception {
        try {
            Double num1 = NumberConverterService.convertToDouble(numberOne);
            Double num2 = NumberConverterService.convertToDouble(numberTwo);
            return math.multiplication(num1, num2);
        } catch (UnsupportedMathOperationException e) {
            throw new UnsupportedMathOperationException(String.format("Invalid input: both parameters should be valid numbers. Error: %s", e.getMessage()));
        }
        catch (Exception e) {
            throw new Exception("An error occurred while trying to multiply the numbers. Error: " + e.getMessage());
        }
    }

    @GetMapping("/division/{numberOne}/{numberTwo}")
    public Double division(@PathVariable String numberOne, @PathVariable String numberTwo) throws Exception {
        try {
            Double num1 = NumberConverterService.convertToDouble(numberOne);
            Double num2 = NumberConverterService.convertToDouble(numberTwo);
            if (num2 == 0) {
                throw new UnsupportedMathOperationException("Division by zero is not allowed");
            }
            return math.division(num1, num2);
        } catch (UnsupportedMathOperationException e) {
            throw new UnsupportedMathOperationException(String.format("Invalid input: both parameters should be valid numbers. Error: %s", e.getMessage()));
        }
        catch (Exception e) {
            throw new Exception("An error occurred while trying to divide the numbers. Error: " + e.getMessage());
        }
    }

    @GetMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(@PathVariable String numberOne, @PathVariable String numberTwo) throws Exception {
        try {
            Double num1 = NumberConverterService.convertToDouble(numberOne);
            Double num2 = NumberConverterService.convertToDouble(numberTwo);
            return math.mean(num1, num2);
        } catch (UnsupportedMathOperationException e) {
            throw new UnsupportedMathOperationException(String.format("Invalid input: both parameters should be valid numbers. Error: %s", e.getMessage()));
        }
        catch (Exception e) {
            throw new Exception("An error occurred while trying to calculate the average of the numbers. Error: " + e.getMessage());
        }
    }

    @GetMapping("/squareRoot/{number}")
    public Double squareRoot(@PathVariable String number) throws Exception {
        try {
            Double num = NumberConverterService.convertToDouble(number);
            return math.squareRoot(num);
        } catch (UnsupportedMathOperationException e) {
            throw new UnsupportedMathOperationException(String.format("Invalid input: the parameter should be a valid number. Error: %s", e.getMessage()));
        }
        catch (Exception e) {
            throw new Exception("An error occurred while trying to calculate the square root of the number. Error: " + e.getMessage());
        }
    }
}
