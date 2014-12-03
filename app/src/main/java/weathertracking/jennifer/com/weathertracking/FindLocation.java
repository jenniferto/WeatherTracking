package weathertracking.jennifer.com.weathertracking;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Created by Jennifer on 29/11/2014.
 */
public class FindLocation {

    private final Context mContext;
    private double latitude;
    private double longitude;
    private String cityName;

    public FindLocation(Context context, double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
        this.mContext = context;
        //System.out.println("lat is " + this.latitude + " long is " + this.longitude);
        new Connection().execute();
    }

    public void findCity()
    {
        Geocoder gcd = new Geocoder(mContext, Locale.getDefault());

        //System.out.println("Testing1");
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(this.latitude, this.longitude, 1);
            //System.out.println("Testing2");
            if (addresses.size() > 0) {
                //System.out.println("Testing3");
                //System.out.println(addresses.get(0).getLocality());
                cityName = addresses.get(0).getLocality();
               // System.out.println("Testing4 " + cityName);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public String getCity()
    {
       // System.out.println("Testing5 " + cityName);
        return cityName;
    }

    private class Connection extends AsyncTask
    {
        @Override
        protected Object doInBackground(Object... arg0)
        {
            findCity();
            return null;
        }
    }
}
