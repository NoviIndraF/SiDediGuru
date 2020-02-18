package com.nifcompany.sidediguru;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;
import com.nifcompany.sidediguru.Get.GetProfileGuru.Data;

import de.hdodenhof.circleimageview.CircleImageView;

public class BottomSheetNavigationFragment extends BottomSheetDialogFragment {
    SharedPrefManager sharedPrefManager;
    Data data;

    public static BottomSheetNavigationFragment newInstance() {

        Bundle args = new Bundle();

        BottomSheetNavigationFragment fragment = new BottomSheetNavigationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            //check the slide offset and change the visibility of close button
            if (slideOffset > 0.5) {
                closeButton.setVisibility(View.VISIBLE);
            } else {
                closeButton.setVisibility(View.GONE);
            }
        }
    };

    private ImageView closeButton ;
    private CircleImageView imageProfile;
    private TextView tvUser, tvEmail;
    private RelativeLayout rlProfile;

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        //Get the content View
        View contentView = View.inflate(getContext(), R.layout.bottom_navigation_drawer, null);
        dialog.setContentView(contentView);

        rlProfile = dialog.findViewById(R.id.RlProfile);
        tvUser = dialog.findViewById(R.id.user_name);
        tvEmail = dialog.findViewById(R.id.user_email);
        imageProfile = dialog.findViewById(R.id.profile_image);

        data = ((MainActivity)getActivity()).getDataProfile();

        if (data!=null){
            tvUser.setText(data.getName());
            tvEmail.setText(data.getEmail());

            Glide.with(imageProfile.getContext())
                    .load(data.getBiodata().getImageProfile())
                    .apply(new RequestOptions().override(300,300))
                    .into(imageProfile);

            rlProfile.setClickable(true);
            rlProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), Profile.class);
                    startActivity(intent);
                }
            });
        }

        NavigationView navigationView = contentView.findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_about:
                        break;
                    case R.id.nav_logout:
                            ShowPopupLogout();
                        break;
                }
                return false;
            }
        });
        closeButton = contentView.findViewById(R.id.close_image_view);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
    }

    public void ShowPopupLogout() {

        Button btnDeleteClassYes, btnDeleteClassNo;

        final Dialog dialogLogout = new Dialog(getContext());
        dialogLogout.setContentView(R.layout.pop_up_logout);

        btnDeleteClassYes           = dialogLogout.findViewById(R.id.BtnLogoutYes);
        btnDeleteClassNo             = dialogLogout.findViewById(R.id.BtnLogoutNo);

        dialogLogout.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogLogout.show();

        btnDeleteClassYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogLogout.dismiss();

                sharedPrefManager = new SharedPrefManager(getContext());
                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                startActivity(new Intent(getContext(), Login.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                getActivity().finish();
            }
        });

        btnDeleteClassNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogLogout.dismiss();
            }
        });
    }

}