package com.example.perfume_store;

import android.content.Context;
import android.view.*;
import android.widget.*;
import java.util.List;

public class PerfumeAdapter extends ArrayAdapter<Perfume> {

    private Context context;
    private List<Perfume> perfumes;

    public PerfumeAdapter(Context context, List<Perfume> perfumes) {
        super(context, 0, perfumes);
        this.context = context;
        this.perfumes = perfumes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.perfume_item, parent, false);
        }

        Perfume perfume = perfumes.get(position);

        TextView name = itemView.findViewById(R.id.tvPerfumeName);
        TextView price = itemView.findViewById(R.id.tvPerfumePrice);
        ImageView image = itemView.findViewById(R.id.imgPerfume);
        ImageButton btnAddToCart = itemView.findViewById(R.id.btnAddToCart);

        name.setText(perfume.getName());
        price.setText("$" + perfume.getPrice());

        int imageResource = perfume.getImageResource();
        if (imageResource != 0) {
            image.setImageResource(imageResource);
        } else {
            image.setImageResource(R.drawable.perfumelogo);
        }

        btnAddToCart.setOnClickListener(v -> {
            Cart.addToCart(context, perfume);
            Toast.makeText(context, perfume.getName() + " Added to Cart 🛒", Toast.LENGTH_SHORT).show();
        });

        return itemView;
    }

}
