package com.markqhao.qdrj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class PromoDetail extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo_detail);
        Intent intent = getIntent();
        String title = intent.getStringExtra(PromotionTab.TITLE);
        String detail = intent.getStringExtra(PromotionTab.DETAIL);
        String issue_date = intent.getStringExtra(PromotionTab.ISSUE_DATE);
        String exp_date = intent.getStringExtra(PromotionTab.EXP_DATE);
        TextView tvTitle = (TextView)findViewById(R.id.tvTitle);
        TextView tvDetail = (TextView)findViewById(R.id.tvDetail);
        TextView tvIssueDate = (TextView)findViewById(R.id.tvIssue);
        TextView tvExpDate = (TextView)findViewById(R.id.tvExp);
        tvTitle.setText(title);
        tvDetail.setText(detail);
        tvIssueDate.setText("起始日期：" + issue_date);
        if(exp_date.equals("Expired"))
            tvExpDate.setText(exp_date);
        else {
            tvExpDate.setText("截止日期："+ exp_date.substring(4));
        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.promo_detail, menu);
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
