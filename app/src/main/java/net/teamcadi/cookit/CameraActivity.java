package net.teamcadi.cookit;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

public class CameraActivity extends AppCompatActivity {
    final int CAMERA_REQUEST_CODE = 11;//onActivityResult() 메소드에서 결과값에 대한 정보를 전달하기 위한 코드
    Button takeBtn = null;
    ImageView photo = null;
    Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        //만들어둔 "사진찍기"버튼과 찍은 사진을 뿌려줄 이미지뷰에 각각 연결시켜줌
        takeBtn = (Button)findViewById(R.id.takeBtn);
        photo = (ImageView)findViewById(R.id.photo);

        takeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionRequester permissionRequester = new PermissionRequester();

                //해당 권한에 대해 true(권한 허가 + 실행)/false(권한 비허가 + 실행불가) 반환
                boolean result = permissionRequester.requester(CameraActivity.this, context, Manifest.permission.CAMERA,"카메라");
                if(result && isExistsCameraApp())//권한허가가 됐고 카메라 앱이 존재한다면
                {
                    Intent cameraApp = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//카메라 불러오기
                    startActivityForResult(cameraApp,CAMERA_REQUEST_CODE);//카메라 동작을 실행하고 결과를 수신
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CAMERA_REQUEST_CODE:
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap)bundle.get("data");
                photo.setImageBitmap(bitmap);
                break;

                default:
                    break;
        }
    }

    private boolean isExistsCameraApp() {
        PackageManager packageManager = getPackageManager();
        Intent cameraApp = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> cameraApps = packageManager.queryIntentActivities(cameraApp, PackageManager.MATCH_DEFAULT_ONLY);

        return cameraApps.size() > 0;
    }
}
