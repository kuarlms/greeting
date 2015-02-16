package greetings.yellow.gray;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class  category_Fragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
        View rootView = inflater.inflate(R.layout.catogeries_list_frag, container, false);
        
        GridView gridview_cat =(GridView)rootView.findViewById(R.id.gridView_categories);
        gallery_adpter_image adapter;
		
		adapter = new gallery_adpter_image(getActivity(), R.layout.image_row, app_loading.cards_url);
		
		gridview_cat.setAdapter(adapter);
		gridview_cat.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				aPP_constants.Passing_img_from = 2;

				FragmentManager fragmentManager = getFragmentManager();
		        fragmentManager.beginTransaction()
		                .replace(R.id.container, new picture_view_Fragment())
		                .commit();
		        return;
			}
		}) ;


		
		gridview_cat.setOnItemLongClickListener(onlongclickListner);
        
        
        
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
        
        Button btn_updates = (Button)rootView.findViewById(R.id.button3);
        btn_updates.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "loading updates", Toast.LENGTH_SHORT).show();
				FragmentManager fragmentManagerd = getFragmentManager();
		        fragmentManagerd.beginTransaction()
		                .replace(R.id.container, new update_Fragment())
		                .commit();
				
			}
		});
        
        
        return rootView;
    }
   
     
		
	
	
	OnItemLongClickListener onlongclickListner = new OnItemLongClickListener() {

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			
			String share_gal_view = (String)parent.getItemAtPosition(position);
			Intent shareIntent = new Intent(Intent.ACTION_SEND);
		    Uri phototUri = Uri.parse(share_gal_view);

		   
		    shareIntent.setData(phototUri);
		    shareIntent.setType("image/png");
		    shareIntent.putExtra(Intent.EXTRA_STREAM, phototUri);
		    startActivity(Intent.createChooser(shareIntent, "Send Imgae Via ->"));
			return true;
		}
	};
    
    
}

