package com.app.qunadai.content.ui.product;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.app.qunadai.R;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.ui.me.AccountActivity;
import com.app.qunadai.third.tencent.X5WebView;
import com.app.qunadai.utils.CheckUtil;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.DownloadUtil;
import com.app.qunadai.utils.KeyBoardListener;
import com.app.qunadai.utils.LogU;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient.CustomViewCallback;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tencent.smtt.utils.TbsLog;
import com.yanzhenjie.album.Album;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class BrowserActivity extends BaseActivity {
    private static final int REQUEST_WEBVIEW = 200;

    @BindView(R.id.ll_x5_layout)
    LinearLayout ll_x5_layout;
    /**
     * 作为一个浏览器的示例展示出来，采用android+web的模式
     */
    private X5WebView mWebView;
    private ViewGroup mViewParent;
    private ImageButton mBack;
    private ImageButton mForward;
    private ImageButton mExit;
    private ImageButton mHome;
    private ImageButton mMore;
    private Button mGo;
    private EditText mUrl;
    private DownloadUtil downloadUtil;

    private static final String mHomeUrl = "http://app.html5.qq.com/navi/index";
    private static final String TAG = "SdkDemo";
    private static final int MAX_LENGTH = 14;
    private boolean mNeedTestPage = false;

    private final int disable = 120;
    private final int enable = 255;

    private ProgressBar mPageLoadingProgressBar = null;


    private ValueCallback<Uri> uploadFile;
    private ValueCallback<Uri[]> uploadFiles;

    //	private URL mIntentUrl;
    private String webUrl;


    @Override
    protected void updateTopViewHideAndShow() {
//        setTitleBarStatus();
//        clearTitleBar();

    }

    @Override
    protected View createCenterView() {
        return View.inflate(this, R.layout.activity_x5_browser, null);
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE|WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        KeyBoardListener.getInstance(this).init();
        downloadUtil = new DownloadUtil(this);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);

        Intent intent = getIntent();
        if (intent != null) {
//				mIntentUrl = new URL(intent.getData().toString());
            webUrl = getIntent().getExtras().getString("url");
            setTitleText(getIntent().getStringExtra("title"));

        }
        //
        try {
            if (Build.VERSION.SDK_INT >= 11) {
                getWindow()
                        .setFlags(
                                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
            }
        } catch (Exception e) {
        }

		/*
         * getWindow().addFlags(
		 * android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 */
        mViewParent = (ViewGroup) findViewById(R.id.webView1);


        initBtnListener();

        mTestHandler.sendEmptyMessageDelayed(MSG_INIT_UI, 10);
    }

    @Override
    public void initViewData() {
        setBackListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWebView != null && mWebView.canGoBack()) {
                    mWebView.goBack();
                } else {
                    BrowserActivity.this.finish();
                }
            }
        });
    }

    private void changGoForwardButton(WebView view) {
        if (view.canGoBack())
            mBack.setImageAlpha(enable);
        else
            mBack.setImageAlpha(disable);
        if (view.canGoForward())
            mForward.setImageAlpha(enable);
        else
            mForward.setImageAlpha(disable);
        if (view.getUrl() != null && view.getUrl().equalsIgnoreCase(mHomeUrl)) {
            mHome.setImageAlpha(disable);
            mHome.setEnabled(false);
        } else {
            mHome.setImageAlpha(enable);
            mHome.setEnabled(true);
        }
    }

    private void initProgressBar() {
        mPageLoadingProgressBar = (ProgressBar) findViewById(R.id.progressBar1);// new
        // ProgressBar(getApplicationContext(),
        // null,
        // android.R.attr.progressBarStyleHorizontal);
        mPageLoadingProgressBar.setMax(100);
        mPageLoadingProgressBar.setProgressDrawable(ContextCompat.getDrawable(this, R.drawable.color_progressbar));
    }

    private void init() {

        mWebView = new X5WebView(this, null);

        mViewParent.addView(mWebView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));

        initProgressBar();
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                LogU.t("u-" + url);
                // mTestHandler.sendEmptyMessage(MSG_OPEN_TEST_URL);
                mTestHandler.sendEmptyMessageDelayed(MSG_OPEN_TEST_URL, 5000);// 5s?
                if (Build.VERSION.SDK_INT >= 16)
                    changGoForwardButton(view);
                /* mWebView.showLog("test Log"); */
            }


        });

        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsConfirm(WebView arg0, String arg1, String arg2,
                                       JsResult arg3) {
                return super.onJsConfirm(arg0, arg1, arg2, arg3);
            }

            View myVideoView;
            View myNormalView;
            CustomViewCallback callback;

            // /////////////////////////////////////////////////////////
            //


            // For Android 3.0+
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                BrowserActivity.this.uploadFile = uploadFile;
                openFileChooseProcess();
            }

            // For Android < 3.0
            public void openFileChooser(ValueCallback<Uri> uploadMsgs) {
                BrowserActivity.this.uploadFile = uploadFile;
                openFileChooseProcess();
            }

            // For Android  > 4.1.1
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                BrowserActivity.this.uploadFile = uploadFile;
                openFileChooseProcess();
            }

            // For Android  >= 5.0
            public boolean onShowFileChooser(com.tencent.smtt.sdk.WebView webView,
                                             ValueCallback<Uri[]> filePathCallback,
                                             WebChromeClient.FileChooserParams fileChooserParams) {
                BrowserActivity.this.uploadFiles = filePathCallback;
                openFileChooseProcess();
                return true;
            }

            /**
             * 全屏播放配置
             */
            @Override
            public void onShowCustomView(View view,
                                         CustomViewCallback customViewCallback) {
                FrameLayout normalView = (FrameLayout) findViewById(R.id.web_filechooser);
                ViewGroup viewGroup = (ViewGroup) normalView.getParent();
                viewGroup.removeView(normalView);
                viewGroup.addView(view);
                myVideoView = view;
                myNormalView = normalView;
                callback = customViewCallback;
            }

            @Override
            public void onHideCustomView() {
                if (callback != null) {
                    callback.onCustomViewHidden();
                    callback = null;
                }
                if (myVideoView != null) {
                    ViewGroup viewGroup = (ViewGroup) myVideoView.getParent();
                    viewGroup.removeView(myVideoView);
                    viewGroup.addView(myNormalView);
                }
            }

            @Override
            public boolean onJsAlert(WebView arg0, String arg1, String arg2,
                                     JsResult arg3) {
                /**
                 * 这里写入你自定义的window alert
                 */
                return super.onJsAlert(null, arg1, arg2, arg3);
            }

            @Override
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                mPageLoadingProgressBar.setProgress(i);
                if (i >= 100) {
                    mPageLoadingProgressBar.setProgress(0);
                }
            }
        });

        mWebView.setDownloadListener(new DownloadListener() {

            @Override
            public void onDownloadStart(final String arg0, String arg1, String arg2,
                                        String arg3, long arg4) {
                TbsLog.d(TAG, "url: " + arg0);
                new AlertDialog.Builder(BrowserActivity.this)
                        .setTitle("allow to download？")
                        .setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        Toast.makeText(
                                                BrowserActivity.this,
                                                "i'll download...",
                                                Toast.LENGTH_LONG).show();
//                                        downloadUtil.downloadAPK("http://192.168.1.104:8080/XXX.apk", "XXX.apk");
//                                        LogU.t("url--"+arg0);
                                        String filename = CheckUtil.findFileName(arg0);
                                        if (!CommUtil.isNull(filename)) {
                                            downloadUtil.downloadAPK(arg0, filename);
                                        } else {
                                            Date date = new Date();
                                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
                                            downloadUtil.downloadAPK(arg0, sdf.format(date));

                                        }

                                    }
                                })
                        .setNegativeButton("no",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        // TODO Auto-generated method stub
                                        Toast.makeText(
                                                BrowserActivity.this,
                                                "fake message: refuse download...",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .setOnCancelListener(
                                new DialogInterface.OnCancelListener() {

                                    @Override
                                    public void onCancel(DialogInterface dialog) {
                                        // TODO Auto-generated method stub
                                        Toast.makeText(
                                                BrowserActivity.this,
                                                "fake message: refuse download...",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }).show();
            }
        });

        WebSettings webSetting = mWebView.getSettings();
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(false);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0)
                .getPath());
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // webSetting.setPreFectch(true);
        long time = System.currentTimeMillis();


        if (!CommUtil.isNull(webUrl)) {
            mWebView.loadUrl(webUrl);
        }
        TbsLog.d("time-cost", "cost time: "
                + (System.currentTimeMillis() - time));
