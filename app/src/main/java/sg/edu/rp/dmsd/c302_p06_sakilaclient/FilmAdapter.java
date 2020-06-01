package sg.edu.rp.dmsd.c302_p06_sakilaclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FilmAdapter extends ArrayAdapter<Film> {
    private Context context;
    private ArrayList<Film> objects;
    private TextView tv1, tv2, tv3;

    public FilmAdapter(Context context, int resource, ArrayList<Film> films) {
        super(context, resource, films);
        this.context = context;
        objects = films;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.film_list, parent, false);
        tv1 = rowView.findViewById(R.id.textView2);
        tv2 = rowView.findViewById(R.id.textView3);
        tv3 = rowView.findViewById(R.id.textView4);


        //Match the UI components with Java variables

        Film film = objects.get(position);
        tv1.setText(film.getTitle());
        tv2.setText(film.getYear() + "");
        tv3.setText(film.getRating());

        return rowView;
    }
}
