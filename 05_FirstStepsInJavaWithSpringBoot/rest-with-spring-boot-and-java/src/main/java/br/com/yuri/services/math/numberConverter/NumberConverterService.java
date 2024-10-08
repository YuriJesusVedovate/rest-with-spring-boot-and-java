package br.com.yuri.services.math.numberConverter;

import br.com.yuri.exceptions.RequiredObjectIsNullException;

public class NumberConverterService {

    public static Double convertToDouble(String strNumber) {
        if (strNumber == null || !isNumeric(strNumber.replaceAll(",", "."))) {
            throw new RequiredObjectIsNullException();
        }
        return Double.parseDouble(strNumber.replaceAll(",", "."));
    }

    private static boolean isNumeric(String strNumber) {
        return strNumber != null && strNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
