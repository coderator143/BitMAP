package com.example.shantanu.bitmap;

import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MarkersList extends FragmentActivity {

        GoogleMap mMap;
        mMarkers ob;
        public  MarkersList(GoogleMap mMap)
        {
            this.mMap=mMap;
        }
        ArrayList<Marker> markerslist=new ArrayList<>();
        public void createMarkerList() {
            markerslist.add(0, mMap.addMarker(new MarkerOptions().position(new LatLng(23.412256, 85.439942))
                    .title("BIT MESRA").icon(BitmapDescriptorFactory.fromResource(R.drawable.academicb1))));
            markerslist.add(1, mMap.addMarker(new MarkerOptions().position(new LatLng(23.424791, 85.433553))
                    .title("SPORTS COMPLEX").icon(BitmapDescriptorFactory.fromResource(R.drawable.sport)).visible(false)));
            markerslist.add(2, mMap.addMarker(new MarkerOptions().position(new LatLng(23.412834, 85.441693))
                    .title("R & D").icon(BitmapDescriptorFactory.fromResource(R.drawable.rd)).visible(false)));
            markerslist.add(3, mMap.addMarker(new MarkerOptions().position(new LatLng(23.422395, 85.438684))
                    .title("G P BIRLA AUDI").icon(BitmapDescriptorFactory.fromResource(R.drawable.auditorium)).visible(false)));
            markerslist.add(4, mMap.addMarker(new MarkerOptions().position(new LatLng(23.416948, 85.4356980))
                    .title("DEPARTMENT OF MANAGEMENT").icon(BitmapDescriptorFactory.fromResource(R.drawable.academicb1)).
                            visible(false)));
            markerslist.add(5, mMap.addMarker(new MarkerOptions().position(new LatLng(23.410594, 85.440889))
                    .title("DEPARTMENT OF PHARMACEUTICAL").icon(BitmapDescriptorFactory.fromResource(R.drawable.chem)).
                            visible(false)));
            markerslist.add(6, mMap.addMarker(new MarkerOptions().position(new LatLng(23.410128, 85.440625))
                    .title("DEPARTMENT OF BIOENGINEERING").icon(BitmapDescriptorFactory.fromResource(R.drawable.chem)).
                            visible(false)));
            markerslist.add(7, mMap.addMarker(new MarkerOptions().position(new LatLng(23.410426, 85.440224))
                    .title("DEPARTMENT OF CHEMICAL").icon(BitmapDescriptorFactory.fromResource(R.drawable.chem)).visible(false)));
            markerslist.add(8, mMap.addMarker(new MarkerOptions().position(new LatLng(23.4139424, 85.4401605))
                    .title("HOSTEL 1").icon(BitmapDescriptorFactory.fromResource(R.drawable.hostels)).visible(false)));
            markerslist.add(9, mMap.addMarker(new MarkerOptions().position(new LatLng(23.413578, 85.438474))
                    .title("HOSTEL 2").icon(BitmapDescriptorFactory.fromResource(R.drawable.hostels)).visible(false)));
            markerslist.add(10, mMap.addMarker(new MarkerOptions().position(new LatLng(23.4159914, 85.4395926))
                    .title("HOSTEL NO. 3").icon(BitmapDescriptorFactory.fromResource(R.drawable.hostels)).visible(false)));
            markerslist.add(11, mMap.addMarker(new MarkerOptions().position(new LatLng(23.4155238, 85.4379242))
                    .title("HOSTEL 4").icon(BitmapDescriptorFactory.fromResource(R.drawable.hostels)).visible(false)));
            markerslist.add(12, mMap.addMarker(new MarkerOptions().position(new LatLng(23.4206811, 85.4343364))
                    .title("HOSTEL NO.- 5").icon(BitmapDescriptorFactory.fromResource(R.drawable.hostels)).visible(false)));
            markerslist.add(13, mMap.addMarker(new MarkerOptions().position(new LatLng(23.4226888, 85.4331968))
                    .title("HOSTEL 6").icon(BitmapDescriptorFactory.fromResource(R.drawable.hostels)).visible(false)));
            markerslist.add(14, mMap.addMarker(new MarkerOptions().position(new LatLng(23.4244907, 85.4321930))
                    .title("HOSTEL NO-7").icon(BitmapDescriptorFactory.fromResource(R.drawable.hostels)).visible(false)));
            markerslist.add(15, mMap.addMarker(new MarkerOptions().position(new LatLng(23.4159607, 85.4410443))
                    .title("HOSTEL NO. 8").icon(BitmapDescriptorFactory.fromResource(R.drawable.hostels)).visible(false)));
            markerslist.add(16, mMap.addMarker(new MarkerOptions().position(new LatLng(23.4167495, 85.4434995))
                    .title("HOSTEL NO. 9").icon(BitmapDescriptorFactory.fromResource(R.drawable.hostels)).visible(false)));
            markerslist.add(17, mMap.addMarker(new MarkerOptions().position(new LatLng(23.419066, 85.435052))
                    .title("HOSTEL 10").icon(BitmapDescriptorFactory.fromResource(R.drawable.hostels)).visible(false)));
            markerslist.add(18, mMap.addMarker(new MarkerOptions().position(new LatLng(23.4179977, 85.4356474))
                    .title("HOSTEL 11").icon(BitmapDescriptorFactory.fromResource(R.drawable.hostels)).visible(false)));
            markerslist.add(19, mMap.addMarker(new MarkerOptions().position(new LatLng(23.4187918, 85.4341889))
                    .title("HOSTEL 12").icon(BitmapDescriptorFactory.fromResource(R.drawable.hostels)).visible(false)));
            markerslist.add(20, mMap.addMarker(new MarkerOptions().position(new LatLng(23.4176854, 85.4345809))
                    .title("HOSTEL 13").icon(BitmapDescriptorFactory.fromResource(R.drawable.hostels)).visible(false)));
            markerslist.add(21, mMap.addMarker(new MarkerOptions().position(new LatLng(23.4255727, 85.4375873))
                    .title("RESEARCH SCHOLAR HOSTEL").icon(BitmapDescriptorFactory.fromResource(R.drawable.hostels)).visible(false)));
            markerslist.add(22, mMap.addMarker(new MarkerOptions().position(new LatLng(23.416731, 85.438045))
                    .title("FOOD JOURNEY").icon(BitmapDescriptorFactory.fromResource(R.drawable.dhaba)).visible(false)));
            markerslist.add(23, mMap.addMarker(new MarkerOptions().position(new LatLng(23.419373, 85.433352))
                    .title("CHHOTU DHABA").icon(BitmapDescriptorFactory.fromResource(R.drawable.dhaba2)).visible(false)));
            markerslist.add(24, mMap.addMarker(new MarkerOptions().position(new LatLng(23.423315, 85.432068))
                    .title("TECHNO").icon(BitmapDescriptorFactory.fromResource(R.drawable.dhaba)).visible(false)));
            markerslist.add(25, mMap.addMarker(new MarkerOptions().position(new LatLng(23.411887, 85.441085))
                    .title("HOT 'n' COLD").icon(BitmapDescriptorFactory.fromResource(R.drawable.cafe)).visible(false)));
            markerslist.add(26, mMap.addMarker(new MarkerOptions().position(new LatLng(23.412454, 85.441746))
                    .title("APNI RASOI").icon(BitmapDescriptorFactory.fromResource(R.drawable.dhaba2)).visible(false)));
            markerslist.add(27, mMap.addMarker(new MarkerOptions().position(new LatLng(23.410208, 85.440295))
                    .title("VEDA é CAFE").icon(BitmapDescriptorFactory.fromResource(R.drawable.cafe)).visible(false)));
            markerslist.add(28, mMap.addMarker(new MarkerOptions().position(new LatLng(23.423339, 85.438229))
                    .title("SHARMA DHABA").icon(BitmapDescriptorFactory.fromResource(R.drawable.dhaba)).visible(false)));
            markerslist.add(29, mMap.addMarker(new MarkerOptions().position(new LatLng(23.423726, 85.439581))
                    .title("OC").icon(BitmapDescriptorFactory.fromResource(R.drawable.dhaba2)).visible(false)));
            markerslist.add(30, mMap.addMarker(new MarkerOptions().position(new LatLng(23.417628, 85.434191))
                    .title("EAT and MEET").icon(BitmapDescriptorFactory.fromResource(R.drawable.dhaba)).visible(false)));
            markerslist.add(31, mMap.addMarker(new MarkerOptions().position(new LatLng(23.413034, 85.441943))
                   .title("UCO BANK").icon(BitmapDescriptorFactory.fromResource(R.drawable.bank)).visible(false)));
            markerslist.add(32, mMap.addMarker(new MarkerOptions().position(new LatLng(23.412534, 85.441914))
                    .title("SBI").icon(BitmapDescriptorFactory.fromResource(R.drawable.bank)).visible(false)));
            markerslist.add(33, mMap.addMarker(new MarkerOptions().position(new LatLng(23.411898, 85.441116))
                    .title("ATM").icon(BitmapDescriptorFactory.fromResource(R.drawable.atm)).visible(false)));
        }
        public void addAllMarkers(double zoom , double a , double b , double c , double d , double e) {
            ob=new mMarkers(mMap);
            ob.placeCustomMarkers(zoom,markerslist.get(0),13.4);
            for(int x=1;x<=7;x++) {    //imp. buildings 15.0366
                ob.placeCustomMarkers(zoom,markerslist.get(x),a);
            }
            for(int x=8;x<=21;x++){    //hostels at 16.412
                ob.placeCustomMarkers(zoom,markerslist.get(x),b);
            }
            for(int x=22;x<=30;x++){    //FOOD AND DRINK at 17.207451
                ob.placeCustomMarkers(zoom,markerslist.get(x),c);
            }
            for(int x=31;x<=32;x++) { //16.807451   20.16
                ob.placeCustomMarkers(zoom,markerslist.get(x),d);
            }
            ob.placeCustomMarkers(zoom,markerslist.get(33),e);
        }
}
