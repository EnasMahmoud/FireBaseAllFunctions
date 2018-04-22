package pro.phoenix.firebasenew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText idText , nameText , emailText;
    Button saveBtn;

    private FirebaseDatabase database ;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idText = findViewById(R.id.IDEditText);
        nameText = findViewById(R.id.NameEditText);
        emailText = findViewById(R.id.EmailEditText);
        saveBtn = findViewById(R.id.saveBtn);

        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        myRef=database.getReference("Info");

        myRef.keepSynced(true);

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String idValue = dataSnapshot.child("ID").getValue(String.class);
                String nameValue = dataSnapshot.child("Name").getValue(String.class);
                String emailValue = dataSnapshot.child("Email").getValue(String.class);

                idText.setText(idValue);
                nameText.setText(nameValue);
                emailText.setText(emailValue);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText( MainActivity.this , "No Info to get " , Toast.LENGTH_LONG).show();
            }
        });



        myRef= database.getReference("Info");




    }

    public void saveData(View view) {
        myRef.child("ID").setValue(idText.getText().toString());
        myRef.child("Name").setValue(nameText.getText().toString());
        myRef.child("Email").setValue(emailText.getText().toString());
    }
}
