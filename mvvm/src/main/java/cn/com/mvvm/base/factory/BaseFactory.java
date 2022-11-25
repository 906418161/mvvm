package cn.com.mvvm.base.factory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import cn.com.mvvm.base.viewmodel.BaseViewModel;

public class BaseFactory implements ViewModelProvider.Factory {
    private Application application;
    public BaseFactory(Application application){
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new BaseViewModel(application);
    }
}
