package greetings.yellow.gray;





import java.io.File;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.Toast;

public class  gallery_Fragment extends Fragment {
	static String path_s;
	
	AsyncTaskLoadFiles myAsyncTaskLoadFiles;
	GridView gridview;
	

	
	static class ViewHolder {
        ImageView image;
       static int position;
       
    }
	
	
	public static Bitmap decodeSampledBitmapFromUri(String path, int reqWidth,
			int reqHeight) {

		Bitmap bm = null;
		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		bm = BitmapFactory.decodeFile(path, options);

		return bm;
	}
	public static int calculateInSampleSize(

			BitmapFactory.Options options, int reqWidth, int reqHeight) {
				// Raw height and width of image
				final int height = options.outHeight;
				final int width = options.outWidth;
				int inSampleSize = 1;

				if (height > reqHeight || width > reqWidth) {
					if (width > height) {
						inSampleSize = Math.round((float) height
								/ (float) reqHeight);
					} else {
						inSampleSize = Math.round((float) width / (float) reqWidth);
					}
				}

				return inSampleSize;
			}
			

	
	
	
	
	
	
	
	
	
	public static class AsyncTaskLoadFiles extends AsyncTask<Void, String, Void> {
		
		File targetDirector;
		

		public AsyncTaskLoadFiles(ImageAdapter adapter) {
			myTaskAdapter = adapter;
		}

		@Override
		protected void onPreExecute() {
			String ExternalStorageDirectoryPath = Environment
					.getExternalStorageDirectory().getAbsolutePath();

			String targetPath = ExternalStorageDirectoryPath + "/Pictures/Ygreeting";
			targetDirector = new File(targetPath);
			myTaskAdapter.clear();
			
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			
			File[] files = targetDirector.listFiles();
			Arrays.sort(files);
			for (File file : files) {
				publishProgress(file.getAbsolutePath());
				if (isCancelled()) break;
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			myTaskAdapter.add(values[0]);
			super.onProgressUpdate(values);
		}

		@Override
		protected void onPostExecute(Void result) {
			myTaskAdapter.notifyDataSetChanged();
			super.onPostExecute(result);
		}

	}

	public static class ImageAdapter extends BaseAdapter {

		private Context mContext;
		ArrayList<String> itemList = new ArrayList<String>();

		public ImageAdapter(Context c) {
			mContext = c;
		}

		void add(String path) {
			itemList.add(path);
		}
		
		void clear() {
			itemList.clear();
		}
		
		void remove(int index){
			itemList.remove(index);
		}

		@Override
		public int getCount() {
			return itemList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return itemList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		//getView load bitmap in AsyncTask
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder;

			ImageView imageView;
			if (convertView == null) { // if it's not recycled, initialize some
										// attributes
				imageView = new ImageView(mContext);
				
				imageView.setScaleType(ImageView.ScaleType.FIT_XY);
				
				//holder.image.setScaleType(ScaleType.FIT_CENTER);
				convertView = imageView;
				
				holder = new ViewHolder();
				holder.image = imageView;
				holder.position = position;
				convertView.setTag(holder);
			} else {
				//imageView = (ImageView) convertView;
				holder = (ViewHolder) convertView.getTag();
				((ImageView)convertView).setImageBitmap(null);
			}
			
			//Bitmap bm = decodeSampledBitmapFromUri(itemList.get(position), 220, 220);
			// Using an AsyncTask to load the slow images in a background thread
			new AsyncTask<ViewHolder, Void, Bitmap>() {
			    private ViewHolder v;

			    @Override
			    protected Bitmap doInBackground(ViewHolder... params) {
			        v = params[0];
			        Bitmap bm = decodeSampledBitmapFromUri(itemList.get(position), 220, 220);
			        return bm;
			    }

			    @Override
			    protected void onPostExecute(Bitmap result) {
			        super.onPostExecute(result);
			        //Not work for me!
			        /*
			        if (v.position == position) {
			            // If this item hasn't been recycled already, 
			        	// show the image
			            v.image.setImageBitmap(result);
			        }
			        */

			        v.image.setImageBitmap(result);

			    }
			}.execute(holder);

			//imageView.setImageBitmap(bm);
			//return imageView;
			return convertView;
		}

		
		
		

	}

