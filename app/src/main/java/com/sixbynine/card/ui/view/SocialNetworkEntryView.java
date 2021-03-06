package com.sixbynine.card.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.sixbynine.card.R;
import com.sixbynine.card.model.LogoSize;
import com.sixbynine.card.model.SocialNetwork;
import com.sixbynine.card.util.Logger;

/**
 * Created by steviekideckel on 11/3/14.
 */
public class SocialNetworkEntryView extends RelativeLayout {

    private SocialNetwork mNetwork;
    private ImageButton mImageIcon;
    private EditText mEditText;
    private ImageButton mRemoveButton;
    private OnRemoveListener mListener;

    public interface OnRemoveListener {
        public void onRemove(SocialNetworkEntryView view);
        public void onNetworkTapped(SocialNetworkEntryView view);
    }

    public SocialNetworkEntryView(Context context){
        this(context, null);
    }

    public SocialNetworkEntryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_social_network_entry, this);
        mImageIcon = (ImageButton) findViewById(R.id.network_image_view);
        mImageIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onNetworkTapped(SocialNetworkEntryView.this);
                }
            }
        });
        mEditText = (EditText) findViewById(R.id.network_edit_text);
        mEditText.addTextChangedListener(mTextWatcher);
        mRemoveButton = (ImageButton) findViewById(R.id.remove_button);
        mRemoveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onRemove(SocialNetworkEntryView.this);
                }
            }
        });

        if(attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.SocialNetworkDisplayView,
                    0, 0);

            final int networkId;
            try {
                networkId = a.getInt(R.styleable.SocialNetworkDisplayView_network, -1);
            } finally {
                a.recycle();
            }

            if (networkId != -1) {
                setNetwork(SocialNetwork.fromId(networkId));
            }
        }
    }

    public void setNetwork(SocialNetwork network){
        mNetwork = network;
        if(network.getLogo(LogoSize.SMALL) > 0){
            mImageIcon.setImageResource(network.getLogo(LogoSize.SMALL));
        }else{
            Logger.w("No image resource for network " + network.name());
            mImageIcon.setImageDrawable(new ColorDrawable(0x55888888));
        }

        String text = mEditText.getText().toString().replaceAll("@","");
        if(network.hasAtInHandle()){
            mEditText.setText("@" + text);
        }else{
            mEditText.setText(text);
        }

    }

    public SocialNetwork getNetwork(){
        return mNetwork;
    }

    public boolean hasValue(){
        String text = mEditText.getText().toString();
        return !(text == null || text.trim().isEmpty() || text.trim().equals("@"));
    }

    public String getText(){
        return mEditText.getText().toString();
    }

    public void setOnRemoveListener(OnRemoveListener listener){
        mListener = listener;
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(mEditText.getText().length() == 0 && mNetwork.hasAtInHandle()){
                mEditText.setText("@");
            }
        }
    };


}
