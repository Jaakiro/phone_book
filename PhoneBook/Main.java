package org.example.PhoneBook;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите фамилию, имя, отчество, дату рождения, номер телефона и пол, " +
                "разделенные пробелами:");
        String input = scanner.nextLine();

        String[] data = input.split(" ");

        if (data.length != 6) {
            System.out.println("Ошибка: введено больше / меньше данных, чем требуется");
            return;
        }

        try {
            String[] formattedData = new String[6];
            formattedData[0] = data[0];
            formattedData[1] = data[1];
            formattedData[2] = data[2];

            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date date = format.parse(data[3]);
            formattedData[3] = format.format(date);
            if (!formattedData[3].matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
                throw new IllegalArgumentException("Неверный формат даты, введите дату в формате dd.MM.yyyy");
            }

            if (formattedData[0].length() < 2 || formattedData[1].length() < 2 || formattedData[2].length() < 2) {
                throw new IllegalArgumentException("Неверный формат ФИО");
            }
            if (!data[5].equals("f") && !data[5].equals("m")) {
                throw new IllegalArgumentException("Неверный формат пола, введите 'f' или 'm'");
            }

            formattedData[4] = data[4];
            if (formattedData[4].length() < 10) {
                throw new IllegalArgumentException("Неверный формат номера телефона, введите 10-значный номер с 89...");
            }
            formattedData[5] = data[5];

            System.out.println("<" + formattedData[0] + "> <" + formattedData[1] + "> <" + formattedData[2] + "> <" +
                    formattedData[3] + "> <" + formattedData[4] + "> <" + formattedData[5] + ">");

            String fileName = data[0];
            File file = new File(fileName + ".txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file, true);

            writer.write("<" + formattedData[0] + "> <" + formattedData[1] + "> <" + formattedData[2] + "> <"
                    + formattedData[3] + "> <" + formattedData[4] + "> <" + formattedData[5] + ">\n");
            writer.close();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
