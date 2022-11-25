package cn.com.lightwightmvvm.viewmodel;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import cn.com.lightwightmvvm.fragment.ImageFragment;
import cn.com.mvvm.base.viewmodel.BaseViewModel;

public class MainViewModel extends BaseViewModel {

    private  ArrayList<Fragment> fragments;
    private  String [] titles = {"标题1"};
    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public  void addFragment(FragmentActivity activity, SlidingTabLayout view, ViewPager pager){
        fragments = new ArrayList<>();
        for (int i=0;i<titles.length;i++){
            fragments.add(ImageFragment.newInstance(i));
        }
        view.setViewPager(pager,titles,  activity,fragments);
    }

}
