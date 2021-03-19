package com.example.sqlitetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText etEditId, etEditName, etEditYear;
    Button btnEditConfirm;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        etEditId = findViewById(R.id.etEditId);
        etEditName = findViewById(R.id.etEditName);
        etEditYear = findViewById(R.id.etEditYear);
        btnEditConfirm = findViewById(R.id.btnEditConfirm);

        databaseHelper = new DatabaseHelper(UpdateActivity.this);

        btnEditConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etEditId.getText().toString();
                String editName = etEditName.getText().toString();
                String editYear = etEditYear.getText().toString();
                int idInt = Integer.parseInt(id);
                int editYearInt = Integer.parseInt(editYear);
                if(id.equals("") || editName.equals("") || editYear.equals("")){
                    Toast.makeText(UpdateActivity.this, "Add values to all text boxes", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseHelper.update(idInt, editName, editYearInt);
                }

            }
        });

    }
}