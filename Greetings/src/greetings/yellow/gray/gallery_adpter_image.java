package greetings.yellow.gray;




import java.io.InputStream;
import java.util.ArrayList;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class gallery_adpter_image extends ArrayAdapter<image_urls> {
	ArrayList<image_urls> urls;
	LayoutInflater vi;
	int Resource;
	ViewHolder holder;
	public static String passin_url_img;
	
	Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/univers-condensed.otf");
    
	

	public gallery_adpter_image(Context context, int res, ArrayList<image_urls> obj ) {
		super(context, res, obj);
		// TODO Auto-generated constructor stub
		vi =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Resource = res;
		urls=obj;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		// convert view = design
		View v = convertView;
		if (v == null) {
			holder = new ViewHolder();
			v = vi.inflate(Resource, null);
			holder.imageview = (ImageView) v.findViewById(R.id.ivImage);
			holder.tvName = (TextView) v.findViewById(R.id.tvName);
			holder.pbar = (ProgressBar)v.findViewById(R.id.progressBar_grid_row);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}

		new DownloadImageTask(holder.imageview).execute(urls.get(position).get_imgurl()); //flag url
		
		holder.tvName.setText(urls.get(position).get_title());
		holder.tvName.setTypeface(font);
		
		/*
		Picasso.with(getContext()) //
	    .load().getItem(position)) //
	    .placeholder(R.drawable.loding_scr_img) //
	    .error(R.drawable.loding_scr_img) //
	    .fit()
	    .centerCrop()//
	    .into(holder.imageview);*/
		
		/* Picasso.with(mContext)
         .load(CATEGORY_LOGO_URL
                 + resultp.get(CategoryActivity.TAG_CATEGORIES_LOGO)) // Photo URL
         .placeholder(R.drawable.placeholder) // This image will be displayed while photo URL is loading
         .error(R.drawable.error) // if error shows up during downloading
         .fit().centerCrop() // settings
         .into(category_logo); */
		holder.tvName.setVisibility(View.GONE);
		
		return v;

	}

	public static class ViewHolder {
		public ImageView imageview;
		public TextView tvName;
		public ProgressBar pbar;
		

	}

	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			passin_url_img  = urldisplay;
			
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			//holder.pbar.setVisibility(View.GONE);
			bmImage.setImageBitmap(result);
			if (result == null) {
				holder.pbar.setVisibility(View.GONE);
			}
			
		}

	}

}
