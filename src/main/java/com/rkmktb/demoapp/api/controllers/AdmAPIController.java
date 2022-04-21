package com.rkmktb.demoapp.api.controllers;

import com.rkmktb.demoapp.domain.AdmAllotmentDTO;
import com.rkmktb.demoapp.domain.AdmIdsDTO;
import com.rkmktb.demoapp.domain.AdmOtpDTO;

public interface AdmAPIController {
	
	public AdmOtpDTO getOTPForID(String dostID);
	
	public AdmOtpDTO getOTPForIDAndPhoneNo(String phoneNo,String Id);
	
	public AdmIdsDTO getIDsForPhoneNo(String phoneNo);
	
	public AdmAllotmentDTO getAllotmentsForIDsOfPhone(String phoneNo);

}
