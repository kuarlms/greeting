package greetings.yellow.gray;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
 
public class app_loading extends Activity {

public ProgressDialog mprogres;
JSONObject jsonObject;
JSONArray jsonArray;
public ProgressBar mprogres_ani;
// volley

RequestQueue mRequestQueue;
volley_adapter new_list;
public static ArrayList<image_urls> new_imgs_lst = new ArrayList<image_urls>(); // volley array model
LayoutInflater lif;
String tag = this.getClass().getSimpleName();


 //public static ArrayList<Web_urls> url_list; // sample
 public static ArrayList<image_urls> cards_url;
 @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading_scr);
		
		isExternalStoragePresent();
		 //Create Folder
		  File folder = new File(Environment.getExternalStorageDirectory().toString()+"/Pictures/YGreeting");
		  folder.mkdirs();

		  //Save the path as a string value
		  String extStorageDirectory = folder.toString();

		  //Create New file and name it Image2.PNG
		  File file = new File(extStorageDirectory, ".nomedia");
		  file.canExecute();
		  
		  
		  lif = LayoutInflater.from(this);
		 
		
		  

	
		
		
		// yG
		cards_url = new ArrayList<image_urls>();
		new Json_astask().execute("http://lemonandshadow.com/Greeting-Application/greet.php");
		
		//Button btn_quit = (Button)findViewById(R.id.btn_close_loading);
		ActionBar actionBar = getActionBar();
  		actionBar.hide();
  		
  		
		StringBuilder yg_builder = new StringBuilder();
		for(image_urls passing_url : cards_url){
			
			yg_builder.append(passing_url + "\n");
		}
		   
		   
		   
		 
  
		

		
	}

public class Json_astask extends AsyncTask<String, Void, Boolean> {

	 @Override
	 protected void onPreExecute() {
	// TODO Auto-generated method stub
	super.onPreExecute();
  mprogres_ani = (ProgressBar)findViewById(R.id.progressBar_loading_scr);
/*   mprogres = new ProgressDialog(app_loading.this);
   mprogres.setTitle("Welcome to Yellow & Gray Greeting's");
   mprogres.setMessage("Fetching Content ...");
   mprogres.setIndeterminate(false);
   mprogres.setCanceledOnTouchOutside(false);
   mprogres.setCancelable(false);
   mprogres.getWindow().setGravity(Gravity.TOP);
	mprogres.show();*/
  

  
	 }
	
	@Override
	protected Boolean doInBackground(String... urls) {
		// TODO Auto-generated method stub
		try {
			HttpGet httppost = new HttpGet(urls[0]);
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse res = httpclient.execute(httppost);
			int status = res.getStatusLine().getStatusCode();
			
			if(status == 200){
				HttpEntity entity = res.getEntity();
				String data = EntityUtils.toString(entity);
				JSONObject jsoob = new JSONObject(data);
				JSONArray jsonarr = jsoob.getJSONArray("path");
				
for (int i=0; i<jsonarr.length();i++){
			JSONObject object= jsonarr.getJSONObject(i);
			
			

			image_urls path = new image_urls();
			
			path.set_imgurl(object.getString("thumb"));
			path.set_title(object.getString("title"));
			path.set_cate(object.getString("cate"));
			
			
			cards_url.add(path);
			
		// second json	
			
}		
			}
			
			return true;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	
	protected void onPostExecute(Boolean result) {
		//mprogres.cancel();
		mprogres_ani.setVisibility(View.GONE);
		//adapter.notifyDataSetChanged();
		if(result == false)
			Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), MainActivity.class);
		startActivity(intent);
		app_loading.this.finish();
	


	
	
	}

}




private boolean isExternalStoragePresent() {

    boolean mExternalStorageAvailable = false;
    boolean mExternalStorageWriteable = false;
    String state = Environment.getExternalStorageState();

    if (Environment.MEDIA_MOUNTED.equals(state)) {
        // We can read and write the media
        mExternalStorageAvailable = mExternalStorageWriteable = true;
    } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
        // We can only read the media
        mExternalStorageAvailable = true;
        mExternalStorageWriteable = false;
    } else {
        // Something else is wrong. It may be one of many other states, but
        // all we need
        // to know is we can neither read nor write
        mExternalStorageAvailable = mExternalStorageWriteable = false;
    }
    if (!((mExternalStorageAvailable) && (mExternalStorageWriteable))) {
        Toast.makeText(getApplicationContext(), "SD card not present", Toast.LENGTH_LONG)
                .show();

    }
    return (mExternalStorageAvailable) && (mExternalStorageWriteable);
}
	
/*private void loadData(String searchText) {
	//specify endpoint and build the restadapter instance
	        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://yellowandgray.com/Greeting-Application/greet.php")
	                .build();
	//Now use restadapter to create an instance of your interface
	        rest_parsing_urls searchService=restAdapter.create(FeedlySuggestionsService.class);
	//populate the request parameters
	        HashMap queryMap=new HashMap();
	        queryMap.put("query",searchText);
	//implement the Callback<T> interface for retrieving the response
	        searchService.searchFeeds(queryMap, new Callback<FeedlyResponse>() {
	            @Override
	            public void success(FeedlyResponse feedlyResponse, Response response) {
	     //convert list to cursor. more on this later
	             MatrixCursor matrixCursor= convertToCursor(feedlyResponse.getResults());
	             mSearchViewAdapter.changeCursor(matrixCursor);
	            }
	 
	            @Override
	            public void failure(RetrofitError error) {
	            Log.e(tag, error.toString());
	            }
	        });
	    }
*/




	}

 
	
