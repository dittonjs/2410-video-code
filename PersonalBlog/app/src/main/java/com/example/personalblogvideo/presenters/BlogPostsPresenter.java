package com.example.personalblogvideo.presenters;

import com.example.personalblogvideo.database.AppDatabase;
import com.example.personalblogvideo.models.BlogPost;

import java.util.ArrayList;

public class BlogPostsPresenter {
    ArrayList<BlogPost> posts = new ArrayList<>();

    public interface MVPView extends BaseMVPView {
        void goToNewBlogPostPage();
        void renderBlogPost(BlogPost post);
        void goToBlogPostPage(BlogPost post);
        void removeBlogPostView(long id);
        void updatePostView(BlogPost post);
    }

    MVPView view;
    AppDatabase database;

    public BlogPostsPresenter(MVPView view) {
        this.view = view;
        database = view.getContextDatabase();
        new Thread(() -> {
            posts = (ArrayList<BlogPost>) database.getBlogPostDao().getAll();
            posts.forEach(post -> {
                view.renderBlogPost(post);
            });
        }).start();
    }

    public void handleNewBlogPostPress() {
        view.goToNewBlogPostPage();
    }

    public void handleNewBlogPostCreated(BlogPost post) {
        posts.add(post);
        view.renderBlogPost(post);
    };

    public void handleBlogPostSelected(BlogPost post) {
        view.goToBlogPostPage(post);
    }

    public void handleBlogPostDeleted(long id) {
        posts.removeIf(post -> {
            return post.id == id;
        });
        view.removeBlogPostView(id);
    }

    public void handleBlogPostUpdated(BlogPost post) {
        for (int i = 0; i < posts.size(); i ++) {
            if (posts.get(i).id == post.id) {
                posts.set(i, post);
            }
        }
        view.updatePostView(post);
    }
}
