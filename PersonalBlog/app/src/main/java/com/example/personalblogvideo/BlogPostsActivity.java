package com.example.personalblogvideo;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.personalblogvideo.components.BlogPostCard;
import com.example.personalblogvideo.models.BlogPost;
import com.example.personalblogvideo.presenters.BlogPostsPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BlogPostsActivity extends BaseActivity implements BlogPostsPresenter.MVPView {
    BlogPostsPresenter presenter;
    LinearLayout postsLayout;
    private final int CREATE_NEW_POST = 1;
    private final int MODIFY_POST = 2;

    public final static int DELETED_RESULT = 1;
    public final static int UPDATED_RESULT = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BlogPostsPresenter(this);
        FrameLayout mainLayout = new FrameLayout(this);
        FloatingActionButton fab = new FloatingActionButton(this);
        FrameLayout.LayoutParams fabParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ScrollView scrollView = new ScrollView(this);
        postsLayout = new LinearLayout(this);
        postsLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(postsLayout);
        mainLayout.addView(scrollView);

        fabParams.setMargins(0, 0, 64, 64);
        fabParams.gravity = (Gravity.BOTTOM | Gravity.RIGHT);
        fab.setLayoutParams(fabParams);
        fab.setImageResource(R.drawable.ic_baseline_add_24);
        mainLayout.addView(fab);

        fab.setOnClickListener((view) -> {
            presenter.handleNewBlogPostPress();
        });

        setContentView(mainLayout);
    }

    @Override
    public void goToNewBlogPostPage() {
        Intent intent = new Intent(this, CreateOrUpdateBlogPostActivity.class);
        startActivityForResult(intent, CREATE_NEW_POST);
    }

    @Override
    public void renderBlogPost(BlogPost post) {
        runOnUiThread(() -> {
            BlogPostCard card = new BlogPostCard(
                    this,
                    post,
                    () -> {
                        presenter.handleBlogPostSelected(post);
                    }
                );
            card.setTag(post.id);
            postsLayout.addView(card);
        });
    }

    @Override
    public void goToBlogPostPage(BlogPost post) {
        Intent intent = new Intent(this, BlogPostActivity.class);
        intent.putExtra("postId", post.id);
        startActivityForResult(intent, MODIFY_POST);
    }

    @Override
    public void removeBlogPostView(long id) {
        View view = postsLayout.findViewWithTag(id);
        postsLayout.removeView(view);
    }

    @Override
    public void updatePostView(BlogPost post) {
        BlogPostCard view = postsLayout.findViewWithTag(post.id);
        view.setPost(post);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_NEW_POST && resultCode == Activity.RESULT_OK) {
            BlogPost post = (BlogPost) data.getSerializableExtra("result");
            presenter.handleNewBlogPostCreated(post);
        }
        if (requestCode == MODIFY_POST && resultCode == DELETED_RESULT) {
            long id = data.getLongExtra("id", -1);
            presenter.handleBlogPostDeleted(id);
        }
        if (requestCode == MODIFY_POST && resultCode == UPDATED_RESULT) {
            BlogPost post = (BlogPost) data.getSerializableExtra("post");
            presenter.handleBlogPostUpdated(post);
        }
    }


}