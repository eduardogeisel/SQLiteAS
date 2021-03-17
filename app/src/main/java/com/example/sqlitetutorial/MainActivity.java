package com.example.sqlitetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etTeamName, etTeamYear;
    Button btnAdd, btnView;
    ListView lvTeams;
    ArrayAdapter teamArrayAdapter;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        lvTeams = findViewById(R.id.lvTeams);
        etTeamName = findViewById(R.id.etTeamName);
        etTeamYear = findViewById(R.id.etTeamYear);

        databaseHelper = new DatabaseHelper(MainActivity.this);

        //click listeners
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TeamModel teamModel;

                try{
                   teamModel = new TeamModel(-1, etTeamName.getText().toString(), Integer.parseInt(etTeamYear.getText().toString()));
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error adding team", Toast.LENGTH_SHORT).show();
                    teamModel = new TeamModel(-1, "error", 0);
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);

                databaseHelper.addOne(teamModel);

                ShowTeamOnListView(databaseHelper);

            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseHelper = new DatabaseHelper(MainActivity.this);
                List<TeamModel> allTeams = databaseHelper.getAllTeams();

                ShowTeamOnListView(databaseHelper);
            }
        });

        //Calling these inside onCreate to show list as soon as app is opened
        ShowTeamOnListView(databaseHelper);
    }

    private void ShowTeamOnListView(DatabaseHelper databaseHelper) {
        teamArrayAdapter = new ArrayAdapter<TeamModel>(MainActivity.this, android.R.layout.simple_list_item_1, databaseHelper.getAllTeams());
        lvTeams.setAdapter(teamArrayAdapter);
    }
}