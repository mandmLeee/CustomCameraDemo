# CustomCameraDemo
Camera+SurfaceView 自定义拍照实例&amp;解决竖拍图片横向问题
对Activity强制横屏，保证预览方向正确。
使用OrientationEventListener监听设备方向，判断竖拍时，旋转照片后再保存，保证竖拍时预览图片和保存后的图片方向一致。
监听方法：
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
