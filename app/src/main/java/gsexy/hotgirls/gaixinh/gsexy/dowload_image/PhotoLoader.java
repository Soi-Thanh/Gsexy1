package gsexy.hotgirls.gaixinh.gsexy.dowload_image;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;

public class PhotoLoader implements com.squareup.picasso.Target {
	private final String name;
	private ImageView imageView;
	Context context;
	public PhotoLoader(String name, ImageView imageView) {
		this.name = name;
		this.imageView = imageView;
	}
	
	
	@Override
	public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
		File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + name);
		try {
			file.createNewFile();
			FileOutputStream ostream = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 75, ostream);
			Log.d("Load_image","Load_image");
			ostream.close();
			imageView.setImageBitmap(bitmap);



		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void onBitmapFailed(Drawable errorDrawable) {
	
	}
	
	@Override
	public void onPrepareLoad(Drawable placeHolderDrawable) {

	}
	
	
}
