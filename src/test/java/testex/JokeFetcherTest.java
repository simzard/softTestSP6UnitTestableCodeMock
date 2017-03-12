/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testex;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import testex.jokefetching.ChuckNorris;
import testex.jokefetching.EduJoke;

import testex.jokefetching.IFetcherFactory;
import testex.jokefetching.IJokeFetcher;
import testex.jokefetching.Moma;
import testex.jokefetching.Tambal;

/**
 *
 * @author simon
 */
@RunWith(MockitoJUnitRunner.class)
public class JokeFetcherTest {

    @Mock
    private IDateFormatter dfMock;

    @Mock
    Moma moma;
    @Mock
    ChuckNorris chuck;
    @Mock
    EduJoke edu;
    @Mock
    Tambal tambal;

    @Mock
    private IFetcherFactory ffMock;

    private JokeFetcher jokeFetcher;

    @Before
    public void setup() throws Exception {
        List<IJokeFetcher> fetchers = Arrays.asList(edu, chuck, moma, tambal);
        when(ffMock.getJokeFetchers("EduJoke,ChuckNorris,Moma,Tambal")).thenReturn(fetchers);
        List<String> types = Arrays.asList("EduJoke", "ChuckNorris", "Moma", "Tambal");

        when(ffMock.getAvailableTypes()).thenReturn(types);
        jokeFetcher = new JokeFetcher(dfMock, ffMock);
        
        given(edu.getJoke()).willReturn(new Joke("This is an educational joke", "EducationalJokes.org"));
        given(chuck.getJoke()).willReturn(new Joke("When Chuck Norris kicks a roundhouse...", "ChuckNorrisJokes.org"));
        given(moma.getJoke()).willReturn(new Joke("Yo mama is so ugly...", "JoMamaJokes.org"));
        given(tambal.getJoke()).willReturn(new Joke("Tambalaaa...", "TambalaJokes.org"));
        
        
    }

    @Test
    public void verifyTestGetAvailableTypesHasFour() {
        assertThat(jokeFetcher.getAvailableTypes(), hasItems("EduJoke", "ChuckNorris", "Moma", "Tambal"));
        assertThat(jokeFetcher.getAvailableTypes().size(), is(4));
    }

    @Test
    public void testCheckIfValidToken() {
        String jokeValidTokens = "EduJoke,ChuckNorris,Moma,Tambal";
        assertThat(jokeFetcher.isStringValid(jokeValidTokens), is(true));
        String jokeNonValidTokens = "EduJokez,ChuckNorrisz,Momaz,Tambalz";
        assertThat(jokeFetcher.isStringValid(jokeNonValidTokens), is(false));
    }

    @Test
    public void testGetJokes() throws Exception {
        // here I test the getTimeZoneString, i.e. state based stating
        given(dfMock.getFormattedDate(eq("Europe/Copenhagen"), anyObject())).willReturn("17 feb. 2017 10:56 AM");
        assertThat(jokeFetcher.getJokes("EduJoke,ChuckNorris,ChuckNorris,Moma,Tambal", "Europe/Copenhagen").getTimeZoneString(), is("17 feb. 2017 10:56 AM"));

        // now we check that the mock was called one time only
        verify(dfMock, times(1)).getFormattedDate(eq("Europe/Copenhagen"), anyObject());

    }
    
    @Test
    public void testEduJoke() throws Exception {
        String expectedJoke = "This is an educational joke";
        String expectedReference = "EducationalJokes.org";
        Jokes jokes = jokeFetcher.getJokes("EduJoke,ChuckNorris,Moma,Tambal", "Europe/Copenhagen");
        assertThat(jokes.getJokes().get(0).toString(), is(
         "Joke{" + "joke=" + expectedJoke + ", reference=" + expectedReference + '}'
        ) );
    }
    
    @Test
    public void testChuckNorrisJoke() throws Exception {
        String expectedJoke = "When Chuck Norris kicks a roundhouse...";
        String expectedReference = "ChuckNorrisJokes.org";
        Jokes jokes = jokeFetcher.getJokes("EduJoke,ChuckNorris,Moma,Tambal", "Europe/Copenhagen");
        assertThat(jokes.getJokes().get(1).toString(), is(
         "Joke{" + "joke=" + expectedJoke + ", reference=" + expectedReference + '}'
        ) );
    }
    
    @Test
    public void testYoMamaJoke() throws Exception {
        String expectedJoke = "Yo mama is so ugly...";
        String expectedReference = "JoMamaJokes.org";
        Jokes jokes = jokeFetcher.getJokes("EduJoke,ChuckNorris,Moma,Tambal", "Europe/Copenhagen");
        assertThat(jokes.getJokes().get(2).toString(), is(
         "Joke{" + "joke=" + expectedJoke + ", reference=" + expectedReference + '}'
        ) );
    }
    
     @Test
    public void testTambalaJoke() throws Exception {
        String expectedJoke = "Tambalaaa...";
        String expectedReference = "TambalaJokes.org";
        Jokes jokes = jokeFetcher.getJokes("EduJoke,ChuckNorris,Moma,Tambal", "Europe/Copenhagen");
        assertThat(jokes.getJokes().get(3).toString(), is(
         "Joke{" + "joke=" + expectedJoke + ", reference=" + expectedReference + '}'
        ) );
    }

}
