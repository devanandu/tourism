package deva.tourism;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText n,p;
    SharedPreferences mPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPreferences= getApplicationContext().getSharedPreferences("logged", 0);

        String first = mPreferences.getString("login", null);
        if((first != null)){
            Intent i = new Intent(login.this,MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        n=(EditText)findViewById(R.id.name);
        p=(EditText)findViewById(R.id.pass);

    }
    public void signin(View v)
    {
        DB db=new DB(this);
        db.all();
        String c=db.view(n.getText().toString(),p.getText().toString());
        Toast.makeText(this,c,Toast.LENGTH_LONG).show();
        if(c.equals("success")){
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString("login",n.getText().toString());
        editor.commit();
            Intent i = new Intent(login.this,MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
    }
}
