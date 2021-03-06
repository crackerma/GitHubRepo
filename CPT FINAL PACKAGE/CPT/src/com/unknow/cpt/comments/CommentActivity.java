/**
 * CommentActivit allow user to commit a comment to server.
 * @author Mali
 */
package com.unknow.cpt.comments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;

import com.facebook.android.Facebook;
import com.unknow.cpt.FacebookActivity;
import com.unknow.cpt.MainActivity;
import com.unknow.cpt.R;
import com.unknow.cpt.ScanActivity;
import com.unknow.cpt.R.id;
import com.unknow.cpt.R.layout;
import com.unknow.cpt.history.HistoryManager;
import com.unknow.cpt.history.RecentManager;
import com.unknow.cpt.product.ProductHelper;
import com.unknow.cpt.product.ProductNotFoundException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Selection;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CommentActivity extends Activity{
	private ArrayList<String> groupList;
	private ArrayList<ArrayList<HashMap<String, Object>>> childList;
	private EditText txtComment;
	private Editable eb;
	private RatingBar rb;
	private SharedPreferences sp;
	private String pID;
	private boolean commit_status; //Keep the commit status, to avoid the user recommit the same comments
								//if the user already commit successfully, then this should be true
	private String commit_response;
	private Handler mHandler;
	private ProgressDialog myDialog;
	private static final int MSG_SUCCESS = 0;//Downloading product data flag
    private static final int MSG_FAILURE = 1;
    private static final int MSG_NOTFOUND = 3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_comment);
    	overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
    	pID = this.getIntent().getExtras().getString("pID");
    	commit_status = false;
    	commit_response = "";
    	sp = MainActivity.sp;
    	setupButtonListener();
    }
    
    private void setupButtonListener(){
    	txtComment = (EditText) findViewById(R.id.txt_comment);
    	rb = (RatingBar) findViewById(R.id.rb);
    	RelativeLayout back = (RelativeLayout) findViewById(R.id.comment_back);
    	back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
			}
    		
    	});
    	RelativeLayout view_comment = (RelativeLayout) findViewById(R.id.view_comment);
    	view_comment.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(CommentActivity.this,ViewCommentsActivity.class);
				intent.putExtra("pID", pID);
				startActivity(intent);
			}
    	});
    	Button clear = (Button)findViewById(R.id.btn_clear);
    	clear.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				txtComment.setText("");
			}
    		
    	});
    	Button confirm = (Button)findViewById(R.id.btn_confirm);
    	confirm.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Check user login
				Facebook fb;
				fb = MainActivity.fb;
				if(!fb.isSessionValid()){
					Runnable r = new Runnable(){  
						@Override  
						public void run() {
							Intent facebookIntent = new Intent(CommentActivity.this, FacebookActivity.class);
							startActivity(facebookIntent);
						}  
					};
					new Thread(r).start();
				}else{
					//Check input
					if(txtComment.getText().length()<3){
						Toast toast = Toast.makeText(CommentActivity.this, "Too short for comments", Toast.LENGTH_SHORT);
						toast.setGravity(Gravity.CENTER, 0, 0);
						toast.show();
					}else if(commit_status == true){
						Toast toast = Toast.makeText(CommentActivity.this, "You already comment this product", Toast.LENGTH_SHORT);
						toast.setGravity(Gravity.CENTER, 0, 0);
						toast.show();
					}
					else{
				        mHandler = new Handler(){
				      	   public void handleMessage(Message msg){
				      		   switch(msg.what){
				      		   		case MSG_SUCCESS:
				      		   			if(commit_response.trim().contains("ok")){
				      		   				Toast toast = Toast.makeText(CommentActivity.this, "Thanks for comments!", Toast.LENGTH_LONG);
				      		   				toast.setGravity(Gravity.CENTER, 0, 0);
				      		   				toast.show();

				      		   			}else{
				      		   				Toast toast = Toast.makeText(CommentActivity.this, "You already commented this product!", Toast.LENGTH_LONG);
				      		   				toast.setGravity(Gravity.CENTER, 0, 0);
				      		   				toast.show();

				      		   			}
				      		   			break;
				      		   		case MSG_FAILURE:
				     		   			Toast toast = Toast.makeText(CommentActivity.this, "Failed connect to internet!", Toast.LENGTH_SHORT);
				     		   			toast.setGravity(Gravity.CENTER, 0, 0);
				     		   			toast.show();
				      		   			break;
				      		   }
				      	   }
				         };
				         myDialog = ProgressDialog.show(CommentActivity.this, "Commiting","Please wait...", true);
				         new Thread() {
				      	   public void run() {
								try {
									CommentsHelper helper = new CommentsHelper();
									int rate = (int) rb.getRating();
									commit_response = helper.postComments(pID, sp.getString("FACEBOOK_ID", ""), txtComment.getText().toString(), rate+"");
									mHandler.obtainMessage(MSG_SUCCESS).sendToTarget();
								} catch (ClientProtocolException e) {
									// TODO Auto-generated catch block
									mHandler.obtainMessage(MSG_FAILURE).sendToTarget();
									e.printStackTrace();
								} catch (IOException e) {
									// Timeout
									mHandler.obtainMessage(MSG_FAILURE).sendToTarget();
									e.printStackTrace();
								}finally{
									myDialog.dismiss();
								}
				      	   }
				        }.start();

					}
				}
			}
    		
    	});
    	
    }
}
