package com.pomac.moltaqaelaghnam.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.pomac.moltaqaelaghnam.Globals;
import com.pomac.moltaqaelaghnam.R;
import com.pomac.moltaqaelaghnam.view.adapters.DrawerAdapter;
import com.pomac.moltaqaelaghnam.view.interfaces.AppNavigator;
import com.pomac.moltaqaelaghnam.view.uimodels.DrawerItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AppNavigator {

    private TextView titleTextView;
    private TextView notificationNumberTextView;

    private ImageView drawerShowImageView;
    private ImageView backImageView;

    private ImageView navigationToMainImageView;
    private ImageView navigationToWishListImageView;
    private ImageView navigationToNotificationsImageView;
    private ImageView navigationToMessagesImageView;

    private DrawerLayout drawerLayout;

    private RecyclerView drawerItemsRecyclerView;

    private ImageView addingAdImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences(Globals.SHARED_PREFERENCES, MODE_PRIVATE);

        if (!sharedPreferences.contains(Globals.AREA_ID)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(Globals.AREA_ID, 1);
            if (editor.commit()) Log.d(Globals.TAG, "Area ID added to shared preferences");
        }

        prepareViews();

        prepareListeners();

        prepareDrawer();

        navigateToMainPage();
    }

    private void prepareDrawer() {

        ArrayList<DrawerItem> items = new ArrayList<>();

        items.add(new DrawerItem(getString(R.string.drawer_main), R.mipmap.drawer_main, v -> {
            drawerLayout.close();
            navigateToMainPage();
        }));
        items.add(new DrawerItem(getString(R.string.drawer_user), R.mipmap.drawer_user, v -> {
            drawerLayout.close();
            navigateToUserAccountPage();
        }));
        items.add(new DrawerItem(getString(R.string.drawer_info), R.mipmap.info, v -> {
            drawerLayout.close();
            navigateToInfoPage();
        }));
        items.add(new DrawerItem(getString(R.string.drawer_contact_us), R.mipmap.call, v -> {
            drawerLayout.close();
            navigateToContactUsPage();
        }));
        items.add(new DrawerItem(getString(R.string.drawer_mor), R.mipmap.more, v -> {
            drawerLayout.close();
            navigateToMorePage();
        }));

        drawerItemsRecyclerView.setAdapter(new DrawerAdapter(this, items));
        drawerItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @SuppressLint("RtlHardcoded")
    private void prepareListeners() {

        drawerShowImageView.setOnClickListener(v -> {
            drawerLayout.openDrawer(Gravity.RIGHT);
        });

        navigationToMainImageView.setOnClickListener(v -> {
            navigateToMainPage();
        });

        navigationToWishListImageView.setOnClickListener(v -> {
            navigateToWishListPage();
        });

        navigationToNotificationsImageView.setOnClickListener(v -> {
            navigateToNotificationsPage();
        });

        navigationToMessagesImageView.setOnClickListener(v -> {
            navigateToMessagesPage();
        });

        addingAdImageView.setOnClickListener(v -> {
            navigateToAddingAdPage();
        });
    }

    private void prepareViews() {

        titleTextView = findViewById(R.id.titleTextView);
        notificationNumberTextView = findViewById(R.id.notificationNumberTextView);

        drawerShowImageView = findViewById(R.id.drawerShowImageView);
        backImageView = findViewById(R.id.backImageView);

        navigationToMainImageView = findViewById(R.id.navigationToMainImageView);
        navigationToWishListImageView = findViewById(R.id.navigationToWishListImageView);
        navigationToNotificationsImageView = findViewById(R.id.navigationToNotificationsImageView);
        navigationToMessagesImageView = findViewById(R.id.navigationToMessagesImageView);

        drawerLayout = findViewById(R.id.drawerLayout);

        drawerItemsRecyclerView = findViewById(R.id.drawerItemsRecyclerView);

        addingAdImageView = findViewById(R.id.addingAdImageView);
    }

    @Override
    public void navigateToMainPage() {
        navigationToMainImageView.setImageResource(R.mipmap.home_selected);
        navigationToWishListImageView.setImageResource(R.mipmap.heart);
        navigationToNotificationsImageView.setImageResource(R.mipmap.notification);
        navigationToMessagesImageView.setImageResource(R.mipmap.speech_bubble);

        titleTextView.setText(getString(R.string.main_page));

        Navigation.findNavController(findViewById(R.id.nav_host)).navigate(R.id.mainFragment);
    }

    @Override
    public void navigateToWishListPage() {
        navigationToMainImageView.setImageResource(R.mipmap.home);
        navigationToWishListImageView.setImageResource(R.mipmap.heart_selected);
        navigationToNotificationsImageView.setImageResource(R.mipmap.notification);
        navigationToMessagesImageView.setImageResource(R.mipmap.speech_bubble);

        titleTextView.setText(getString(R.string.wish_list_page));

        Navigation.findNavController(findViewById(R.id.nav_host)).navigate(R.id.wishListFragment);
    }

    @Override
    public void navigateToNotificationsPage() {
        navigationToMainImageView.setImageResource(R.mipmap.home);
        navigationToWishListImageView.setImageResource(R.mipmap.heart);
        navigationToNotificationsImageView.setImageResource(R.mipmap.notification_selected);
        navigationToMessagesImageView.setImageResource(R.mipmap.speech_bubble);

        titleTextView.setText(getString(R.string.notifications_page));

        Navigation.findNavController(findViewById(R.id.nav_host)).navigate(R.id.notificationsFragment);
    }

    @Override
    public void navigateToMessagesPage() {
        navigationToMainImageView.setImageResource(R.mipmap.home);
        navigationToWishListImageView.setImageResource(R.mipmap.heart);
        navigationToNotificationsImageView.setImageResource(R.mipmap.notification);
        navigationToMessagesImageView.setImageResource(R.mipmap.speech_bubble_selected);

        titleTextView.setText(getString(R.string.messages_page));

        Navigation.findNavController(findViewById(R.id.nav_host)).navigate(R.id.messagesFragment);
    }

    @Override
    public void navigateToUserAccountPage() {
        navigationToMainImageView.setImageResource(R.mipmap.home);
        navigationToWishListImageView.setImageResource(R.mipmap.heart);
        navigationToNotificationsImageView.setImageResource(R.mipmap.notification);
        navigationToMessagesImageView.setImageResource(R.mipmap.speech_bubble);

        titleTextView.setText(getString(R.string.user_account_page));

        Navigation.findNavController(findViewById(R.id.nav_host)).navigate(R.id.userFragment);
    }

    @Override
    public void navigateToInfoPage() {
        navigationToMainImageView.setImageResource(R.mipmap.home);
        navigationToWishListImageView.setImageResource(R.mipmap.heart);
        navigationToNotificationsImageView.setImageResource(R.mipmap.notification);
        navigationToMessagesImageView.setImageResource(R.mipmap.speech_bubble);

        titleTextView.setText(getString(R.string.info_page));

        Navigation.findNavController(findViewById(R.id.nav_host)).navigate(R.id.infoFragment);

    }

    @Override
    public void navigateToContactUsPage() {
        navigationToMainImageView.setImageResource(R.mipmap.home);
        navigationToWishListImageView.setImageResource(R.mipmap.heart);
        navigationToNotificationsImageView.setImageResource(R.mipmap.notification);
        navigationToMessagesImageView.setImageResource(R.mipmap.speech_bubble);

        titleTextView.setText(getString(R.string.contact_us_page));

        Navigation.findNavController(findViewById(R.id.nav_host)).navigate(R.id.contactUsFragment);
    }

    @Override
    public void navigateToMorePage() {
        navigationToMainImageView.setImageResource(R.mipmap.home);
        navigationToWishListImageView.setImageResource(R.mipmap.heart);
        navigationToNotificationsImageView.setImageResource(R.mipmap.notification);
        navigationToMessagesImageView.setImageResource(R.mipmap.speech_bubble);

        titleTextView.setText(getString(R.string.more_page));

        Navigation.findNavController(findViewById(R.id.nav_host)).navigate(R.id.moreFragment);
    }

    @Override
    public void navigateToAddingAdPage() {
        navigationToMainImageView.setImageResource(R.mipmap.home);
        navigationToWishListImageView.setImageResource(R.mipmap.heart);
        navigationToNotificationsImageView.setImageResource(R.mipmap.notification);
        navigationToMessagesImageView.setImageResource(R.mipmap.speech_bubble);

        titleTextView.setText(getString(R.string.adding_ad_page));

        Navigation.findNavController(findViewById(R.id.nav_host)).navigate(R.id.addingAdFragment);
    }
}