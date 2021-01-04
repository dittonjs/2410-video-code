package com.example.personalblogvideo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.content.FileProvider;

import com.example.personalblogvideo.components.ImageSelector;
import com.example.personalblogvideo.components.MaterialInput;
import com.example.personalblogvideo.models.BlogPost;
import com.example.personalblogvideo.presenters.CreateOrUpdateBlogPostPresenter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateOrUpdateBlogPostActivity extends BaseActivity implements CreateOrUpdateBlogPostPresenter.MVPView{
    CreateOrUpdateBlogPostPresenter presenter;
    ImageSelector imageSelector;
    MaterialInput titleInput;
    MaterialInput descriptionInput;
    MaterialInput contentsInput;
    String currentPhotoPath = "";
    private static int PICK_IMAGE = 1;
    private static int TAKE_PICTURE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new CreateOrUpdateBlogPostPresenter(this);
        Intent intent = getIntent();
        long id = intent.getLongExtra("id", -1);
        presenter.loadPost(id);
        ScrollView scrollView = new ScrollView(this);
        LinearLayout mainLayout = new LinearLayout(this);
        scrollView.addView(mainLayout);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        imageSelector = new ImageSelector(
                this,
                () -> {
                    new MaterialAlertDialogBuilder(this)
                            .setTitle("Choose Image")
                            .setItems(new CharSequence[]{"From Camera", "From Photos"}, (view, i) -> {
                                if (i == 0) {
                                    presenter.handleTakePicturePress();
                                } else {
                                    presenter.handleSelectPicturePress();
                                }
                            })
                            .show();
                }
        );
        mainLayout.addView(imageSelector);

        titleInput = new MaterialInput(this, "Title");
        descriptionInput = new MaterialInput(this, "Description");
        contentsInput = new MaterialInput(this, "Contents", true);


        MaterialButton saveButton = new MaterialButton(this, null, R.attr.materialButtonStyle);
        saveButton.setText("Save");

        MaterialButton cancelButton = new MaterialButton(this, null, R.attr.borderlessButtonStyle);
        cancelButton.setText("Cancel");

        cancelButton.setOnClickListener((view) -> {
            presenter.handleCancelPress();
        });

        LinearLayout buttonsLayout = new LinearLayout(this);
        buttonsLayout.setPadding(48, 0, 48, 0);
        buttonsLayout.setGravity(Gravity.RIGHT);
        buttonsLayout.addView(cancelButton);
        buttonsLayout.addView(saveButton);

        saveButton.setOnClickListener((view) -> {
            presenter.saveBlogPost(
                    titleInput.getText().toString(),
                    descriptionInput.getText().toString(),
                    contentsInput.getText().toString(),
                    imageSelector.getImageUri()
            );
        });

        mainLayout.addView(titleInput);
        mainLayout.addView(descriptionInput);
        mainLayout.addView(contentsInput);
        mainLayout.addView(buttonsLayout);

        setContentView(scrollView);
    }

    @Override
    public void goBackToPreviousPage(BlogPost post) {
        if (post == null) {
            setResult(Activity.RESULT_CANCELED, null);
        } else {
            Intent intent = new Intent();
            intent.putExtra("result", post);
            setResult(Activity.RESULT_OK, intent);
        }
        finish();
    }

    @Override
    public void goToPhotos() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    public void takePicture() {

        // come up with unique file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_.jpg";
        // create a file
        File imageFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), imageFileName);
        currentPhotoPath = imageFile.getAbsolutePath();

        // get the location to send to the camera
        Uri photoUri = FileProvider.getUriForFile(this, "com.example.personalblogvideo.fileprovider", imageFile);

        // send the location of the file to the camera
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(intent, TAKE_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            String uri = data.getData().toString();
            presenter.handlePictureSelected(uri);
        }
        if (requestCode == TAKE_PICTURE && resultCode == Activity.RESULT_OK) {
            presenter.handlePictureSelected(currentPhotoPath);
        }
    }


    @Override
    public void displayImage(String uri) {
        imageSelector.setImageUri(uri);
    }

    @Override
    public void renderPostForm(BlogPost post) {
        runOnUiThread(() -> {
            imageSelector.setImageUri(post.pictureUri);
            titleInput.setText(post.title);
            descriptionInput.setText(post.description);
            contentsInput.setText(post.contents);
        });
    }

    @Override
    public void displayTitleError() {
        runOnUiThread(() -> {
            Snackbar.make(titleInput, "Title cannot be blank", Snackbar.LENGTH_SHORT).show();
            titleInput.setErrorEnabled(true);
            titleInput.setError("Title cannot be blank");
        });
    }
}
