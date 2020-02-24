package student;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import student.adventure.Adventure;
import org.junit.contrib.java.lang.system.SystemOutRule;
/**
 * https://stefanbirkner.github.io/system-rules/apidocs/org/junit/contrib/java/lang/system/SystemOutRule.html
 * The SystemOutRule API will be used for these tests to make sure that the right file was parsed.
 * The main prints different statements depending on the user's file input.
 */
import java.io.File;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static student.Main.defaultFile;

public class FileInputTest {

  @Rule
  public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

  @Before
  public void setUp() {
  }

  @Test
  public void invalidURL() {
    Main.main(new String[] {"https://www.google.com/"});
    assertEquals(Main.adventure, new Adventure(defaultFile));
  }
}
