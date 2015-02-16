package greetings.yellow.gray;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import android.app.Application;
import android.text.TextUtils;

public  class volley_class extends Application {
	
	public static final String TAG = volley_class.class.getSimpleName();
	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;
	public static volley_class mInstace;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mInstace = this;
	}
	 
public static synchronized volley_class getInstance(){
	return mInstace;
}
public RequestQueue getRequestQueue(){
	
	if (mRequestQueue == null){
		try {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	return mRequestQueue;
	
}

public ImageLoader getImageLoader(){
	getRequestQueue();
	if (mImageLoader == null){
		mImageLoader = new ImageLoader(this.mRequestQueue, new volley_image_cache());
	}
	
	
	
	return this.mImageLoader;
	
}
public <T> void addToReque(Request<T> req,String tag){
	req.setTag(TextUtils.isEmpty(tag)? TAG : tag);
	getRequestQueue().add(req);
	
}
public <T> void addToReque(Request<T> req){
req.setTag(TAG);
getRequestQueue().add(req);
}
public void cancelpendingRequest(Object tag){
	if(mRequestQueue != null){
		mRequestQueue.cancelAll(tag);
	}
}

}
