package cn.com.mvvm.base.model;


import androidx.databinding.Bindable;

import cn.com.mvvm.BR;

public class VideoBean extends BaseBean {
    private String name;
    private String path;
    private long time;

    public VideoBean(){

    }

    public VideoBean(String name,String path,long time){
        setName(name);
        setPath(path);
        setTime(time);
    }
    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
    @Bindable
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        notifyPropertyChanged(BR.path);
    }
    @Bindable
    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
        notifyPropertyChanged(BR.time);
    }
}
