package greetings.yellow.gray;



import java.util.ArrayList;
import java.util.List;

import android.R.menu;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;

public class volley_adapter extends BaseAdapter{
	Activity activity;
	ArrayList<volley_model> new_imgs;
	LayoutInflater lif;
	ImageLoader imageLoader = volley_class.getInstance().getImageLoader();
	
	public volley_adapter(Activity activity, ArrayList<volley_model> new_imgs) {
		this.activity = activity;
		this.new_imgs = new_imgs;
		
	}

    @Override
    public int getCount() {
        return new_imgs.size();
    }

    @Override
    public Object getItem(int i) {
        return new_imgs.get(i);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
    	
    	 if(lif ==  null)
      	   lif = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	 if(view == null)
      	   view = lif.inflate(R.layout.new_gallery_vw_row, null);
         
       if(view == null)
           if (imageLoader ==  null)
        	   imageLoader = volley_class.getInstance().getImageLoader();
          
            
           
          
          // view = lif.inflate(R.layout.new_gallery_vw_row);
          // vh.imgview = (ImageView) view.findViewById(R.id.volley_nw_Image);
           TextView tvTitle = (TextView) view.findViewById(R.id.tvName);
           TextView cate = (TextView) view.findViewById(R.id.tv_cat);
           NetworkImageView nw_img = (NetworkImageView)view.findViewById(R.id.volley_nw_Image);
           
           
          volley_model vm = new_imgs.get(i);  
          
          nw_img.setImageUrl(vm.get_imgurl(),imageLoader);
          
          tvTitle.setText(vm.get_title());
          cate.setText(vm.get_cate());
          
     
        
       
        return view;
    }
     
}
    
	



