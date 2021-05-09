package com.emna.myfamily;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Membersactivity extends AppCompatActivity {
    ListView listView;
    TextView nomembers;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    FloatingActionButton fab;
    DatabaseReference root;
    private TextInputEditText name;
    private TextInputEditText lastname;
    private TextInputEditText age;
    private TextInputEditText relation;
    private TextInputEditText email;
    private TextInputEditText phone;
    private Button addmemeber;
    private DatabaseReference uFamily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membersactivity);
        nomembers = findViewById(R.id.nommbers);
        listView = findViewById(R.id.lists);
        fab = findViewById(R.id.addm);
        root = FirebaseDatabase.getInstance().getReference();
        root = FirebaseDatabase.getInstance().getReference();
        uFamily = root.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("memebers");
        uFamily.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    for (DataSnapshot member : snapshot.getChildren()) {

                        member m = member.getValue(member.class);
                        arrayList.add(m.getName());
                        arrayAdapter = new ArrayAdapter<String>(Membersactivity.this, android.R.layout.simple_list_item_1, arrayList);
                        listView.setAdapter(arrayAdapter);

                }
            }}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            private member member;

            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Membersactivity.this);
                builder.setTitle("Add member");

                // set the custom layout
                final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
                builder.setView(customLayout);
                // send data from the AlertDialog to the Activity
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
                        if (name.getEditableText().toString().isEmpty()) {

                            name.setError("Enter name...");
                            name.requestFocus();
                            return;
                        }  if (lastname.getEditableText().toString().isEmpty()) {

                            lastname.setError("Enter lastname...");
                            lastname.requestFocus();
                            return;
                        }  if (age.getEditableText().toString().isEmpty()) {

                            age.setError("Enter age...");
                            age.requestFocus();
                            return;
                        }  if (relation.getEditableText().toString().isEmpty()) {

                            relation.setError("Enter relation...");
                            relation.requestFocus();
                            return;
                        }  if (email.getEditableText().toString().isEmpty()) {

                            email.setError("Enter email...");
                            email.requestFocus();
                            return;
                        }  if (phone.getEditableText().toString().isEmpty()) {

                            phone.setError("Enter phone...");
                            phone.requestFocus();
                            return;
                        }
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
                        uFamily.child(member.getRelation()).setValue(member);
                    }
                });
                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }


        });

        if (arrayList.isEmpty()) {
            nomembers.setVisibility(View.VISIBLE);
        }
    }
}