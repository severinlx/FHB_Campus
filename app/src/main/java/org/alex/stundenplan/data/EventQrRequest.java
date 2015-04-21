package org.alex.stundenplan.data;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bschramke on 21.04.15.
 */
public class EventQrRequest {

    private long uuid;

    @SerializedName("api-key")
    private String apiKey;

    public EventQrRequest(long uuid, @NonNull String apiKey) {
        this.uuid = uuid;
        this.apiKey = apiKey;
    }
}
