package net.teamcadi.cookit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

public class PermissionRequester {
    private String permissionName;
    private boolean result = false;
    private Activity activity;
    private Context context;

    public boolean requester(final Activity activity, final Context context, String permission, String permissionText){
        this.permissionName = permission;
        this.activity = activity;
        this.context = context;

        if(Build.VERSION.SDK_INT >= 23)//마시멜로우 버전 이상일 때 true, 이하이면 false
        {
            //앱에 권한이 존재하는지를 확인(checkSelfPermission)
            if(ContextCompat.checkSelfPermission(activity,permissionName) != PackageManager.PERMISSION_GRANTED)
            {
                //해당권한이 존재하지 않음.
                //해당권한을 가져와야 하는 이유를 설명해야 하는 경우(shouldShowRequestPermissionRationale)
                if(ActivityCompat.shouldShowRequestPermissionRationale(activity,permissionName))
                {
                    //과거에 권한요청에 대해 이미 사용자가 '거절'을 선택했던 경우
                    //마시멜로우 이상, 과거 거절 이력, 권한 존재 X -> 사용자에게 충분한 설명과 권한 요청 필요
                    AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
                    dialog.setTitle("권한이 필요합니다.")
                            .setMessage("이 기능을 사용하기 위해서는 단말기의 \""+permissionText+"\"권한이 필요합니다. " +
                                    "\"거부\"하실 경우 직접 \"설정->애플리케이션 권한\"에 들어가서 변경해주셔야 합니다." +
                                    " 계속 하시겠습니까?")
                            .setPositiveButton("네", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if(Build.VERSION.SDK_INT>=23)
                                    {
                                        activity.requestPermissions(new String[]{String.valueOf(permissionName)},1000);
                                        result = true;
                                    }
                                }
                            })
                            .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(activity, "기능을 취소했습니다", Toast.LENGTH_SHORT).show();
                                    result = false;
                                }
                            }).create().show();
                }
                else
                {
                    //마시멜로우 이상, 과거 거절 이력 X, 권한 존재 X -> 사용자가 처음 접한 기능이므로 권한 요청 필요
                    //권한 요청하기(requestPermission)
                    activity.requestPermissions(new String[]{String.valueOf(permissionName)},1000);
                    result = true;
                }
            }
            else
            {
                //마시멜로우 이상, 권한 존재 -> 코드 실행
                result = true;
            }
        }
        else
        {
            //마시멜로우 이하 -> 코드 실행
            result = true;
        }

        return result;
    }
}
