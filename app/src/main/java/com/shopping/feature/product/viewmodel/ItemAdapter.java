package com.shopping.feature.product.viewmodel;

import android.annotation.SuppressLint;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shopping.R;
import com.shopping.databinding.EmptyViewBinding;
import com.shopping.databinding.RecyclerviewUsersBinding;
import com.shopping.feature.product.model.Item;
import com.shopping.framework.network.NetworkState;

public class ItemAdapter extends PagedListAdapter<Item, ItemAdapter.ItemViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private Context mCtx;
    private NetworkState nState;

   public ItemAdapter(Context mCtx) {
        super(DIFF_CALLBACK);
        this.mCtx = mCtx;
       mLayoutInflater = (LayoutInflater)mCtx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

   }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_users, parent, false);
//        return new ItemViewHolder(view);

         if(viewType == R.layout.recyclerview_users){
             RecyclerviewUsersBinding binding = DataBindingUtil.inflate(mLayoutInflater , viewType , parent, false);
            return new ItemViewHolder(binding);
        }else {
            EmptyViewBinding binding = DataBindingUtil.inflate(LayoutInflater.
                    from(parent.getContext()), viewType, parent, false);


            return new ItemViewHolder(binding);
        }
    }
    @Override
    public int getItemViewType(int position) {
        int layoutId = R.layout.recyclerview_users;
        if(hasExtraRow() && (position == getItemCount() - 1)){
            if((nState.status == NetworkState.STATUS.START || nState.status == NetworkState.STATUS.START_ERROR) || nState.status == NetworkState.STATUS.NO_DATA){
                return R.layout.empty_view;
            }else{
                return R.layout.empty_view;
            }
        }
        return layoutId;
    }
    @Override
    public int getItemCount() {
        int count = super.getItemCount();
        if(hasExtraRow()){
            count++;
        }
        return count;
    }

    private boolean hasExtraRow() {
        return (null != nState && NetworkState.STATUS.LOADED != nState.status && (getCurrentList() ==null || getCurrentList().size() == 0));
    }

    public void setNetworkState(NetworkState state) {
        NetworkState previousState = nState;
        boolean hadExtraRow = hasExtraRow();
        nState = state;
        boolean hasExtraRow = hasExtraRow();
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount());
            } else {
                notifyItemInserted(super.getItemCount());
            }
        } else if (hasExtraRow && previousState != nState) {
            notifyItemChanged(getItemCount() - 1);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        if (null != holder.mlItemLayoutBinding) {
            Item item = getItem(position);

            if (item != null) {
                holder.textView.setText(item.owner.getDisplayName());
                Glide.with(mCtx)
                        .load(item.owner.getProfileImage())
                        .into(holder.imageView);
            }else{
                Toast.makeText(mCtx, "Item is null", Toast.LENGTH_LONG).show();
            }
        }else if (null != holder.mRetryBinding) {
            if (null != nState) {
                holder.mRetryBinding.setModel(nState);
                holder.mRetryBinding.executePendingBindings();
            }
        }

    }

    private static DiffUtil.ItemCallback<Item> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Item>() {
                @Override
                public boolean areItemsTheSame(Item oldItem, Item newItem) {
                    return oldItem.getQuestionId() == newItem.getQuestionId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(Item oldItem, Item newItem) {
                    return oldItem.equals(newItem);
                }
            };

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        ImageView imageView;
        private RecyclerviewUsersBinding mlItemLayoutBinding;
        EmptyViewBinding mRetryBinding;

        public ItemViewHolder(RecyclerviewUsersBinding itemView) {
            super(itemView.getRoot());
            mlItemLayoutBinding = itemView;
            textView = mlItemLayoutBinding.textViewName;
            imageView = mlItemLayoutBinding.imageView;

        }
        public ItemViewHolder(EmptyViewBinding binding) {
            super(binding.getRoot());
            mRetryBinding = binding;
        }

        @Override
        public void onClick(View v) {

        }
    }
}
