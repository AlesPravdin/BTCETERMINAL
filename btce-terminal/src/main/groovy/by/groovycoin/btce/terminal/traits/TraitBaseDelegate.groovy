package by.groovycoin.btce.terminal.traits

import groovy.json.JsonSlurper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import by.groovycoin.btce.terminal.LoggerName
import by.groovycoin.btce.terminal.MethodCallerProvider

/**
 * Created by Ales Pravdin on 10/16/14.
 */
class TraitBaseDelegate {

    Logger logger = LoggerFactory.getLogger(LoggerName.TRADETERMINAl.name)

    private final JsonSlurper slurper = new JsonSlurper()
    private final String methodName
    private final MethodCallerProvider methodCallerProvider
    private final String name

    TraitBaseDelegate(
            final MethodCallerProvider methodCallerProvider, final String methodName) {

        this.methodCallerProvider = methodCallerProvider
        this.methodName = methodName
        this.name = methodCallerProvider.getName()
    }

    private String lastRaw

    String requestRaw() {
        logger.info("[${methodCallerProvider.getName()}] requestRaw {}",methodName)
        lastRaw = methodCallerProvider.getCaller().call(methodName);
        logger.info("[${methodCallerProvider.getName()}] requestRaw {}={}",methodName, lastRaw)
        lastRaw
    }

    def requestRaw(final Map params) {
        logger.info("[${methodCallerProvider.getName()}] requestRaw {}{}",methodName, params)
        lastRaw = methodCallerProvider.getCaller().call(methodName, params);
        logger.info("[${methodCallerProvider.getName()}] {}={}",methodName, lastRaw)
        lastRaw
    }

    String getRaw() { lastRaw == null ? requestRaw() : lastRaw }

    def private last
    def private lastParams

    def request() {
        request([:])
    }

    def request(Map params) {
        lastParams = params
        last = slurper.parseText(requestRaw(params));
        last[0].'return'
    }

    def getLast() {

        def needNoParamsRequest = !(last != null && lastParams == null)
        if (needNoParamsRequest) {
            last == request()
        }

        //println ">>>>>>>> gteLast ${this} ${last}"

        last[0].'return'
    }

    def getLast(Map params) {

        def needParamsRequest = !(last != null && lastParams == params)
        if (needParamsRequest) {
            last == request(params)
        }

        last[0].'return'
    }

    def getLastRawParsed() {
        last
    }

}