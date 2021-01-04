package com.example.personalblogvideo.components;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.personalblogvideo.R;
import com.example.personalblogvideo.models.BlogPost;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.net.URI;

public class BlogPostCard extends MaterialCardView {

    public interface PostSelectedEventListener {
        public void onSelect();
    }

    private BlogPost post;
    PostSelectedEventListener listener;

    public BlogPostCard(Context context, BlogPost post, PostSelectedEventListener listener) {
        super(context);
        this.post = post;
        this.listener = listener;
        setTag(post.id);
        createViews();
    }

    public void setPost(BlogPost post) {
        this.post = post;
        createViews();
    }

    private void createViews() {
        // cards layout params
        removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(64, 24, 64, 24);
        setLayoutParams(params);

        // wraps all sub layouts
        LinearLayout wrapper = new LinearLayout(getContext());
        wrapper.setOrientation(LinearLayout.VERTICAL);

        // contains the image
        LinearLayout top = new LinearLayout(getContext());
        top.setOrientation(LinearLayout.VERTICAL);

        // contains the content
        LinearLayout body = new LinearLayout(getContext());
        body.setOrientation(LinearLayout.VERTICAL);
        body.setPadding(64, 48, 64, 0);

        //contain the buttons
        LinearLayout bottom = new LinearLayout(getContext());

        wrapper.addView(top);
        wrapper.addView(body);
        wrapper.addView(bottom);
        addView(wrapper);

        if (!post.pictureUri.equals("")) {
            AppCompatImageView imageView = new AppCompatImageView(getContext());
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 480);
            imageView.setLayoutParams(imageParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageURI(Uri.parse(post.pictureUri));
            top.addView(imageView);
        }




        MaterialTextView titleView = new MaterialTextView(getContext(), null, R.attr.textAppearanceHeadline6);
        titleView.setText(post.title);

        MaterialTextView descriptionView = new MaterialTextView(getContext());
        descriptionView.setText(post.description);

        MaterialTextView contentsView = new MaterialTextView(getContext());
        LinearLayout.LayoutParams contentsParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentsParams.setMargins(0, 24, 0, 24);
        contentsView.setLayoutParams(contentsParams);
        contentsView.setMaxLines(3);
        contentsView.setEllipsize(TextUtils.TruncateAt.END);
        contentsView.setText(post.contents);


        body.addView(titleView);
        body.addView(descriptionView);
        body.addView(contentsView);

        MaterialButton readButton = new MaterialButton(getContext(), null, R.attr.borderlessButtonStyle);
        readButton.setText("Read");
        readButton.setOnClickListener((view) -> {
            listener.onSelect();
        });

        bottom.addView(readButton);
    }
}
