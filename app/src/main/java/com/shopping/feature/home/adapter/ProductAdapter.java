package com.shopping.feature.home.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shopping.R;
import com.shopping.databinding.HomeProductListTemsBinding;
import com.shopping.feature.home.data.model.Product;

import java.util.ArrayList;

public class ProductAdapter  extends RecyclerView.Adapter<ProductViewHolder> {

    private Context context;
    private ArrayList<Product> list;
    private LayoutInflater inflater;

    public ProductAdapter(Context context, ArrayList<Product> list) {
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
        Product product = list.get(i);
        productViewHolder.bind(product);

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

    public void bind(Product product) {
        binding.setProduct(product);
        binding.executePendingBindings();
    }
}
