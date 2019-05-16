package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button upload, choose;
    public ImageView imageView;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d("MR_uploaOnclick", "onClick: I am on"+requestCode);
        if(resultCode != RESULT_CANCELED){
            switch(requestCode){
                case 0:
                    Log.d("MR_uploaOnclick", "onClick: I am 0");
                    if(resultCode == RESULT_OK && data != null){
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        imageView.setImageBitmap(selectedImage);
                    }
                    break;
                case 1:
                    Log.d("MR_uploaOnclick", "onClick: I am 1");
                    if(resultCode == RESULT_OK && data != null) {
                        Log.d("MR_uploaOnclick", "onClick: I am 1 in");
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            Log.d("MR_uploaOnclick", "onClick: I am 1 in in");
                            if (cursor != null) {
                                Log.d("MR_uploaOnclick", "onClick: I am 1 in in in");
                                cursor.moveToFirst();
                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                cursor.close();
                            }
                        }
                    }
                    break;
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        upload = (Button) findViewById(R.id.btn_uplaod);
        choose = (Button) findViewById(R.id.btn_choose);
        imageView = (ImageView) findViewById(R.id.imageView);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MR_uploaOnclick", "onClick: I am clicked");
                Toast.makeText(MainActivity.this, "I am pressed", Toast.LENGTH_SHORT).show();

                Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);

//                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(pickPhoto , 1);
            }
        });









//        Intent intent = new Intent(MainActivity.this, AccountKitActivity.class);
//        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
//                new AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.PHONE, AccountKitActivity.ResponseType.TOKEN);
//        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configurationBuilder.build());
//        startActivityForResult(intent, 999);

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 999){
//            AccountKitLoginResult res = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
//            if (res.getError() != null){
//                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
//                return;
//            }else if(res.wasCancelled()){
//                Toast.makeText(this, ""+res.getError().getErrorType().getMessage(), Toast.LENGTH_SHORT).show();
//                return;
//            }else {
//                startActivity(new Intent(this, BottomNavAct.class));
//            }
//        }
//    }
}
