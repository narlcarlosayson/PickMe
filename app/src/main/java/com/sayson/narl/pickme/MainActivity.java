package com.sayson.narl.pickme;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

    private Uri selectImage;
    private Button shareButton;
    private ImageView buttonImage;
    private RelativeLayout backMain;
    private static int RESULT_LOAD_IMAGE = 1;
    private String imgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shareButton = (Button) findViewById(R.id.shareButton);
        buttonImage = (ImageView) findViewById(R.id.pickM);
        backMain = (RelativeLayout) findViewById(R.id.backM);


        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent picturee = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(picturee, 0);
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, selectImage);
                shareIntent.setType("image/jpeg");
                startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)));
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageBackIntent) {
        super.onActivityResult(requestCode, resultCode, imageBackIntent);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    selectImage = imageBackIntent.getData();
                    buttonImage.setVisibility(View.GONE);
                    shareButton.setVisibility(View.VISIBLE);
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(selectImage);
                        Drawable mDrawable = Drawable.createFromStream(inputStream, selectImage.toString());
                        backMain.setBackground(mDrawable);
                    } catch (FileNotFoundException e) {

                        Drawable mDrawable = getResources().getDrawable(R.drawable.pickcam);
                    }
                }
                break;


            case 1:
                if (resultCode == RESULT_OK) {
                    Uri selectImage = imageBackIntent.getData();
                    buttonImage.setVisibility(View.GONE);
                    shareButton.setVisibility(View.VISIBLE);
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(selectImage);
                        Drawable yourDrawable = Drawable.createFromStream(inputStream, selectImage.toString());
                        backMain.setBackground(yourDrawable);
                    } catch (FileNotFoundException e) {
                        Drawable yourDrawable = getResources().getDrawable(R.drawable.pickcam);

                    }


                }
break;

        }

    }
}





