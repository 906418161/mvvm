package cn.com.lightwightmvvm.activity;

import cn.com.lightwightmvvm.R;
import cn.com.lightwightmvvm.databinding.ActivityMainBinding;
import cn.com.lightwightmvvm.viewmodel.MainViewModel;
import cn.com.mvvm.base.activity.BaseActivity;

public class MainActivity extends BaseActivity {
    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected Class bindViewModel() {
        return MainViewModel.class;
    }

    ActivityMainBinding binding;
    MainViewModel viewModel;

    @Override
    protected void initView() {
        binding = (ActivityMainBinding) getBinding();
        viewModel = (MainViewModel) getViewModel();
        viewModel.addFragment(this,binding.slidingTabLayout,binding.viewpager);
    }

    @Override
    protected void initData() {

    }


}
