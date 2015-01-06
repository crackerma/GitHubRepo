
package com.unknow.cpt;

/**
 * RecentActivity:
 * onCreate: Load the product data from sqlite database, display it on listView;
 * onResume: Reload the data, and refresh the listView.
 * onItemLoingClick: Display a contextMenu with two Options: Add to favourite
 * 															 Delete
 * onItemClick:
 * 				Call ScanActivity to show the product details
 * 
 * Depend classes:All depend classes are in the com.unknow.cpt.history
 * @author Mali
 */
import java.util.ArrayList;
import java.util.HashMap;
import com.unknow.cpt.R;
import com.unknow.cpt.history.FavouriteManager;
import com.unknow.cpt.history.HistoryManager;
import com.unknow.cpt.history.RecentManager;
import com.unknow.cpt.product.Product;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


public class RecentActivity extends Activity {
	public static Activity recentActivity;
	public static String selectPID;
	ArrayList<HashMap<String, Object>> listItem;
	ListView recentList;
	HashMap<String, Object> checkedItem;
	SimpleAdapter listItemAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acvitivy_recent);
		selectPID = "";
		recentActivity = this;
		dataSetup();
		listViewListenerSetup();
	}
	//Load the data
	private void dataSetup(){
		HistoryManager hm = new RecentManager(this);
		listItem = new ArrayList<HashMap<String, Object>>();
		if(hm.hasItems()){
			ArrayList<Object> os = (ArrayList<Object>) hm.getItems();
			for(Object o : os){
				Product p = (Product)o;
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("ItemID", p.getProductID());
				map.put("ItemTitle", p.getProductName());
				listItem.add(map);
			}
		}
	}
	/**
	 * Setup Listener with data
	 */
	private void listViewListenerSetup(){
		recentList = (ListView) this.findViewById(R.id.recent_view);
		
		/*SimpleAdapter listItemAdapter = new SimpleAdapter(
				this,listItem,R.layout.list_recent,
				new String[]{"item","Image"}}
				);*/
		//ArrayAdapter listItemAdapter = new ArrayAdapter(this,R.layout.list_recent,(ArrayList)hashMap.values());
        listItemAdapter = new SimpleAdapter(this,listItem,// ���Դ   
	            R.layout.list_recent,
	            new String[] {"ItemID","ItemTitle"},
	            new int[] {R.id.item_id,R.id.item_title}  
	        ); 
		recentList.setAdapter(listItemAdapter);
		recentList.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				checkedItem = (HashMap<String, Object>) recentList.getItemAtPosition(arg2);
				return false;
			}
			
		});
		recentList.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				checkedItem = (HashMap<String, Object>) recentList.getItemAtPosition(arg2);
				selectPID = checkedItem.get("ItemID").toString();
				MainActivity.mainTab.clearCheck();
				MainActivity.mTabHost.setCurrentTabByTag("tab_tag_scan");
			}
			
		});
        recentList.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {  
            
            @Override  
            public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
                menu.setHeaderTitle("Options");
                menu.add(0, 0, 0, "DELETE");
                menu.add(0, 1, 0, "ADD TO FAVOURITE");
            }

        });
	}
	//Store the productID and productName into favourite table in the sqlitedatabase 
	private void addToFavourite(Product p){
		//Check the existence of product p in Favourite Table
		HistoryManager hm = new FavouriteManager(this);
		if(hm.getItem(p.getProductID()) != null){
			Toast.makeText(this, "Product already exist in favourite", Toast.LENGTH_LONG).show();
		}else{
			hm.addItem(p);
			Toast.makeText(this, "Add to favourite", Toast.LENGTH_LONG).show();
		}
	}
	private void removeRecentItem(Product p){
		HistoryManager hm = new RecentManager(this);
		if(hm.getItem(p.getProductID()) == null){
			Toast.makeText(this, "Data not exist in database", Toast.LENGTH_LONG).show();
		}else{
			hm.deleteItem(p.getProductID());
    		listItem.remove(checkedItem);
    		listItemAdapter.notifyDataSetChanged();
		}
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		dataSetup();
		listViewListenerSetup();
	}
    @Override  
    public boolean onContextItemSelected(MenuItem item) {
    	if(item.getTitle() == "DELETE"){
    		//Delete item from listview
    		Product p = new Product(checkedItem.get("ItemID").toString(),checkedItem.get("ItemTitle").toString());
    		removeRecentItem(p);
    	}
    	if(item.getTitle() == "ADD TO FAVOURITE"){
    		Product p = new Product(checkedItem.get("ItemID").toString(),checkedItem.get("ItemTitle").toString());
    		addToFavourite(p);
    	}
        return super.onContextItemSelected(item);  
    }

}
