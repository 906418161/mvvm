package cn.com.lightwightmvvm.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.time.Instant;

import cn.com.lightwightmvvm.activity.MainActivity;
import cn.com.lightwightmvvm.bean.LoginBean;
import cn.com.mvvm.base.util.StringUtils;
import cn.com.mvvm.base.viewmodel.BaseViewModel;

public class LoginViewModel extends BaseViewModel {

    private LoginBean loginBean;
    private Context context;
    private MutableLiveData<Boolean> jump;
    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginBean = new LoginBean();
        context = getApplication().getApplicationContext();
        jump = new MutableLiveData<>();
        jump.setValue(false);
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public LiveData<Boolean> jump(){
        return jump;
    }

    public void login(View view){
        if (StringUtils.isEmpty(loginBean.getAccount())){
            loginBean.setErr("账号不能为空");
            return;
        }
        if (StringUtils.isEmpty(loginBean.getPassword())){
            loginBean.setErr("密码不能为空");
            return;
        }
        if (loginBean.getAccount().equals("1")&&loginBean.getPassword().equals("1")){
            loginBean.setErr("");
            jump.setValue(true);
        }else {
            loginBean.setErr("账号或密码错误");
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        System.out.println("onCleared");
    }
}
