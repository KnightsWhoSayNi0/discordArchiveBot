package me.knightswhosayni.archive;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Librarian {

    public static List<Message> archive;

    public static void init() {
        archive = parseCsv("archive.csv");
        System.out.println(archive.size() + " messages collected.");
    }

    private static List<Message> parseCsv(String file) {
        List<Message> output = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(file))) {

            CSVReader csvReader = new CSVReader(br);

                String[] message;
                while ((message = csvReader.readNext()) != null) {
                    output.add(new Message(message[0], message[1], parseDate(message[2]), message[3], message[4]));
                    System.out.println("["+ String.join(", ", message) +"]");
                }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output;
    }

    public static Message getRandomMessage() {
        Random r = new Random();
        return archive.get(r.nextInt(archive.size()));
    }

    public static LocalDateTime parseDate(String i) {
        String[] a = i.split(" ");
        String[] date = a[0].split("-");

        int month = 1;
        switch (date[1]) {
            case "Jan":
                month = 1;
                break;
            case "Feb":
                month = 2;
                break;
            case "Mar":
                month = 3;
                break;
            case "Apr":
                month = 4;
                break;
            case "May":
                month = 5;
                break;
            case "Jun":
                month = 6;
                break;
            case "Jul":
                month = 7;
                break;
            case "Aug":
                month = 8;
                break;
            case "Sep":
                month = 9;
                break;
            case "Oct":
                month = 10;
                break;
            case "Nov":
                month = 11;
                break;
            case "Dec":
                month = 12;
                break;
        }

        String[] time = a[1].split(":");
        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);
        if (a[2].equals("PM")) {
            hour = hour + 12;
        }

        return LocalDateTime.of(20 + Integer.parseInt(date[0]), month, Integer.parseInt(date[2]), hour, minute);
    }

}