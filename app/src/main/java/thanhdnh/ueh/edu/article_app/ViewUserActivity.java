package thanhdnh.ueh.edu.article_app;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ViewUserActivity extends AppCompatActivity {
  ImageView iv_detail;
  ProgressBar progressBar;
  TextView tv_username, tv_email, tv_description, tv_hobby, tv_back;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_user);
    getSupportActionBar().hide();

    iv_detail = findViewById(R.id.iv_detail);
    progressBar = findViewById(R.id.progressBar);
    tv_username = findViewById(R.id.tv_username);
    tv_email = findViewById(R.id.tv_email);
    tv_description = findViewById(R.id.tv_description);
    tv_hobby = findViewById(R.id.tv_hobby);
    tv_back = findViewById(R.id.tv_back);
    tv_back.setOnClickListener(v -> finish());
    int id = (int) getIntent().getLongExtra("id", 0);
    UserProfile user = UserData.getUserFromId(id);

    tv_username.setText(user.getUsername());
    tv_email.setText(user.getEmail());
    tv_description.setText(user.getDescription());
    tv_hobby.setText("Sở thích: " + user.getHobby());

    Handler mainHandler = new Handler(Looper.getMainLooper());
    Downloader.downloadWithProgress(user.getAvatar_url(), mainHandler, getBaseContext(), getCacheDir(), progressBar, iv_detail);
  }
}