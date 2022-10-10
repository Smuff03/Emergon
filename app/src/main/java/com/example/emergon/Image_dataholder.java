package com.example.emergon;

import android.net.Uri;

public class Image_dataholder {

    String name, uri;

    public Image_dataholder(String Name,String Uri){
        name = Name;
        uri = Uri;

    }
    public String getName() {
        return name;
    }

    public void setName(String Name) {
        name = Name;
    }
    public String getUri() {
        return uri;
    }

    public void setUri(String Uri) {
        uri = Uri;
    }
}
