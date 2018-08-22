package in.kestone.testingapp.Speaker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.kestone.testingapp.R;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_message)
    TextView message;
    @BindView(R.id.webview)
    WebView webView;
    String link=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        link = getIntent().getStringExtra("link");

        message.setText(link);
        webView.loadUrl(link);


    }
}
