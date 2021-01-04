package com.example.personalblogvideo.presenters;

import com.example.personalblogvideo.database.AppDatabase;
import com.example.personalblogvideo.models.BlogPost;

public class CreateOrUpdateBlogPostPresenter {
    public static final int DEFAULT_ID = -1;
    public interface MVPView extends BaseMVPView {
        void goBackToPreviousPage(BlogPost post);
        void goToPhotos();
        void takePicture();
        void displayImage(String uri);
        void renderPostForm(BlogPost post);
        void displayTitleError();

    }

    MVPView view;
    AppDatabase database;
    BlogPost post;

    public CreateOrUpdateBlogPostPresenter(MVPView view) {
        this.view = view;
        database = view.getContextDatabase();
    }

    public void saveBlogPost(String title, String description, String contents, String pictureUri) {
        new Thread(() -> {
            if (title.equals("")) {
                view.displayTitleError();
                return;
            }
            BlogPost postToSave;
            if (post != null) {
                postToSave = post;
            } else {
                postToSave = new BlogPost();
            }
            postToSave.title = title;
            postToSave.description = description;
            postToSave.contents = contents;
            postToSave.pictureUri = pictureUri;
            if (post != null) {
                database.getBlogPostDao().update(postToSave);
            } else {
                postToSave.id = database.getBlogPostDao().insert(postToSave);
            }

            view.goBackToPreviousPage(postToSave);
        }).start();
    }

    public void handleCancelPress() {
        view.goBackToPreviousPage(null);
    }

    public void handleSelectPicturePress() {
        view.goToPhotos();
    }

    public void handleTakePicturePress() {
        view.takePicture();
    }

    public void handlePictureSelected(String uri) {
        view.displayImage(uri);
    }

    public void loadPost(long id) {
        if (id != DEFAULT_ID) {
            new Thread(() -> {
                post = database.getBlogPostDao().findById(id);
                view.renderPostForm(post);
            }).start();
        }
    }
}
