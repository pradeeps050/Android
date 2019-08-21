package com.shopping.feature.cart;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.shopping.R;
import com.shopping.databinding.CartItemsBinding;
import com.shopping.feature.home.data.model.Cart;
import com.shopping.framework.utils.AddOrRemoveItem;

import java.util.Collection;
import java.util.HashMap;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private HashMap<Integer, Cart> cartMap;
    private LayoutInflater inflater;
    private AddOrRemoveItem addOrRemoveItem;

    public CartAdapter(Context context, HashMap<Integer, Cart> cartMap) {
        this.context = context;
        this.cartMap = cartMap;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (inflater==null) {
            inflater = LayoutInflater.from(viewGroup.getContext());
        }
        CartItemsBinding binding = DataBindingUtil.inflate(inflater, R.layout.cart_items, viewGroup, false);
        return new CartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i) {
        Collection<Cart> collection =  cartMap.values();
        Object[] objects = collection.toArray();
        cartViewHolder.bind((Cart) objects[i]);
    }

    @Override
    public int getItemCount() {
        return cartMap.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        CartItemsBinding binding;
        public CartViewHolder(@NonNull CartItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Cart cart) {
            binding.discountText.setText(cart.getDiscount());
            binding.mrp.setText(cart.getMrp());
            binding.productTitle.setText(cart.getTitle());
            binding.quantity.setText(cart.getVolume());

        }
    }
}
