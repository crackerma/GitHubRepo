package com.unknow.cpt;

import com.facebook.android.Facebook;
import com.unknow.cpt.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * MainActivity use Tabbar activity to construct a bottom fixed navigator;
 * MainActivity is the the base activity of this project.
 * If MainActivity is destroy, the project is exit.
 * RecentActivity, FavouriteActivity, ScanActivity, FacebookActivity and
 * SettingActivity are all create in this MainActivity.
 * */
@TargetApi(15)
public class MainActivity extends TabActivity implements OnCheckedChangeListener{
	public static RadioGroup mainTab;
	public static TabHost mTabHost;
	public static Activity mainActivity;
	//Intent
	private Intent recentIntent;
	private Intent favouriteIntent;
	private Intent scanIntent;
	private Intent settingIntent;
	private Intent facebookIntent;
	private Button btn_facebook;
	private Button btn_scan;
	private Button btn_recent;
	
	//Ref by other activity
	public Context c;
	public static Facebook fb;
	public static SharedPreferences sp;
	private final static String TAB_TAG_RECENT="tab_tag_recent";
	private final static String TAB_TAG_FAVOURITE="tab_tag_favourite";
	private final static String TAB_TAG_SCAN="tab_tag_scan";
	private final static String TAB_TAG_FACEBOOK="tab_tag_facebook";
	private final static String TAB_TAG_SETTING="tab_tag_setting";
	private final static String APP_ID_FACEBOOK = "357671844303809";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        /**Debug use only: for the NetWorkOnMainThread Exception */
        /****************************************************************/
        /**StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
        .detectDiskReads()
        .detectDiskWrites()
        .detectNetwork()   // or .detectAll() for all detectable problems
        .penaltyLog()
        .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
        .detectLeakedSqlLiteObjects()
        .detectLeakedClosableObjects()
        .penaltyLog()
        .penaltyDeath()
        .build());
        /****************************************************************/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //Facebook instance used globally
        fb = new Facebook(APP_ID_FACEBOOK);
        sp = this.getSharedPreferences("FACE_PREF", MODE_PRIVATE);
        mainTab=(RadioGroup)findViewById(R.id.main_tab);
        mainTab.setOnCheckedChangeListener(this);
        c= this.getApplicationContext();
        this.mainActivity = this;
        btn_scan = (Button) this.findViewById(R.id.radio_button2);
        btn_recent = (Button) this.findViewById(R.id.radio_button0);
        prepareIntent();
        setupIntent();
        setupButtonListener();

    }

	/**
     * The following two function used to prepare and setup Intents
     */
	private void prepareIntent() {
		recentIntent=new Intent(this, RecentActivity.class);
		favouriteIntent=new Intent(this, FavouriteActivity.class);
		scanIntent=new Intent(this, ScanActivity.class);
		settingIntent=new Intent(this, SettingActivity.class);
	}
	/**
	 * BuildTabSpecs
	 */
	private void setupIntent() {
		this.mTabHost=getTabHost();
		TabHost localTabHost=this.mTabHost;
		localTabHost.addTab(buildTabSpec(TAB_TAG_RECENT, R.string.main_home, R.drawable.bottomicon_recent, recentIntent));
		localTabHost.addTab(buildTabSpec(TAB_TAG_FAVOURITE, R.string.main_news, android.R.drawable.btn_star, favouriteIntent));
		localTabHost.addTab(buildTabSpec(TAB_TAG_SCAN, R.string.main_my_info, android.R.drawable.ic_menu_camera, scanIntent));
		//localTabHost.addTab(buildTabSpec(TAB_TAG_FACEBOOK, R.string.main_my_info, R.drawable.facebook, facebookIntent));
		localTabHost.addTab(buildTabSpec(TAB_TAG_SETTING, R.string.more, android.R.drawable.ic_menu_preferences, settingIntent));
	}
	/**
	 * Construct TabHost
	 * @param tag
	 * @param resLabel
	 * @param resIcon
	 * @param content
	 * @return tab
	 */
	private TabHost.TabSpec buildTabSpec(String tag, int resLabel, int resIcon,final Intent content) {
		return this.mTabHost.newTabSpec(tag).setIndicator(getString(resLabel),
				getResources().getDrawable(resIcon)).setContent(content);
	} 
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		
		switch(checkedId){
		case R.id.radio_button0:
			this.mTabHost.setCurrentTab(0);
			break;
		case R.id.radio_button1:
			this.mTabHost.setCurrentTabByTag(TAB_TAG_FAVOURITE);
			break;
		case R.id.radio_button2:
			this.mTabHost.setCurrentTabByTag(TAB_TAG_SCAN);
			break;
		case R.id.radio_button3:
			break;
		case R.id.radio_button4:
			this.mTabHost.setCurrentTabByTag(TAB_TAG_SETTING);
			break;
		}
	}
	private void setupButtonListener(){
        btn_facebook = (Button) this.findViewById(R.id.radio_button3);
        btn_facebook.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("debug", "start activity");
				Runnable r = new Runnable(){  
					@Override  
					public void run() {
						facebookIntent = new Intent(c, FacebookActivity.class);
						startActivity(facebookIntent);
					}  
				};
				new Thread(r).start();

			}
        });
        
	}
	
	/**
	 * Before MainActivity destroy, show a dialog to ask user about exit
	 */
	@Override
	public void finish(){
	    AlertDialog.Builder builder = new Builder(this);
	    builder.setMessage("Do you want to exit?");
	    builder.setPositiveButton("YES", new DialogInterface.OnClickListener(){

	    	@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				MainActivity.super.finish();
			}		
	    });
	    builder.setNegativeButton("NO", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
   			
   		});
   		builder.show();
	}
	//Set the select tab to recentActivity
	public static void moveToRecent(){
		mTabHost.setCurrentTabByTag("tab_tag_recent");
		mainTab.clearCheck();
	}
}