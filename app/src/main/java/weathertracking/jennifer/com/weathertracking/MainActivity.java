package weathertracking.jennifer.com.weathertracking;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button btnShowLocation;

    Tracker tracker;
    // myLocation;
    RetrieveWeather weather;
    private double latitude;
    private double longitude;
    private String city = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tracker = new Tracker(MainActivity.this);


        //check gps enabled
        if (tracker.canGetLocation())
        {
            latitude = tracker.getLatitude();
            longitude = tracker.getLongitude();
            weather = new RetrieveWeather(MainActivity.this, latitude, longitude);
            //myLocation = new FindLocation(MainActivity.this, latitude, longitude);
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }
        else
        {
            tracker.showSettingsAlert();
        }
    }



    public void getLocation(View view)
    {
/*
        if (latitude != 0 && longitude != 0)
        {
            //city = myLocation.getCity();
           // weather = new RetrieveWeather(city);
            weather = new RetrieveWeather(MainActivity.this, latitude, longitude);

               Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude +
                   "\nYour City is : " + city, Toast.LENGTH_LONG).show();

        }*/
    }

    public void getWeather (View view)
    {
        String city = weather.getCityName();
        if (city != "" && city != null)
        {
            String condition = weather.getCondition();
            Toast.makeText(getApplicationContext(), "Your condition is : " + condition, Toast.LENGTH_LONG).show();

        }
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
