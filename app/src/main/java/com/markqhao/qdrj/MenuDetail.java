package com.markqhao.qdrj;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;


public class MenuDetail extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener{

    public static final String DISHNAME = "com.markqhao.QDRJ.DISHNAME";
    public static final String IMAGENAME = "com.markqhao.QDRJ.IMAGENAME";
    public static final String INFO = "com.markqhao.QDRJ.INFO";
    ListView menuDetailListView;
    String categoryName;
    private qdrjDBHelper mDBHelper;
    private Cursor mCursor;
    ArrayAdapter<Dishes> arrayAdapter;
    ArrayList<Dishes> dishesArrayList;
    ImageView ivDishes;
    private Animator mCurrentAnimator;
    private int mShortAnimationDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        ActionBar actionBar = getActionBar();


        mDBHelper = new qdrjDBHelper(this);

        try {
            mDBHelper.createDataBase();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mDBHelper.openDataBase();

        Intent intent = getIntent();
        categoryName = intent.getStringExtra(MenuTab.MENUNAME);
        if (categoryName.equals("liangcai"))  actionBar.setTitle("凉菜 Cold Dishes");
        else if (categoryName.equals("tang"))  actionBar.setTitle("汤 Soups");
        else if (categoryName.equals("recai"))  actionBar.setTitle("热菜 Hot Dishes");
        else if (categoryName.equals("shaokao"))  actionBar.setTitle("烧烤 BBQ");
        else if (categoryName.equals("zhushi"))  actionBar.setTitle("主食 Staple food");
        else if (categoryName.equals("ganguo"))  actionBar.setTitle("干锅 Griddle Cooked Dishes");


        menuDetailListView = (ListView)findViewById(R.id.menuDetail_ListView);
        dishesArrayList = new ArrayList<Dishes>();
        dishesArrayList = loadDishes(categoryName);

        arrayAdapter = new ArrayAdapter<Dishes>(this,R.layout.category_list_item, dishesArrayList) {
            @Override
            public boolean isEnabled(int position)
            {
                if(!(dishesArrayList.get(position).image == null) && !(dishesArrayList.get(position).image.equals(""))) return true;
                else return false;
            }
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = (RelativeLayout)RelativeLayout.inflate(getBaseContext(),R.layout.category_list_item,null);
                ivDishes = (ImageView)convertView.findViewById(R.id.dishImageView);
                TextView tvName = (TextView)convertView.findViewById(R.id.dishNameTextView);
                TextView tvEname = (TextView)convertView.findViewById(R.id.dishEnameTextView);
                TextView tvPrice = (TextView)convertView.findViewById(R.id.dishPriceTextView);
                ImageView ivSpecial = (ImageView)convertView.findViewById(R.id.ivSpecial);
                if(dishesArrayList.get(position).info != null && !(dishesArrayList.get(position).info.equals(""))) {
                    ivSpecial.setVisibility(View.VISIBLE);
                }
                if(!(dishesArrayList.get(position).image == null) && !(dishesArrayList.get(position).image.equals(""))) {
                    ivDishes.setImageDrawable(convertView.getResources().getDrawable(convertView.getResources().getIdentifier(dishesArrayList.get(position).image.replace(".jpg",""), "drawable", getPackageName())));
                }
                else {
                    ivDishes.setImageDrawable(convertView.getResources().getDrawable(convertView.getResources().getIdentifier("empty","drawable",getPackageName())));
                }
                tvName.setText(dishesArrayList.get(position).name);
                tvEname.setText(dishesArrayList.get(position).ename);
                tvPrice.setText("$"+dishesArrayList.get(position).price);

                return convertView;
            }
        };

