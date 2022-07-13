package com.example.fulltextsearchapi;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.search-db")
public class SearchConfig {
    private String protocol;
    private String host;
    private String port;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
