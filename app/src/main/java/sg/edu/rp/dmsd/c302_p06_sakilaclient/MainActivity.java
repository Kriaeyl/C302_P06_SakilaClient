package sg.edu.rp.dmsd.c302_p06_sakilaclient;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.*;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ListView lvCategories;
    ArrayList<Category> alCategories = new ArrayList<Category>();
    ArrayAdapter<Category> aaCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCategories = (ListView) findViewById(R.id.lv1);
        aaCategories = new ArrayAdapter<Category>(this, android.R.layout.simple_list_item_1, alCategories);
        lvCategories.setAdapter(aaCategories);

        //TODO: Task 1: Consume getCategories.php using AsyncHttpClient
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://10.0.2.2/C302_sakila/getCategories.php", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    for (int i=0; i<response.length(); i++) {
                        JSONObject category = (JSONObject) response.get(i);
                        Category c = new Category(category.getInt("category_id"), category.getString("name"));
                        alCategories.add(c);
                    }

                } catch (JSONException e) {

                }
                aaCategories.notifyDataSetChanged();
            }
        });

        lvCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Category cat = alCategories.get(position);
                Intent intent = new Intent(MainActivity.this, ViewFilmActivity.class);
                intent.putExtra("cat", cat);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Context context = MainActivity.this;
        Intent intent;
        switch (item.getItemId()) {
            case R.id.VCS:
                intent = new Intent(context, CategorySummaryActivity.class);
                startActivity(intent);
                break;
            case R.id.SF:
                break;
            case R.id.SRL:
                intent = new Intent(context, SearchFilmsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}
