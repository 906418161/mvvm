package cn.com.lightwightmvvm.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import cn.com.lightwightmvvm.R;
import cn.com.lightwightmvvm.bean.LoginBean;
import cn.com.lightwightmvvm.databinding.ActivityLoginBinding;
import cn.com.lightwightmvvm.viewmodel.LoginViewModel;
import cn.com.mvvm.base.activity.BaseActivity;
import cn.com.mvvm.base.util.StringUtils;

public class LoginActivity extends BaseActivity {


    @Override
    protected int getContentLayout() {
        return R.layout.activity_login;
    }
    @Override
    protected Class bindViewModel() {
        return LoginViewModel.class;
    }

    ActivityLoginBinding binding;
    LoginViewModel viewModel;
    LoginBean loginBean;
    @Override
    protected void initView() {
        binding = (ActivityLoginBinding) getBinding();
        viewModel = (LoginViewModel) getViewModel();
        loginBean = viewModel.getLoginBean();
        binding.setLogin(loginBean);
        binding.setViewmodel(viewModel);
    }

    @Override
    protected void initData() {
        viewModel.jump().observe(this,jump->{
            if (jump){
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
