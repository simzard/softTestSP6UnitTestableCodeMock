/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testex.jokefetching;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author simon
 */
public class FetcherFactory implements IFetcherFactory {

    private final List<String> availableTypes = Arrays.asList("EduJoke", "ChuckNorris", "Moma", "Tambal");

    private IJokeFetcher eduJoke;
    private IJokeFetcher chuckNorrisJoke;
    private IJokeFetcher momaJoke;
    private IJokeFetcher tambalJoke;

    public FetcherFactory() {

    }

    public FetcherFactory(IJokeFetcher eduJoke, IJokeFetcher chuckNorrisJoke, IJokeFetcher momaJoke, IJokeFetcher tambalJoke) {
        this.eduJoke = eduJoke;
        this.chuckNorrisJoke = chuckNorrisJoke;
        this.momaJoke = momaJoke;
        this.tambalJoke = tambalJoke;
    }

    @Override
    public List<String> getAvailableTypes() {
        return availableTypes;
    }

    @Override
    public List<IJokeFetcher> getJokeFetchers(String jokesToFetch) {
        List<IJokeFetcher> list = new ArrayList();
        String[] tokens = jokesToFetch.split(",");
        for (String token : tokens) {
            switch(token) {
                case "EduJoke":
                    list.add(new EduJoke());
                    break;
                case "ChuckNorris":
                    list.add(new ChuckNorris());
                    break;
                case "Moma":
                    list.add(new Moma());
                    break;
                case "Tambal":
                    list.add(new Tambal());
                    break;
            }
        }
        return list;
    }
}
