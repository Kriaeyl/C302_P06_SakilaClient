package sg.edu.rp.dmsd.c302_p06_sakilaclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import cz.msebera.android.httpclient.Header;

public class SearchFilmsActivity extends AppCompatActivity {

    SearchView sv1;
    TextView tv1;
    ListView lv1;
    CustomAdapter aa;
    ArrayList<Store> sL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_films);

        sv1 = findViewById(R.id.sv);
        tv1 = findViewById(R.id.textView5);
        lv1 = findViewById(R.id.lv3);
        sL = new ArrayList<>();
        aa = new CustomAdapter(this, R.layout.row, sL);
        lv1.setAdapter(aa);


        sv1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                sL.clear();
                AsyncHttpClient client = new AsyncHttpClient();
                client.get("http://10.0.2.2/C302_sakila/getRentalLocationsByFilmId.php?id=" + query, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            tv1.setText("Title: " + response.getString("title") + "\nDescription: " + response.getString("description") + "\nRelease Year: " + response.getString("release_year") + "\nRating: " + response.getString("rating"));
                            ArrayList<JSONObject> al = new ArrayList<>();
                            JSONArray l = response.getJSONArray("stores");
                            for (int i=0; i<l.length(); i++) {
                                JSONObject x = l.getJSONObject(i);
                                Store store = new Store(x.getString("address"), x.getString("city"), x.getString("country"));
                                sL.add(store);
                            }
                        } catch (JSONException e) {

                        }
                        aa.notifyDataSetChanged();
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
