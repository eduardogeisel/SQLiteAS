package com.example.sqlitetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etTeamName, etTeamYear;
    Button btnAdd, btnEdit;
    ListView lvTeams;
    ArrayAdapter teamArrayAdapter;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
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

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, UpdateActivity.class);
                startActivity(i);
            }
        });

        //Calling these inside onCreate to show list as soon as app is opened
        ShowTeamOnListView(databaseHelper);

        lvTeams.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TeamModel clickedTeam = (TeamModel) parent.getItemAtPosition(position);
                databaseHelper.deleteTeam(clickedTeam);
                ShowTeamOnListView(databaseHelper);
                Toast.makeText(MainActivity.this, "Deleted " + clickedTeam.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ShowTeamOnListView(DatabaseHelper databaseHelper) {
        teamArrayAdapter = new ArrayAdapter<TeamModel>(MainActivity.this, android.R.layout.simple_list_item_1, databaseHelper.getAllTeams());
        lvTeams.setAdapter(teamArrayAdapter);
    }
}