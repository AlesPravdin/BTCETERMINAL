package by.groovycoin.btce.terminal.btceapi.trade

import groovy.json.JsonSlurper
import by.groovycoin.btce.terminal.btceapi.trade.traits.*
import groovy.util.logging.Slf4j

import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by Ales Pravdin on 10/11/14.
 */
@Slf4j()
final class TradeTerminal implements InfoTrait, TransHistoryTrait, TradeHistoryTrait, ActiveOrdersTrait, CancelOrderTrait, TradeTrait {

    private final JsonSlurper slurper
    private final MethodCaller methodCaller
    private final String name

    TradeTerminal(final String name, final String key, final String secret) {
        this.name = name
        this.slurper = new JsonSlurper()
        this.methodCaller = new HttpPostMethodCaller(key, secret, "https://btc-e.com/tapi")
    }

    private final AtomicBoolean initiated = new AtomicBoolean(false)

    def init() {
        log.info "Trade terminal '${name}' init started"
        if (!initiated.get()) {

            def info = requestInfo()
            if (info == null) {

                def rawInfo = getLastRawInfoParsed()
                log.info "Trade terminal '${name}'. Trying to re-init after first attempt failure '${rawInfo}'"

                tryRestore(rawInfo[0]?.'error')

                if (requestInfo() == null) {
                    throw new ExceptionInInitializerError("Can't initialize TradeTerminal.")
                }
            }

            initiated.set(true)
        }
        log.info "Trade terminal '${name}' init ended"
    }

    MethodCaller getCaller() {
        methodCaller;
    }

    String getName() {
        return name
    }

    private def tryRestore(final String error) {
        if (error != null) {

            if (error.contains("invalid nonce parameter")) {
                String markerText = "you should send:"
                int pos = error.indexOf(markerText)
                String nonceString = error[pos + markerText.length()..-1]
                try {
                    long nonceLong = Long.parseLong(nonceString)

                    ((HttpPostMethodCaller) this.methodCaller).updateCookie(nonceLong)

                } catch (NumberFormatException e) {
                    throw new ExceptionInInitializerError("Invalid nonce. Can't parse from invalid responce ${e.toString()} ")
                }

            } else {
                throw new ExceptionInInitializerError(error)
            }

        } else {
            throw new ExceptionInInitializerError("Can't initialize TradeTerminal.")
        }
    }

}
