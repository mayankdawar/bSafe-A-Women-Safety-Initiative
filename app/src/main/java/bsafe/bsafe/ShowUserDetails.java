package bsafe.bsafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ShowUserDetails extends AppCompatActivity {
    ImageButton mydetails, guard1, guard2, guard3, guard4, guard5;
    TextView u1u,u1n,u1p,g1n,g1u,g1p,g2u,g2p,g2n,g3u,g3n,g3p,g4u,g4n,g4p,g5u,g5n,g5p;

    Intent intent;
    //SQLiteDatabase myDatabase = this.openOrCreateDatabase("bSafe",MODE_PRIVATE,null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_details);
        u1u = findViewById(R.id.u1u);
        u1n = findViewById(R.id.u1n);
        u1p = findViewById(R.id.u1p);
        g1u = findViewById(R.id.g1u);
        g1n = findViewById(R.id.g1n);
        g1p = findViewById(R.id.g1p);
        g2u = findViewById(R.id.g2u);
        g2n = findViewById(R.id.g2n);
        g2p = findViewById(R.id.g2p);
        g3u = findViewById(R.id.g3u);
        g3n = findViewById(R.id.g3n);
        g3p = findViewById(R.id.g3p);
        g1u = findViewById(R.id.g1u);
        g1n = findViewById(R.id.g1n);
        g1p = findViewById(R.id.g1p);
        g4u = findViewById(R.id.g4u);
        g4n = findViewById(R.id.g4n);
        g4p = findViewById(R.id.g4p);
        g5u = findViewById(R.id.g5u);
        g5n = findViewById(R.id.g5n);
        g5p = findViewById(R.id.g5p);
        userDetail();
        guardian1Detail();
        guardian2Detail();
        guardian3Detail();
        guardian4Detail();
        guardian5Detail();
        showDetailsG1();
        showDetailsu1();
        showDetailsG2();
        showDetailsG3();
        showDetailsG4();
        showDetailsG5();
    }
    public void showDetailsu1(){
        final SQLiteDatabase myDatabase = this.openOrCreateDatabase("bSafe",MODE_PRIVATE,null);
        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users(ID VARCHAR,username VARCHAR,Name VARCHAR,Phone VARCHAR)");
        try {
            Cursor c = myDatabase.rawQuery("SELECT * FROM users WHERE ID = '0'",null);
            int userIndex = c.getColumnIndex("username");
            int nameIndex = c.getColumnIndex("Name");
            int phoneIndex = c.getColumnIndex("Phone");
            c.moveToFirst();
            String name = c.getString(nameIndex);
            String UserName = c.getString(userIndex);
            String PhoneNum = c.getString(phoneIndex);
            u1n.setText(name);
            u1u.setText(UserName);
            u1p.setText(PhoneNum);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void showDetailsG1(){
        final SQLiteDatabase myDatabase = this.openOrCreateDatabase("bSafe",MODE_PRIVATE,null);
       // myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users(ID VARCHAR,username VARCHAR,Name VARCHAR,Phone VARCHAR)");
        try {
            Cursor c = myDatabase.rawQuery("SELECT * FROM users WHERE ID = '1'",null);
            int userIndex = c.getColumnIndex("username");
            int nameIndex = c.getColumnIndex("Name");
            int phoneIndex = c.getColumnIndex("Phone");
            c.moveToFirst();
            String name = c.getString(nameIndex);
            String UserName = c.getString(userIndex);
            String PhoneNum = c.getString(phoneIndex);
            g1n.setText(name);
            g1u.setText(UserName);
            g1p.setText(PhoneNum);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void showDetailsG2(){
        try{
            final SQLiteDatabase myDatabase = this.openOrCreateDatabase("bSafe",MODE_PRIVATE,null);
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users(ID VARCHAR,username VARCHAR,Name VARCHAR,Phone VARCHAR)");
            Cursor c = myDatabase.rawQuery("SELECT * FROM users WHERE ID = '2'",null);
            int userIndex = c.getColumnIndex("username");
            int nameIndex = c.getColumnIndex("Name");
            int phoneIndex = c.getColumnIndex("Phone");
            c.moveToFirst();
            String name = c.getString(nameIndex);
            String UserName = c.getString(userIndex);
            String PhoneNum = c.getString(phoneIndex);
            g2n.setText(name);
            g2u.setText(UserName);
            g2p.setText(PhoneNum);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void showDetailsG5(){
        try{
            final SQLiteDatabase myDatabase = this.openOrCreateDatabase("bSafe",MODE_PRIVATE,null);
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users(ID VARCHAR,username VARCHAR,Name VARCHAR,Phone VARCHAR)");
            Cursor c = myDatabase.rawQuery("SELECT * FROM users WHERE ID = '5'",null);
            int userIndex = c.getColumnIndex("username");
            int nameIndex = c.getColumnIndex("Name");
            int phoneIndex = c.getColumnIndex("Phone");
            c.moveToFirst();
            String name = c.getString(nameIndex);
            String UserName = c.getString(userIndex);
            String PhoneNum = c.getString(phoneIndex);
            g5n.setText(name);
            g5u.setText(UserName);
            g5p.setText(PhoneNum);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void showDetailsG4(){
        try{
            final SQLiteDatabase myDatabase = this.openOrCreateDatabase("bSafe",MODE_PRIVATE,null);
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users(ID VARCHAR,username VARCHAR,Name VARCHAR,Phone VARCHAR)");
            Cursor c = myDatabase.rawQuery("SELECT * FROM users WHERE ID = '4'",null);
            int userIndex = c.getColumnIndex("username");
            int nameIndex = c.getColumnIndex("Name");
            int phoneIndex = c.getColumnIndex("Phone");
            c.moveToFirst();
            String name = c.getString(nameIndex);
            String UserName = c.getString(userIndex);
            String PhoneNum = c.getString(phoneIndex);
            g4n.setText(name);
            g4u.setText(UserName);
            g4p.setText(PhoneNum);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void showDetailsG3(){
        try{
            final SQLiteDatabase myDatabase = this.openOrCreateDatabase("bSafe",MODE_PRIVATE,null);
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users(ID VARCHAR,username VARCHAR,Name VARCHAR,Phone VARCHAR)");
            Cursor c = myDatabase.rawQuery("SELECT * FROM users WHERE ID = '3'",null);
            int userIndex = c.getColumnIndex("username");
            int nameIndex = c.getColumnIndex("Name");
            int phoneIndex = c.getColumnIndex("Phone");
            c.moveToFirst();
            String name = c.getString(nameIndex);
            String UserName = c.getString(userIndex);
            String PhoneNum = c.getString(phoneIndex);
            g3n.setText(name);
            g3u.setText(UserName);
            g3p.setText(PhoneNum);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void userDetail() {
        mydetails = findViewById(R.id.md1);
        mydetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), EnterDetails.class);
                intent.putExtra("key", "0");
                startActivity(intent);
            }
        });
    }

    public void guardian1Detail() {
        //final SQLiteDatabase myDatabase = this.openOrCreateDatabase("bSafe",MODE_PRIVATE,null);
        guard1 = findViewById(R.id.g1);
        guard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), EnterDetails.class);
                intent.putExtra("key", "1");
                startActivity(intent);

            }
        });
    }

    public void guardian2Detail() {
        //final SQLiteDatabase myDatabase = this.openOrCreateDatabase("bSafe",MODE_PRIVATE,null);
        guard2 = findViewById(R.id.g2);
        guard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), EnterDetails.class);
                intent.putExtra("key", "2");
                startActivity(intent);

            }
        });
    }

    public void guardian3Detail() {
        //final SQLiteDatabase myDatabase = this.openOrCreateDatabase("bSafe",MODE_PRIVATE,null);
        guard3 = findViewById(R.id.g3);
        guard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), EnterDetails.class);
                intent.putExtra("key", "3");
                startActivity(intent);
            }
        });
    }

    public void guardian4Detail() {
        //final SQLiteDatabase myDatabase = this.openOrCreateDatabase("bSafe",MODE_PRIVATE,null);
        guard4 = findViewById(R.id.g4);
        guard4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), EnterDetails.class);
                intent.putExtra("key", "4");
                startActivity(intent);
            }
        });
    }

    public void guardian5Detail() {
        //final SQLiteDatabase myDatabase = this.openOrCreateDatabase("bSafe",MODE_PRIVATE,null);
        guard5 = findViewById(R.id.g5);
        guard5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), EnterDetails.class);
                intent.putExtra("key", "5");
                startActivity(intent);
            }
        });
    }
}