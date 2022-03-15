package com.futronic.SDKReader;

import java.awt.image.BufferedImage;

import com.futronic.SDKHelper.FTR_PROGRESS;
import com.futronic.SDKHelper.IEnrollmentCallBack;

public class StartReader implements IEnrollmentCallBack {

	@Override
	public void OnPutOn(FTR_PROGRESS Progress) {
		 System.out.println( "Coloque seu do dedo no leitor...." );
		
	}

	@Override
	public void OnTakeOff(FTR_PROGRESS Progress) {
		 System.out.println( "Retire seu do dedo no leitor...." );
		
	}

	@Override
	public void UpdateScreenImage(BufferedImage Bitmap) {
		System.out.println( Bitmap.toString() );
		
	}

	@Override
	public boolean OnFakeSource(FTR_PROGRESS Progress) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void OnEnrollmentComplete(boolean bSuccess, int nResult) {
		// TODO Auto-generated method stub
		
	}

}
