package bsafe.bsafe;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageButton amb,pol,fam,hosp,nearPol,emergency,pro;
    Intent intent;
    LocationManager locationManager;
    LocationListener locationListener;
    double currentLat , currentLong;
    int nearCallId;
    MediaPlayer music;
    private static int Video_req = 101;
    private Uri videoUri = null;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dial();
        nearHosp();
        nearPol();
        siren();
        editDetails();
    }


    public String[] fetch(){
        final SQLiteDatabase myDatabase = this.openOrCreateDatabase("bSafe",MODE_PRIVATE,null);
        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users(ID VARCHAR,username VARCHAR,Name VARCHAR,Phone VARCHAR)");
        Cursor c = myDatabase.rawQuery("SELECT * FROM users",null);
        int PhoneIndex = c.getColumnIndex("Phone");
        c.moveToFirst();
        String temp;
        String[] arr = new String[10];
        int i = 0;
        try {
            while ((c != null) && (i != 4)){
                Log.i("Username ",c.getString(PhoneIndex));
                arr[i] =  c.getString(PhoneIndex);
                i++;
                c.moveToNext();
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Enter details",Toast.LENGTH_SHORT).show();
        }

        //String[] arr = {"90414","54826","798945","48956","78987645"};
        return arr;
    }


    public void Send(){
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            // ask for permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
        } else {
            // we have permission!
        }
            locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged (Location location){
                        currentLat = location.getLatitude();
                        currentLong = location.getLongitude();
                        Log.i("Location", location.toString());
                        String message = "https://www.google.com/maps/@"+currentLat+","+currentLong+","+"15z";
                        String Message = "Your friend abc is in problem , Here's the Location\nLatitude :"+currentLat +"\nLongitude : " + currentLong+"\n"+message;
                        String numbers[] = fetch();
                        SmsManager mySmsManager = SmsManager.getDefault();
                        for(String number : numbers) {
                            mySmsManager.sendTextMessage(number, null, Message, null, null);
                            Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT).show();
                        }
                }
                    @Override
                    public void onStatusChanged (String s,int i, Bundle bundle){
                }
                    @Override
                    public void onProviderEnabled (String s){
                }
                    @Override
                    public void onProviderDisabled (String s){
                }
            };
        // If device is running SDK < 23
        if (Build.VERSION.SDK_INT < 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // ask for permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                // we have permission!
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);
            }
        }
    }

    public void siren()
    {
        music = MediaPlayer.create(this, R.raw.policesiren);
        emergency = findViewById(R.id.emergency);
        final int i = 1;
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),CameraMain.class);
                startActivity(intent);
                //music.setLooping(true);
                //music.start();
                Send();
            }
        });

    }
    public void nearPol()
    {
        nearPol = findViewById(R.id.polstation);
        nearPol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getApplicationContext(),MapsActivity.class);
                intent.putExtra("key", "pol");
                startActivity(intent);
            }
        });
    }
    public void editDetails()
    {
        pro = findViewById(R.id.profile);
        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),ShowUserDetails.class);
                startActivity(intent);
            }
        });
    }
    public void nearHosp()
    {
        hosp = findViewById(R.id.hospital);
        nearCallId = 5;
        hosp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getApplicationContext(),MapsActivity.class);
                intent.putExtra("key", "hosp");
                startActivity(intent);
            }
        });

    }

    public void dial()
    {
        amb = findViewById(R.id.ambulance);
        pol = findViewById(R.id.cops);
        fam = findViewById(R.id.family);
        final SQLiteDatabase myDatabase = this.openOrCreateDatabase("bSafe",MODE_PRIVATE,null);
        pol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
                Send();
                String no = "tel: 9053846728";
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse(no));

                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        //Ask for permission
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    }
                    else {
                        startActivity(callIntent);
                    }
            }
        });
        fam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Send();
                Cursor c = myDatabase.rawQuery("SELECT * FROM users ",null);
                int PhoneIndex = c.getColumnIndex("Phone");
                c.moveToFirst();
                String no = "tel: "+c.getString(PhoneIndex);
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse(no));

                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    //Ask for permission
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
                }
                else {
                    startActivity(callIntent);
                }
            }
        });
        amb.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onClick(View v) {
                Send();
                String no = "tel: 9053846728";
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse(no));
                Send();
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    //Ask for permission
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
                }
                else {
                    startActivity(callIntent);
                }
            }
        });
    }
}