package com.example.m.meba_v2.Data;

        import android.app.Activity;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.example.m.meba_v2.R;

        import java.util.ArrayList;

/**
 * Created by m on 25/04/2017.
 */

public class adaptardatos extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Datos> items;

    public adaptardatos(Activity activity, ArrayList<Datos> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(convertView==null)
        {
            LayoutInflater inf = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inf.inflate(R.layout.list_item,null);

        }
        Datos datos = items.get(position);
        Datos dir = items.get(position);

        ImageView foto = (ImageView) view.findViewById(R.id.solacia);
        foto.setImageDrawable(datos.getFoto());
        TextView title = (TextView) view.findViewById(R.id.titulo);
        title.setText(datos.getNombre());
        TextView info = (TextView) view.findViewById(R.id.descripcion);
        info.setText(datos.getInfo());
        return view;

    }
}
