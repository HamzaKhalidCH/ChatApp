package com.example.conversationsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> convo_names = new ArrayList<>();
    ListView convo_list_view;


    final int REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        convo_names.add("Muaviya Ijaz");
        convo_names.add("Daud Samim");
        convo_names.add("Usama Riaz");
        convo_names.add("Fahad Arshad");
        convo_names.add("Kamran Arshad");


        convo_list_view = (ListView) findViewById(R.id.ConvoList);
        Convo_adapter adapter = new Convo_adapter(this,convo_names);
        convo_list_view.setAdapter(adapter);

        convo_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String itemValue = (String) convo_list_view.getItemAtPosition(position);  //seems to be useless for now
                String id_position = String.valueOf(position + 1);  //getting id + adding 1 in it to start id from 1 index
                String person_name = convo_names.get(position);   //getting the name of the person whom i want to chat with
                //passing data to another Activity
                Intent intent = new Intent(getApplicationContext(), Messages_exchage.class);

                intent.putExtra("Id", id_position);
                intent.putExtra("Name", person_name);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
              //  Toast.makeText(getApplicationContext(), "Draft saved", Toast.LENGTH_SHORT).show();  //seems useless
            }
        }
    }


}