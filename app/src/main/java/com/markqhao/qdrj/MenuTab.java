package com.markqhao.qdrj;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.sql.Array;


public class MenuTab extends Activity implements AdapterView.OnItemClickListener{

    public static final String MENUNAME = "com.markqhao.QDRJ.MESSAGE";
    ImageView ivMenu;
    ListView menuListView;
    String[] menu = {"liangcai","tang","recai","ganguo","shaokao","zhushi"};
    ArrayAdapter<String> mMenuListViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_tab);

        menuListView = (ListView)findViewById(R.id.menuListView);
        mMenuListViewAdapter = new ArrayAdapter<String>(this,R.layout.menu_list_item,menu){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = (RelativeLayout)RelativeLayout.inflate(getBaseContext(),R.layout.menu_list_item,null);
                ivMenu = (ImageView)convertView.findViewById(R.id.menuImageView);
                ivMenu.setImageDrawable(convertView.getResources().getDrawable(convertView.getResources().getIdentifier(menu[position], "drawable", getPackageName())));
                return convertView;
            }
        };


        menuListView.setAdapter(mMenuListViewAdapter);
        menuListView.setOnItemClickListener(this);

    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_tab, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String name = (String)adapterView.getItemAtPosition(i);
        Intent intent = new Intent(this,MenuDetail.class);
        intent.putExtra(MENUNAME,name);
        startActivity(intent);
    }
}
