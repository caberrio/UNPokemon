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


public class PokeData extends AsyncTask<Void,Void,List<Pokemon>> {

    private MapsActivity mapActivity;

    public PokeData(MapsActivity mapActivity) {
        this.mapActivity = mapActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Pokemon> doInBackground(Void... voids) {

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
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        List<Pokemon> pokemonList = new ArrayList<Pokemon>();
        if (response != null){
            try {
                JSONArray pokemons = new JSONArray(response);

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
        }
        return pokemonList;
    }

    @Override
    protected void onPostExecute(List<Pokemon> aVoid) {
        super.onPostExecute(aVoid);
//        mapActivity.pokemons(aVoid);
    }

}
