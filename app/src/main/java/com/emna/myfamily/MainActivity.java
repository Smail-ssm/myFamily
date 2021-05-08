package com.emna.myfamily;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.emna.myfamily.login.login;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class MainActivity extends AppCompatActivity {
    DatabaseReference root;
    private TextInputEditText name;
    private TextInputEditText lastname;
    private TextInputEditText age;
    private TextInputEditText relation;
    private TextInputEditText email;
    private TextInputEditText phone;
    private Button addmemeber;
    private member member;
    private DatabaseReference cUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root = FirebaseDatabase.getInstance().getReference();

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        // permission is granted
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        if (response.isPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
        findViewById(R.id.mylocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                i.putExtra("type", "mylocation");
                startActivity(i);
            }
        });
        findViewById(R.id.familylocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Myfamily.class);
                i.putExtra("type", "familylocation");
                startActivity(i);
            }
        });
        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Membersactivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                SharedPreferences prefs = getApplicationContext().getSharedPreferences("USER_PREF", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                editor.apply();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, login.class));

                finish();
                return true;
            case R.id.profile:
                cUser = root.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("profile");
                cUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()) {
                            member memb = snapshot.getValue(member.getClass());
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Profile");
                            final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
                            builder.setView(customLayout);
                            name = customLayout.findViewById(R.id.name);
                            lastname = customLayout.findViewById(R.id.lastname);
                            age = customLayout.findViewById(R.id.age);
                            relation = customLayout.findViewById(R.id.relation);
                            email = customLayout.findViewById(R.id.email);
                            phone = customLayout.findViewById(R.id.phone);
                            addmemeber = customLayout.findViewById(R.id.addmemeber);
                            name.setText(memb.getName());
                            lastname.setText(memb.getLastname());
                            age.setText(memb.getName());
                            relation.setText(memb.getName());
                            email.setText(memb.getName());
                            phone.setText(memb.getName());
//                            .setText(memb.getName());
//                            addmemeber.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    member = new member();
//                                    member.setName(name.getEditableText().toString());
//                                    member.setLastname(lastname.getEditableText().toString());
//                                    member.setAge(age.getEditableText().toString());
//                                    member.setRelation(relation.getEditableText().toString());
//                                    member.setEmail(email.getEditableText().toString());
//                                    member.setPhone(phone.getEditableText().toString());
//                                    member.setLatit("-");
//                                    member.setLongtit("-");
//                                    member.setId(email.getEditableText().toString() + " " + phone.getEditableText().toString());
//
//                                }
//                            });

                            // create and show the alert dialog
                            AlertDialog dialog = builder.create();
                            dialog.show();

                        } else {
                            Toast.makeText(MainActivity.this, "pleas create your profile ", Toast.LENGTH_SHORT).show();
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Profile");

                            // set the custom layout
                            final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
                            builder.setView(customLayout);
                            name = customLayout.findViewById(R.id.name);
                            lastname = customLayout.findViewById(R.id.lastname);
                            age = customLayout.findViewById(R.id.age);
                            relation = customLayout.findViewById(R.id.relation);
                            email = customLayout.findViewById(R.id.email);
                            phone = customLayout.findViewById(R.id.phone);
                            addmemeber = customLayout.findViewById(R.id.addmemeber);
                            addmemeber.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    member = new member();
                                    member.setName(name.getEditableText().toString());
                                    member.setLastname(lastname.getEditableText().toString());
                                    member.setAge(age.getEditableText().toString());
                                    member.setRelation(relation.getEditableText().toString());
                                    member.setEmail(email.getEditableText().toString());
                                    member.setPhone(phone.getEditableText().toString());
                                    member.setLatit("-");
                                    member.setLongtit("-");
                                    member.setId(email.getEditableText().toString() + " " + phone.getEditableText().toString());
                                    cUser.setValue(member);
                                }
                            });

                            // add a button


                            // create and show the alert dialog
                            AlertDialog dialog = builder.create();
                            dialog.show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }
}