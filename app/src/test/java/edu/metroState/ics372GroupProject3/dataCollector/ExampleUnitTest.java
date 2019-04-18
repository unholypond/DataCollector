package edu.metroState.ics372GroupProject3.dataCollector;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testCreateNewStudy(){
        ProgramState ps = new ProgramState();

        assertTrue(ps.createNewStudy("12584", "test study name"));

        ps.createNewStudy("12584", "test study name");
        assertEquals("test study name", ps.getCurrentProgramStudy().getStudyName());
    }
}