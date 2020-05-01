package bsafe.bsafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EnterDetails extends AppCompatActivity {

    Intent intent,intent2;
    EditText na,use,pho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_details);
        intent = getIntent();
        String id = intent.getStringExtra("key");
        Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();
    }
    public void saveData(View view)
    {

        final SQLiteDatabase myDatabase = this.openOrCreateDatabase("bSafe",MODE_PRIVATE,null);
        intent = getIntent();
        try{
            na = findViewById(R.id.Name);
            use = findViewById(R.id.Username);
            pho = findViewById(R.id.Contact);
            String id = intent.getStringExtra("key");
            //Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();
            String nam = na.getText().toString();
            String User = use.getText().toString();
            String Phone = pho.getText().toString();
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users(ID VARCHAR,username VARCHAR,Name VARCHAR,Phone VARCHAR)");
            try{
                myDatabase.execSQL("DELETE FROM users WHERE ID ="+id);
            }catch (Exception e){
                e.printStackTrace();
            }
            String sql = "INSERT INTO users (ID,username,Name,Phone) VALUES(?,?,?,?)";
            SQLiteStatement statement = myDatabase.compileStatement(sql);
            statement.bindString(1,id);
            statement.bindString(2,User);
            statement.bindString(3,nam);
            statement.bindString(4,Phone);
            statement.execute();
            Cursor c = myDatabase.rawQuery("SELECT * FROM users",null);
            c.moveToFirst();
            int NameIndex = c.getColumnIndex("ID");
            while (c != null){
                String temp = c.getString(NameIndex);
                Toast.makeText(getApplicationContext(),temp,Toast.LENGTH_SHORT).show();
                Log.i("Username ",c.getString(NameIndex));
                c.moveToNext();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        intent2 =new Intent(getApplicationContext(),ShowUserDetails.class);
        startActivity(intent2);
    }
}
