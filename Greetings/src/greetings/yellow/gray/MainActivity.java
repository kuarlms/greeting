package greetings.yellow.gray;



import com.jess.ui.TwoWayGridView;
import com.squareup.picasso.Picasso;
import com.jess.ui.TwoWayAdapterView;
import com.jess.ui.TwoWayAdapterView.OnItemClickListener;
import com.jess.ui.TwoWayGridView;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.R.color;
import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.sax.RootElement;
import android.support.v4.widget.DrawerLayout;
import android.widget.Adapter;
import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
	
	
	 
	




public static int actionbar;
 public boolean doubleBackToExitPressedOnce;
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
       actionbar_customization();
       isNetworkAvailable(h,2000);
      

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
       mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }
    
    public static void isNetworkAvailable(final Handler handler, final int timeout) {
        // ask fo message '0' (not connected) or '1' (connected) on 'handler'
        // the answer must be send before before within the 'timeout' (in milliseconds)

        new Thread() {
            private boolean responded = false;   
            @Override
            public void run() { 
                // set 'responded' to TRUE if is able to connect with google mobile (responds fast) 
                new Thread() {      
                    @Override
                    public void run() {
                        HttpGet requestForTest = new HttpGet("http://m.google.com");
                        try {
                            new DefaultHttpClient().execute(requestForTest); // can last...
                            responded = true;
                        } 
                        catch (Exception e) {
                        }
                    } 
                }.start();

                try {
                    int waited = 0;
                    while(!responded && (waited < timeout)) {
                        sleep(100);
                        if(!responded ) { 
                            waited += 100;
                        }
                    }
                } 
                catch(InterruptedException e) {} // do nothing 
                finally { 
                    if (!responded) { handler.sendEmptyMessage(0); } 
                    else { handler.sendEmptyMessage(1); }
                }
            }
        }.start();
    }
    
    
    Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what != 1) { // code if not connected
            	Toast.makeText(getApplicationContext(), "Please check your internet connectivity.", Toast.LENGTH_LONG).show();

            } else { // code if connected

            }   
        }
    };
    
    private void actionbar_customization() {
  		// TODO Auto-generated method stub
  		ActionBar actionBar = getActionBar();
  		//actionBar.hide();
  		// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
  		//actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#a52a2a")));
  		
  		actionBar.setSubtitle("Home");
  		actionBar.setHomeButtonEnabled(true);
  		
  	}
