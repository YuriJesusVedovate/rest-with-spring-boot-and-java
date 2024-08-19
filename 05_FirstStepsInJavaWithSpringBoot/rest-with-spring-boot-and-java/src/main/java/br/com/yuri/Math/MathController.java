package br.com.yuri.Math;

import br.com.yuri.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable String numberOne, @PathVariable String numberTwo) throws Exception {
        try {
            Double num1 = convertToDouble(numberOne);
            Double num2 = convertToDouble(numberTwo);
            return num1 + num2;
        } catch (IllegalArgumentException e) {
            throw new UnsupportedMathOperationException(String.format("Invalid input: both parameters should be valid numbers. Error: %s", e.getMessage()));
        }
        catch (Exception e) {
            throw new Exception("An error occurred while trying to sum the numbers. Error: " + e.getMessage());
        }
    }

    private Double convertToDouble(String strNumber) {
        if (strNumber == null || !isNumeric(strNumber.replaceAll(",", "."))) {
            throw new IllegalArgumentException();
        }
        return Double.parseDouble(strNumber.replaceAll(",", "."));
    }

    private boolean isNumeric(String strNumber) {
        return strNumber != null && strNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
