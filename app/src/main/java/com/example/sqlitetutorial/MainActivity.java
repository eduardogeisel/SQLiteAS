package com.example.sqlitetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    EditText etTeamName, etTeamYear;
    Button btnAdd, btnView;
    ListView lvTeams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        lvTeams = findViewById(R.id.lvTeams);
        etTeamName = findViewById(R.id.etTeamName);
        etTeamYear = findViewById(R.id.etTeamYear);

        //click listeners
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TeamModel teamModel = new TeamModel(-1, etTeamName.getText().toString(), Integer.parseInt(etTeamYear.getText().toString()));
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}