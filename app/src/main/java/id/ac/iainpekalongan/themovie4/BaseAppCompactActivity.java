package id.ac.iainpekalongan.themovie4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;

public abstract class BaseAppCompactActivity extends AppCompatActivity {
    public static final String KEY_FRAGMENT = "fragment";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidNetworking.initialize(this);
    }
}
