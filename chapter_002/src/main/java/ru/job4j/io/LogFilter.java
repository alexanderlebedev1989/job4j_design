package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {

    public static List<String> filter(String file) {
        List<String> lines404 = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            List<String> lines = new ArrayList<>();
            in.lines().forEach(lines::add);
            for (String line : lines) {
                 line = line.replace("-", "-1");
                 String[] arrayLine = line.split(" ");
                 int number = Integer.parseInt(arrayLine[arrayLine.length - 2]);
                 int number1 = Integer.parseInt(arrayLine[arrayLine.length - 1]);
                 if (number == 404 && number1 != -1) {
                     line = line.replace("-1", "-");
                     lines404.add(line);
                 }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines404;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(new FileOutputStream(file)))) {
            for (String line : log) {
                out.write(line + "\n");
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}
