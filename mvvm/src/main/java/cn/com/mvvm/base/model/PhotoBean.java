package cn.com.mvvm.base.model;

import androidx.databinding.Bindable;

import cn.com.mvvm.BR;

public class PhotoBean extends BaseBean {
    private String url;
    private String name;
    private int width;
    private int height;

    public PhotoBean(){

    }

    public PhotoBean(String url,String name,int width,int height){
        setUrl(url);
        setName(name);
        setWidth(width);
        setHeight(height);
    }

    public void setUrl(String url) {
        this.url = url;
        notifyPropertyChanged(BR.url);
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public void setWidth(int width) {
        this.width = width;
        notifyPropertyChanged(BR.width);
    }

    public void setHeight(int height) {
        this.height = height;
        notifyPropertyChanged(BR.height);
    }

    @Bindable
    public String getUrl() {
        return url;
    }
    @Bindable
    public String getName() {
        return name;
    }
    @Bindable
    public int getWidth() {
        return width;
    }
    @Bindable
    public int getHeight() {
        return height;
    }
}
