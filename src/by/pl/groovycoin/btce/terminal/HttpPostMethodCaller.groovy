package by.pl.groovycoin.btce.terminal

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import org.apache.commons.codec.binary.Hex

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import java.util.concurrent.atomic.AtomicLong

import static groovyx.net.http.Method.POST

/**
 * Created by Ales Pravdin on 10/16/14.
 */
class HttpPostMethodCaller implements MethodCaller {

    private final String apiKey

    private final String apiSecretString
    private final SecretKeySpec apiSecret;

    private final HTTPBuilder httpBuilder
    private final Mac mac

    private static ConcurrentMap<String, AtomicLong> nonceMap = new ConcurrentHashMap<>()

    HttpPostMethodCaller(
            final String key,
            final String secret,
            final String url) {

        this.apiKey = key
        this.apiSecretString = secret

        this.apiSecret = initHmacSHA512Secret(this.apiSecretString)
        this.mac = initHmacSHA512Mac(this.apiSecret)

        //this.nonce = new AtomicLong(System.currentTimeMillis() / 100 as long)
        //this.nonce = new AtomicLong(1414696270)
        nonceMap.putIfAbsent(apiKey, new AtomicLong(System.currentTimeMillis() / 100 as long))

        this.httpBuilder = new HTTPBuilder(url)
    }

    private AtomicLong getNonceHolder() {
        nonceMap.get(apiKey)
    }

    @Override
    String call(String data) {
        call(data, [:])
    }

    @Override
    String call(final String data, final Map params) {
        Object[] result
        try {
            result = doPostData(encodePOST(data, params))
            if (result[0] != 0) {
                //try again
                result = doPostData(encodePOST(data, params))
            }
        } catch (Exception e) {
            //try again
            result = doPostData(encodePOST(data, params))
        }

        return result[2]
    }

    private def encodePOST(final String method) {
        return encodePOST(method, [:])
    }

    private def encodePOST(final String method, final Map params) {

        Map<String, String> arguments = params;

        if (arguments == null) {  // If the user provided no arguments, just create an empty argument array.
            arguments = new HashMap<String, String>();
        }

        arguments.put("method", method);  // Add the method to the post data.
        arguments.put("nonce", "" + getNonceHolder().incrementAndGet());  // Add the dummy nonce.

        String postData = "";


        for (Iterator argumentIterator = arguments.entrySet().iterator(); argumentIterator.hasNext();) {
            Map.Entry argument = (Map.Entry) argumentIterator.next();

            if (postData.length() > 0) {
                postData += "&";
            }
            postData += argument.getKey() + "=" + argument.getValue();
        }

        return postData
    }

    // Thread local variable containing each thread's ID
    private final ThreadLocal<Object[]> postResultHolder =
            new ThreadLocal<Object[]>() {
                @Override
                protected Object[] initialValue() {
                    return new Object[3];
                }
            };

    private Object[] doPostData(final String data) {

        //println ">>>>>>>>>>>>> doPost ${System.currentTimeMillis()} ${Thread.currentThread().getName()} ${data}"

        Object[] postResult = postResultHolder.get();

        String signedData = signData(data)
        httpBuilder.request(POST, ContentType.URLENC) {
            headers."Key" = apiKey
            headers."Sign" = signedData
            body = data

            /*
            response.success = { resp, data ->
                println "POST response status: ${resp.statusLine}"
                println "${data.toString()}"
            }
            */

            response.success = { resp, respData ->
                postResult[0] = 0
                postResult[1] = resp.statusLine
                postResult[2] = respData.toString().replace('}:null]', '}]')
            }

            response.failure = { resp ->
                postResult[0] = 1
                postResult[1] = resp.statusLine
                postResult[2] = null

                println("request failure >> '$resp.statusLine'")
                println("request failure key>> $apiKey")
                println("request failure Sign>> $signedData")
                println("request failure body>> $data")
                println("request failure headers>> ${resp.getAllHeaders().toString()}")
                println("request failure client params>> ${resp.getParams().getClientParams().parameters.toString()}")
                println("request failure request params>> ${resp.getParams().getRequestParams().parameters.toString()}")
                println("request failure >> $resp")
            }
        }

        return postResult
    }

    private String signData(final String data) throws UnsupportedEncodingException {
        return Hex.encodeHexString(mac.doFinal(data.getBytes("UTF-8")))
    }


    private static SecretKeySpec initHmacSHA512Secret(final String apiSecretString)
            throws UnsupportedEncodingException {
        return new SecretKeySpec(apiSecretString.getBytes("UTF-8"), "HmacSHA512")
    }

    private static Mac initHmacSHA512Mac(final SecretKeySpec secret)
            throws InvalidKeyException, NoSuchAlgorithmException {
        Mac retValue = Mac.getInstance("HmacSHA512")
        retValue.init(secret)
        return retValue
    }

    def updateCookie(final long cookie) {
        getNonceHolder().set(cookie)
    }
}
