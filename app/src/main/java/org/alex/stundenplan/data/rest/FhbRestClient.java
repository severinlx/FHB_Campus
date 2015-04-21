/**
 * @file FhbRestServices.java
 * @author bschramke
 * @date 2015-04-21
 */
package org.alex.stundenplan.data.rest;


import org.alex.stundenplan.data.MensaDay;
import org.androidannotations.annotations.rest.Accept;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.MediaType;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.util.List;

@Rest(rootUrl = "https://mobile-quality-research.org/services",
        converters = {GsonHttpMessageConverter.class},
        interceptors = {FhbRestInterceptor.class})
public interface FhbRestClient extends RestClientErrorHandling {

    @Get("/meals")
    @Accept(MediaType.APPLICATION_JSON)
    List<MensaDay> getMensaDays();

}
