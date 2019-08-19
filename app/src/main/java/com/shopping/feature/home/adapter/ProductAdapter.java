package com.shopping.feature.home.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.shopping.R;
import com.shopping.databinding.HomeProductListTemsBinding;
import com.shopping.feature.home.data.model.Offers;
import com.shopping.feature.home.data.model.Product;

import java.util.List;

public class ProductAdapter  extends RecyclerView.Adapter<ProductViewHolder> {

    private Context context;
    private List<Offers> list;
    private LayoutInflater inflater;

    public ProductAdapter(Context context, List<Offers> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (inflater==null) {
            inflater = LayoutInflater.from(viewGroup.getContext());
        }
        HomeProductListTemsBinding binding = DataBindingUtil.inflate(inflater, R.layout.home_product_list_tems,
                viewGroup, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        Offers offers = list.get(i);
        productViewHolder.bind(offers);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}



class ProductViewHolder extends RecyclerView.ViewHolder {

    HomeProductListTemsBinding binding;

    public ProductViewHolder(@NonNull HomeProductListTemsBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Offers offers) {
        Integer i = offers.getQuantity();

        binding.proQty.setText(String.valueOf(i.intValue()));

    }
}
