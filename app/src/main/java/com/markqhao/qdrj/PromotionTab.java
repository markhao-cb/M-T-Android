package com.markqhao.qdrj;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;


public class PromotionTab extends Activity implements RadioGroup.OnCheckedChangeListener, AdapterView.OnItemClickListener {

    public static final String TITLE = "com.markqhao.QDRJ.TITLE";
    public static final String DETAIL = "com.markqhao.QDRJ.DETAIL";
    public static final String ISSUE_DATE = "com.markqhao.QDRJ.ISSUE_DATE";
    public static final String EXP_DATE = "com.markqhao.QDRJ.EXP_DATE";
    private qdrjDBHelper mDBHelper;
    private Cursor mCursor;
    SegmentedRadioGroup segmentText;
    ListView promoListView;
    ArrayList<Promotion> PromoList;
    ArrayList<Promotion> expPromoList;
    ArrayAdapter PromoListAdapter;
    ArrayAdapter expPromoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_tab);


        mDBHelper = new qdrjDBHelper(this);

        try {
            mDBHelper.createDataBase();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mDBHelper.openDataBase();


        ActionBar actionBar = getActionBar();

        segmentText = (SegmentedRadioGroup)findViewById(R.id.segment_text);
        segmentText.setOnCheckedChangeListener(this);

        promoListView = (ListView)findViewById(R.id.promoListView);
        PromoList = new ArrayList<Promotion>();
        //expPromoList = new ArrayList<Promotion>();

        loadAvlPromo();

        PromoListAdapter = new ArrayAdapter(this,R.layout.promo_list_item,PromoList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = (LinearLayout)LinearLayout.inflate(getBaseContext(),R.layout.promo_list_item,null);
                TextView tvTitle = (TextView)convertView.findViewById(R.id.tvPromoTitle);
                tvTitle.setText(PromoList.get(position).title);
                TextView tvExp = (TextView)convertView.findViewById(R.id.tvPromoExp);
                tvExp.setText(PromoList.get(position).exp_date);
                return convertView;
            }
        };
        PromoListAdapter.notifyDataSetChanged();
//        expPromoListAdapter = new ArrayAdapter(this,R.layout.promo_list_item,expPromoList) {
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                convertView = (LinearLayout)LinearLayout.inflate(getBaseContext(),R.layout.promo_list_item,null);
//                TextView tvTitle = (TextView)convertView.findViewById(R.id.tvPromoTitle);
//                tvTitle.setText(expPromoList.get(position).title);
//                TextView tvExp = (TextView)convertView.findViewById(R.id.tvPromoExp);
//                tvExp.setText("Expired");
//                return convertView;
//            }
//        };
        promoListView.setAdapter(PromoListAdapter);
        promoListView.setOnItemClickListener(this);
    }

    private void loadAvlPromo() {

        try {
            mCursor = mDBHelper.fetchAvlPromo();
            mCursor.moveToFirst();
            PromoList.clear();
            while (!mCursor.isAfterLast())
            {
                Promotion promotion = new Promotion();
                promotion.title = mCursor.getString(0);
                promotion.detail = mCursor.getString(1);
                promotion.issue_date = mCursor.getString(2);
                promotion.exp_date = "EXP: " + mCursor.getString(3);
                PromoList.add(promotion);
                mCursor.moveToNext();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            PromoList = null;
        }
    }

    private void loadExpPromo() {

        try {
            mCursor = mDBHelper.fetchExpPromo();
            mCursor.moveToFirst();
            PromoList.clear();
            while (!mCursor.isAfterLast())
            {
                Promotion promotion = new Promotion();
                promotion.title = mCursor.getString(0);
                promotion.detail = mCursor.getString(1);
                promotion.issue_date = mCursor.getString(2);
                promotion.exp_date = "Expired";
                PromoList.add(promotion);
                mCursor.moveToNext();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            PromoList = null;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(group == segmentText) {
            if(checkedId == R.id.button_one) {
                loadAvlPromo();
                PromoListAdapter.notifyDataSetChanged();
            }
            else if (checkedId == R.id.button_two) {
                loadExpPromo();
                PromoListAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Promotion promotion = (Promotion)adapterView.getItemAtPosition(i);
        String title = promotion.title;
        String detail = promotion.detail;
        String issue_date = promotion.issue_date;
        String exp_date = promotion.exp_date;
        Intent intent = new Intent(this,PromoDetail.class);
        intent.putExtra(TITLE,title);
        intent.putExtra(DETAIL,detail);
        intent.putExtra(ISSUE_DATE,issue_date);
        intent.putExtra(EXP_DATE,exp_date);
        startActivity(intent);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.promotion_tab, menu);
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