//        CookieSyncManager.createInstance(this);
//        CookieSyncManager.getInstance().sync();
    }

    private ArrayList<String> mImageList;

    private void openFileChooseProcess() {
//        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//        i.addCategory(Intent.CATEGORY_OPENABLE);
//        i.setType("*/*");
//        startActivityForResult(Intent.createChooser(i, "test"), 0);


        Point p = CommUtil.getSize(this);

        Album.albumRadio(this)
                .statusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
                .toolBarColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .title("相册") // Title.
//                .selectCount(1) // Choose up to a few pictures.
                .columnCount(p.x >= 720 ? 4 : 3) // 宽度大于720px则显示4列
                .camera(true) // Have a camera function.
//                .checkedList(mImageList) // Has selected the picture, automatically select.
                .start(REQUEST_WEBVIEW);
    }

    private void initBtnListener() {
        mBack = (ImageButton) findViewById(R.id.btnBack1);
        mForward = (ImageButton) findViewById(R.id.btnForward1);
        mExit = (ImageButton) findViewById(R.id.btnExit1);
        mHome = (ImageButton) findViewById(R.id.btnHome1);
        mGo = (Button) findViewById(R.id.btnGo1);
        mUrl = (EditText) findViewById(R.id.editUrl1);
        mMore = (ImageButton) findViewById(R.id.btnMore);
        if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 16) {
            mBack.setImageAlpha(disable);
            mForward.setImageAlpha(disable);
            mHome.setImageAlpha(disable);
        }
        mHome.setEnabled(false);

        mBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mWebView != null && mWebView.canGoBack())
                    mWebView.goBack();
            }
        });

        mForward.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mWebView != null && mWebView.canGoForward())
                    mWebView.goForward();
            }
        });

        mGo.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String url = mUrl.getText().toString();
                mWebView.loadUrl(url);
                mWebView.requestFocus();
            }
        });

        mMore.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(BrowserActivity.this, "not completed",
                        Toast.LENGTH_LONG).show();
            }
        });

        mUrl.setOnFocusChangeListener(new OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mGo.setVisibility(View.VISIBLE);
                    if (null == mWebView.getUrl())
                        return;
                    if (mWebView.getUrl().equalsIgnoreCase(mHomeUrl)) {
                        mUrl.setText("");
                        mGo.setText("首页");
                        mGo.setTextColor(0X6F0F0F0F);
                    } else {
                        mUrl.setText(mWebView.getUrl());
                        mGo.setText("进入");
                        mGo.setTextColor(0X6F0000CD);
                    }
                } else {
                    mGo.setVisibility(View.GONE);
                    String title = mWebView.getTitle();
                    if (title != null && title.length() > MAX_LENGTH)
                        mUrl.setText(title.subSequence(0, MAX_LENGTH) + "...");
                    else
                        mUrl.setText(title);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }

        });

        mUrl.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

                String url = null;
                if (mUrl.getText() != null) {
                    url = mUrl.getText().toString();
                }

                if (url == null
                        || mUrl.getText().toString().equalsIgnoreCase("")) {
                    mGo.setText("请输入网址");
                    mGo.setTextColor(0X6F0F0F0F);
                } else {
                    mGo.setText("进入");
                    mGo.setTextColor(0X6F0000CD);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub

            }
        });

        mHome.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mWebView != null)
                    mWebView.loadUrl(mHomeUrl);
            }
        });

        mExit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

