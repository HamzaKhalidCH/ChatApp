package com.example.conversationsapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
public class Messages_exchage extends AppCompatActivity {


    final ArrayList<String> samples = new ArrayList<String>();


    public String random_string()
    {
        Date date = new Date();
        Random rand = new Random();

        SimpleDateFormat sdf = new SimpleDateFormat("H:mm a");
        String curr_date = sdf.format(date);
        return samples.get(rand.nextInt(11));
    }

    public String current_time()
    {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
        String dateString = dateFormat.format(new Date()).toString();
        return dateString;
    }

    private RecyclerView chat_messages; //Recycler view for messages

    ArrayList<Chat_message> messages_list = new ArrayList<>(); //array which will store msgs info

    //MessageAdapter obj;
    SQLiteDatabase db;
    Tables_database db_tables;
    EditText DraftM;
    int DraftID;
    String DraftTime;
    String person_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        samples.add("Hi!!");
        samples.add("Hi, How're you doing today?");
        samples.add("JUst finished my work so going to take a nap now......!!");
        samples.add("Wow what a match it was last night, Hala Madrid");
        samples.add("So whats up with you lately, havent seen you around");
        samples.add("Anyways hows your semester going, good ? cant say the same for me, yeah has been really tough !!");
        samples.add("Okay, really nice chating with you man");
        samples.add("Lets talk some other time, some unexpected work just came up");
        samples.add("Good bye man, see you tomorrow");
        samples.add("I hope sir grades our assignment well, could really use the boost");
        samples.add("Nani !!!!");

        db_tables = new Tables_database(this);
        db = db_tables.getWritableDatabase();

        //RecyclerView for messages_exchange b/w users
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages_list);

        //getting results from previous activity using Intent
        String user_id = getIntent().getStringExtra("Id");
        person_name = getIntent().getStringExtra("Name");


        ActionBar br = getSupportActionBar();

        br.setHomeButtonEnabled(true);
        br.setTitle(person_name);
        br.setSubtitle("online");

        //converting userID into integer
        final int USERID = Integer.valueOf(user_id);
        db_tables.insertConversation(USERID, person_name);

        //Get id of the text field and send button of the layout
        Button button= (Button) findViewById(R.id.sendBtn);
        final EditText editSendMsg= (EditText) findViewById(R.id.sendMsgText);



        // check if any draft in database remove from there and show in text field
        String dft = "Draft";
        Cursor c = db_tables.getDraft(USERID, dft);
        if (c.getCount() == 0) {
            // System.out.println("if condition chale hai");
        } else {
            while (c.moveToNext()) {
                editSendMsg.setText(c.getString(3));
                break;
            }
        }

        //Remove Draft message from the Database
        db.delete("Message", "Msg_Id = " + USERID + " AND Status = ?", new String[]{dft});

        chat_messages=findViewById(R.id.ListMessage);
        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(messages_list);

        //load data from database of conversation
        Cursor cursor = db_tables.fetchData(USERID);
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No Messages", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                if (!cursor.getString(5).equals("Draft")) {
                    char cs=cursor.getString(1).charAt(0);   //getting initials
                    String s=Character.toString(cs); //convert char to string

                    messages_list.add(new Chat_message(cursor.getString(3),cursor.getString(1),s,cursor.getString(4)));

                }
            }
        }




        adapter.setMessages(messages_list);



        //Get Necessary information of the draft message
        DraftM = editSendMsg;
        DraftID = USERID;
        //DraftTime = current_time();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String st= (String) editSendMsg.getText().toString();
                String st2=random_string();
                String st3=current_time();

                messages_list.add(new Chat_message(st,"Muaviya Ijaz","M",st3));
                db_tables.insertMessage(USERID,"Muaviya Ijaz", "Sender", st, st3, "send");
                char ch=person_name.charAt(0);
                messages_list.add(new Chat_message(st2,person_name,(String)Character.toString(ch),st3));
                db_tables.insertMessage(USERID,person_name, "Receiver", st2, st3, "received");
                adapter.setMessages(messages_list);
                editSendMsg.setText("");

                chat_messages.scrollToPosition(messages_list.size() - 1); //seems useless
                editSendMsg.onEditorAction(EditorInfo.IME_ACTION_DONE); //seems useless
            }
        });

        chat_messages.setAdapter(adapter);
        chat_messages.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);

        String d = DraftM.getText().toString();
        DraftTime = current_time();  //time of draft

        if (!d.isEmpty()) {
            db_tables.insertMessage(DraftID,"Muaviya Ijaz", "Sender", d, DraftTime, "Draft");
            //Toast.makeText(getApplicationContext(), "Draft", Toast.LENGTH_SHORT).show();
        }
        super.onBackPressed();
    }


}
