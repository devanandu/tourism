package deva.tourism;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Spinner spin;
    ArrayAdapter<CharSequence> adapter;
    int count[];
    TextView t;
    String places[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        places = getResources().getStringArray(R.array.places);
        t=(TextView)findViewById(R.id.disp);
        count=new int[places.length];
        spin = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.places, android.R.layout.simple_spinner_item);
        spin.setAdapter(adapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("logged", 0);
                preferences.edit().remove("login").commit();
                Intent i = new Intent(MainActivity.this,login.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        //historydb db=new historydb(this);
        show();
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("asd",spin.getSelectedItem().toString());
                show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
void show()
{
    t.setText("");
    for (int i=0;i<places.length;i++)
        count[i]=0;
    places = getResources().getStringArray(R.array.places);
    historydb db=new historydb(this);
    Cursor c=db.get();
    while(c.moveToNext())
    {
        if(c.getString(1).contains(spin.getSelectedItem().toString()))
        {
            Log.e("check",c.getString(1));
            String[] parts = c.getString(1).split(",");
            for(int i=0;i<parts.length;i++){
            switch (parts[i]) {
                case "thrissur":
                    count[0] = count[0] + 1;
                    break;
                case "guruvayoor":
                    count[1] = count[1] + 1;
                    break;
                case "athirapilly":
                    count[2] = count[2] + 1;
                    break;
                case "snehatheeram":
                    count[3] = count[3] + 1;
                    break;
                case "angamaly":
                    count[4] = count[4] + 1;
                    break;
                case "irinjalakuda":
                    count[5] = count[5] + 1;
                    break;
            }
            }
        }
    }
    for(int i=0;i<count.length;i++){
        //Log.e("dsd",places[i]+":"+count[i]);

    }
    for (int i = 0; i <count.length; ++i)
    {
        for (int j = i + 1; j < count.length; ++j)
        {
            if (count[i] < count[j])
            {
                String s =  places[i];
                int p=count[i];
                count[i] = count[j];
                count[j] = p;
                places[i] = places[j];
                places[j] = s;
            }
        }
    }
    StringBuffer s=new StringBuffer();
    s.append("User have visited these places:\n");
    for(int i=0;i<4;i++){
    Log.e("dsd",places[i]);
       if(count[i]!=0 && !places[i].equals(spin.getSelectedItem().toString()))
           s.append(places[i]+"\n");
}
t.setText(s.toString());
}}
