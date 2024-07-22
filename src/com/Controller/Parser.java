package com.Controller;

public class Parser {
    public static boolean valPattern(String input, String pattern) {
        return input.matches(pattern);
    }

    public static int[] parseDate(String input) {
        if (input.matches("^(\\d{1,2})\\/(\\d{1,2})\\/(\\d{4})$")) {
            String[] splitInput = input.split("/");
            int[] date = new int[] {
                Integer.parseInt(splitInput[0]),
                Integer.parseInt(splitInput[1]),
                Integer.parseInt(splitInput[2])
            };

            if (date[0] >= 1 && date[0] <= 31) {
                if (date[1] >= 1 && date[1] <= 12) {
                    if (date[2] >= 1968) return date;
                    else System.out.println("Error año no válido");
                } else System.out.println("Error mes inválido");
            } else System.out.println("Día inválido");
        }
        return null;
    }

    public static String[] splitString(String input) {
        return input.split("#");
    }
}