	ImageAdapter myImageAdapter;
	static ImageAdapter myTaskAdapter;
	
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
        View rootView = inflater.inflate(R.layout.my_gallery_fragment, container, false);
          gridview =(GridView)rootView.findViewById(R.id.gridView_my_gallery);
        ActionBar actionBar = getActivity().getActionBar();    		  		
  		actionBar.setTitle("Yellow & Gray");
  		actionBar.setSubtitle("MY GALLERY");
  		actionBar.setHomeButtonEnabled(true);
        myImageAdapter = new ImageAdapter(getActivity());
        gridview.setAdapter(myImageAdapter);
        myAsyncTaskLoadFiles = new AsyncTaskLoadFiles(myImageAdapter);
		myAsyncTaskLoadFiles.execute();
		aPP_constants.current_scr = 3;
		gridview.setOnItemClickListener(myOnItemClickListener);
		gridview.setOnItemLongClickListener(onlongclickListner);
		
		
		
		
		
		
		
		
		
		
		
		
        Button btn_home = (Button)rootView.findViewById(R.id.button1);
        btn_home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "Home", Toast.LENGTH_SHORT).show();
				FragmentManager fragmentManagerd = getFragmentManager();
		        fragmentManagerd.beginTransaction()
		                .replace(R.id.container, new MainActivity.home_screen_Fragment())
		                .commit();
			}
		});
        
        Button btn_gallery = (Button)rootView.findViewById(R.id.button2);
        btn_gallery.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "Reloading Gallery", Toast.LENGTH_SHORT).show();
				//Cancel the previous running task, if exist.
				myAsyncTaskLoadFiles.cancel(true);
				
				//new another ImageAdapter, to prevent the adapter have
				//mixed files
				myImageAdapter = new ImageAdapter(getActivity());
				gridview.setAdapter(myImageAdapter);
				myAsyncTaskLoadFiles = new AsyncTaskLoadFiles(myImageAdapter);
				myAsyncTaskLoadFiles.execute();
			}
		});
        
        Button btn_updates = (Button)rootView.findViewById(R.id.button3);
        btn_updates.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "Loading updates", Toast.LENGTH_SHORT).show();
				FragmentManager fragmentManagerd = getFragmentManager();
		        fragmentManagerd.beginTransaction()
		                .replace(R.id.container, new update_Fragment())
		                .commit();
			}
		});
        
        return rootView;
    }
    
    
	
	OnItemClickListener myOnItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int positionx,
				long id) {
			
			
			path_s =(String) parent.getItemAtPosition(positionx);
			//Toast.makeText(getActivity(), "Gal"+path_s, Toast.LENGTH_SHORT).show();
					
			
			//myImageAdapter.remove(position);
			//myImageAdapter.notifyDataSetChanged();*/
			
			aPP_constants.Passing_img_from = 3; // 3 = Galleryfragment
			FragmentManager fragmentManager = getFragmentManager();
	        fragmentManager.beginTransaction()
	                .replace(R.id.container, new picture_view_Fragment())
	                .commit();
			
			
			

		}
	};
	
	
	OnItemLongClickListener onlongclickListner = new OnItemLongClickListener() {

		@Override
		public boolean onItemLongClick(final AdapterView<?> parent, View view,
				final int position, long id) {
			
			AlertDialog ad =new AlertDialog.Builder(getActivity()).setIcon(android.R.drawable.ic_dialog_alert)
		     .show();new AlertDialog.Builder(getActivity())
		     .setTitle("Delete Greeting")
		     .setMessage("Are you sure you want to delete this Greeting ?")
		     .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
		         public void onClick(DialogInterface dialog, int which) { 
		             // continue with delete
		        	 String del_path =(String) parent.getItemAtPosition(position);
		 			
		 			boolean deleted;
		 			File file = new File(del_path);
		 		    if (file.exists()) {
		 		     deleted = file.delete();
		 		    }
		 		    
		 			if (deleted = true) {
		 				myImageAdapter.notifyDataSetChanged();
		 				gridview.invalidateViews();
		 			}
		         }
		      })
		     .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
		         public void onClick(DialogInterface dialog, int which) { 
		             // do nothing
		         }
		      })
		     .setIcon(android.R.drawable.ic_dialog_alert)
		      .show();
			
			
			//sharing on longpress
			/*String share_gal_view = (String)parent.getItemAtPosition(position);
			Intent shareIntent = new Intent(Intent.ACTION_SEND);
		    Uri phototUri = Uri.parse(share_gal_view);

		   
		    shareIntent.setData(phototUri);
		    shareIntent.setType("image/png");
		    shareIntent.putExtra(Intent.EXTRA_STREAM, phototUri);
		    startActivity(Intent.createChooser(shareIntent, "Send Imgae Via ->"));*/
			return true;
		}
	};
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    
}

