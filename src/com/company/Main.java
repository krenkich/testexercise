package com.company;

import java.util.*;
import java.util.stream.Collectors;

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
        arabicToRoman.put(40, "XL");
        arabicToRoman.put(50, "L");
        arabicToRoman.put(90, "XC");
        arabicToRoman.put(100, "C");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите пример");
        String[] inputArgs = scanner.nextLine().toUpperCase().split(" ");
        if (inputArgs.length != 3) {
            throw new RuntimeException("Неверный ввод. Пример ввода '1 + 1'");
        }
        String arg1 = inputArgs[0];
        String arg2 = inputArgs[2];
        String operation = inputArgs[1];

        boolean arabicArgs = isArabic(arg1) && isArabic(arg2);
        boolean romanArgs = !isArabic(arg1) && !isArabic(arg2);
        int number1;
        int number2;

        if (arabicArgs) {
            try {
                number1 = Integer.parseInt(arg1);
                number2 = Integer.parseInt(arg2);
            } catch (NumberFormatException exception) {
                throw new RuntimeException("Неверный ввод " + exception.getMessage());
            }
        } else if (romanArgs) {
            number1 = convert(arg1);
            number2 = convert(arg2);
        } else {
            throw new RuntimeException("Некорректные аргументы");
        }

        validate(number1);
        validate(number2);

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
            if (result < 1) {
                throw new RuntimeException("Результат менее единицы в римских числах быть не может");
            } else {
                List<Map.Entry<Integer, String>> reversedArabicToRomanEntryList = arabicToRoman.entrySet().stream()
                        .sorted(Comparator.comparing(Map.Entry<Integer, String>::getKey).reversed())
                        .collect(Collectors.toList());
                StringBuilder romanResult = new StringBuilder();
                int i = 0;
                while (result > 0) {
                    Map.Entry<Integer, String> entry = reversedArabicToRomanEntryList.get(i);
                    if (result >= entry.getKey()) {
                        romanResult.append(entry.getValue());
                        result = result - entry.getKey();
                    }
                    if (result < entry.getKey()) {
                        i++;
                    }
                }
                System.out.println(romanResult);
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

    public static Boolean isArabic(String arg) {
        return !romanToArabic.containsKey(arg);
    }
}
