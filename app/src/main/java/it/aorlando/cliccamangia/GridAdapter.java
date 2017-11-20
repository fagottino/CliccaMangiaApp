package it.aorlando.cliccamangia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import it.aorlando.cliccamangia.Model.Item;
import static it.aorlando.cliccamangia.MainActivity.URL_DOMAIN;

/**
 * Created by fagottino on 16/05/17.
 */

public class GridAdapter extends BaseAdapter {

    private List<Item> items;
    private Context context;
    private LayoutInflater layoutInflater;

    public GridAdapter(Context pContext, List<Item> pItems) {
        this.context = pContext;
        this.items = pItems;
        this.layoutInflater = LayoutInflater.from(pContext);
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View gridView = convertView;

        if (convertView == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView = layoutInflater.inflate(R.layout.custom_grid_item, null);
        }

        ImageView image = (ImageView) gridView.findViewById(R.id.imageViewGridItem);
        TextView text = (TextView) gridView.findViewById(R.id.textViewGridItem);

        Picasso.with(context).load(URL_DOMAIN + items.get(position).image.substring(3)).into(image);
        //text.setText(items.get(position).toString()); "images/not-available.png"
        text.setText(items.get(position).name);
        return gridView;
    }
}
