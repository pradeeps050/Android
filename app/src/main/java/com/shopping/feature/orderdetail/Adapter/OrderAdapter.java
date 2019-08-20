package com.shopping.feature.orderdetail.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shopping.R;
import com.shopping.databinding.OrderlistBinding;
import com.shopping.feature.orderdetail.data.model.ProductOrder;
import com.shopping.framework.model.Product;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderDetailViewHolder> {
    private Context context;
    private ArrayList<ProductOrder> list;
    private LayoutInflater inflater;

    public OrderAdapter(Context context, ArrayList<ProductOrder>list)
    {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public OrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(inflater == null)
        {
            inflater = LayoutInflater.from(viewGroup.getContext());
        }

        OrderlistBinding binding = DataBindingUtil.inflate(inflater, R.layout.orderlist, viewGroup,false);
        return new OrderDetailViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailViewHolder orderDetailViewHolder, int i) {
 ProductOrder productOrder = list.get(i);
 orderDetailViewHolder.bind(productOrder);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class OrderDetailViewHolder extends RecyclerView.ViewHolder{
        OrderlistBinding binding;

        public OrderDetailViewHolder(@NonNull OrderlistBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public  void bind(ProductOrder productOrder){
            binding.setProductOrder(productOrder);
            binding.executePendingBindings();
        }
    }
}
