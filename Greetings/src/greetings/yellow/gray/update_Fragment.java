package greetings.yellow.gray;



import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class update_Fragment extends Fragment {

	    public class web_client extends WebViewClient {
	    	
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);
			}
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				return super.shouldOverrideUrlLoading(view, url);
			}
			
			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
				ProgressBar progressbar  = (ProgressBar)getActivity().findViewById(R.id.progressBar_webview);
				progressbar.setVisibility(View.GONE);
			}

	}

		@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	    	
	        View rootView = inflater.inflate(R.layout.updates_fragment, container, false);
	        
	        ProgressBar progressbar  = (ProgressBar)rootView.findViewById(R.id.progressBar_webview);
	        ActionBar actionBar = getActivity().getActionBar();    		  		
	  		actionBar.setTitle("Yellow & Gray");
	  		actionBar.setSubtitle("UPDATES");
	  		actionBar.setHomeButtonEnabled(true);
	        WebView web_update = (WebView)rootView.findViewById(R.id.webView_updates);
	       web_update.setWebViewClient(new web_client()
	       
	    		   );
	      
	      
	        web_update.getSettings().setJavaScriptEnabled(true);
			String loadurl = "http://www.yellowandgray.com/";
	       // String loadurl ="http://yellowandgray.com/Greeting-Application/about.php";
			web_update.loadUrl(loadurl );
	      //  web_update.loadUrl("http://www.yellowandgray.com/");
	       
			
	     
	        
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
					Toast.makeText(getActivity(), "Loading gallery", Toast.LENGTH_SHORT).show();
					FragmentManager fragmentManagerd = getFragmentManager();
			        fragmentManagerd.beginTransaction()
			                .replace(R.id.container, new gallery_Fragment())
			                .commit();
				}
			});
	        
	     
	        
	        return rootView;
	    }
	

}
