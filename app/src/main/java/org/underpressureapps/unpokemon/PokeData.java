package org.underpressureapps.unpokemon;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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


public class PokeData extends AppCompatActivity {

    private ProgressDialog pDialog;
    private static String TAG = "elTag";
    private List<Pokemon> pokemonList;
    private static Context context;

    // JSON
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
    }

    protected static String getData(){
        Log.d(TAG,"get");
        String response = null;
        try {
            URL url = null;
            url = new URL("http://api.randomuser.me/?results=25&format=jason");
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
            //pDialog = new ProgressDialog(this);
            //pDialog.setMessage("Please wait...");
            //pDialog.setCancelable(false);
            //pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            String response = getData();
            pokemonList = new ArrayList<Pokemon>();
            if (response != null){
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray pokemons = jsonObject.getJSONArray("results");
                    Log.d(TAG,"response " + pokemons.length());
                    for (int i = 0; i < pokemons.length(); i++){
                        JSONObject c = pokemons.getJSONObject(i);
                        JSONObject pokemon = c.getJSONObject("");
                        pokemonList.add(new Pokemon(pokemon.getInt("Id"),pokemon.getString("Name"),pokemon.getString("Type"),
                                pokemon.getInt("Total"),pokemon.getInt("HP"),pokemon.getInt("Attack"),pokemon.getInt("Defense"),
                                pokemon.getInt("Sp. Atk"),pokemon.getInt("Sp. Def"),pokemon.getInt("Speed"),pokemon.getString("ImgFront"),
                                pokemon.getString("ImgBack"),pokemon.getString("GifFront"),pokemon.getString("GifBack"),pokemon.getString("ImgUrl"),
                                pokemon.getInt("ev_id")));
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
