package com.geaosu.wanandroid.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class HomeBannerModel implements Parcelable {

    /**
     * data : [{"desc":"1024 程序员节，感谢有你们，福利送上！","id":19,"imagePath":"http://www.wanandroid.com/blogimgs/f9ada32d-3b57-45dc-9a25-ada25113ef99.jpeg","isVisible":1,"order":0,"title":"1024 程序员节，感谢有你们，福利送上！","type":0,"url":"http://www.wanandroid.com/blog/show/2375"},{"desc":"","id":18,"imagePath":"http://www.wanandroid.com/blogimgs/00f83f1d-3c50-439f-b705-54a49fc3d90d.jpg","isVisible":1,"order":1,"title":"公众号文章列表强势上线","type":0,"url":"http://www.wanandroid.com/wxarticle/list/408/1"},{"desc":"一起来做个App吧","id":10,"imagePath":"http://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png","isVisible":1,"order":3,"title":"一起来做个App吧","type":0,"url":"http://www.wanandroid.com/blog/show/2"},{"desc":"","id":4,"imagePath":"http://www.wanandroid.com/blogimgs/ab17e8f9-6b79-450b-8079-0f2287eb6f0f.png","isVisible":1,"order":0,"title":"看看别人的面经，搞定面试~","type":1,"url":"http://www.wanandroid.com/article/list/0?cid=73"},{"desc":"","id":3,"imagePath":"http://www.wanandroid.com/blogimgs/fb0ea461-e00a-482b-814f-4faca5761427.png","isVisible":1,"order":1,"title":"兄弟，要不要挑个项目学习下?","type":1,"url":"http://www.wanandroid.com/project"},{"desc":"","id":6,"imagePath":"http://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png","isVisible":1,"order":1,"title":"我们新增了一个常用导航Tab~","type":1,"url":"http://www.wanandroid.com/navi"},{"desc":"","id":2,"imagePath":"http://www.wanandroid.com/blogimgs/90cf8c40-9489-4f9d-8936-02c9ebae31f0.png","isVisible":1,"order":2,"title":"JSON工具","type":1,"url":"http://www.wanandroid.com/tools/bejson"},{"desc":"","id":5,"imagePath":"http://www.wanandroid.com/blogimgs/acc23063-1884-4925-bdf8-0b0364a7243e.png","isVisible":1,"order":3,"title":"微信文章合集","type":1,"url":"http://www.wanandroid.com/blog/show/6"}]
     * errorCode : 0
     * errorMsg :
     */

    private int errorCode;
    private String errorMsg;
    private List<DataBean> data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * desc : 1024 程序员节，感谢有你们，福利送上！
         * id : 19
         * imagePath : http://www.wanandroid.com/blogimgs/f9ada32d-3b57-45dc-9a25-ada25113ef99.jpeg
         * isVisible : 1
         * order : 0
         * title : 1024 程序员节，感谢有你们，福利送上！
         * type : 0
         * url : http://www.wanandroid.com/blog/show/2375
         */

        private String desc;
        private int id;
        private String imagePath;
        private int isVisible;
        private int order;
        private String title;
        private int type;
        private String url;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public int getIsVisible() {
            return isVisible;
        }

        public void setIsVisible(int isVisible) {
            this.isVisible = isVisible;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.desc);
            dest.writeInt(this.id);
            dest.writeString(this.imagePath);
            dest.writeInt(this.isVisible);
            dest.writeInt(this.order);
            dest.writeString(this.title);
            dest.writeInt(this.type);
            dest.writeString(this.url);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.desc = in.readString();
            this.id = in.readInt();
            this.imagePath = in.readString();
            this.isVisible = in.readInt();
            this.order = in.readInt();
            this.title = in.readString();
            this.type = in.readInt();
            this.url = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.errorCode);
        dest.writeString(this.errorMsg);
        dest.writeList(this.data);
    }

    public HomeBannerModel() {
    }

    protected HomeBannerModel(Parcel in) {
        this.errorCode = in.readInt();
        this.errorMsg = in.readString();
        this.data = new ArrayList<DataBean>();
        in.readList(this.data, DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<HomeBannerModel> CREATOR = new Parcelable.Creator<HomeBannerModel>() {
        @Override
        public HomeBannerModel createFromParcel(Parcel source) {
            return new HomeBannerModel(source);
        }

        @Override
        public HomeBannerModel[] newArray(int size) {
            return new HomeBannerModel[size];
        }
    };
}
