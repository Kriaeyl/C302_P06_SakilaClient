package sg.edu.rp.dmsd.c302_p06_sakilaclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ViewFilmActivity extends AppCompatActivity {

    ListView lv1;
    TextView tv1;
    FilmAdapter aa;
    ArrayList<Film> al = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_film);
        lv1 = findViewById(R.id.lv2);
        tv1 = findViewById(R.id.textView);
        Intent i = getIntent();
        Category cat = (Category) i.getSerializableExtra("cat");
        tv1.setText(cat.getName());

        aa = new FilmAdapter(this, R.layout.film_list, al);
        lv1.setAdapter(aa);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://10.0.2.2/C302_sakila/getFilmsByCategoryId.php?id=" + cat.getId(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    for (int i=0; i<response.length(); i++) {
                        JSONObject film = (JSONObject) response.get(i);
                        Film c = new Film(film.getString("title"), film.getString("rating"), film.getString("release_year"));
                        al.add(c);
                    }

                } catch (JSONException e) {

                }
                aa.notifyDataSetChanged();
            }
        });
    }
}
