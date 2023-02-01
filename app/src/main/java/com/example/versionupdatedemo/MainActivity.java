package com.example.versionupdatedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.net.URI;

public class MainActivity extends AppCompatActivity {
    String globalUri, globalFilepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void versionUpdate(View view) {

        Log.d("TAG", "inside click");
        // URI uriBase = new URI("https://www.geeksforgeeks.org/java-net-uri-class-java/");
        //Uri uri = Uri.parse("https://www.apkmirror.com/apk/line-music/line-music%ef%bc%88%e3%83%a9%e3%82%a4%e3%83%b3%e3%83%9f%e3%83%a5%e3%83%bc%e3%82%b8%e3%83%83%e3%82%af%ef%bc%89/line-music%ef%bc%88%e3%83%a9%e3%82%a4%e3%83%b3%e3%83%9f%e3%83%a5%e3%83%bc%e3%82%b8%e3%83%83%e3%82%af%ef%bc%89-6-5-0-release/line-music-%e9%9f%b3%e6%a5%bd%e3%81%af%e3%83%a9%e3%82%a4%e3%83%b3%e3%83%9f%e3%83%a5%e3%83%bc%e3%82%b8%e3%83%83%e3%82%af-6-5-0-android-apk-download/download/?key=1d523aca549fe97fa277ee497c8bae96b4cb5994&forcebaseapk=true");
        Uri uri = Uri.parse("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");
        openDownloadedAttachment(MainActivity.this, String.valueOf(1234), uri);
    }

    private void openDownloadedAttachment(final Context context, final String downloadId, Uri uri) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        String fileDir = getFilesDir() + "/DownloadTestFolder";
        request.setDestinationInExternalPublicDir(fileDir, "dummy.pdf");
        Log.d("TAG", "files " + fileDir);
        globalFilepath = fileDir;

        long reference = downloadManager.enqueue(request);
        DownloadManager.Query query = new DownloadManager.Query();

        query.setFilterById(reference);
        Cursor cursor = downloadManager.query(query);

        Log.d("TAG", "inside attach");

//        if (cursor.moveToFirst()) {
//            Log.d("TAG", "inside cursor");
//            @SuppressLint("Range")
//            int downloadStatus = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
//            @SuppressLint("Range")
//            String downloadLocalUri = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
//            @SuppressLint("Range")
//            String downloadMimeType = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_MEDIA_TYPE));
//            if (downloadLocalUri != null) {
//                try {
//                    @SuppressLint("Range")
//                    String fileuri = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
//                    Log.d("TAG", "fileuri " + fileuri);
//                    Log.d("TAG", "localuri " + downloadLocalUri);
//                    globalUri = downloadLocalUri;
//
//                    //downloadLocalUri = "/storage/sdcard/downloads";
////                    //openDownloadedAttachment(context, downloadLocalUri, Uri.parse(downloadLocalUri));
////                    Intent promptInstall = new Intent(Intent.ACTION_VIEW);
//////                    promptInstall.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//////                    promptInstall.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//////                    promptInstall.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
//////                    File file = new File(downloadLocalUri);
//////                    Uri uriProvider = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider",
//////                            file);
//////                    promptInstall.setDataAndType(uri,
//////                            "application/vnd.android.package-archive");
////                    promptInstall.setData(Uri.parse(downloadLocalUri));
////                    promptInstall.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                    startActivity(promptInstall);
//                } catch (ActivityNotFoundException e) {
//                    e.printStackTrace();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        cursor.close();
    }

    public void openApp(View view) {
        Intent promptInstall = new Intent(Intent.ACTION_VIEW);
        promptInstall.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        promptInstall.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        promptInstall.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);

        promptInstall.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        File file = new File(globalFilepath + "/dummy.pdf");
        //Uri uris = Uri.fromFile(file);

//
//        Uri uriProvider = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider",
//                file);
////        promptInstall.setDataAndType(Uri.parse(globalUri),
////                "application/vnd.android.package-archive");
//
//        Log.d("TAG", "uriprovider " + uriProvider);
        //promptInstall.setData(uriProvider);


        //startActivity(promptInstall);
    }
}