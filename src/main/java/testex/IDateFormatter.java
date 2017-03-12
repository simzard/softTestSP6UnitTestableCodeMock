/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testex;

import java.util.Date;

/**
 *
 * @author simon
 */
public interface IDateFormatter {

    /**
     * Returns a formatted string representing NOW, adjusted to the time zone string
     * passed in
     * @param timeZone. Must be a valid time zone as returned by:TimeZone.getAvailableIDs()
     * @return Time Zone string formatted like ("dd MMM yyyy hh:mm aa") and adjusted to the provided
     * time zone
     * @throws JokeException If the timeZone string is not a valid string
     */
    String getFormattedDate(String timeZone, Date time) throws JokeException;
    
}
