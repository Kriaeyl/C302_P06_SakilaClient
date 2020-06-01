package sg.edu.rp.dmsd.c302_p06_sakilaclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Store> {
    private Context context;
    private ArrayList<Store> objects;
    private TextView tv1, tv2, tv3;

    public CustomAdapter(Context context, int resource, ArrayList<Store> stores) {
        super(context, resource, stores);
        this.context = context;
        objects = stores;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row, parent, false);
        tv1 = rowView.findViewById(R.id.textView7);
        tv2 = rowView.findViewById(R.id.textView8);
        tv3 = rowView.findViewById(R.id.textView9);


        //Match the UI components with Java variables

        Store store = objects.get(position);
        tv1.setText(store.getAddress());
        tv2.setText(store.getCity());
        tv3.setText(store.getCountry());

        return rowView;
    }
}