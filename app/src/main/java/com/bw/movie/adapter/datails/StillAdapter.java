package com.bw.movie.adapter.datails;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


public class StillAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<String> list;
    private View view;
    private TextView tv;
    private ImageView iv;
//    private Bitmap bitmap;
    private Dialog mLoadingDialog;

    public StillAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = View.inflate(context, R.layout.item_jizhao, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        final String s = list.get(position);
        Uri uri = Uri.parse(s);
        ((ViewHolder)holder).iv.setImageURI(uri);
//        Glide.with(context).load(s).into(((ViewHolder) holder).iv);

        ((ViewHolder)holder).iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLoadingDialog == null) {
                    mLoadingDialog = new Dialog(context);
                    if (mLoadingDialog.isShowing() == false) {
                        View view = View.inflate(context, R.layout.dialog_loading, null);
                        iv = view.findViewById(R.id.iv_loading);
                        tv = view.findViewById(R.id.tv);
                        Glide.with(context).load(s).into(iv);
                        mLoadingDialog.addContentView(view,
                                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT));
                        mLoadingDialog.show();
                    }
                }else {
                    Dialog dialog = new Dialog(context);
                    if (dialog.isShowing() == false) {
                        View view = View.inflate(context, R.layout.dialog_loading, null);
                        iv = view.findViewById(R.id.iv_loading);
                        tv = view.findViewById(R.id.tv);
                        Glide.with(context).load(s).into(iv);
                        dialog.addContentView(view,
                                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT));
                        dialog.show();
                    }
                }
            }
        });


//        ((ViewHolder) holder).iv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((ViewHolder) holder).zoomImageFromThumb(((ViewHolder) holder).iv
//                        ,R.mipmap.banner_1);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

//        private final ImageButton iv;
        private final SimpleDraweeView iv;
//        private final ImageView  expandedImageView;
        private final LinearLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
//            expandedImageView = itemView.findViewById(R.id.iv_big);
            container = itemView.findViewById(R.id.ll);
        }

//        private AnimatorSet mCurrentAnimation;
//        private int mShortAnimationDuration = 1000;
//
//    private void zoomImageFromThumb(final View thumbView, int imageResId) {
//        //如果当前有动画就取消，并执行当前的动画
//        if (mCurrentAnimation != null) {
//            mCurrentAnimation.cancel();
//        }
//
//        //加载大图到预设好的ImageView中
////        final ImageView expandedImageView = findViewById(
////                R.id.iv_big);
//        expandedImageView.setImageResource(imageResId);
//
//        //计算放大图像的起始边界和结束边界
//        final Rect startBounds = new Rect();
//        final Rect finalBounds = new Rect();
//        final Point globalOffset = new Point();
//
//        //起始边界是缩略图的全局可见矩形，最后的边界是容器container的全局可见矩形
//        //将容器视图container的偏移量设置为区域的起源，因为定位动画的起源属性是（X，Y）
//        thumbView.getGlobalVisibleRect(startBounds);
//
//        container.getGlobalVisibleRect(finalBounds, globalOffset);
//
//        startBounds.offset(-globalOffset.x, -globalOffset.y);
//        finalBounds.offset(-globalOffset.x, -globalOffset.y);
//
//        //使用center_crop将起始边界调整为与最终边界相同的纵横比边界
//        //这可以防止在动画期间的不良拉伸。还计算开始缩放比例
//        float startScale;
//        if ((float) finalBounds.width() / finalBounds.height()
//                > (float) startBounds.width() / startBounds.height()) {
//            //横向放大
//            startScale = (float) startBounds.height() / finalBounds.height();
//            float startWidth = startScale * finalBounds.width();
//            float deltaWidth = (startWidth - startBounds.width()) / 2;
//            startBounds.left -= deltaWidth;
//            startBounds.right += deltaWidth;
//        } else {
//            //纵向放大
//            startScale = (float) startBounds.width() / finalBounds.width();
//            float startHeight = startScale * finalBounds.height();
//            float deltaHeight = (startHeight - startBounds.height()) / 2;
//            startBounds.top -= deltaHeight;
//            startBounds.bottom += deltaHeight;
//        }
//
//        //隐藏缩略图并显示放大视图
//        //当动画开始时，它会将放大的视图定位在原来缩略图的位置
//        thumbView.setAlpha(0f);
//        expandedImageView.setVisibility(View.VISIBLE);
//
//
//        //设置SCALE_X和SCALE_Y转换的轴心点为到放大后视图的左上角（默认值是视图的中心）
//        expandedImageView.setPivotX(0f);
//        expandedImageView.setPivotY(0f);
//
//
//        //四种动画同时播放
//        AnimatorSet set = new AnimatorSet();
//        set
//                .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
//                        startBounds.left, finalBounds.left))
//                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
//                        startBounds.top, finalBounds.top))
//                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
//                        startScale, 1f))
//                .with(ObjectAnimator.ofFloat(expandedImageView,
//                        View.SCALE_Y, startScale, 1f));
//        set.setDuration(mShortAnimationDuration);
//        set.setInterpolator(new DecelerateInterpolator());
//        set.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                mCurrentAnimation = null;
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//                mCurrentAnimation = null;
//            }
//        });
//        set.start();
//
//        mCurrentAnimation = set;
//
//        //从这往下就是给大图设置点击监听，完成缩小回去的过程
//        final float startScaleFinal = startScale;
//        expandedImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mCurrentAnimation != null) {
//                    mCurrentAnimation.cancel();
//                }
//
//                AnimatorSet set = new AnimatorSet();
//                set.play(ObjectAnimator
//                        .ofFloat(expandedImageView, View.X, startBounds.left))
//                        .with(ObjectAnimator
//                                .ofFloat(expandedImageView,
//                                        View.Y, startBounds.top))
//                        .with(ObjectAnimator
//                                .ofFloat(expandedImageView,
//                                        View.SCALE_X, startScaleFinal))
//                        .with(ObjectAnimator
//                                .ofFloat(expandedImageView,
//                                        View.SCALE_Y, startScaleFinal));
//                set.setDuration(mShortAnimationDuration);
//                set.setInterpolator(new DecelerateInterpolator());
//                set.addListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        thumbView.setAlpha(1f);
//                        expandedImageView.setVisibility(View.GONE);
//                        mCurrentAnimation = null;
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//                        thumbView.setAlpha(1f);
//                        expandedImageView.setVisibility(View.GONE);
//                        mCurrentAnimation = null;
//                    }
//                });
//                set.start();
//                mCurrentAnimation = set;
//            }
//        });
//    }

    }

//

}
