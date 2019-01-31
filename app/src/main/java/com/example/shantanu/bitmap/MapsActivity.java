package com.example.shantanu.bitmap;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.Manifest;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.GeoApiContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.abs;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleApiClient.OnConnectionFailedListener
        ,GoogleApiClient.ConnectionCallbacks, LocationListener,View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    LocationManager locationManager;
    protected GoogleMap mMap;
    protected static double lat, lng;
    protected GoogleApiClient googleApiClient1,googleApiClient2;
    protected LocationRequest l;
    protected Marker marker2;
    protected int c=1,lcro=0,PLACE_PICKER_REQUEST=1;;
    protected ToggleButton toggleButton;
    protected float zoom,tzoom;
    protected ImageView imageView,placePicker;
    AutoCompleteTextView editText;
    protected LatLngBounds llb;
    protected LatLng ll2,ll3;
    mMarkers ob;
    MarkersList mobj;
    Address address;
    PlaceInfo placeInfo;
    PlaceAutocompleteAdapter placeAutocompleteAdapter;
    Place place;
    GeoApiContext geoApiContext=null;
    public int hostel=0,academic=0,money=0,food=0,misc=0,ground=0;
    public double a=15.0366,b=16.412,ce=17.207451,d=16.807451,e=20.16;
    public double a1=15.0366,b1=16.412,ce1=17.207451,d1=16.807451,e1=20.16,max=13.2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        NavigationView navigationView =findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        buildGoogleApiClient();
        imageView = findViewById(R.id.reset);
        toggleButton = findViewById(R.id.toggle);
        editText = findViewById(R.id.search);
        placePicker=findViewById(R.id.placepicker);
        if(geoApiContext==null) geoApiContext=new GeoApiContext.Builder().apiKey(getString(R.string.google_maps_key)).build();
    }

    @Override
    public void onConnectionSuspended(int i) {
        googleApiClient1.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) { googleApiClient1.connect(); }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient1.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (googleApiClient1.isConnected()) googleApiClient1.disconnect();
    }

    protected synchronized void buildGoogleApiClient() {
        googleApiClient1 = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).
                addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        l = LocationRequest.create();
        l.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        l.setInterval(5000);
        while (true) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}, 10);
            }
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient1, l, this);
                break;
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.mapstyle));
        ll2 = new LatLng(23.429169, 85.448664);
        ll3 = new LatLng(23.408374, 85.430956);
        llb = new LatLngBounds(ll3, ll2);
        mMap.setLatLngBoundsForCameraTarget(llb);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.setPadding(14,400,0,0);
        mMap.setMinZoomPreference(13.5f);
        mobj = new MarkersList(mMap);
        mobj.createMarkerList();
        ob = new mMarkers(mMap);
        ob.addCustomMarkers(geoApiContext,mobj.markerslist);
        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                zoom = mMap.getCameraPosition().zoom;
                if(zoom!=tzoom){
                    Log.v("zoom",tzoom+"");
                    tzoom = zoom;
                }
                if (lcro == 1 && marker2 != null) {
                    if (isLocationEnabled()) marker2.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.locin));
                    else marker2.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.locdis));
                }
                ob.showAllInfo(place);
                place=null;
            }
        });
        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                zoom = mMap.getCameraPosition().zoom;
                mobj.addAllMarkers(zoom,a,b,ce,d,e);
                if (lcro == 1) {
                    if (isLocationEnabled() && marker2 != null)
                        marker2.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.locin));
                    else marker2.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.locdis));
                }
                if(ob.ckl==1)
                {
                    a=a1; b=b1; ce=ce1; d=d1; e=e1;
                    hostel=0;academic=0;money=0;food=0;misc=0;ground=0;
                    ob.ckl=0;
                }
            }
        });
        init();
    }
    @Override
    public void onLocationChanged(Location location) {
        if (c != 1) marker2.remove();
        lat = location.getLatitude();
        lng = location.getLongitude();
        if (isLocationEnabled()) marker2 = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng))
                    .title("Location Confirmed").icon(BitmapDescriptorFactory.fromResource(R.drawable.locin)));
        else marker2 = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng))
                .title("TURN ON YOUR GPS!").icon(BitmapDescriptorFactory.fromResource(R.drawable.locdis)));
        marker2.setVisible(true);
        if (c == 1) mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), (float) 14.225365));
        c++;
        lcro = 1;
    }

    public boolean isLocationEnabled() {
        String le = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) getSystemService(le);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) return true;

        else return false;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.reset) {
            zoom = mMap.getCameraPosition().zoom;
            if (zoom >= 16) mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), zoom));
            else mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 16), 5000,
                        null);
        }
        if (isLocationEnabled() && marker2 != null) marker2.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.locin));
        else marker2.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.locdis));
    }

    public void changeMapState(View view) {
        boolean checked = ((ToggleButton) view).isChecked();
        if (checked) mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.retro_mapstyle));
        else mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.mapstyle));
    }

    public void changeMapView(View view) {
        boolean checked=((ToggleButton)view).isChecked();
        LatLng curr=mMap.getCameraPosition().target;
        if(checked) mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(curr,18.5f,70,0)));
        else mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(curr,16.5f,0,0)));
    }

    private void init() {
        googleApiClient2=new GoogleApiClient.Builder(this).addApi(Places.GEO_DATA_API).addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this,this).build();
        editText.setOnItemClickListener(onItemClickListener);
        placeAutocompleteAdapter=new PlaceAutocompleteAdapter(this,googleApiClient2,llb,null);
        editText.setAdapter(placeAutocompleteAdapter);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionID, KeyEvent keyEvent) {
                if (actionID == EditorInfo.IME_ACTION_SEARCH || actionID == EditorInfo.IME_ACTION_DONE ||
                        keyEvent.getAction() == KeyEvent.ACTION_DOWN || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER) {
                    geolocate();
                }
                return true;
            }
        });
        placePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder=new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(MapsActivity.this),PLACE_PICKER_REQUEST);
                    builder.setLatLngBounds(llb);
                }
                catch (GooglePlayServicesRepairableException e) { e.printStackTrace(); }
                catch (GooglePlayServicesNotAvailableException e) { e.printStackTrace(); }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==PLACE_PICKER_REQUEST) {
            if(resultCode==RESULT_OK) {
                Place place=PlacePicker.getPlace(this,data);
                com.google.android.gms.common.api.PendingResult<PlaceBuffer> pendingResult=
                        Places.GeoDataApi.getPlaceById(googleApiClient2,place.getId());
                pendingResult.setResultCallback(updatePlaceDetailsCallback);
            }
        }
    }

    private void geolocate() {
        String search = editText.getText().toString();
        Geocoder geocoder = new Geocoder(this);
        List<Address> list = new ArrayList<>();
        try { list = geocoder.getFromLocationName(search,1); }
        catch (IOException e) { e.printStackTrace(); }
        if (list.size() > 0) address = list.get(0);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(address.getLatitude(), address.getLongitude()),
                (float) 17.3), 3500, null);
    }

    private AdapterView.OnItemClickListener onItemClickListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            final AutocompletePrediction autocompletePrediction= placeAutocompleteAdapter.getItem(i);
            final String placeId=(autocompletePrediction).getPlaceId();
            com.google.android.gms.common.api.PendingResult<PlaceBuffer> pendingResult=
                    Places.GeoDataApi.getPlaceById(googleApiClient2,placeId);
            pendingResult.setResultCallback(updatePlaceDetailsCallback);
        }
    };

    private ResultCallback<PlaceBuffer> updatePlaceDetailsCallback=new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(@NonNull PlaceBuffer places) {
            if(!places.getStatus().isSuccess()) places.release();
            place=places.get(0);
            try {
                placeInfo=new PlaceInfo();
                placeInfo.setName(place.getName().toString());
                placeInfo.setAddress(place.getAddress().toString());
                placeInfo.setAttributions(place.getAttributions().toString());
                placeInfo.setID(place.getId());
                placeInfo.setLatLng(place.getLatLng());
                placeInfo.setRating(place.getRating());
                placeInfo.setPhoneNumber(place.getPhoneNumber().toString());
                placeInfo.setWebsiteUri(place.getWebsiteUri());
            }
            catch (Exception e) { e.printStackTrace(); }
            if(place.getViewport().getCenter().latitude>=23.427929|| place.getViewport().getCenter().latitude<=23.406237
                ||place.getViewport().getCenter().longitude>=85.451729|| place.getViewport().getCenter().longitude<=
                85.431798) {
                Toast.makeText(MapsActivity.this,"!!DO NOT ATTEMPT TO ESCAPE BIT!!",Toast.LENGTH_LONG).show();
            }
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(place.getViewport().getCenter().latitude,
                            place.getViewport().getCenter().longitude),(float)18), 4000, null);
        }
    };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Log.v("item", "item selected");
        int id = menuItem.getItemId();
        if (id == R.id.nav_hostel) {
            if (hostel % 2 == 0) {
                for (int x = 8; x <= 21; x++)
                    mobj.markerslist.get(x).setVisible(true);
                b = max;
            }
            else {
                for (int x = 8; x <= 21; x++)
                    mobj.markerslist.get(x).setVisible(false);
                b = b1;
            }
            hostel++;
            Log.v("hostel",hostel+"");
        }
        else if (id == R.id.nav_ground) {
            if (ground % 2 == 0) {
                for (int x = 1; x <= 1; x++)
                    mobj.markerslist.get(x).setVisible(true);
                a = max;
            }
            else {
                for (int x = 1; x <= 1; x++)
                    mobj.markerslist.get(x).setVisible(false);
                a = a1;
            }
            ground++;
        }
        else if (id == R.id.nav_academic) {
            if (academic % 2 == 0) {
                for (int x = 4; x <= 7; x++) mobj.markerslist.get(x).setVisible(true);
                mobj.markerslist.get(2).setVisible(true);
                a = max;
            } else {
                for (int x = 4; x <= 7; x++) mobj.markerslist.get(x).setVisible(false);
                mobj.markerslist.get(2).setVisible(false);
                a = a1;
            }
            academic++;
        }
        else if(id==R.id.nav_money) {
            if (money % 2 == 0) {
                for (int x = 31; x <= 33; x++) mobj.markerslist.get(x).setVisible(true);
                d = max;
            } else {
                for (int x = 31; x <= 33; x++) mobj.markerslist.get(x).setVisible(false);
                d = d1;
            }
            money++;
        }
        else if(id==R.id.nav_food)
        {
            if (food % 2 == 0) {
                for (int x = 22; x <= 30; x++) mobj.markerslist.get(x).setVisible(true);
                ce = max;
            } else {
                for (int x = 22; x <= 30; x++) mobj.markerslist.get(x).setVisible(false);
                mobj.markerslist.get(2).setVisible(false);
                ce = ce1;
            }
            food++;
        }
        else if(id==R.id.nav_misc)
        {
            if (misc % 2 == 0) {
                for (int x = 3; x <= 3; x++) mobj.markerslist.get(x).setVisible(true);
                a = max;
            } else {
                for (int x = 3; x <= 3; x++) mobj.markerslist.get(x).setVisible(false);
                a = a1;
            }
            misc++;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}