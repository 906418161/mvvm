package cn.com.mvvm.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import cn.com.mvvm.base.factory.BaseFactory;
import cn.com.mvvm.base.viewmodel.BaseViewModel;

public abstract class BaseFragment<T> extends Fragment {
    protected View contentView;
    ViewDataBinding binding;
    BaseFactory factory;
    BaseViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(getContentLayout(),container,false);
        binding = DataBindingUtil.bind(contentView);
        factory = new BaseFactory(getActivity().getApplication());
        if (bindViewModel()!=null)viewModel = (BaseViewModel) new ViewModelProvider(this).get(bindViewModel());
        initView();
        initData();
        return contentView;
    }

    protected abstract int getContentLayout();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract Class  bindViewModel();
    public T getBinding() {return (T) binding;}
    public T getViewModel(){return (T)viewModel;}
}
