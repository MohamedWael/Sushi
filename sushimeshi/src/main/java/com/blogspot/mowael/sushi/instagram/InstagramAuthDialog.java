package com.blogspot.mowael.sushi.instagram;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.blogspot.mowael.sushi.databinding.DialogInstagramAuthBinding;

/**
 * Created by mohamed on 1/24/18.
 */

public class InstagramAuthDialog extends DialogFragment implements InstagramAuthContract.InstagramAuthView {

    private String TAG = InstagramAuthDialog.class.getSimpleName();
    private DialogInstagramAuthBinding binding;
    private InstagramAuthContract.InstagramAuthPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogInstagramAuthBinding.inflate(inflater, container, false);
        setUpWepView();
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().loadUri();
    }

    public InstagramAuthContract.InstagramAuthPresenter getPresenter() {
        if (presenter == null) {
            presenter = new InstagramAuthPresenter(this);
        }
        return presenter;
    }

    private void setUpWepView() {
        binding.wvLoginView.setVerticalScrollBarEnabled(false);
        binding.wvLoginView.setHorizontalScrollBarEnabled(false);
        binding.wvLoginView.getSettings().setJavaScriptEnabled(true);
        binding.wvLoginView.setWebViewClient(getPresenter().getClient());

    }

    public void showDialog(FragmentManager manager) {
        show(manager, TAG);
    }

    public void setInstagramAuthListener(InstagramAuthListener listener) {
        getPresenter().setAuthListener(listener);
    }

    public void setAuthenticationData(@StringRes int clientID, @StringRes int redirectUri) {
        getPresenter().setAuthenticationData(getString(clientID), getString(redirectUri));
    }

    public void setAuthenticationData(String clientID, String redirectUri) {
        getPresenter().setAuthenticationData(clientID, redirectUri);
    }

    private void progressVisibility(boolean visible) {
        binding.progress.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void loadUri(String url) {
        binding.wvLoginView.loadUrl(url);
    }

    @Override
    public void dismissDialog() {
        dismiss();
    }

    @Override
    public void refreshView() {
        if (getView() != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            getView().setLayoutParams(params);
            getView().invalidate();
        }

    }

    @Override
    public void showProgress() {
        progressVisibility(true);
    }

    @Override
    public void stopProgress() {
        progressVisibility(false);
    }

    @Override
    public void setTitle(String title) {
        if (getDialog() != null)
            getDialog().setTitle(title);
    }

    @Override
    public void showError(String description) {

    }

    @Override
    public void onDestroy() {
        getPresenter().onDestroy();
        presenter = null;
        super.onDestroy();
    }
}
