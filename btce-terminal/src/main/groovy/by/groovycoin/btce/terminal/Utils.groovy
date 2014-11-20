package by.groovycoin.btce.terminal

import groovy.transform.CompileStatic

/**
 * Created by Ales Pravdin on 11/1/14.
 */
@CompileStatic
final class Utils {
    private Utils() {
    }

    //TODO; this way to slow. Refactor
    static String roundOffToDecPlaces(final Number val, final int decPlaces) {
        return String.format("%.${decPlaces}f", val)
    }
}
