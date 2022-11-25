package cn.com.lightwightmvvm.bean;

import androidx.databinding.Bindable;

import cn.com.lightwightmvvm.BR;
import cn.com.mvvm.base.model.BaseBean;

public class LoginBean extends BaseBean {
    private String account;
    private String password;
    private String err;

    public void setAccount(String account) {
        this.account = account;
        notifyPropertyChanged(BR.account);
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public void setErr(String err) {
        this.err = err;
        notifyPropertyChanged(BR.err);
    }

    @Bindable
    public String getAccount() {
        return account;
    }
    @Bindable
    public String getPassword() {
        return password;
    }
    @Bindable
    public String getErr() {
        return err;
    }
}
