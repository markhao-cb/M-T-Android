package com.markqhao.qdrj;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class AboutTab extends Activity implements View.OnClickListener{

    Button btTel;
    Button btEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_tab);

        btTel = (Button)findViewById(R.id.btTel);
        btEmail = (Button)findViewById(R.id.btEmail);

        btEmail.setOnClickListener(this);
        btTel.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btEmail) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"laqdrj@gmail.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
            i.putExtra(Intent.EXTRA_TEXT   , "");
            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(AboutTab.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
            startActivity(i);
        }
        else if(view.getId()==R.id.btTel) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + btTel.getText()));
            startActivity(intent);
        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.about_tab, menu);
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
