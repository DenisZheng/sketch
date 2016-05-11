/*
 * Copyright (C) 2013 Peng fei Pan <sky@xiaopan.me>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.xiaopan.sketch.request;

import android.graphics.Bitmap;

import me.xiaopan.sketch.display.ImageDisplayer;
import me.xiaopan.sketch.process.ImageProcessor;

/**
 * 显示选项
 */
public class DisplayOptions extends LoadOptions {
    private boolean cacheInMemory;    //是否每次加载图片的时候先从内存中去找，并且加载完成后将图片缓存在内存中
    private ImageDisplayer imageDisplayer;    // 图片显示器
    private ImageHolder loadingImageHolder;    //当正在加载时显示的图片
    private ImageHolder failedImageHolder;    //当失败时显示的图片
    private ImageHolder pauseDownloadImageHolder;    //暂停下载时显示的图片
    private boolean resizeByFixedSize;

    public DisplayOptions() {
        reset();
    }

    public DisplayOptions(DisplayOptions from) {
        copy(from);
    }

    @Override
    public DisplayOptions setCacheInDisk(boolean cacheInDisk) {
        super.setCacheInDisk(cacheInDisk);
        return this;
    }

    @Override
    public DisplayOptions setRequestLevel(RequestLevel requestLevel) {
        super.setRequestLevel(requestLevel);
        return this;
    }

    @Override
    DisplayOptions setRequestLevelFrom(RequestLevelFrom requestLevelFrom) {
        super.setRequestLevelFrom(requestLevelFrom);
        return this;
    }

    @Override
    public DisplayOptions setMaxSize(MaxSize maxSize) {
        super.setMaxSize(maxSize);
        return this;
    }

    @Override
    public DisplayOptions setMaxSize(int width, int height) {
        super.setMaxSize(width, height);
        return this;
    }

    @Override
    public DisplayOptions setResize(Resize resize) {
        super.setResize(resize);
        this.resizeByFixedSize = false;
        return this;
    }

    @Override
    public DisplayOptions setResize(int width, int height) {
        super.setResize(width, height);
        this.resizeByFixedSize = false;
        return this;
    }

    @Override
    public DisplayOptions setDecodeGifImage(boolean decodeGifImage) {
        super.setDecodeGifImage(decodeGifImage);
        return this;
    }

    @Override
    public DisplayOptions setLowQualityImage(boolean lowQualityImage) {
        super.setLowQualityImage(lowQualityImage);
        return this;
    }

    @Override
    public DisplayOptions setForceUseResize(boolean forceUseResize) {
        super.setForceUseResize(forceUseResize);
        return this;
    }

    @Override
    public DisplayOptions setImageProcessor(ImageProcessor processor) {
        super.setImageProcessor(processor);
        return this;
    }

    @Override
    public DisplayOptions setBitmapConfig(Bitmap.Config bitmapConfig) {
        super.setBitmapConfig(bitmapConfig);
        return this;
    }

    /**
     * 是否将图片缓存在内存中（默认是）
     */
    public boolean isCacheInMemory() {
        return cacheInMemory;
    }

    /**
     * 设置是否将图片缓存在内存中（默认是）
     */
    public DisplayOptions setCacheInMemory(boolean cacheInMemory) {
        this.cacheInMemory = cacheInMemory;
        return this;
    }

    /**
     * 获取图片显示器
     */
    public ImageDisplayer getImageDisplayer() {
        return imageDisplayer;
    }

    /**
     * 设置图片显示器，在加载完成后会调用此显示器来显示图片
     */
    public DisplayOptions setImageDisplayer(ImageDisplayer displayer) {
        this.imageDisplayer = displayer;
        return this;
    }

    /**
     * 获取正在加载时显示的图片
     */
    public ImageHolder getLoadingImageHolder() {
        return loadingImageHolder;
    }

    /**
     * 设置正在加载时显示的图片
     */
    public DisplayOptions setLoadingImage(ImageHolder loadingImageHolder) {
        this.loadingImageHolder = loadingImageHolder;
        return this;
    }

    /**
     * 设置正在加载时显示的图片
     */
    public DisplayOptions setLoadingImage(int drawableResId) {
        setLoadingImage(new ImageHolder(drawableResId));
        return this;
    }

