package by.common.httppoller

import groovy.transform.CompileStatic
import org.apache.http.HttpResponse

/**
 * Created by Ales Pravdin on 12/18/14.
 */
@CompileStatic
public class HttpPollerRequestFailureException extends HttpPollerException {
    final String requestUri;
    final HttpResponse resp;

    public HttpPollerRequestFailureException(
            final String requestUri, final HttpResponse resp) {

        super(resp.getStatusLine().toString());
        this.requestUri = requestUri;
        this.resp = resp;
    }

    public HttpPollerRequestFailureException(
            final String requestUri, final HttpResponse resp, final String message) {

        super(message);
        this.requestUri = requestUri;
        this.resp = resp;
    }

    @Override
    public String toString() {
        return resp.getStatusLine().toString()
    }
}
