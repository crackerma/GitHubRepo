/**
 * ViewCommentsActivity
 * Provide a visiual view for user with the comments related to a product.
 * 
 * Depend Classes all include in com.unknow.cpt.comments
 */
package com.unknow.cpt.comments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import com.unknow.cpt.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class ViewCommentsActivity extends Activity{
	ArrayList<HashMap<String, Object>> listItem;
	ListView listView;
	SimpleAdapter listItemAdapter;
	private String pID;
	private Handler mHandler;
	private ProgressDialog myDialog;
	private List<Comments> comments;
	private static final int MSG_SUCCESS = 0;//Downloading product data flag
    private static final int MSG_FAILURE = 1;
    private static final int MSG_NOTFOUND = 3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_viewcomments);
    	overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
    	pID = this.getIntent().getExtras().getString("pID");
    	comments = new ArrayList<Comments>();
		listViewListenerSetup();
		RelativeLayout back = (RelativeLayout) findViewById(R.id.viewcomment_back);
		back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
			}
			
		});
    }
    
    
	private void listViewListenerSetup(){
    	listItem = new ArrayList<HashMap<String, Object>>();
    	
        mHandler = new Handler(){
	      	   public void handleMessage(Message msg){
	      		   switch(msg.what){
	      		   		case MSG_SUCCESS:
	      		   			updateUI();
	      		   			break;
	      		   		case MSG_FAILURE:
	     		   			Toast toast = Toast.makeText(ViewCommentsActivity.this, "Failed connect to internet!", Toast.LENGTH_SHORT);
	     		   			toast.setGravity(Gravity.CENTER, 0, 0);
	     		   			toast.show();
	      		   			break;
	      		   		case MSG_NOTFOUND:
	     		   			Toast toast1 = Toast.makeText(ViewCommentsActivity.this, "There is no Comments for this product yet, be first one!", Toast.LENGTH_SHORT);
	     		   			toast1.setGravity(Gravity.CENTER, 0, 0);
	     		   			toast1.show();
	      		   			break;
	      		   }
	      	   }
	         };
	         myDialog = ProgressDialog.show(ViewCommentsActivity.this, "Getting Reviews","Please wait...", true);
	         new Thread() {
	      	   public void run() {
	      		   CommentsHelper ch = new CommentsHelper();
	      		   try {
					comments = ch.viewComments(pID);
					mHandler.obtainMessage(MSG_SUCCESS).sendToTarget();
				} catch (CommentsNotFoundException e) {
					// TODO Auto-generated catch block
					mHandler.obtainMessage(MSG_NOTFOUND).sendToTarget();
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					mHandler.obtainMessage(MSG_FAILURE).sendToTarget();
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					mHandler.obtainMessage(MSG_FAILURE).sendToTarget();
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					mHandler.obtainMessage(MSG_FAILURE).sendToTarget();
					e.printStackTrace();
				}finally{
					myDialog.dismiss();
				}
	      	   }
	        }.start();
    	
	}
	private void updateUI(){
		for(Comments c : this.comments){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("comments", c.getReviews());
			String markedFacebookID = c.getFacebookID().substring(0, c.getFacebookID().length()-4);
			markedFacebookID += "****";
			map.put("facebook_id", markedFacebookID);
			listItem.add(map);
		}
		listView = (ListView) this.findViewById(R.id.lv_viewcomments);
        listItemAdapter = new SimpleAdapter(this,listItem,
	            R.layout.customer_comment_lst,
	            new String[] {"comments","facebook_id"},
	            new int[] {R.id.txt_single_comment,R.id.txt_facebook_id}  
	        ); 
        listView.setAdapter(listItemAdapter);
	}
}