    /**
     * 获取失败时显示的图片
     */
    public ImageHolder getFailedImageHolder() {
        return failedImageHolder;
    }

    /**
     * 设置失败时显示的图片
     */
    public DisplayOptions setFailedImage(ImageHolder failedImageHolder) {
        this.failedImageHolder = failedImageHolder;
        return this;
    }

    /**
     * 设置失败时显示的图片
     */
    public DisplayOptions setFailedImage(int drawableResId) {
        setFailedImage(new ImageHolder(drawableResId));
        return this;
    }

    /**
     * 获取暂停下载时显示的图片
     */
    public ImageHolder getPauseDownloadImageHolder() {
        return pauseDownloadImageHolder;
    }

    /**
     * 设置暂停下载时显示的图片
     */
    public DisplayOptions setPauseDownloadImage(ImageHolder pauseDownloadImageHolder) {
        this.pauseDownloadImageHolder = pauseDownloadImageHolder;
        return this;
    }

    /**
     * 设置暂停下载时显示的图片
     */
    public DisplayOptions setPauseDownloadImage(int drawableResId) {
        setPauseDownloadImage(new ImageHolder(drawableResId));
        return this;
    }

    /**
     * 是否使用ImageView的layout_width和layout_height作为resize
     */
    public boolean isResizeByFixedSize() {
        return resizeByFixedSize;
    }

    /**
     * 设置是否使用ImageView的layout_width和layout_height作为resize
     */
    public DisplayOptions setResizeByFixedSize(boolean isResizeByFixedSize) {
        this.resizeByFixedSize = isResizeByFixedSize;
        if (this.resizeByFixedSize && getResize() != null) {
            super.setResize(null);
        }
        return this;
    }

    @Override
    public void reset(){
        super.reset();

        cacheInMemory = true;
        imageDisplayer = null;
        resizeByFixedSize = false;
        loadingImageHolder = null;
        failedImageHolder = null;
        pauseDownloadImageHolder = null;
    }

    /**
     * 拷贝属性，绝对的覆盖
     */
    public void copy(DisplayOptions options) {
        if(options == null){
            return;
        }

        super.copy(options);

        cacheInMemory = options.cacheInMemory;
        imageDisplayer = options.imageDisplayer;
        resizeByFixedSize = options.resizeByFixedSize;
        loadingImageHolder = options.loadingImageHolder;
        failedImageHolder = options.failedImageHolder;
        pauseDownloadImageHolder = options.pauseDownloadImageHolder;
    }

    /**
     * 应用属性，应用的过程并不是绝对的覆盖
     */
    public void apply(DisplayOptions options){
        if(options == null){
            return;
        }

        super.apply(options);

        cacheInMemory = options.isCacheInMemory();

        if (imageDisplayer == null) {
            imageDisplayer = options.getImageDisplayer();
        }

        if (loadingImageHolder == null) {
            loadingImageHolder = options.getLoadingImageHolder();
        }

        if (failedImageHolder == null) {
            failedImageHolder = options.getFailedImageHolder();
        }

        if (pauseDownloadImageHolder == null) {
            pauseDownloadImageHolder = options.getPauseDownloadImageHolder();
        }

        if(!resizeByFixedSize && options.isResizeByFixedSize()){
            resizeByFixedSize = true;
        }
    }

    public StringBuilder appendMemoryCacheKey(StringBuilder builder) {
        if (getMaxSize() != null) {
            builder.append("_");
            getMaxSize().appendIdentifier(builder);
        }
        if (getResize() != null) {
            builder.append("_");
            getResize().appendIdentifier(builder);
        }
        if (isForceUseResize()) {
            builder.append("_");
            builder.append("forceUseResize");
        }
        if (isLowQualityImage()) {
            builder.append("_");
            builder.append("lowQualityImage");
        }
        if (getBitmapConfig() != null) {
            builder.append("_");
            builder.append(getBitmapConfig().name());
        }
        if (getImageProcessor() != null) {
            builder.append("_");
            getImageProcessor().appendIdentifier(builder);
        }
        return builder;
    }
}
