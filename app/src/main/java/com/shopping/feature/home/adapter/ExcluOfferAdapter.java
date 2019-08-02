package com.shopping.feature.home.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shopping.R;
import com.shopping.databinding.ExclOfferBinding;
import com.shopping.feature.home.data.model.ExclusiveOffer;

import java.util.ArrayList;

public class ExcluOfferAdapter extends RecyclerView.Adapter<ExcluOfferViewHolder> {

    private Context context;
    private ArrayList<ExclusiveOffer> list;
    private LayoutInflater inflater;

    public ExcluOfferAdapter(Context context, ArrayList<ExclusiveOffer> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ExcluOfferViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(inflater == null) {
            inflater = LayoutInflater.from(viewGroup.getContext());
        }
        ExclOfferBinding binding = DataBindingUtil.inflate(inflater, R.layout.exclu_offer_list_item, viewGroup, false);
        return new ExcluOfferViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExcluOfferViewHolder excluOfferViewHolder, int i) {
        ExclusiveOffer exclusiveOffer = list.get(i);
        excluOfferViewHolder.bind(exclusiveOffer);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}



class ExcluOfferViewHolder extends RecyclerView.ViewHolder {
    ExclOfferBinding binding;

    public ExcluOfferViewHolder(@NonNull ExclOfferBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(ExclusiveOffer exclusiveOffer) {
        binding.setExclOffer(exclusiveOffer);
        binding.executePendingBindings();
    }
}
