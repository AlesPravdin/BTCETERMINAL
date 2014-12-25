package by.groovycoin.btce.terminal.btceapi.marketdata

import by.common.httppoller.HttpPoller
import groovy.json.JsonSlurper

/**
 * Created by Ales Pravdin on 12/25/14.
 */
class MarketDataPoller {

    private final HttpPoller poller
    private final JsonSlurper slurper

    MarketDataPoller() {
        this.poller = new HttpPoller("https://btc-e.com")
        poller.setup {
            userAgent 'Chrome/39.0.2171.95'
            httpTimeout 600
            ignoreSSLIssues
        }

        this.slurper = new JsonSlurper()
    }

    String requestRawMarketData(final String symbol){ doRequestRawMarketData(symbol)}

    Map<String, List<List<BigDecimal>>> requestMarketData(final String symbol){
        slurper.parseText(doRequestRawMarketData(symbol))
    }

    private String doRequestRawMarketData(String symbol) { poller.poll("/api/2/${symbol}/depth")}
}
