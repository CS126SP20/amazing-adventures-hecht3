package student;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import student.adventure.Adventure;

import java.io.File;
import java.util.List;

//@TODO Next Steps: figure out how to read and output user input so that I can decide if I really need one method for
//reading and one method for outputting, write initial tests for the functions I have made.

public class Main {
    static File defaultFile;

    public static void main(String[] args) {
        defaultFile = new File("src/main/resources/siebel.json"); //Possibly add a getFile function here
        Adventure adventure = new Adventure(defaultFile);
        adventure.readInput(null);

    }

}


