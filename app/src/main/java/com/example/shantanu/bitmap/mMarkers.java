package com.example.shantanu.bitmap;

import android.support.v4.app.FragmentActivity;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.GeoApiContext;

import java.util.ArrayList;

public class mMarkers extends FragmentActivity
{
    Marker customMarker1,customMarker2,s;
    int custom=1,ckl=0,st=0;
    double lat,lng,zoom;
    final GoogleMap mMap;
    MarkersList mList;
    Directions directions=new Directions();
    public double a1=15.0366,b1=16.412,ce1=17.207451,d1=16.807451,e1=20.16,max=13.2;

    public mMarkers(GoogleMap mMap) {
        this.mMap = mMap;
    }

    public void addCustomMarkers(final GeoApiContext geoApiContext,final ArrayList<Marker> markerslist) {
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                lat=latLng.latitude;
                lng=latLng.longitude;
                if(custom==1) {
                    customMarker1 = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng))
                            .title(""+custom).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                }
                else if(custom==2) {
                    customMarker2 = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng))
                            .title(""+custom).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                    directions.calculateDirections(customMarker1,customMarker2,geoApiContext);
                }
                else {
                    if(custom%2!=0) {
                        customMarker1.remove();
                        customMarker1 = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng))
                                .title(""+custom).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                        directions.calculateDirections(customMarker2,customMarker1,geoApiContext);
                    }
                    else {
                        customMarker2.remove();
                        customMarker2 = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng))
                                .title(""+custom).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                        directions.calculateDirections(customMarker1,customMarker2,geoApiContext);
                    }
                }
                custom++;
            }
        });
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (st==1&&custom==1) {
                    s.remove();
                    st=0;
                }
                else if(custom==1&&st==0)
                {
                    for (int x = 1; x <= 33; x++)
                        markerslist.get(x).setVisible(false);
                    ckl=1;
                }
                else {
                    if (custom != 1) {
                        customMarker1.remove();
                        if (custom != 2) customMarker2.remove();
                    }
                    custom = 1;
                }
            }
        });
    }

    public void placeCustomMarkers(double zoom,Marker marker,double value) {
        if(zoom>value) {
            marker.setVisible(true);
        }
        else marker.setVisible(false);
    }

    public void showAllInfo(Place place) {
        mList=new MarkersList(mMap);
        mList.createMarkerList();
        if(place!=null&&mList.markerslist!=null) {
            for(int i=0;i<34;i++) {
                if(place.getName().toString().toLowerCase().contains(mList.markerslist.get(i).getTitle().toLowerCase())) {
                    if(s!=null) s.remove();
                    s=mMap.addMarker(new MarkerOptions()
                            .position(mList.markerslist.get(i).getPosition())
                            .title(mList.markerslist.get(i).getTitle())
                            .icon(BitmapDescriptorFactory.defaultMarker()));
                    s.setVisible(true);
                    s.showInfoWindow();
                    st=1;
                }
            }
        }
    }
}

