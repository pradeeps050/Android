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
import com.shopping.feature.home.data.model.Offers;
import com.shopping.framework.utils.AddOrRemoveItem;

import java.util.HashMap;
import java.util.List;


public class ProductAdapter  extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Offers> list;
    private LayoutInflater inflater;
    private HashMap<Integer, Boolean> map = new HashMap<>();
    private AddOrRemoveItem addOrRemoveItem;

    public ProductAdapter(Context context, List<Offers> list, AddOrRemoveItem addOrRemoveItem) {
        this.context = context;
        this.list = list;
        this.addOrRemoveItem = addOrRemoveItem;
        prepareMap();
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

    private void prepareMap() {
        for (Offers offers : list) {
            map.put(offers.getProductId(), false);
        }
    }
    public class ProductViewHolder extends RecyclerView.ViewHolder {

        HomeProductListTemsBinding binding;

        public ProductViewHolder(@NonNull HomeProductListTemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Offers offers) {
            binding.proQty.setText(offers.getQuantity());
            binding.proDetail.setText(offers.getProductDetail().getTitle());
            Double fl = offers.getmRP();
            binding.discount.setText("₹"+ String.valueOf(fl.floatValue()));
            binding.mrp.setText("₹"+String.valueOf(fl.floatValue()));
            binding.cartBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (! map.get(offers.getProductId())) {
                        binding.cartBtn.setText(R.string.remove);
                        addOrRemoveItem.addItem(offers);
                        map.put(offers.getProductId(), true);
                    } else {
                        binding.cartBtn.setText(R.string.add_to_cart);
                        addOrRemoveItem.removeItem(offers);
                        map.put(offers.getProductId(), false);
                    }







                    /*if (! offers.isAdded()) {
                        offers.setAdded(true);
                        binding.cartBtn.setText(R.string.remove);
                        addOrRemoveItem.addItem(offers);
                        Logger.d("Adapter", ">> if");
                    } else {
                        offers.setAdded(false);
                        binding.cartBtn.setText(R.string.add_to_cart);
                        addOrRemoveItem.removeItem(offers);
                        Logger.d("Adapter", ">> else");
                    }*/
                }
            });
        }
    }
//    public interface CartClickListner{
//        void onClick(Offers offers);
//    }


}



