/**
 * FavouriteActivity is the favourite page in the application.
 * onCreate: load favourite product information(productName and productBarcode)
 * 			from android sqlite database and display it in a listView.
 * onResume: Delete all data from listView, and reload the data from database.
 * onListItemClick:
 * 			Show a ContextMenu with one option delete,delete will remove
 * 			the currently selected item from sqlite database.
 * FavouriteActivity depend on the other class to use:
 * 			All the classes are in com.unknows.cpt.history
 * @author Mali
 */
package com.unknow.cpt;

import java.util.ArrayList;
import java.util.HashMap;

import com.unknow.cpt.R;
import com.unknow.cpt.history.FavouriteManager;
import com.unknow.cpt.history.HistoryManager;
import com.unknow.cpt.product.Product;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;


public class FavouriteActivity extends Activity {
	ArrayList<HashMap<String, Object>> listItem;
	ListView favouriteList;
	HashMap<String, Object> checkedItem;
	SimpleAdapter listItemAdapter;
	public static String selectPID;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favourite);
		selectPID = "";
		loadData();
		listViewListenerSetup();
	}
	/**
	 * Load data from sqlite database.
	 */
	private void loadData(){
		listItem = new ArrayList<HashMap<String, Object>>();
		HistoryManager hm = new FavouriteManager(this);
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
	 * ListViewListener initialize with data as well as on click, on long click.
	 */
	private void listViewListenerSetup(){
		favouriteList = (ListView) this.findViewById(R.id.favourite_view);
        listItemAdapter = new SimpleAdapter(this,listItem,
	            R.layout.list_recent,
	            new String[] {"ItemID","ItemTitle"},
	            new int[] {R.id.item_id,R.id.item_title}  
	        ); 
        favouriteList.setAdapter(listItemAdapter);
        
        //Long click event on the listItem. Should show delete contextMenu
        favouriteList.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				checkedItem = (HashMap<String, Object>) favouriteList.getItemAtPosition(arg2);
				return false;
			}
			
		});
        //Click event on the listItem, this will call the scan page to display the item information
        favouriteList.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				checkedItem = (HashMap<String, Object>) favouriteList.getItemAtPosition(arg2);
				selectPID = checkedItem.get("ItemID").toString();
				MainActivity.mainTab.clearCheck();
				MainActivity.mTabHost.setCurrentTabByTag("tab_tag_scan");
			}
			
		});
        favouriteList.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {  
            //Initialze the ContextMenu
            @Override  
            public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
                menu.setHeaderTitle("Options");
                menu.add(0, 0, 0, "DELETE").setIcon(android.R.drawable.ic_menu_delete);
            }

        });
	}
	@Override
	protected void onResume() {
		// loaddata and display it
		super.onResume();
		loadData();
		listViewListenerSetup();
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
    	if(item.getTitle() == "DELETE"){
    		//Delete item from listview
    		Product p = new Product(checkedItem.get("ItemID").toString(),checkedItem.get("ItemTitle").toString());
    		HistoryManager hm = new FavouriteManager(this);
    		if(hm.getItem(p.getProductID()) == null){
    			Toast.makeText(this, "Data not exist in database", Toast.LENGTH_LONG).show();
    		}else{
    			hm.deleteItem(p.getProductID());
        		listItem.remove(checkedItem);
        		listItemAdapter.notifyDataSetChanged();
    		}
    	}
		return super.onContextItemSelected(item);
	}
}
