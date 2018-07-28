package gsexy.hotgirls.gaixinh.gsexy.mData;

import android.os.Parcel;
import android.os.Parcelable;

public class TVShowIH implements Parcelable {

    String name;
    String url;

    public TVShowIH() {
    }

    protected TVShowIH(Parcel in) {
        name = in.readString();
        url = in.readString();
    }

    public static final Creator<TVShowIH> CREATOR = new Creator<TVShowIH>() {
        @Override
        public TVShowIH createFromParcel(Parcel source) {
            return new TVShowIH(source);
        }

        @Override
        public TVShowIH[] newArray(int size) {
            return new TVShowIH[0];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        dest.writeString(name);
        dest.writeString(url);
    }
}

