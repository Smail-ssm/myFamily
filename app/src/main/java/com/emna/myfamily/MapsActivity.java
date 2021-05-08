package com.emna.myfamily;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private static GoogleMap googleMap;
    public LatLng destinationLatLng;
    Location mLastLocation;
    LocationRequest mLocationRequest;
    Marker marker = null;
    LatLng clientLocation;
    //    GeoQuery geoQuery;
    LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            for (Location location : locationResult.getLocations()) {
                if (getApplicationContext() != null) {
                    mLastLocation = location;

                    clientLocation = new LatLng(location.getLatitude(), location.getLongitude());

//                    //googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//                    //googleMap.animateCamera(CameraUpdateFactory.zoomTo(11));
//                    if (!getDriversAroundStarted)
//                        getDriversAround();
                }
            }
        }
    };
    Marker mCurrLocationMarker;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    TextView driverLogin;
    private FusedLocationProviderClient mFusedLocationClient;
    private Button mLogout, mRequest, mSettings, mHistory;
    private LatLng pickupLocation;
    private final Boolean requestBol = false;
    private Marker pickupMarker;
    private SupportMapFragment mapFragment;
    private String destination, requestService;
    private LinearLayout mDriverInfo;
    private ImageView mDriverProfileImage;
    private TextView mDriverName, mDriverPhone, mDriverCar;
    private RadioGroup mRadioGroup;
    private RatingBar mRatingBar;
    private final int radius = 1;
    private final Boolean driverFound = false;
    private String driverFoundID;
    private Marker mDriverMarker;
    //    private DatabaseReference driverLocationRef;
//    private ValueEventListener driverLocationRefListener;
//    private DatabaseReference driveHasEndedRef;
//    private ValueEventListener driveHasEndedRefListener;
    private Object AutocompleteSupportFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//        if (!Places.isInitialized()) {
//            Places.initialize(getApplicationContext(), getString(R.string.google_api_key), Locale.US);
//        }
//        //---------------
//        // Initialize the AutocompleteSupportFragment.
//        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
//                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
//
//        // Specify the types of place data to return.
//        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));
//
//        // Set up a PlaceSelectionListener to handle the response.
//        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(@NotNull Place place) {
//                // TODO: Get info about the selected place.
//               destination=place.getAddress();
//               destinationLatLng=place.getLatLng();}
//
//
//            @Override
//            public void onError(@NotNull Status status) {
//                // TODO: Handle the error.
//                Toast.makeText(MapsActivity.this, status.getStatus().toString()+ " "+status.getStatusMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//        // Set the fields to specify which types of place data to
//        // return after the user has made a selection.
//        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME);
//
//        // Start the autocomplete intent.
//        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
//                .build(this);
//        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
//        //---------------
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        destinationLatLng = new LatLng(0.0, 0.0);
//        driverLogin = (TextView) findViewById(R.id.driverlogin);
//        driverLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(MapsActivity.this, activity_login.class));
//                finish();
//            }
//        });
//        mDriverInfo = findViewById(R.id.driverInfo);
//
//        mDriverProfileImage = findViewById(R.id.driverProfileImage);
//
//        mDriverName = findViewById(R.id.driverName);
//        mDriverPhone = findViewById(R.id.driverPhone);
//        mDriverCar = findViewById(R.id.driverCar);
//
//        mRatingBar = findViewById(R.id.ratingBar);
//
//        mRadioGroup = findViewById(R.id.radioGroup);
//        mRadioGroup.check(R.id.normal);
//
//        mRequest = findViewById(R.id.request);
//        mSettings = findViewById(R.id.settings);
//        mHistory = findViewById(R.id.history);
//
//        mRequest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (requestBol) {
//                    endRide();
//                } else {
//                    int selectId = mRadioGroup.getCheckedRadioButtonId();
//                    final RadioButton radioButton = findViewById(selectId);
//                    if (radioButton.getText() == null) {
//                        return;
//                    }
//                    requestService = radioButton.getText().toString();
//                    requestBol = true;
//
//                    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
//
//                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("customerRequest");
//                    GeoFire geoFire = new GeoFire(ref);
//                    geoFire.setLocation(userId, new GeoLocation(destinationLatLng.latitude, destinationLatLng.longitude));
//
//                    pickupLocation = destinationLatLng;
//                    pickupMarker = googleMap.addMarker(new MarkerOptions().position(pickupLocation).title("Pickup Here").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_pickup)));
//
//                    mRequest.setText("Getting your Driver....");
//
//                    getClosestDriver();
//                }
//            }
//        });
//        mSettings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MapsActivity.this, CustomerSettingsActivity.class);
//                startActivity(intent);
//                return;
//            }
//        });
// Initialize the AutocompleteSupportFragment.

    }

