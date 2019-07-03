package com.example.kalandarz;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView textView1;
    ListView listView;
    EditText editText;
    Button button;
    String date;
    EventAdapter adapter;
    DatabaseHandler db = new DatabaseHandler(this);

    String ListToString(List<Event> events){
        String string=new String();

        for(int i=0; i<events.size();i++){
            string += events.get(i)._name+"\n";
        }

        return string;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RecyclerView recyclerView=findViewById(R.id.showevent);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        calendarView = findViewById(R.id.calendar);
        textView1 = findViewById(R.id.text);
        editText =findViewById(R.id.eventsname);
        button = findViewById(R.id.button);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                if (month < 9) {
                    date = dayOfMonth + "/" + "0" + (month + 1) + "/" + year;
                } else {
                    date = dayOfMonth + "/" + (month + 1) + "/" + year;
                }
                textView1.setText(date);

                ArrayList<Event> events= new ArrayList<>();
                events.addAll(db.getDayEvents(date));

                adapter=new EventAdapter(events);
                recyclerView.setAdapter(adapter);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event event =new Event(editText.getText().toString(),textView1.getText().toString());
                db.addEvent(event);
                editText.setText("");
                Toast.makeText(v.getContext(), "Event saved!",Toast.LENGTH_LONG).show();
                ArrayList<Event> events= new ArrayList<>();
                adapter= new EventAdapter(events);
                recyclerView.setAdapter(adapter);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
