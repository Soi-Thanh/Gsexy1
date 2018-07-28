package gsexy.hotgirls.gaixinh.gsexy.mCloud;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;

public class CloudinaryClient {


    public static String getRoundedCornerImage(String imageName){
        Cloudinary cloud = new Cloudinary(MyConfiguration.getMyConfigs());
        Transformation t = new Transformation();
        t.radius(10);

        String url = cloud.url().transformation(t).generate(imageName);

        return url;
    }
}
