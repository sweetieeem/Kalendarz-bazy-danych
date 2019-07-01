package com.example.kalandarz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView textView1, textView2;
    EditText editText;
    Button button;
    String date;
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

        calendarView = findViewById(R.id.calendar);
        textView1 = findViewById(R.id.text);
        editText =findViewById(R.id.eventsname);
        button = findViewById(R.id.button);
        textView2 =findViewById(R.id.showevent);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                if (month < 9) {
                    date = dayOfMonth + "/" + "0" + (month + 1) + "/" + year;
                } else {
                    date = dayOfMonth + "/" + (month + 1) + "/" + year;
                }
                textView1.setText(date);
                textView2.setText(ListToString(db.getDayEvents(date)));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event event =new Event(editText.getText().toString(),textView1.getText().toString());
                db.addEvent(event);
                editText.setText("Name your event");
                Toast.makeText(v.getContext(), "Event saved!",Toast.LENGTH_LONG).show();
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
