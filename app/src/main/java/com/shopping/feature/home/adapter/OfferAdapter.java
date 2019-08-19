package com.shopping.feature.home.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shopping.R;
import com.shopping.databinding.OfferBinding;
import com.shopping.databinding.OffersListItemBinding;
import com.shopping.feature.home.data.model.Offers;

import java.util.ArrayList;

public class OfferAdapter extends RecyclerView.Adapter<OfferViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Offers> offersList;

    public OfferAdapter(Context context, ArrayList<Offers> offersList) {
        this.context = context;
        this.offersList = offersList;
    }

    @NonNull
    @Override
    public OfferViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (inflater == null) {
            inflater = LayoutInflater.from(viewGroup.getContext());
        }
        OffersListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.offers_list_item, viewGroup, false);
        return new OfferViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferViewHolder offerViewHolder, int i) {
       Offers offers = offersList.get(i);
       //offerViewHolder.bind(offers);
    }

    @Override
    public int getItemCount() {
        return offersList.size();
    }
}



class OfferViewHolder extends RecyclerView.ViewHolder {
    private OffersListItemBinding offerBinding;

    public OfferViewHolder(@NonNull OffersListItemBinding offerBinding) {
        super(offerBinding.getRoot());
        this.offerBinding = offerBinding;
    }

    /*public void bind(Offers offers) {
        offerBinding.setOffer(offers);
        offerBinding.executePendingBindings();
    }*/
}