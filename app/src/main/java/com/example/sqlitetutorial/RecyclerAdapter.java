package com.example.sqlitetutorial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList team_id, team_name, team_year;

    RecyclerAdapter(Activity activity, Context context, ArrayList team_id, ArrayList team_name, ArrayList team_year){
        this.activity = activity;
        this.context = context;
        this.team_id = team_id;
        this.team_name = team_name;
        this.team_year = team_year;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.tv_team_id.setText(String.valueOf(team_id.get(position)));
        holder.tv_team_name.setText(String.valueOf(team_name.get(position)));
        holder.tv_team_year.setText(String.valueOf(team_year.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(team_id.get(position)));
                intent.putExtra("name", String.valueOf(team_name.get(position)));
                intent.putExtra("year", String.valueOf(team_year.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return team_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_team_id, tv_team_name, tv_team_year;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_team_id = itemView.findViewById(R.id.tv_team_id);
            tv_team_name = itemView.findViewById(R.id.tv_team_name);
            tv_team_year = itemView.findViewById(R.id.book_pages_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}


