package student;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import student.adventure.Adventure;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {
    static File defaultFile;
    static Adventure adventure;
    static File userFile;

    public static void main(String[] args) {
        defaultFile = new File("src/main/resources/siebel.json");
        System.out.println("Please input a filepath with a valid JSON for your adventure game.");
        System.out.println("If you do not provide a valid file, the default file for " +
                "siebel will be used.");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        userFile = new File(input);
        if (!userFile.isDirectory()) {
            System.out.println("You have not provided a file or you have provided a bad filepath. "
                    + "The default JSON will be used.\nPlease press enter to continue");
            adventure = new Adventure(defaultFile);
            adventure.readInput(null);
        } else {
            System.out.println("You have provided a valid file. " +
                    "\nPlease press enter to continue. Enjoy your game!");
            adventure = new Adventure(userFile);
            adventure.readInput(null);
        }
    }

}


