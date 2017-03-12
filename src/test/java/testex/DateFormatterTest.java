/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testex;

import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;


/**
 *
 * @author simon
 */
public class DateFormatterTest {
    
    
    private IDateFormatter dateFormatter;
    
    public DateFormatterTest() {
    }

    @Before
    public void setup() {
        dateFormatter = new DateFormatter();
    }
    
    @Test(expected = JokeException.class)
    public void testExceptionIsThrownIfIllegalTimeZoneString() throws JokeException {
        dateFormatter.getFormattedDate("unknown timezone", new Date());
    }
    
    // just a single test, to see if it is working
    // unfortunately I don't have the time to test all the time zones
    @Test
    public void testThatTheTimeZoneWorksForCopenhagen() throws JokeException {
        String expected = "17 Feb 2017 10:56 AM";
        assertThat(dateFormatter.getFormattedDate("Europe/Copenhagen", new Date(2017 - 1900, 1, 17, 10, 56)), is(expected) );
    }
    
}
