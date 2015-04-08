Camera+SurfaceView 自定义拍照实例&amp;解决竖拍图片横向问题
==========================================================

我们都知道Android Camera取景方向是横向的。这意味着当使用Camera+SurfaceView实现自定义拍照时，你手竖直持手机，拍出来的照片和SurfaceView中预览的照片方向相差90度。
此Demo目的就是解决此问题：

对Activity强制横屏，保证预览方向正确。
使用**OrientationEventListener**监听设备方向，判断手机设备为竖直方向时，旋转照片后再保存，保证竖拍时预览图片和保存后的图片方向一致。

**监听手机设备方向的方法：**
```java
 private final void startOrientationChangeListener() {  
        mOrEventListener = new OrientationEventListener(this) {  
            @Override  
            public void onOrientationChanged(int rotation) {  
                if (((rotation >= 0) && (rotation <= 45)) || (rotation >= 315)  
                        || ((rotation >= 135) && (rotation <= 225))) {// portrait  
                    mCurrentOrientation = true;  
                    Log.i(TAG, "竖屏");  
                } else if (((rotation > 45) && (rotation < 135))  
                        || ((rotation > 225) && (rotation < 315))) {// landscape  
                    mCurrentOrientation = false;  
                    Log.i(TAG, "横屏");  
                }  
            }  
        };  
        mOrEventListener.enable();  
    }  
```
