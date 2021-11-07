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

    private static List<Message> archive;
    private static int lastMessage;

    public static void init() {
        archive = parseCsv("archive.csv");
        System.out.println(archive.size() + " messages collected.");
        lastMessage = 0;
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
        Message m = archive.get(r.nextInt(archive.size()));
        lastMessage = archive.indexOf(m) + 1;
        return m;
    }

    public static List<Message> getRandomMessages(int messages) {
        List<Message> o = new ArrayList<>();
        Random r = new Random();
        int first = r.nextInt(archive.size());
        for (int i = 1; i <= messages; i++) {
            o.add(archive.get(first + i));
        }
        lastMessage = archive.indexOf(archive.get(first + messages)) + 1;
        return o;
    }
    
    public static Message getLastMessage() {
        Message m = archive.get(lastMessage);
        lastMessage++;
        return m;
    }

    public static List<Message> getLastMessages(int messages) {
        List<Message> o = new ArrayList<>();
        for (int i = 1; i <= messages; i++) {
            o.add(archive.get(lastMessage + i));
        }
        lastMessage = archive.indexOf(archive.get(lastMessage + messages)) + 1;
        return o;
    }

    public static LocalDateTime parseDate(String i) {
        String[] a = i.split(" ");
        String[] date = a[0].split("-");

        int month = 0;
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

        return LocalDateTime.of(Integer.parseInt("20".concat(date[2])), month, Integer.parseInt(date[0]), hour - 1, minute);
    }

}
