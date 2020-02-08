package student;

import com.fasterxml.jackson.databind.ObjectMapper;
import student.adventure.Adventure;

import java.io.File;
import java.util.List;

//@TODO Next Steps: figure out how to read and output user input so that I can decide if I really need one method for
//reading and one method for outputting, write initial tests for the functions I have made.

public class Main {
    public static void main(String[] args) {
        File defaultFile = new File("src/main/resources/siebel.json"); //Possibly add a getFile function here
        ObjectMapper mapper = new ObjectMapper();
        try {
            RoomExplorer explorer = mapper.readValue(defaultFile, RoomExplorer.class);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        Adventure.readInput();



    }

}


