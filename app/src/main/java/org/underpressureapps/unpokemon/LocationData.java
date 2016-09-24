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

public class LocationData extends AppCompatActivity {
    private ProgressDialog pDialog;
    private static String TAG = "elTag";
    private List<Location> pokeLocation;
    private static Context context;

    /*// JSON
    public boolean verificarRed(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            //Toast.makeText(this,"Network is available",Toast.LENGTH_LONG).show();
            return true;
        } else {
            Toast.makeText(this,"Network is not available",Toast.LENGTH_LONG)
                    .show();
            return false;
        }
    }*/

    protected static String getData(){
        Log.d(TAG,"get");
        String response = null;
        try {
            URL url = null;
            url = new URL("http://190.144.171.172/function.php?lat=11.0199414&lng=-74.8487154");
            URLConnection yt = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yt.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                //Log.d(TAG, inputLine);
                response = inputLine;
            }
            in.close();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
    private class GetData extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //pDialog = new ProgressDialog(MainActivity.this);
            //pDialog.setMessage("Please wait...");
            //pDialog.setCancelable(false);
            //pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            String response = getData();
            pokeLocation = new ArrayList<Location>();
            if (response != null){
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray usuarios = jsonObject.getJSONArray("results");
                    Log.d(TAG,"response " + usuarios.length());
                    for (int i = 0; i < usuarios.length(); i++){
                        JSONObject c = usuarios.getJSONObject(i);
                        JSONObject location = c.getJSONObject("");
                        pokeLocation.add(new Location(location.getDouble("lt"), location.getDouble("lng")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.d(TAG,"response is null");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (pDialog.isShowing())
                pDialog.dismiss();
        }
    }
}
