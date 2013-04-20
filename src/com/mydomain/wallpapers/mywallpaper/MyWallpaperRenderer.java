package com.mydomain.wallpapers.mywallpaper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import rajawali.BaseObject3D;
import rajawali.animation.Animation3D;
import rajawali.animation.RotateAnimation3D;
import rajawali.lights.ALight;
import rajawali.lights.DirectionalLight;
import rajawali.materials.SimpleMaterial;
import rajawali.math.Number3D;
import rajawali.parser.ObjParser;
import rajawali.renderer.RajawaliRenderer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.animation.AccelerateDecelerateInterpolator;

public class MyWallpaperRenderer extends RajawaliRenderer {
	private Animation3D mAnim;
	private BaseObject3D mCan;
	private BaseObject3D mMetal;
	private BaseObject3D mColor;
	
	public MyWallpaperRenderer(Context context) {
		super(context);
		setFrameRate(30);
	}
	
	public void initScene() {
		
		System.gc();
		ALight light = new DirectionalLight();
		light.setPower(1);
		light.setPosition(0, 0, -5);
		mCamera.setPosition(0, 0, -7);
		mCamera.setLookAt(0, 0, 0);

		Bitmap texture = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.label);
		ObjParser parser = new ObjParser(mContext.getResources(), mTextureManager, R.raw.label_obj);
		parser.parse();
		mCan = parser.getParsedObject();
		mCan.addLight(light);
		mCan.setScale(1.7f);
		mCan.addTexture(mTextureManager.addTexture(texture));
		addChild(mCan);
		
		Bitmap tex = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.metal);
		ObjParser parser2 = new ObjParser(mContext.getResources(), mTextureManager, R.raw.metal_obj);
		parser2.parse();
		mMetal = parser2.getParsedObject();
		mMetal.addTexture(mTextureManager.addTexture(tex));
		mMetal.addLight(light);
		mMetal.setScale(1.7f);
		addChild(mMetal);
		
		SimpleMaterial simple = new SimpleMaterial();
		simple.setUseColor(true);
		
		ObjParser parser3 = new ObjParser(mContext.getResources(), mTextureManager, R.raw.color_obj);
		parser3.parse();
		mColor = parser3.getParsedObject();
		mColor.addLight(light);
		mColor.setScale(1.7f);
		mColor.setMaterial(simple);
		mColor.setColor(0xff009900);
		addChild(mColor);
		
		Number3D axis = new Number3D(0, 10, 0);
		axis.normalize();
		mAnim = new RotateAnimation3D(axis, 360);
		mAnim.setDuration(8000);
		mAnim.setRepeatCount(Animation3D.INFINITE);
		mAnim.setInterpolator(new AccelerateDecelerateInterpolator());
		mAnim.setTransformable3D(mCan);
		
	}
	
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		super.onSurfaceCreated(gl, config);
		mAnim.start();
	}

	public void onDrawFrame(GL10 glUnused) {
		super.onDrawFrame(glUnused);
	}
}
