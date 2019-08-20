package com.shopping.feature.myorder.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shopping.R;
import com.shopping.databinding.ListBinding;
import com.shopping.feature.myorder.RecyclerViewClickListener;
import com.shopping.feature.myorder.data.model.MyListData;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter  extends  RecyclerView.Adapter<MyListAdapter.OrderViewHolder> {
    private Context context;
    private ArrayList<MyListData> list;
    private LayoutInflater inflater;
    public MyListAdapter(Context context, ArrayList<MyListData> list) {
        this.context = context;
        this.list = list;




    }

    /*@NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(layoutInflater ==null)
        {
            layoutInflater = layoutInflater.from(ViewGroup viewGroup, int i);
        }
        ListBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.list, viewGroup,false);

        return new OrderViewHolder(binding);
    }*/

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      //  Log.d("Adapter","onCreateViewHolder");
        if (inflater==null) {
            inflater = LayoutInflater.from(viewGroup.getContext());
        }

        ListBinding binding = DataBindingUtil.inflate(inflater, R.layout.list,
                viewGroup, false);
        return new OrderViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder orderViewHolder, int i) {
        MyListData myListData = list.get(i);
        orderViewHolder.bind(myListData);

      /*  orderViewHolder.binding.orderNo1Txt.setText(data.getOrderNum());
        orderViewHolder.binding.orderNo2Txt.setText(data.getOrderStatus());*/

    }

    /*@Override
    public void onBindViewHolder(@NonNull ListBinding binding, int i) {
        final MyListData myListData = listdata[i];
        binding.orderNo1Txt.setText(listdata[i].getOrderNum());
        //binding
    }*/

    @Override
    public int getItemCount() {

            return list.size();
    }
    class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        private RecyclerViewClickListener mListener;
        private TextView orderTxt1;
        private TextView orderTxt2;
        ListBinding binding ;


       /* public OrderViewHolder(@NonNull ListBinding binding) {
           *//* super(binding.getRoot());
            this.binding = binding;*//*
            super(binding.getRoot());
            binding.setOnClickListener(this);
            orderTxt1 = binding.orderNo1Txt;
            orderTxt2 = binding.orderNo2Txt;
    }*/


        @Override
        public void onClick(View view) {
            @NonNull ListBinding binding;
        }

        public OrderViewHolder(@NonNull ListBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;

        }
        public  void bind(MyListData ListData)
        {
            binding.setMyListData(ListData);
            binding.executePendingBindings();
        }
    }
}

