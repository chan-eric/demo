package com.rbc.midtier.arrangements.config;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.springframework.stereotype.Component;

@Component
public class ContentLengthHeaderRemover implements HttpRequestInterceptor{

	@Override
    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        request.removeHeaders(HTTP.CONTENT_LEN);// fighting org.apache.http.protocol.RequestContent's ProtocolException("Content-Length header already present");
    }
}
