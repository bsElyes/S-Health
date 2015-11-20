package main.health.com.s_health.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import main.health.com.s_health.Entities.Medicament;
import main.health.com.s_health.R;

/**
 * Created by ElyesL on 20/11/2015.
 */
public class MedicamentAdapter extends ArrayAdapter<Medicament> {
    int layoutResourceId;
    // imageLoader;
    Context context;
    List<Medicament> medicaments=new ArrayList<Medicament>();

    public MedicamentAdapter(Context context, int layoutResourceId
            , List<Medicament> medicaments) {
        super(context, layoutResourceId, medicaments);


        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.medicaments = medicaments;
        //imageLoader = new ImageLoader(context.getApplicationContext());

    }


    @Override
    public int getPosition(Medicament item) {
        return super.getPosition(item);
    }

    @Override
    public Medicament getItem(int position) {
        return medicaments.get(position);
    }

    @Override
    public int getCount() {
        return medicaments.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }



    @SuppressLint("SdCardPath")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(layoutResourceId, parent, false);
        final int pos=position;

        ImageView medicamentImg = (ImageView) row.findViewById(R.id.icon);
        TextView medicamentName = (TextView) row.findViewById(R.id.travelName);
        TextView medicamentDate = (TextView) row.findViewById(R.id.travelAdress);



        Medicament m = medicaments.get(position);
        System.out.println(m.getLabel() + "  " + m.getQuantity() + " *** " + m.getExpiryDate());
        //imageLoader.DisplayImage(ProductsFragment.ipServer+p.getImagePath(), produitImg);
        //travelName.setText(m.getName());
        //travelAdress.setText(m.getAdress());
        //produitDesc.setText(p.getName());
        return row;
    }
}
