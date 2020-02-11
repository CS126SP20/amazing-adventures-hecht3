package student;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import student.adventure.Adventure;

import java.io.File;
import java.util.List;

public class Main {
    static File defaultFile;

    public static void main(String[] args) {
        defaultFile = new File("src/main/resources/siebel.json"); //Possibly add a getFile function here
        Adventure adventure = new Adventure(defaultFile);
        adventure.readInput(null);
    }

}


