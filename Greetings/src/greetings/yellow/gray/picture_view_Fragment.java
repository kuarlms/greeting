package greetings.yellow.gray;

import greetings.yellow.gray.MainActivity.home_screen_Fragment;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.Character.UnicodeBlock;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


import com.squareup.picasso.Callback;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import android.R.string;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class picture_view_Fragment extends Fragment {
	public String passed_sd_dir = gallery_Fragment.path_s; // Passing from SD-card
	//public String web_path = GalleryAdapter.passin_url; // from GalleryAdapter
	public String web_path_img = gallery_adpter_image.passin_url_img;
	public static int pos_plus;
	Boolean cha;
	public ImageView imageView_full;
	public String fileName;
	public ProgressBar p_bar;
	public String sve_location;
	
	
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	aPP_constants.onbackpress = 9;
	 View rootView = inflater.inflate(R.layout.view_pager_screen, container, false);
	   imageView_full = (ImageView)rootView.findViewById(R.id.imageView_fullscr);
	   
	   
	 
	   final ActionBar actionBar = getActivity().getActionBar();
	   //actionBar.hide();
	   actionBar.setDisplayHomeAsUpEnabled(true);
	/*   onOptionsItemSelected(MenuItem item){
		   
		   
		   
		   
	   }
	   
	   */
	   
	  
	   
	   
	   View decorView = getActivity().getWindow().getDecorView();
	   int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;	            
	   decorView.setSystemUiVisibility(uiOptions);
	   
	   
	 
	   SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	   Date now = new Date();
       fileName = format.format(now) + "_";
        sve_location = "/Pictures/Ygreeting/img"+fileName+".jpg";
       // Toast.makeText(getActivity(), sve_location, Toast.LENGTH_SHORT).show();
	   p_bar = (ProgressBar)rootView.findViewById(R.id.progressBar_loading_img);
	   
	
if (aPP_constants.Passing_img_from == 1) {
	
	// 1 = Home scr Gallery view
	picaso();
	
}
else if (aPP_constants.Passing_img_from == 3) {
	// 3 = Sd from gal
	sd_image();
}

	
else if (aPP_constants.Passing_img_from == 2){
	//2= grid view web
	picaso();
}


	 

	
 		
 		
 		
	final FrameLayout top_bar;	
	Button back,share,ful_scr;
    back = (Button)rootView.findViewById(R.id.btn_full_view_back);
    share = (Button)rootView.findViewById(R.id.btn_full_view_Share);
    ful_scr = (Button)rootView.findViewById(R.id.btn_full_screen);
    top_bar = (FrameLayout)rootView.findViewById(R.id.picture_view_topbar);
   
   ful_scr.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 
		   View decorView = getActivity().getWindow().getDecorView();
		   int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;	            
		   decorView.setSystemUiVisibility(uiOptions);
		   top_bar.setVisibility(View.GONE);
	}
}) ;
   
   back.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 actionBar.show();
		
		
		switch (aPP_constants.onbackpress) {
		case 9:
			if (aPP_constants.Passing_img_from == 1 ) {
				
			    	FragmentManager fragmentManagerd = getFragmentManager();
			        fragmentManagerd.beginTransaction()
			                .replace(R.id.container, new home_screen_Fragment())
			                .commit();
			        
				
				aPP_constants.onbackpress = 1;
				
			} 
			else {
				FragmentManager fragmentManagerd = getFragmentManager();
		        fragmentManagerd.beginTransaction()
		                .replace(R.id.container, new gallery_Fragment())
		                .commit();
		        

			}
			break;
		}
		
        actionBar.show();
		}
		
	
});
   
   
   
   
   // Sharing
   share.setOnClickListener(new OnClickListener() {	   
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
if (aPP_constants.Passing_img_from == 1) {
	
	Picasso.with(getActivity()).load(web_path_img).into(target);
	
	 Intent shareIntent = new Intent(Intent.ACTION_SEND);
	    Uri web_phototUri = Uri.parse(web_path_img);
	   

	    new File(web_phototUri.getPath());
	    shareIntent.setData(web_phototUri);
	    shareIntent.setType("image/png");
	    shareIntent.putExtra(Intent.EXTRA_STREAM, web_phototUri);
	    startActivity(Intent.createChooser(shareIntent, "Send Imgae Via ->"));
	   
	
	
} 

else 
// From SD card
{
	 Intent shareIntent = new Intent(Intent.ACTION_SEND);
	    Uri phototUri = Uri.parse(passed_sd_dir);
	    p_bar.setVisibility(View.GONE);

	    new File(phototUri.getPath());
	    shareIntent.setData(phototUri);
	    shareIntent.setType("image/png");
	    shareIntent.putExtra(Intent.EXTRA_STREAM, phototUri);
	    startActivity(Intent.createChooser(shareIntent, "Send Imgae Via ->"));
}
}
		   
	});
	return rootView;
}







		protected void my_gaL_fra() {
	// TODO Auto-generated method stub
			FragmentManager fragmentManagerd = getFragmentManager();
	        fragmentManagerd.beginTransaction()
	                .replace(R.id.container, new gallery_Fragment())
	                .commit();
}







		private Target target = new Target() {
         @Override
		 public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
		 new Thread(new Runnable() {
		 @Override
		 public void run() {          
		  
		  //Toast.makeText(getActivity(), sve_location, Toast.LENGTH_SHORT).show();
		 File file = new File(Environment.getExternalStorageDirectory().getPath() +sve_location);
		 try
		 {
		 file.createNewFile();
		 FileOutputStream ostream = new FileOutputStream(file);
		 bitmap.compress(CompressFormat.JPEG, 100, ostream);
		 //bitmap.compress(CompressFormat.WEBP, 100, ostream);
		 ostream.close();
		 }
		 catch (Exception e)
		 {
		 e.printStackTrace();
		 }
		  
		 }
		 }).start();
		 }
		 @Override
		 public void onBitmapFailed(Drawable errorDrawable) {
		 }
		  
		 @Override
		 public void onPrepareLoad(Drawable placeHolderDrawable) {
		 if (placeHolderDrawable != null) {
		 }
		 }
		 };








private void sd_image() {
	// TODO Auto-generated method stub
	Toast.makeText(getActivity(), passed_sd_dir, Toast.LENGTH_SHORT).show();
		p_bar.setVisibility(View.GONE);
		
	BitmapFactory.Options bfo = new BitmapFactory.Options();
	bfo.inSampleSize = 1;
	final Bitmap bitmap;
	bitmap = BitmapFactory.decodeFile(passed_sd_dir, bfo);
	imageView_full.setImageBitmap(bitmap);
}


//web loading 
private void picaso() {
	

	Picasso.with(this.getActivity()).load(web_path_img).fit().centerInside()
			.placeholder(R.drawable.ic_launcher).error(R.drawable.big_problem).into(imageView_full, new Callback() {
				public void onSuccess() {
					p_bar.setVisibility(View.GONE);
					Picasso.with(getActivity()).load(web_path_img).into(target);
					}
					public void onError() {
					p_bar.setVisibility(View.VISIBLE);
					}
					});
	 //
	

	
	gallery_adpter_image.passin_url_img= null;

}
}


