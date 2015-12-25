package com.markqhao.qdrj;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import org.w3c.dom.Text;


public class OverviewTab extends Activity {


    ImageView mImageView1;
    ImageView mImageView2;
    ImageView mImageView3;
    ImageView mImageView4;
    TextView mTextView;
    HorizontalScrollView mHorizontalScrollView;
    ScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview_tab);

        mHorizontalScrollView = (HorizontalScrollView) findViewById(R.id.HorizontalScrollView);

//        mImageView1 = (ImageView)findViewById(R.id.OverviewImageView1);
//        mImageView2 = (ImageView)findViewById(R.id.OverviewImageView2);
//        mImageView3 = (ImageView)findViewById(R.id.OverviewImageView3);
//        mImageView4 = (ImageView)findViewById(R.id.OverviewImageView4);


        mTextView = (TextView)findViewById(R.id.OverviewTextView);
        mTextView.setText("      青岛人家于2009年7月正式落户于美国纽约皇后区法拉盛。自开业以来得到中外宾客的一致好评。"+"\n\n"+"      2009年9月以及11月，分别两次荣登纽约时报。世界餐饮的权威刊物<<米其林餐饮指南>> 于2010年、2011年两次报道过，并被米奇林评为二星餐饮。2010年9月3日，美国广播电视ABC7曾报道过青岛人家，盛赞青岛人家菜品“百菜百品”、“真正中国菜”。2010年资深电台NYTV8面向24个国家的华语报道中国的八大菜系之一鲁菜之首青岛菜，并得到美国各大网站及刊物的赞誉。"+"\n\n"+"      而今青岛人家落户于洛杉矶。"+"\n\n"+"      鲁菜是中国八大菜系之一。"+"\n\n"+"      鲁菜分为两答派系：一是鲁西南派系，口味偏咸偏重；二是胶东半岛沿海派系，口味适中，食材以海鲜为主，素来享有＂一菜一格，百菜百味＂之美誉。"+"\n\n"+"      青岛位于胶东半岛的南端，有东方瑞士之美称。此地海产丰富，另有崂山的山珍及绿茶。"+"\n\n"+"      青岛人家在烹饪方法上擅长炒、滑、爆、剪、炸、塌。其自行研制的酱卤制品以及水饺等面食更是有其独到之处。从高级的满汉全席乃至民间的小吃，家常菜更为大众叫绝。在品味上，特别讲究＂色、想、味、意、形＂，兼有南北之长。"+"\n\n"+"      青岛人家以其别具一格的烹饪方法和浓郁的地方风味享誉中外。"+"\n\n"+"      Tsingtao M&T Seafood Restaurant was opened first in Flushing, Queens of New York in July 2009, and it has won great acclaim from all guests since its opening."+"\n\n"+"      Tsingtao M&T was favorably reported twice by New York Times, in September and November, 2009, respectively. Michelin Red Guide,the world-renewed authoritative restaurant reference guide, book in the world, honorably mentioned Tsingtao M&T twice in 2010 and 2011, and awarded it as two-star restaurant in 2010. Tsingtao M&T was also reported by the American Broadcasting Company (ABC) on September 3, 2010, who praised M&T for its rich set of delicious dishes as truly authentic Chinese Food. In 2010, the well-known NYTV8 reported to the Chinese channels of 24 different countries the Tsingtao style as the top of the Shandong Cuisine, one of the 8 major cuisines in China, leading to high opinion of major US web sites and publishers. "+"\n\n"+"     Now, Tsingtao M&T is coming to Los Angeles. "+"\n\n"+"      Shandong Cuisine is one of the 8 major cuisines of China and there are two different styles of Shandong Cuisine: Southwest, known for its saltier and heavier taste, and Peninsula Seaside, known for its mild taste and use of seafood as its main ingredient. The Seaside style has the long standing reputation as 'one taste for one dish; one hundred tastes for one hundred dishes'. "+"\n\n"+"      Tsingtao or Qingdao is located in the southern tip of Shandong Peninsula,widely known as The Oriental Switzerland. Tsingtao is rich in its seafood and famous for all sorts of delicacies and Green Tea of Laoshan. Tsingtao M&T is expert at Stir-fry, Stewed, Quick-fry, Pan-fry, Fried, and Omelet. Their marinated food and food made of flour such asduare home-made with unique style.M&T can server you a wide variety of food, from the royal feast of complete manchu­ han courses to popular snacks enjoyed by ordinary people. Tsingtao M&T devotes great details to to the color, smell, taste, intent, and look while cooking, integrates the strengths of both southern and northern Chinese food, and is famous for their unique cooking methods and rich local flavor."+"\n\n");


    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.overview_tab, menu);
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

//class mHorizontalScrollView extends ScrollView {
//    private float xDistance, yDistance, lastX, lastY;
//
//    public mHorizontalScrollView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                xDistance = yDistance = 0f;
//                lastX = ev.getX();
//                lastY = ev.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                final float curX = ev.getX();
//                final float curY = ev.getY();
//                xDistance += Math.abs(curX - lastX);
//                yDistance += Math.abs(curY - lastY);
//                lastX = curX;
//                lastY = curY;
//                if(xDistance < yDistance)
//                    return false;
//        }
//
//        return super.onInterceptTouchEvent(ev);
//    }
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        boolean ret = super.onTouchEvent(ev);
//        if (ret)
//            getParent().requestDisallowInterceptTouchEvent(true);
//        return ret;
//    }
//}

//class MyPagerAdapter extends PagerAdapter {
//
//    @Override
//    public int getCount() {
//        return 4;
//    }
//
//    public Object instantiateItem(View collection, int position) {
//
//        LayoutInflater inflater = (LayoutInflater) collection.getContext()
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        int resId = 0;
//        switch (position) {
//            case 0:
//                resId = R.layout.image1;
//                break;
//            case 1:
//                resId = R.layout.image2;
//                break;
//            case 2:
//                resId = R.layout.image3;
//                break;
//            case 3:
//                resId = R.layout.image4;
//                break;
//
//        }
//
//        View view = inflater.inflate(resId, null);
//
//        ((ViewPager) collection).addView(view, 0);
//
//        return view;
//    }
//
//    @Override
//    public void destroyItem(View arg0, int arg1, Object arg2) {
//        ((ViewPager) arg0).removeView((View) arg2);
//
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object o) {
//        return view == ((View) o);
//    }
//
//    @Override
//    public Parcelable saveState() {
//        return null;
//    }
//}