//    private void getClosestDriver() {
//        DatabaseReference driverLocation = FirebaseDatabase.getInstance().getReference().child("driversAvailable");
//
//        GeoFire geoFire = new GeoFire(driverLocation);
//        geoQuery = geoFire.queryAtLocation(new GeoLocation(pickupLocation.latitude, pickupLocation.longitude), radius);
//        geoQuery.removeAllListeners();
//
//        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
//            @Override
//            public void onKeyEntered(String key, GeoLocation location) {
//                if (!driverFound && requestBol) {
//                    DatabaseReference mCustomerDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(key);
//                    mCustomerDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
//                                Map<String, Object> driverMap = (Map<String, Object>) dataSnapshot.getValue();
//                                if (driverFound) {
//                                    return;
//                                }
//
//                                if (driverMap.get("service").equals(requestService)) {
//                                    driverFound = true;
//                                    driverFoundID = dataSnapshot.getKey();
//
//                                    DatabaseReference driverRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(driverFoundID).child("customerRequest");
//                                    String customerId = FirebaseAuth.getInstance().getCurrentUser().getUid();
//                                    HashMap map = new HashMap();
//                                    map.put("customerRideId", customerId);
//                                    map.put("destination", destination);
//                                    map.put("destinationLat", destinationLatLng.latitude);
//                                    map.put("destinationLng", destinationLatLng.longitude);
//                                    driverRef.updateChildren(map);
//
//                                    getDriverLocation();
//                                    getDriverInfo();
//                                    getHasRideEnded();
//                                    mRequest.setText("Looking for Driver Location....");
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onKeyExited(String key) {
//
//            }
//
//            @Override
//            public void onKeyMoved(String key, GeoLocation location) {
//
//            }
//
//            @Override
//            public void onGeoQueryReady() {
//                if (!driverFound) {
//                    radius++;
//                    getClosestDriver();
//                }
//            }
//
//            @Override
//            public void onGeoQueryError(DatabaseError error) {
//
//            }
//        });
//    }

//    private void getDriverLocation() {
//        driverLocationRef = FirebaseDatabase.getInstance().getReference().child("driversWorking").child(driverFoundID).child("l");
//        driverLocationRefListener = driverLocationRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists() && requestBol) {
//                    List<Object> map = (List<Object>) dataSnapshot.getValue();
//                    double locationLat = 0;
//                    double locationLng = 0;
//                    if (map.get(0) != null) {
//                        locationLat = Double.parseDouble(map.get(0).toString());
//                    }
//                    if (map.get(1) != null) {
//                        locationLng = Double.parseDouble(map.get(1).toString());
//                    }
//                    LatLng driverLatLng = new LatLng(locationLat, locationLng);
//                    if (mDriverMarker != null) {
//                        mDriverMarker.remove();
//                    }
//
//                    Location loc1 = new Location("");
//                    loc1.setLatitude(pickupLocation.latitude);
//                    loc1.setLongitude(pickupLocation.longitude);
//                    Location loc2 = new Location("");
//                    loc2.setLatitude(driverLatLng.latitude);
//                    loc2.setLongitude(driverLatLng.longitude);
//
//                    float distance = loc1.distanceTo(loc2);
//
//                    if (distance < 100) {
//                        mRequest.setText("Driver's Here");
//                    } else {
//                        mRequest.setText("Driver Found: " + distance);
//                    }
//
//
//                    mDriverMarker = googleMap.addMarker(new MarkerOptions().position(driverLatLng).title("your driver").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_car)));
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//
//    }

    //    private void getDriverInfo() {
//        mDriverInfo.setVisibility(View.VISIBLE);
//        DatabaseReference mCustomerDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(driverFoundID);
//        mCustomerDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
//                    if (dataSnapshot.child("name") != null) {
//                        mDriverName.setText(dataSnapshot.child("name").getValue().toString());
//                    }
//                    if (dataSnapshot.child("phone") != null) {
//                        mDriverPhone.setText(dataSnapshot.child("phone").getValue().toString());
//                    }
//                    if (dataSnapshot.child("car") != null) {
//                        mDriverCar.setText(dataSnapshot.child("car").getValue().toString());
//                    }
//                    if (dataSnapshot.child("profileImageUrl").getValue() != null) {
//                        Glide.with(getApplication()).load(dataSnapshot.child("profileImageUrl").getValue().toString()).into(mDriverProfileImage);
//                    }
//
//                    int ratingSum = 0;
//                    float ratingsTotal = 0;
//                    float ratingsAvg = 0;
//                    for (DataSnapshot child : dataSnapshot.child("rating").getChildren()) {
//                        ratingSum = ratingSum + Integer.valueOf(child.getValue().toString());
//                        ratingsTotal++;
//                    }
//                    if (ratingsTotal != 0) {
//                        ratingsAvg = ratingSum / ratingsTotal;
//                        mRatingBar.setRating(ratingsAvg);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//    }
    @SuppressLint("RestrictedApi")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsActivity.googleMap = googleMap;

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            } else {
                checkLocationPermission();
            }
        }

        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Geocoder geocoder;
                List<Address> addresses = null;
                geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());

                try {
                    addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();
                // First check if myMarker is null
                if (marker == null) {

                    // Marker was not set yet. Add marker:
                    marker = googleMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Destination")
                            .snippet(addresses.get(0).getAddressLine(0)));

                } else {

                    // Marker already exists, just update it's position
                    marker.remove();
                    marker = googleMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Destination")
                            .snippet(addresses.get(0).getAddressLine(0)));
                    destination = addresses.get(0).getAddressLine(0);
                    destinationLatLng = latLng;

                }
            }
        });


    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = googleMap.addMarker(markerOptions);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MapsActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }


}
