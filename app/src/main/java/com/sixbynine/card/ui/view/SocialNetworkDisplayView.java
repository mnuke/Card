package com.sixbynine.card.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sixbynine.card.R;
import com.sixbynine.card.model.LogoSize;
import com.sixbynine.card.model.SocialNetwork;

/**
 * Created by steviekideckel on 11/9/14.
 */
public class SocialNetworkDisplayView extends LinearLayout{

    private ImageView mImageView;
    private TextView mTextView;
    private SocialNetwork mNetwork;
    private CharSequence mText;

    public SocialNetworkDisplayView(Context context) {
        this(context, null);
    }

    public SocialNetworkDisplayView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_social_network_display, this, true);

        if(attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.SocialNetworkDisplayView,
                    0, 0);

            final int networkId;
            try {
                networkId = a.getInt(R.styleable.SocialNetworkDisplayView_network, -1);
                mNetwork = SocialNetwork.fromId(networkId);
                mText = a.getString(R.styleable.SocialNetworkDisplayView_network_txt);
            } finally {
                a.recycle();
            }
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mImageView = (ImageView) findViewById(R.id.image_view);
        mTextView = (TextView) findViewById(R.id.text_view);
        if(mText != null){
            setText(mText);
        }else if(isInEditMode()){
            mTextView.setText("Your text goes here");
        }
        if(mNetwork != null){
            setNetwork(mNetwork);
        }else{
            mImageView.setImageDrawable(new ColorDrawable(getResources().getColor(android.R.color.darker_gray)));
        }

    }

    public void setText(CharSequence s){
        mTextView.setText(s);
        mText = s;
    }

    public void setText(int resId){
        mTextView.setText(resId);
        mText = getResources().getText(resId);
    }

    public void setNetwork(SocialNetwork network){
        mNetwork = network;
        mImageView.setImageResource(network.getLogo(LogoSize.LARGE));
    }
}
