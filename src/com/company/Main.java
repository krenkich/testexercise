package com.company;

import java.util.HashMap;

public class Main {
    final static HashMap<String, Integer> romanToArabic = new HashMap<>();
    final static HashMap<Integer, String> arabicToRoman = new HashMap<>();

    static {
        romanToArabic.put("I", 1);
        romanToArabic.put("II", 2);
        romanToArabic.put("III", 3);
        romanToArabic.put("IV", 4);
        romanToArabic.put("V", 5);
        romanToArabic.put("VI", 6);
        romanToArabic.put("VII", 7);
        romanToArabic.put("VIII", 8);
        romanToArabic.put("IX", 9);
        romanToArabic.put("X", 10);

        arabicToRoman.put(1, "I");
        arabicToRoman.put(2, "II");
        arabicToRoman.put(3, "III");
        arabicToRoman.put(4, "IV");
        arabicToRoman.put(5, "V");
        arabicToRoman.put(6, "VI");
        arabicToRoman.put(7, "VII");
        arabicToRoman.put(8, "VIII");
        arabicToRoman.put(9, "IX");
        arabicToRoman.put(10, "X");
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            throw new RuntimeException("Для работы программы нужны 3 аргумента");
        }

        String arg1 = (args[0]);
        String arg2 = (args[2]);
        boolean arabicArgs = isArabic(arg1) && isArabic(arg2);
        boolean romanArgs = !isArabic(arg1) && !isArabic(arg2);
        int number1;
        int number2;

        if (arabicArgs) {
            number1 = Integer.parseInt(arg1);
            number2 = Integer.parseInt(arg2);
        } else if (romanArgs) {
            number1 = convert(arg1);
            number2 = convert(arg2);
        } else {
            throw new RuntimeException("Аргументы разных типов");
        }

        validate(number1);
        validate(number2);
        String operation = args[1];
        int result;

        switch (operation) {
            case "+":
                result = number1 + number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "*":
                result = number1 * number2;
                break;
            case "/":
                result = number1 / number2;
                break;
            default:
                throw new IllegalArgumentException(operation + "формат математической операции не удовлетворяет заданию -  (+, -, /, *)");
        }

        if (romanArgs) {
            if (result > 10) {
                throw new RuntimeException("Римские числа больше 10 в этой версии калькулятора не поддерживаются");
            } else if (result < 1) {
                throw new RuntimeException("Резултат менее единицы в римских числах быть не может");
            } else {
                System.out.println(convert(result));
            }
        } else {
            System.out.println(result);
        }
    }

    public static void validate(int arg) {
        if (arg <= 0 || arg > 10)
            throw new IllegalArgumentException(arg + " не подходит под условие программы");
    }

    public static Integer convert(String arg) {
        Integer i = romanToArabic.get(arg);
        if (i == null) {
            throw new IllegalArgumentException("Не римское");
        }
        return i;
    }

    public static String convert(Integer arg) {
        String i = arabicToRoman.get(arg);
        if (i == null) {
            throw new IllegalArgumentException("Не арабское");
        }
        return i;
    }

    public static Boolean isArabic(String arg) {
        return !romanToArabic.containsKey(arg);
    }
}
