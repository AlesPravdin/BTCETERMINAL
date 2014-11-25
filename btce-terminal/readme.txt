1. To trade on BTC-e with btce-terminal you need account on BTC-e and at least one API key. You can add API keys in your BTC-e profile.
2. Dump your API keys in apikeys.json (file name is free) like in ./src/examples folder.
3. Dump btc-e certificate and install it in your local truststore. In ./scripts there is grab-btce-cert.sh which automates the process.
4. Build and install btce-terminal in your local maven repository with:
    4.1 Gradle
        gradle install
5. Use ./src/examples as starting and expiration point for your own scripts.

Should you find any part of this project not as groovy as it might be don't hesitate to give me a note o even better to participate!

Cheers,
Ales

P.S.
This project was initially inspire by
http://pastebin.com/jyd9tACF

