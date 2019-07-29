package com.shopping.feature.home.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shopping.R;
import com.shopping.databinding.CatOfferBinding;
import com.shopping.feature.home.data.model.CategoryOffer;
import com.shopping.feature.home.data.model.ExclusiveOffer;

import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;

public class CategoryOfferAdapter extends RecyclerView.Adapter<CatOfferViewHolder> {

    private Context context;
    private ArrayList<CategoryOffer> list;
    private LayoutInflater inflater;

    public CategoryOfferAdapter(Context context, ArrayList<CategoryOffer> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CatOfferViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(inflater == null) {
            inflater = LayoutInflater.from(viewGroup.getContext());
        }
        CatOfferBinding binding = DataBindingUtil.inflate(inflater, R.layout.cat_offer_list_item, viewGroup, false);
        return new CatOfferViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull CatOfferViewHolder catOfferViewHolder, int i) {
        CategoryOffer categoryOffer = list.get(i);
        catOfferViewHolder.bind(categoryOffer);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}


    class CatOfferViewHolder extends RecyclerView.ViewHolder {

    CatOfferBinding binding;

    public CatOfferViewHolder(@NonNull CatOfferBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(CategoryOffer categoryOffer) {
        binding.setCatOffer(categoryOffer);
        binding.executePendingBindings();
    }
}
