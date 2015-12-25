package com.markqhao.qdrj;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class DishDetail extends Activity {

    String name;
    String image;
    String info;
    ImageView ivDishDetail;
    TextView tvDishDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_detail);
        ActionBar actionBar = getActionBar();
        Intent intent = getIntent();
        name = intent.getStringExtra(MenuDetail.DISHNAME);
        image = intent.getStringExtra(MenuDetail.IMAGENAME);
        info = intent.getStringExtra(MenuDetail.INFO);
        actionBar.setTitle(name);
        ivDishDetail = (ImageView)findViewById(R.id.ivDishDetail);
        tvDishDetail = (TextView)findViewById(R.id.tvDishDetail);

        ivDishDetail.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(image.replace(".jpg",""),"drawable",getPackageName())));
        tvDishDetail.setText(info);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.dish_detail, menu);
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
}
