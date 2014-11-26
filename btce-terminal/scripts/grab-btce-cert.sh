#!/bin/bash

#read more
#http://www.dontesta.it/blog/en/web-services/importing-ssl-certificates-on-the-java-keystore-jks/
#http://stackoverflow.com/questions/7885785/using-openssl-to-get-the-certificate-from-a-server
#http://notepad2.blogspot.com/2012/04/import-gmail-certificate-into-java.html
#http://www.grim.se/guide/jre-cert

ROOTDIR=~/.groovycoin/btce
BTCETRUSTSTORE=btcetruststore
HOST=btc-e.com
PORT=443

mkdir -p ${ROOTDIR}
mkdir -p ${ROOTDIR}/cert

echo | openssl s_client -connect ${HOST}:${PORT} 2>&1 | sed -ne '/-BEGIN CERTIFICATE-/,/-END CERTIFICATE-/p' | openssl x509 -outform der -out ${ROOTDIR}/cert/btce.der
keytool -import -keystore ${ROOTDIR}/${BTCETRUSTSTORE} -alias btce -v -file ${ROOTDIR}/cert/btce.der


echo "
Well done!

We've just exported ${HOST} cert and imported it into the local truststore ${ROOTDIR}/${BTCETRUSTSTORE}
To test success of the operation use command 'keytool -list -keystore ${ROOTDIR}/${BTCETRUSTSTORE} -v -alias btce'

In groovy/java program/script use System property
    System.setProperty(\"javax.net.ssl.trustStore\", \"${ROOTDIR}/${BTCETRUSTSTORE}\");
or
    '-Djavax.net.ssl.trustStore=${ROOTDIR}/${BTCETRUSTSTORE}'

To debug ssl use '-Djavax.net.debug=ssl'
"

#keytool -list -keystore ~/.btcekeystore -v -alias btce
