package com.chenyuwei.loadimageview;


/**
 * Created by vivi on 2016/7/23.
 */
public class Options {
    private int failedRes;
    private Shape shape;

    public Options() {
        this.failedRes = R.drawable.img_default;
        this.shape = Shape.DEFAULT;
    }

    public Options(Builder builder) {
        this.failedRes = R.drawable.img_default;
        this.shape = Shape.DEFAULT;
        this.failedRes = builder.failedRes;
        this.shape = builder.shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setFailedRes(int failedRes) {
        this.failedRes = failedRes;
    }

    public int getFailedRes() {
        return this.failedRes;
    }

    public Shape getShape() {
        return this.shape;
    }

    public static class Builder {
        private int failedRes;
        private Shape shape;

        public Builder() {
            this.failedRes = R.drawable.img_default;
            this.shape = Shape.DEFAULT;
        }

        public Builder setFailedRes(int failedRes) {
            this.failedRes = failedRes;
            return this;
        }

        public Builder setShape(Shape shape) {
            this.shape = shape;
            return this;
        }

        public Options build() {
            return new Options(this);
        }
    }

    public enum Shape {
        DEFAULT,
        Circle,
        Round
    }
}