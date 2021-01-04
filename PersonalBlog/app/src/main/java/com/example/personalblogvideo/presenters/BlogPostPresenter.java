package com.example.personalblogvideo.presenters;

import android.graphics.drawable.AdaptiveIconDrawable;

import com.example.personalblogvideo.database.AppDatabase;
import com.example.personalblogvideo.models.BlogPost;

public class BlogPostPresenter {
    boolean didUpdate = false;
    public interface MVPView extends BaseMVPView {
        void renderPost(BlogPost post);
        void goBackToPostsPage(BlogPost post, boolean didDelete, boolean didUpdate);
        void goToEditPage(BlogPost post);
        void displayDeleteConfirmation();
    }

    MVPView view;
    AppDatabase database;
    BlogPost post;

    public BlogPostPresenter(MVPView view) {
        this.view = view;
        database = view.getContextDatabase();
    }

    public void loadPost(long postId) {
        new Thread(() -> {
            post = database.getBlogPostDao().findById(postId);
            view.renderPost(post);
        }).start();
    }

    public void handleDeletePressed() {
        view.displayDeleteConfirmation();
    }

    public void deletePost() {
        new Thread(() -> {
            database.getBlogPostDao().delete(post);
            view.goBackToPostsPage(post, true, false);
        }).start();
    }

    public void handleEditClick() {
        view.goToEditPage(post);
    }

    public void handleBlogPostUpdated(BlogPost post) {
        didUpdate = true;
        this.post = post;
        view.renderPost(post);
    }

    public void handleBackPressed() {
        view.goBackToPostsPage(post, false, didUpdate);
    }
}
