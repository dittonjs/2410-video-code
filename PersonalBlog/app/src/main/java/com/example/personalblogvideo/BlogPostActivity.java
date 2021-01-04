package com.example.personalblogvideo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

import com.example.personalblogvideo.models.BlogPost;
import com.example.personalblogvideo.presenters.BlogPostPresenter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

public class BlogPostActivity extends BaseActivity implements BlogPostPresenter.MVPView {
    BlogPostPresenter presenter;
    private final int UPDATE_POST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BlogPostPresenter(this);
        Intent intent = getIntent();
        long id = intent.getLongExtra("postId", -1);
        presenter.loadPost(id);
    }

    @Override
    public void renderPost(BlogPost post) {
        // wraps all sub layouts
        runOnUiThread(() -> {
            FrameLayout frameLayout = new FrameLayout(this);
            LinearLayout wrapper = new LinearLayout(this);
            frameLayout.addView(wrapper);
            wrapper.setOrientation(LinearLayout.VERTICAL);

            if (!post.pictureUri.equals("")) {
                AppCompatImageView imageView = new AppCompatImageView(this);
                LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 480);
                imageView.setLayoutParams(imageParams);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setImageURI(Uri.parse(post.pictureUri));
                wrapper.addView(imageView);
            }

            // contains the content
            LinearLayout body = new LinearLayout(this);
            body.setOrientation(LinearLayout.VERTICAL);
            body.setPadding(64, 48, 64, 0);
            ScrollView scrollView = new ScrollView(this);
            scrollView.addView(body);
            wrapper.addView(scrollView);

            MaterialTextView titleView = new MaterialTextView(this, null, R.attr.textAppearanceHeadline3);
            titleView.setText(post.title);

            MaterialTextView descriptionView = new MaterialTextView(this, null, R.attr.textAppearanceHeadline5);
            descriptionView.setText(post.description);

            MaterialTextView contentsView = new MaterialTextView(this, null, R.attr.textAppearanceBody1);
            LinearLayout.LayoutParams contentsParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            contentsParams.setMargins(0, 24, 0, 24);
            contentsView.setLayoutParams(contentsParams);
            contentsView.setText(post.contents);

            body.addView(titleView);
            body.addView(descriptionView);
            body.addView(contentsView);

            FloatingActionButton fab = new FloatingActionButton(this);
            fab.setImageResource(R.drawable.ic_baseline_edit_24);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (post.pictureUri.equals("")) {
                params.setMargins(0, 0, 64, 64);
                params.gravity = (Gravity.BOTTOM | Gravity.RIGHT);
            } else {
                params.setMargins(0, 408, 64, 0);
                params.gravity = Gravity.RIGHT;
            }
            fab.setLayoutParams(params);
            frameLayout.addView(fab);

            PopupMenu popupMenu = new PopupMenu(this, fab);
            popupMenu.getMenu().add("Edit");
            popupMenu.getMenu().add("Delete");

            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (item.getTitle().toString().equals("Edit")) {
                        // handle going to edit
                        presenter.handleEditClick();
                    } else {
                        presenter.handleDeletePressed();
                        // handle going to delete
                    }
                    return false;
                }
            });

            fab.setOnClickListener((view) -> {
                popupMenu.show();
            });
            setContentView(frameLayout);
        });

    }

    @Override
    public void goBackToPostsPage(BlogPost post, boolean didDelete, boolean didUpdate) {
        Intent intent = new Intent();
        if (didDelete) {
            intent.putExtra("id", post.id);
            setResult(BlogPostsActivity.DELETED_RESULT, intent);
        } else if (didUpdate) {
            intent.putExtra("post", post);
            setResult(BlogPostsActivity.UPDATED_RESULT, intent);
        } else {
            setResult(Activity.RESULT_CANCELED, null);
        }

        finish();
    }

    @Override
    public void goToEditPage(BlogPost post) {
        Intent intent = new Intent(this, CreateOrUpdateBlogPostActivity.class);
        intent.putExtra("id", post.id);
        startActivityForResult(intent, UPDATE_POST);
    }

    @Override
    public void displayDeleteConfirmation() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Are you sure you want to delete this post?")
                .setPositiveButton("Delete", (view, i) -> {
                    presenter.deletePost();
                })
                .setNeutralButton("Cancel", (view, i) -> {
                    view.dismiss();
                })
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UPDATE_POST && resultCode == Activity.RESULT_OK) {
            BlogPost post = (BlogPost) data.getSerializableExtra("result");
            presenter.handleBlogPostUpdated(post);
        }
    }

    @Override
    public void onBackPressed() {
        presenter.handleBackPressed();
    }
}