//                android.os.Process.killProcess(android.os.Process.myPid());
                BrowserActivity.this.finish();
            }
        });
    }

    boolean[] m_selected = new boolean[]{true, true, true, true, false,
            false, true};

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView != null && mWebView.canGoBack()) {
                mWebView.goBack();
                if (Build.VERSION.SDK_INT >= 16)
                    changGoForwardButton(mWebView);
                return true;
            } else
                return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TbsLog.d(TAG, "onActivityResult, requestCode:" + requestCode
                + ",resultCode:" + resultCode);
/*
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    if (null != uploadFile) {
                        Uri result = data == null || resultCode != RESULT_OK ? null
                                : data.getData();
                        uploadFile.onReceiveValue(result);
                        uploadFile = null;
                    }
                    if (null != uploadFiles) {
                        Uri result = data == null || resultCode != RESULT_OK ? null
                                : data.getData();
                        uploadFiles.onReceiveValue(new Uri[]{result});
                        uploadFiles = null;
                    }
                    break;
                default:
                    break;
            }
        } else if (resultCode == RESULT_CANCELED) {
            if (null != uploadFile) {
                uploadFile.onReceiveValue(null);
                uploadFile = null;
            }

            if (null != uploadFiles) {
                uploadFiles.onReceiveValue(null);
                uploadFiles = null;
            }

        }
*/
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_WEBVIEW: {
                    //选取图片，选取完成后进入裁剪
                    if (resultCode == RESULT_OK) { // Successfully.
                        mImageList = Album.parseResult(data); // Parse select result.

                        if (null != uploadFile) {
//                            Uri result = data == null || resultCode != RESULT_OK ? null
//                                    : data.getData();
                            File f = new File(mImageList.get(0));
                            uploadFile.onReceiveValue(Uri.fromFile(f));
                            uploadFile = null;
                        }
                        if (null != uploadFiles) {
//                            Uri result = data == null || resultCode != RESULT_OK ? null
//                                    : data.getData();
                            File f = new File(mImageList.get(0));
                            uploadFiles.onReceiveValue(new Uri[]{Uri.fromFile(f)});
                            uploadFiles = null;
                        }
                        LogU.t(mImageList.size() + "-select");
                    } else if (resultCode == RESULT_CANCELED) { // User canceled.
                        Snackbar.make(ll_x5_layout, "没有选择图片", Snackbar.LENGTH_LONG).show();
                    }
                    break;
                }
            }
        } else {
            Snackbar.make(ll_x5_layout, "没有选择图片", Snackbar.LENGTH_LONG).show();
            if (null != uploadFile) {
                uploadFile.onReceiveValue(null);
                uploadFile = null;
            }

            if (null != uploadFiles) {
                uploadFiles.onReceiveValue(null);
                uploadFiles = null;
            }
        }


    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent == null || mWebView == null || intent.getData() == null)
            return;
        mWebView.loadUrl(intent.getData().toString());
    }

    @Override
    protected void onDestroy() {
//        if (mTestHandler != null)
//            mTestHandler.removeCallbacksAndMessages(null);
//        if (mWebView != null)
//            mWebView.destroy();

//        if(mWebView != null) {
//            mWebView.getSettings().setBuiltInZoomControls(true);
//            mWebView.setVisibility(View.GONE);
//            long timeout = ViewConfiguration.getZoomControlsTimeout();//timeout ==3000
////            Log.i("time==",timeout+"");
//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    // TODO Auto-generated method stub
//                    mWebView.destroy();
//                }
//            }, timeout);
//        }


        if (mWebView != null) {
            mWebView.getSettings().setBuiltInZoomControls(true);
            mWebView.setVisibility(View.GONE);
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }

    public static final int MSG_OPEN_TEST_URL = 0;
    public static final int MSG_INIT_UI = 1;
    private final int mUrlStartNum = 0;
    private int mCurrentUrl = mUrlStartNum;
    private Handler mTestHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_OPEN_TEST_URL:
                    if (!mNeedTestPage) {
                        return;
                    }

                    String testUrl = "file:///sdcard/outputHtml/html/"
                            + Integer.toString(mCurrentUrl) + ".html";
                    if (mWebView != null) {
                        mWebView.loadUrl(testUrl);
                    }

                    mCurrentUrl++;
                    break;
                case MSG_INIT_UI:
                    init();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    @Override
    public void updateView(Object serverData) {

    }

    @Override
    public void updateError(String error) {

    }

    @Override
    public void requestStart() {

    }

    @Override
    public void requestEnd() {

    }
}