        menuDetailListView.setAdapter(arrayAdapter);
        menuDetailListView.setOnItemClickListener(this);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_detail, menu);
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

    private ArrayList<Dishes> loadDishes(String categoryName) {
        ArrayList<Dishes> DishList = new ArrayList<Dishes>();
        try {
            mCursor = mDBHelper.fetchMenu(categoryName);
            mCursor.moveToFirst();
            while (!mCursor.isAfterLast())
            {
                Dishes dishes = new Dishes();
                dishes.name = mCursor.getString(0);
                dishes.ename = mCursor.getString(1);
                dishes.price = mCursor.getString(2);
                dishes.info = mCursor.getString(3);
                dishes.image = mCursor.getString(4);
                DishList.add(dishes);
                mCursor.moveToNext();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            DishList = null;
        }
        return DishList;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Dishes dishes = (Dishes)adapterView.getItemAtPosition(i);
        String name = dishes.name;
        String image = dishes.image;
        String info = dishes.info;
        if(dishes.info==null ||dishes.info.equals("")) {
            Intent intent = new Intent(this,DishImage.class);
            intent.putExtra(DISHNAME,name);
            intent.putExtra(IMAGENAME,image);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, DishDetail.class);
            intent.putExtra(DISHNAME, name);
            intent.putExtra(IMAGENAME,image);
            intent.putExtra(INFO,info);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {

    }

//    private void zoomImageFromThumb(final View thumbView, int imageResId) {
//        // If there's an animation in progress, cancel it immediately and proceed with this one.
//        if (mCurrentAnimator != null) {
//            mCurrentAnimator.cancel();
//        }
//
//        // Load the high-resolution "zoomed-in" image.
//        final ImageView expandedImageView = (ImageView) findViewById(R.id.expanded_image);
//        expandedImageView.setImageResource(imageResId);
//
//        // Calculate the starting and ending bounds for the zoomed-in image. This step
//        // involves lots of math. Yay, math.
//        final Rect startBounds = new Rect();
//        final Rect finalBounds = new Rect();
//        final Point globalOffset = new Point();
//
//        // The start bounds are the global visible rectangle of the thumbnail, and the
//        // final bounds are the global visible rectangle of the container view. Also
//        // set the container view's offset as the origin for the bounds, since that's
//        // the origin for the positioning animation properties (X, Y).
//        thumbView.getGlobalVisibleRect(startBounds);
//        findViewById(R.id.container).getGlobalVisibleRect(finalBounds, globalOffset);
//        startBounds.offset(-globalOffset.x, -globalOffset.y);
//        finalBounds.offset(-globalOffset.x, -globalOffset.y);
//
//        // Adjust the start bounds to be the same aspect ratio as the final bounds using the
//        // "center crop" technique. This prevents undesirable stretching during the animation.
//        // Also calculate the start scaling factor (the end scaling factor is always 1.0).
//        float startScale;
//        if ((float) finalBounds.width() / finalBounds.height()
//                > (float) startBounds.width() / startBounds.height()) {
//            // Extend start bounds horizontally
//            startScale = (float) startBounds.height() / finalBounds.height();
//            float startWidth = startScale * finalBounds.width();
//            float deltaWidth = (startWidth - startBounds.width()) / 2;
//            startBounds.left -= deltaWidth;
//            startBounds.right += deltaWidth;
//        } else {
//            // Extend start bounds vertically
//            startScale = (float) startBounds.width() / finalBounds.width();
//            float startHeight = startScale * finalBounds.height();
//            float deltaHeight = (startHeight - startBounds.height()) / 2;
//            startBounds.top -= deltaHeight;
//            startBounds.bottom += deltaHeight;
//        }
//
//        // Hide the thumbnail and show the zoomed-in view. When the animation begins,
//        // it will position the zoomed-in view in the place of the thumbnail.
//        thumbView.setAlpha(0f);
//        expandedImageView.setVisibility(View.VISIBLE);
//
//        // Set the pivot point for SCALE_X and SCALE_Y transformations to the top-left corner of
//        // the zoomed-in view (the default is the center of the view).
//        expandedImageView.setPivotX(0f);
//        expandedImageView.setPivotY(0f);
//
//        // Construct and run the parallel animation of the four translation and scale properties
//        // (X, Y, SCALE_X, and SCALE_Y).
//        AnimatorSet set = new AnimatorSet();
//        set
//                .play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left,
//                        finalBounds.left))
//                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top,
//                        finalBounds.top))
//                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale, 1f))
//                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale, 1f));
//        set.setDuration(mShortAnimationDuration);
//        set.setInterpolator(new DecelerateInterpolator());
//        set.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                mCurrentAnimator = null;
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//                mCurrentAnimator = null;
//            }
//        });
//        set.start();
//        mCurrentAnimator = set;
//
//        // Upon clicking the zoomed-in image, it should zoom back down to the original bounds
//        // and show the thumbnail instead of the expanded image.
//        final float startScaleFinal = startScale;
//        expandedImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mCurrentAnimator != null) {
//                    mCurrentAnimator.cancel();
//                }
//
//                // Animate the four positioning/sizing properties in parallel, back to their
//                // original values.
//                AnimatorSet set = new AnimatorSet();
//                set
//                        .play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left))
//                        .with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top))
//                        .with(ObjectAnimator
//                                .ofFloat(expandedImageView, View.SCALE_X, startScaleFinal))
//                        .with(ObjectAnimator
//                                .ofFloat(expandedImageView, View.SCALE_Y, startScaleFinal));
//                set.setDuration(mShortAnimationDuration);
//                set.setInterpolator(new DecelerateInterpolator());
//                set.addListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        thumbView.setAlpha(1f);
//                        expandedImageView.setVisibility(View.GONE);
//                        mCurrentAnimator = null;
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//                        thumbView.setAlpha(1f);
//                        expandedImageView.setVisibility(View.GONE);
//                        mCurrentAnimator = null;
//                    }
//                });
//                set.start();
//                mCurrentAnimator = set;
//            }
//        });
//    }
}
