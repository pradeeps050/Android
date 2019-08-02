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
import com.shopping.databinding.OfferProductBinding;
import com.shopping.feature.home.data.model.OfferProduct;
import com.shopping.feature.home.data.model.Product;

import java.util.ArrayList;

public class OfferProductAdapter extends RecyclerView.Adapter<OfferProductView> {

    private Context context;
    private ArrayList<OfferProduct> list;
    private LayoutInflater inflater;

    public OfferProductAdapter(Context context, ArrayList<OfferProduct> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public OfferProductView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (inflater==null) {
            inflater = LayoutInflater.from(viewGroup.getContext());
        }
        OfferProductBinding binding = DataBindingUtil.inflate(inflater, R.layout.offer_product_list_item,
                viewGroup, false);
        return new OfferProductView(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferProductView offerProductView, int i) {
       OfferProduct offerProduct =  list.get(i);
       offerProductView.bind(offerProduct);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}




    class OfferProductView extends RecyclerView.ViewHolder {

        OfferProductBinding binding;

        public OfferProductView(@NonNull OfferProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(OfferProduct offerProduct) {
            binding.setOfferProduct(offerProduct);
        }
    }