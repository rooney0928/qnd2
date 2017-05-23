package com.app.qunadai.content.ui.product;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.app.qunadai.R;
import com.app.qunadai.content.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/9/19.
 */
public class H5WebActivity extends BaseActivity {

    @BindView(R.id.pb_loading)
    ProgressBar pb_loading;
    @BindView(R.id.webView)
    WebView mWebView;
    private String webUrl = "";


    private ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> mUploadMessageForAndroid5;

    private WebChromeClient wcc;
    private final static int FILECHOOSER_RESULTCODE = 1;
    public final static int FILECHOOSER_RESULTCODE_FOR_ANDROID_5 = 2;





    @Override
    protected void updateTopViewHideAndShow() {

    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this,R.layout.activity_h5,null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        //重写返回按钮
        findViewById(R.id.rl_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (getIntent() != null && getIntent().getExtras() != null) {
            webUrl = getIntent().getExtras().getString("url");
            String title =getIntent().getStringExtra("title");


            setTitleText(title);
        }
        initWebView();
    }

    @Override
    public void initViewData() {

    }

    private void initWebView() {
        String ua = mWebView.getSettings().getUserAgentString();
        mWebView.getSettings().setUserAgentString(ua + ";qnd-Android");
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setAllowContentAccess(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);//设置WebView可触摸放大缩小
        mWebView.getSettings().setAllowFileAccess(true);// 设置允许访问文件数据
        mWebView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);


        //自适应屏幕
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        mWebView.setWebViewClient(new WebViewClient() {
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();  // 接受所有网站的证书
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                //调用拨号程序
                if (url.startsWith("mailto:") || url.startsWith("geo:") || url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
                Log.e("URL", url);
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (isFinishing())
                    return;
                pb_loading.setVisibility(View.GONE);
            }
        });
        mWebView.loadUrl(webUrl);

        wcc = new WebChromeClient() {

            public void onProgressChanged(WebView view, int progress) {
//                progressCtrl.setProgress(progress);
                if (isFinishing())
                    return;
                pb_loading.setProgress(progress);
                if (progress > 95) {
                    pb_loading.setVisibility(View.GONE);
                }
            }


            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog,
                                          boolean isUserGesture, Message resultMsg) {
                mWebView = new WebView(H5WebActivity.this);
                final WebSettings settings = mWebView.getSettings();
                settings.setJavaScriptEnabled(true);
                mWebView.setWebChromeClient(this);
                WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
                transport.setWebView(mWebView);
                resultMsg.sendToTarget();
                return true;
            }

            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
//                T.showLong(getApplicationContext(), message);
//            	Toast.makeText(WebViewActivity.this, message , Toast.LENGTH_LONG).show();
                new AlertDialog.Builder(H5WebActivity.this)
                        .setMessage(message)
                        .setPositiveButton("确定", null)
                        .show();
                result.cancel();
                return true;
            }

            @Override
            public void onReceivedTitle(WebView view, String mtTitle) {
                // TODO Auto-generated method stub
                super.onReceivedTitle(view, mtTitle);

                //title_middle_textview.setText(mtTitle);
            }

            //扩展浏览器上传文件
            //3.0++版本
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                openFileChooserImpl(uploadMsg);
            }

            //3.0--版本
            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                openFileChooserImpl(uploadMsg);
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                openFileChooserImpl(uploadMsg);
            }

            // For Android > 5.0
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> uploadMsg, FileChooserParams fileChooserParams) {
                openFileChooserImplForAndroid5(uploadMsg);
                return true;
            }

//			@Override
//			public boolean onShowFileChooser(WebView webView,
//					ValueCallback<Uri[]> filePathCallback,
//					FileChooserParams fileChooserParams) {
//				mUploadMessage = filePathCallback;
//				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//				intent.addCategory(Intent.CATEGORY_OPENABLE);
//				intent.setType("image/*");
//				startActivityForResult(
//						Intent.createChooser(intent, "完成操作需要使用"),
//						FILECHOOSER_RESULTCODE);
//				return true;
//			}
        };
        mWebView.setWebChromeClient(wcc);

    }


    private void openFileChooserImpl(ValueCallback<Uri> uploadMsg) {
        mUploadMessage = uploadMsg;
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
    }

    private void openFileChooserImplForAndroid5(ValueCallback<Uri[]> uploadMsg) {
        mUploadMessageForAndroid5 = uploadMsg;
        Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
        contentSelectionIntent.setType("image/*");

        Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");

        startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE_FOR_ANDROID_5);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

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
