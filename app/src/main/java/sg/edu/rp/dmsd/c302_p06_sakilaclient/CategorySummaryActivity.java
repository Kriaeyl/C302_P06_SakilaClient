package sg.edu.rp.dmsd.c302_p06_sakilaclient;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class CategorySummaryActivity extends AppCompatActivity {

    TableLayout tb1;
    TableRow tbH;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_summary);
        tv1 = findViewById(R.id.textView);

        //Make Blank Table
        tb1 = findViewById(R.id.tbL);
        tb1.setStretchAllColumns(true);
        tb1.bringToFront();

        //Make Header
        tbH = findViewById(R.id.tbH);
        TextView tvH = new TextView(this);
        TextView tvH2 = new TextView(this);
        tvH.setText("Category");
        tvH2.setText("Number of Films");
        tbH.addView(tvH);
        tbH.addView(tvH2);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://10.0.2.2/C302_sakila/getCategorySummary.php", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    for (int i=0; i<response.length(); i++) {
                        TableRow tbR = new TableRow(CategorySummaryActivity.this);
                        TextView tv1 = new TextView(CategorySummaryActivity.this);
                        TextView tv2 = new TextView(CategorySummaryActivity.this);
                        JSONObject category = (JSONObject) response.get(i);
                        tv1.setText(category.getString("category"));
                        tv2.setText(category.getString("number_films"));
                        if (i%2 == 0) {
                            tv1.setBackgroundColor(Color.WHITE);
                            tv2.setBackgroundColor(Color.WHITE);
                            tv1.setTextColor(Color.BLACK);
                            tv2.setTextColor(Color.BLACK);
                        }
                        else {
                            tv1.setBackgroundColor(Color.BLACK);
                            tv2.setBackgroundColor(Color.BLACK);
                            tv1.setTextColor(Color.WHITE);
                            tv2.setTextColor(Color.WHITE);
                        }
                        tbR.addView(tv1);
                        tbR.addView(tv2);
                        tb1.addView(tbR);
                    }

                } catch (JSONException e) {

                }
            }
        });



    }
}