// navigation drawer items
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
    	//Toast.makeText(getApplicationContext(), "Pos "+position, Toast.LENGTH_LONG).show();
    	actionbar = position;
    	switch (position) {
		case 0:
			homefrag_mtd();
			
			break;
			case 1:
				 categoriefrag_mtd();
				 
				break;
				
			case 2:
				 galleryfrag_mtd();
				 
				break;

		default:
			homefrag_mtd();
			 
			break;
		}
       
    }

    public void categoriefrag_mtd() {
		// TODO Auto-generated method stub
    	FragmentManager fragmentManager1 = getFragmentManager();
        fragmentManager1.beginTransaction()
                .replace(R.id.container, new category_Fragment())
                .commit();
      actionbar_customization_categories();  
        
        Toast.makeText(getApplicationContext(), "ac "+actionbar, Toast.LENGTH_LONG).show();
	}

	public void galleryfrag_mtd() {
		// TODO Auto-generated method stub
    	FragmentManager fragmentManager2 = getFragmentManager();
        fragmentManager2.beginTransaction()
                .replace(R.id.container, new gallery_Fragment())
                .commit();
        actionbar_customization_my_gallery();
	}

	private void actionbar_customization_my_gallery() {
		// TODO Auto-generated method stub
		ActionBar actionBar = getActionBar();
  		//actionBar.hide();
  		// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
  		//actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#a52a2a")));
  		actionBar.setTitle("Yellow & Gray");
  		actionBar.setSubtitle("MY GALLERY");
  		actionBar.setHomeButtonEnabled(true);
	}

	public void homefrag_mtd() {
		// TODO Auto-generated method stub
    	FragmentManager fragmentManagerd = getFragmentManager();
        fragmentManagerd.beginTransaction()
                .replace(R.id.container, new home_screen_Fragment())
                .commit();
        actionbar_customization_home();
	}

	private void actionbar_customization_home() {
		// TODO Auto-generated method stub
		ActionBar actionBar = getActionBar();
  		actionBar.setTitle("Yellow & Gray");
  		actionBar.setSubtitle("HOME");
  		actionBar.setHomeButtonEnabled(true);
	}

	public void actionbar_customization_categories() {
		// TODO Auto-generated method stub
    	ActionBar actionBar = getActionBar();
  		//actionBar.hide();
  		// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
  		//actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#a52a2a")));
  		actionBar.setTitle("Yellow & Gray");
  		actionBar.setSubtitle("Categories");
  		actionBar.setHomeButtonEnabled(true);
	}

	public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
       // actionBar.setTitle(mTitle);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#86ad42")));
       
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	Toast.makeText(getApplicationContext(), "View Categories ", Toast.LENGTH_LONG).show();	
        	categoriefrag_mtd();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

   
    public static class  home_screen_Fragment extends Fragment {
    	

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	

            View rootView = inflater.inflate(R.layout.home_screen_frag, container, false);
            
            Typeface myTypeface = null;
			try {
				myTypeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/univers-condensed.otf");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(getActivity(), "font"+e, Toast.LENGTH_LONG).show();
			}
        // loading category with images using square picasso    
		ImageView img_cat_1 = (ImageView)rootView.findViewById(R.id.imageView_cat_1);
		
		 img_cat_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentManager fragmentManager = getFragmentManager();
		        fragmentManager.beginTransaction()
		                .replace(R.id.container, new category_Fragment())
		                .commit();
			}
		});
         
		 ImageView img_cat_2 = (ImageView)rootView.findViewById(R.id.imageView_cat_2);
		
          
		 ImageView img_cat_3 = (ImageView)rootView.findViewById(R.id.imageView_cat_3);
		 
		 

		 
		// Gallery Horizontall
		 TwoWayGridView ga_pop = (TwoWayGridView)rootView.findViewById(R.id.gallery_popular);
		 TwoWayGridView ga_new = (TwoWayGridView)rootView.findViewById(R.id.gallery_new);
		/* ga_pop.setAdapter(new GalleryAdapter(getActivity()));
		 ga_new.setAdapter(new GalleryAdapter(getActivity()));
		*/
		 gallery_adpter_image adapter;
		 volley_adapter v_adp;
		//adapter = new CustomAdapter(this, list);
		// v_adp = new volley_adapter(getActivity(),R.layout.new_gallery_vw_row, app_loading.new_imgs);
		 adapter = new gallery_adpter_image(getActivity(), R.layout.image_row, app_loading.cards_url);
		// cards_adapter = new gallery_adpter_image(getActivity(), R.layout.image_row, app_loading.cards_url);
		 ga_pop.setAdapter(adapter);
	//	ga_new.setAdapter(v_adp);
		 
		 
		 
		 ga_pop.setOnItemClickListener(new OnItemClickListener() {

			

			@Override
			public void onItemClick(TwoWayAdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				aPP_constants.Passing_img_from = 1;

				FragmentManager fragmentManager = getFragmentManager();
		        fragmentManager.beginTransaction()
		                .replace(R.id.container, new picture_view_Fragment())
		                .commit();
			}
		});
		 
		 
		 ga_new.setOnItemClickListener(new OnItemClickListener() {

			

				@Override
				public void onItemClick(TwoWayAdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					
				}
			});
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
                
            // text view fonts and manupulation    
            TextView tv_cat_se = (TextView)rootView.findViewById(R.id.textView_categories);    
            TextView tv_pop_se = (TextView)rootView.findViewById(R.id.TextView_popular);
            TextView tv_new_se = (TextView)rootView.findViewById(R.id.TextView_new);
            TextView tv_cat = (TextView)rootView.findViewById(R.id.textView_cat);
            TextView tv_pop = (TextView)rootView.findViewById(R.id.TextView_pop);
            TextView tv_new = (TextView)rootView.findViewById(R.id.TextView_new_title);
            
           tv_cat.setTypeface(myTypeface);
           tv_pop.setTypeface(myTypeface);
           tv_new.setTypeface(myTypeface);
           tv_cat_se.setTypeface(myTypeface);
           tv_pop_se.setTypeface(myTypeface);
           tv_new_se.setTypeface(myTypeface);
            
           tv_cat_se.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "cate", Toast.LENGTH_LONG).show();
				FragmentManager fragmentManagerd = getFragmentManager();
		        fragmentManagerd.beginTransaction()
		                .replace(R.id.container, new category_Fragment())
		                .commit();
			}
		}); 
           
           
           tv_pop_se.setOnClickListener(new OnClickListener() {
   			
   			@Override
   			public void onClick(View v) {
   				// TODO Auto-generated method stub
   				Toast.makeText(getActivity(), "popular imgs", Toast.LENGTH_LONG).show();
   				FragmentManager fragmentManagerd = getFragmentManager();
   		        fragmentManagerd.beginTransaction()
   		                .replace(R.id.container, new category_Fragment())
   		                .commit();
   		        
   			}
   		}); 
           
           
           tv_new_se.setOnClickListener(new OnClickListener() {
   			
   			@Override
   			public void onClick(View v) {
   				// TODO Auto-generated method stub
   				Toast.makeText(getActivity(), "new imgs", Toast.LENGTH_LONG).show();
   				FragmentManager fragmentManagerd = getFragmentManager();
   		        fragmentManagerd.beginTransaction()
   		                .replace(R.id.container, new category_Fragment())
   		                .commit();
   		        
   			}
   		}); 
            
            Button btn_exit = (Button)rootView.findViewById(R.id.button1);
            
            btn_exit.setOnClickListener(new OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				// TODO Auto-generated method stub
    				getActivity().finish();
    				Toast.makeText(getActivity(), "Quitting", Toast.LENGTH_SHORT).show();
    				
    			}
    		});
            
            Button btn_gallery = (Button)rootView.findViewById(R.id.button2);
            btn_gallery.setOnClickListener(new OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				// TODO Auto-generated method stub
    				Toast.makeText(getActivity(), "Loading Gallery", Toast.LENGTH_SHORT).show();
    				FragmentManager fragmentManagerd = getFragmentManager();
    		        fragmentManagerd.beginTransaction()
    		                .replace(R.id.container, new gallery_Fragment())
    		                .commit();
    				
    			}
    		});
            
            Button btn_updates = (Button)rootView.findViewById(R.id.button3);
            btn_updates.setOnClickListener(new OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				// TODO Auto-generated method stub
    				Toast.makeText(getActivity(), "Loading Updates", Toast.LENGTH_SHORT).show();
    				FragmentManager fragmentManagerd = getFragmentManager();
    		        fragmentManagerd.beginTransaction()
    		                .replace(R.id.container, new update_Fragment())
    		                .commit();
    		        
    			}
    		});
            
            return rootView;
        }
    }
    
    @Override
    	public void onBackPressed() {
    	
    	ActionBar abar = getActionBar();
    	abar.show();
    	
    	
    	switch (aPP_constants.onbackpress) {
		case 9:
			if (aPP_constants.Passing_img_from == 1 ) {
				homefrag_mtd();
				aPP_constants.onbackpress = 1;
				
			} 
			else {
				FragmentManager fragmentManagerd = getFragmentManager();
		        fragmentManagerd.beginTransaction()
		                .replace(R.id.container, new gallery_Fragment())
		                .commit();
		        

			}
			break;
			
		case 1:
			back_warn();
			
			break;

		default:
			back_warn();
			break;
		}
    }

	private void back_warn() {
		// TODO Auto-generated method stub
		if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Click back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;                       
            }
        }, 2000);
    	
	}

	
}
