package cn.com.mvvm.base.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder> {

    public List<T> baseList;

    public BaseAdapter(List<T> list){
        this.baseList = list;
    }

    protected abstract int getContentLayout();
    protected abstract void setData(BaseViewHolder holder, int position);

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),getContentLayout(),parent,false);
        return new BaseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseAdapter.BaseViewHolder holder, int position) {
        setData(holder,position);
    }

    @Override
    public int getItemCount() {
        return baseList.size();
    }

    public class BaseViewHolder<T> extends RecyclerView.ViewHolder{
        public T binding;
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public BaseViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (T) binding;
            initData();
        }

        public void initData(){

        }

        public T getBinding() {
            return (T) binding;
        }
    }

}
