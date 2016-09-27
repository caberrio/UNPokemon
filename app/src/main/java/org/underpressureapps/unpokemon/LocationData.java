package org.underpressureapps.unpokemon;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class LocationData extends AsyncTask<Void,Void,List<Location>> {

    private MapsActivity mapActivity;

    public LocationData(MapsActivity mapActivity) {
        this.mapActivity = mapActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Location> doInBackground(Void... voids) {

        String response = null;
        try {
            URL url = null;
            url = new URL("http://190.144.171.172/function3.php?lat=11.0199414&lng=-74.8487154");
            URLConnection yt = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yt.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                //Log.d(TAG, inputLine);
                response = inputLine;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        List<Location> pokeLocation = new ArrayList<Location>();
        if (response != null){
            try {
                JSONArray usuarios = new JSONArray(response);

                for (int i = 0; i < usuarios.length(); i++){
                    JSONObject location = usuarios.getJSONObject(i);
                    pokeLocation.add(new Location(location.getDouble("lt"), location.getDouble("lng")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {

        }
        return pokeLocation;
    }

    @Override
    protected void onPostExecute(List<Location> aVoid) {
        super.onPostExecute(aVoid);
        mapActivity.pokeLocations(aVoid);
    }
}