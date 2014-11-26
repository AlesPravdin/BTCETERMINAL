package by.groovycoin.btce.terminal.btceapi.trade

import groovy.json.JsonSlurper

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

/**
 * Created by Ales Pravdin on 11/1/14.
 */
class TradeTerminalManager {

    private final Map<String, TradeTerminal> allTerminals
    private final ConcurrentMap<String, TradeTerminal> lazyTerminals


    TradeTerminalManager(final String confFileName) {
        def inputFile = new File("./${confFileName}")
        def apiKeys = new JsonSlurper().parseText(inputFile.text)

        Map<String, TradeTerminal> tts = apiKeys.inject([:]) { result, key, value ->
            result.put(key, new TradeTerminal(value.'name' as String, value.'key' as String, value.'secret' as String))
            result
        }

        this.allTerminals = Collections.unmodifiableMap(tts)
        this.lazyTerminals = new ConcurrentHashMap<>()
    }

    TradeTerminal getTerminal(final String name) {
        def retValue = lazyTerminals.get(name)

        if (retValue == null) {
            retValue = allTerminals.get(name)

            if (retValue != null) {
                retValue.init()
                lazyTerminals.putIfAbsent(name, retValue)
            }
        }

        return retValue
    }
}
