package com.example.shantanu.bitmap;

import android.util.Log;

import com.google.android.gms.maps.model.Marker;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.model.DirectionsResult;

public class Directions
{
    public void calculateDirections(Marker marker1, Marker marker2, GeoApiContext geoApiContext) {
        DirectionsApiRequest directionsApiRequest=new DirectionsApiRequest(geoApiContext);
        com.google.maps.model.LatLng origin=new com.google.maps.model.LatLng(marker1.getPosition().latitude,
                marker1.getPosition().longitude);
        Log.v("origin",marker1.getPosition().latitude+","+marker1.getPosition().longitude);
        com.google.maps.model.LatLng destination=new com.google.maps.model.LatLng(marker2.getPosition().latitude,
                marker2.getPosition().longitude);
        Log.v("des",marker2.getPosition().latitude+","+marker2.getPosition().longitude);
        directionsApiRequest.alternatives(true);
        directionsApiRequest.origin(origin);
        directionsApiRequest.destination(destination).setCallback(new PendingResult.Callback<DirectionsResult>() {
            @Override
            public void onResult(DirectionsResult result) {
                Log.d("Dir","Routes : "+result.routes[0].toString());
                Log.d("Dir","Routes : "+result.routes[0].legs[0].distance);
            }

            @Override
            public void onFailure(Throwable e) {
                Log.d("Dir","Failed to get Directions");
            }
        });
    }
}
