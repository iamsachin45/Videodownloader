package com.example.youvid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.material.textfield.TextInputEditText;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YouTubeUriExtractor;
import at.huber.youtubeExtractor.YtFile;

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;
    private RewardedAd rewardedAd;
    String uri1 = "https://youtu.be/QiOM2MoTGcc";
    String uri2;
    String link;
    // EditText e1;
    EditText e1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = findViewById(R.id.editTextTextPersonName);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
       });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    /*    rewardedAd = new RewardedAd(this,
                "ca-app-pub-8810062716899108/1907010244");
        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad successfully loaded.
            }

            @Override
            public void onRewardedAdFailedToLoad(LoadAdError adError) {
                // Ad failed to load.
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);*/
    }


    public void Download(View view)
    {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        else
            {
                            uri2 = e1.getText().toString();
                            @SuppressLint("StaticFieldLeak") YouTubeUriExtractor youTubeUriExtractor = new YouTubeUriExtractor(MainActivity.this) {
                                @Override
                                public void onUrisAvailable(String videoId, String videoTitle, SparseArray<YtFile> ytFiles)
                                {
                                 //   ytFiles = uri2;
                                 //  ytFiles.append();
                                    if (ytFiles != null)
                                    {

                                        int tag = 18;
                                   //     ytFiles.append(tag,uri2);
                                        //  link = ytFiles.get(tag).getUrl();

                                        //  Toast.makeText(this, uri2, Toast.LENGTH_SHORT).show();
                                        link = ytFiles.get(tag).getUrl();

                                        //  link = e1.getText().toString();
                                        //   uri1 = e1.getText().toString();

                                        //  String title = "My video";
                                        DownloadManager.Request downloadManager = new DownloadManager.Request(Uri.parse(String.valueOf(link)));
                                        downloadManager.setTitle(videoTitle);
                                        downloadManager.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, videoTitle + ".mp4");
                                        DownloadManager downloadManager1 = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                                        downloadManager.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                                        downloadManager.allowScanningByMediaScanner();
                                        downloadManager.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                                        downloadManager1.enqueue(downloadManager);
                                    }
                                }
                            };
                                youTubeUriExtractor.execute(uri2);

        }

    }
    public void Clear(View view)
    {
        e1.setText("");
        e1.clearFocus();
    }

}