package org.alex.stundenplan.data.rest;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

/**
 * Created by bschramke on 21.04.15.
 */
public class FhbRestInterceptor implements ClientHttpRequestInterceptor {

    final static private List<Charset> acceptedCharsets = new ArrayList<>(1);
    static {
        acceptedCharsets.add(Charset.forName("UTF-8"));
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] data, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.setAcceptCharset(acceptedCharsets);
        return execution.execute(request, data);
    }
}
