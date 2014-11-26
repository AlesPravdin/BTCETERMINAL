BTCETERMINAL
============

Groovy client for BTC-E trade API


```groovy
@Grab('org.slf4j:slf4j-api:1.7.7')
@Grab('org.codehaus.groovy.modules.http-builder:http-builder:0.7.1')
@Grab('by.groovycoin:btce-terminal:0.9.5g')

import by.groovycoin.btce.terminal.btceapi.trade.TradeTerminal
import by.groovycoin.btce.terminal.btceapi.trade.TradeTerminalManager

System.setProperty("javax.net.ssl.trustStore", "${System.getProperty("user.home")}/.groovycoin/btce/btcetruststore")

TradeTerminalManager tradeTerminalManager = new TradeTerminalManager("apikeys.json")
TradeTerminal tt = tradeTerminalManager.getTerminal("TradeTerminal")

println tt.getFunds()
println tt.placeOrder("usd_cnh", TradeIntention.BUY, "5", "1")
println tt.requestActiveOrders()
println tt.cancelOrder("466XXX858")
```


1. To trade on BTC-e with btce-terminal you need account on BTC-e and at least one API key. You can add API keys in your BTC-e profile.
2. Dump your API keys in apikeys.json (file name is free) like in ./src/examples folder.
3. Dump btc-e certificate and install it in your local truststore. In ./scripts there is grab-btce-cert.sh which automates the process.
4. Build and install btce-terminal in your local maven repository with:
    4.1 Gradle
        gradle install
    4.2
        Maven
        mvn clean install

5. Use ./src/examples as starting and expiration point for your own scripts.

Should you find any part of this project not as groovy as it might be don't hesitate to give me a note or even better to participate!


Cheers,
Ales

P.S.
This project was initially inspired by
http://pastebin.com/jyd9tACF
