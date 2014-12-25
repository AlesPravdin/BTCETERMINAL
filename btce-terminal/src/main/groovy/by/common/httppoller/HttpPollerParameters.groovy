package by.common.httppoller

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j;

/**
 * Created by Ales Pravdin on 12/25/14.
 */
@CompileStatic
@Slf4j()
public class HttpPollerParameters {

    private String userAgentText;
    private Integer connectionTimeoutValue;
    private Integer socketTimeoutValue;
    private Boolean ignoreSSLIssuesValue = false

    /**
     * When a method is not recognized, assume it is a title for a new section. Create a simple
     * object that contains the method name and the parameter which is the body.
     */
    def methodMissing(String methodName, args) {
        log.warn "methodMissing ${methodName} ${args}"
    }

    def userAgent(final String userAgent) {
        this.userAgentText = userAgent
    }

    def connectionTimeout(final Integer connectionTimeout) {
        this.connectionTimeoutValue = connectionTimeout
    }

    def socketTimeout(final Integer socketTimeout) {
        this.socketTimeoutValue = socketTimeout
    }

    def httpTimeout(final Integer httpTimeout) {
        this.connectionTimeoutValue = httpTimeout
        this.socketTimeoutValue = httpTimeout
    }

    def getIgnoreSSLIssues() {
        this.ignoreSSLIssuesValue = true
    }

    String getUserAgentText() {
        return userAgentText
    }

    Integer getConnectionTimeoutValue() {
        return connectionTimeoutValue
    }

    Integer getSocketTimeoutValue() {
        return socketTimeoutValue
    }

    Boolean isIgnoreSSLIssuesValue() {
        return ignoreSSLIssuesValue
    }
}
