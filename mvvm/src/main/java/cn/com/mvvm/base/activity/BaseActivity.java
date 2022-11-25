package cn.com.mvvm.base.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.gyf.immersionbar.ImmersionBar;

import cn.com.mvvm.base.factory.BaseFactory;
import cn.com.mvvm.base.viewmodel.BaseViewModel;

public abstract class BaseActivity<T> extends AppCompatActivity {
    ViewDataBinding binding;
    BaseFactory factory;
    BaseViewModel viewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,getContentLayout());
        factory = new BaseFactory(getApplication());
        if (bindViewModel()!=null)viewModel = (BaseViewModel) new ViewModelProvider(this).get(bindViewModel());
        if (isShowNavigationBarHeight()){
            setImmersionbar();
        }else {
            setSinkingType();
        }
        initView();
        initData();
    }

    protected abstract int getContentLayout();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract Class  bindViewModel();
    public T getBinding() {return (T) binding;}
    public T getViewModel(){return (T)viewModel;}


    /**
     * 设置沉侵式状态栏
     */
    @SuppressLint("ResourceType")
    protected void setImmersionbar(){
        ImmersionBar.with(this).statusBarDarkFont(isDarkFont())
                .statusBarColor(getStatusBarColor())
                .navigationBarColor(getNavigationBarColor()).init();  //必须调用方可沉浸式
    }

    /**
     * 设置标题栏文字颜色
     * @return
     */
    protected boolean isDarkFont(){
        return true;
    }

    /**
     * 设置沉侵式状态栏和虚拟按键
     * 如果不希望app的内容被遮挡，可以给view设置  android:fitsSystemWindows="true"
     */
    protected void setSinkingType(){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);       //设置沉浸式状态栏，在MIUI系统中，状态栏背景透明。原生系统中，状态栏背景半透明。
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);   //设置沉浸式虚拟键，在MIUI系统中，虚拟键背景透明。原生系统中，虚拟键背景半透明。
    }

    /**
     * 是否显示虚拟键盘高度
     * @return
     */
    protected boolean isShowNavigationBarHeight(){
        return true;
    }

    /**
     *状态栏背景色
     * @return
     */
    protected String getStatusBarColor(){
        return "#FFFFFF";
    }

    /**
     * 虚拟键盘背景色
     * @return
     */
    protected String getNavigationBarColor(){
        return "#FFFFFF";
    }

}
