package by.common.httppoller

import groovy.transform.CompileStatic
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import org.apache.http.HttpResponse

import java.util.concurrent.atomic.AtomicReference

/**
 * Created by Ales Pravdin on 10/5/14.
 */
@CompileStatic
final class HttpPoller implements Poller {
    private final String url
    private final HTTPBuilder httpBuilder
    private final AtomicReference<HttpPollerParameters> parameters = new AtomicReference<>();

    private String path

    HttpPoller(
            final String url) {

        this(url, null)
    }

    HttpPoller(
            final String url,
            final String path) {

        this.url = url
        this.path = path

        HttpPollerParameters defaultParameters = new HttpPollerParameters()
        defaultParameters.userAgent("Chrome/39.0.2171.95")
        defaultParameters.httpTimeout(900)
        parameters.set(defaultParameters)

        this.httpBuilder = path == null ?
                prepareHttpBuilder(url) :
                prepareHttpBuilder("${url}${path}")
    }

    def setup(Closure closure) {
        HttpPollerParameters pollerParameters = new HttpPollerParameters()
        // any method called in closure will be delegated to the memoDsl class
        closure.delegate = pollerParameters
        closure()

        parameters.set(pollerParameters)
        applyParameters(this.httpBuilder, parameters.get())
    }

    @Override
    String poll() {
        return doPoll()
    }

    @Override
    String poll(final String path) {

        setPath(path)

        return doPoll()
    }

    void setPath(final String path) {
        if (!path.equals(this.path)) {
            this.path = path
            this.httpBuilder.uri = url + path
        }
    }

    private long lastStartedMillis
    private long lastStartedNanos

    private long lastFinishedMillis
    private long lastFinishedNanos

    private String doPoll() throws HttpPollerException {

        this.lastFinishedMillis = 0
        this.lastFinishedNanos = 0

        this.lastStartedMillis = System.currentTimeMillis()
        this.lastStartedNanos = System.nanoTime()

        httpBuilder.get([:]) { HttpResponse resp, InputStreamReader data ->

            this.lastFinishedMillis = System.currentTimeMillis()
            this.lastFinishedNanos = System.nanoTime()

            return data.text
        }
    }

    long getLastStartedMillis() {
        return lastStartedMillis
    }

    long getLastStartedNanos() {
        return lastStartedNanos
    }

    long getLastFinishedMillis() {
        return lastFinishedMillis
    }

    long getLastFinishedNanos() {
        return lastFinishedNanos
    }

    long getLastDurationMillis() {
        return lastFinishedMillis - lastStartedMillis
    }

    private HTTPBuilder prepareHttpBuilder(final String url) {
        HTTPBuilder retValue = new HTTPBuilder(url, ContentType.TEXT)

        applyParameters(retValue, parameters.get())

        retValue.handler.failure = { HttpResponse resp ->

            this.lastFinishedMillis = System.currentTimeMillis()
            this.lastFinishedNanos = System.nanoTime()

            throw new HttpPollerRequestFailureException(this.httpBuilder.uri.toString(), resp)
        }

        return retValue
    }

    private static def applyParameters(final HTTPBuilder builder, final HttpPollerParameters parameters) {
        builder.getClient().getParams().setParameter("http.connection.timeout", parameters.connectionTimeoutValue)
        builder.getClient().getParams().setParameter("http.socket.timeout", parameters.socketTimeoutValue)
        ((Map<Object, Object>)builder.getHeaders())."User-Agent" = parameters.userAgentText

        if (parameters.isIgnoreSSLIssuesValue()!= null && parameters.isIgnoreSSLIssuesValue()) {
            builder.ignoreSSLIssues()
        }
    }

    @Override
    public String toString() {
        return "HttpPoller{ ${url} }";
    }
}

/*
        httpBuilder.request(Method.GET) { req ->
            uri.query = [:]
            headers.'User-Agent' = USER_AGENT

            response.success = { HttpResponse resp, InputStreamReader data ->
                return data.text
            }

            response.failure = { HttpResponse resp ->
                throw new HttpPollerRequestFailureException(
                        this.httpBuilder.uri.toString(), resp)
            }
        }
        */
