package com.rkmktb.demoapp.api.controllers;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rkmktb.demoapp.domain.AdmAllotmentDTO;
import com.rkmktb.demoapp.domain.AdmIdsDTO;
import com.rkmktb.demoapp.domain.AdmOtpDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "api")
public class AdmAPIControllerImpl implements AdmAPIController {

	@GetMapping(path = "/getotp/{Id}", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public AdmOtpDTO getOTPForID(@PathVariable String Id) {
		
		AdmOtpDTO dto = new AdmOtpDTO();
		dto.setId(Id);
		dto.setOtp(StringUtils.leftPad(String.valueOf(new Random().nextInt(100000)), 5, "0"));
		log.info(dto.toString());
		return dto;
	}
	
	@GetMapping(path = "/getotp/{phoneNo}/{Id}", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public AdmOtpDTO getOTPForIDAndPhoneNo(@PathVariable String phoneNo, @PathVariable String Id) {
		
		//TODO check if phone no is valid one.
		//TODO First check if Id belongs to that phoneNo
		
		
		String otpNo = StringUtils.leftPad(String.valueOf(new Random().nextInt(100000)), 5, "0");
		
		AdmOtpDTO dto = new AdmOtpDTO();
		dto.setId(Id);
		dto.setPhoneNo(phoneNo);
		
		int randomNo = new Random().nextInt(10);
		
		if(randomNo < 6) {
			dto.setOtp(otpNo);
			dto.setMessage(otpNo+" is your OTP generated for your linked ID: "+Id);
		}else if(randomNo >= 6 && randomNo < 8) {
			dto.setMessage("No OTP is generated during last five minutes for your linked ID: "+Id);
		}else {
			dto.setMessage("Incorrect / No ID : "+Id+" is linked to this Mobile Number : "+phoneNo);
		}
		log.info(dto.toString());		
		return dto;

	}

	@GetMapping(path = "/getid/{phoneNo}", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public AdmIdsDTO getIDsForPhoneNo(@PathVariable String phoneNo) {
		//TODO check if phone no is valid one.
		
		AdmIdsDTO dto = new AdmIdsDTO();
		dto.setPhoneNo(phoneNo);
		
		ArrayList<String> Ids = new ArrayList<String>();
		int randomNo = new Random().nextInt(10);
		
		if(randomNo < 6) {
			Ids.add("ABCD123");
			
			dto.setIds(Ids);
			
			String idstr = "";
			for(String id: Ids) {
				idstr += id;
				idstr+= ", ";
			}
			//System.out.println(idstr+" - "+idstr.length());
			idstr = removeLastCharOptional(idstr);
			//System.out.println(idstr+" - "+idstr.length());
			
			dto.setMessage(idstr+" is/are your Admission ID(s) linked to this Mobile Number: "+phoneNo);
			
		}else if(randomNo >= 6 && randomNo < 8) {
			Ids.add("ABCD456");
			Ids.add("ABCD789");
			
			dto.setIds(Ids);
			
			String idstr = "";
			for(String id: Ids) {
				idstr += id;
				idstr+= ", ";
			}
			//System.out.println(idstr+" - "+idstr.length());
			idstr = removeLastCharOptional(idstr);
			//System.out.println(idstr+" - "+idstr.length());
			
			dto.setMessage(idstr+" is/are your Admission ID(s) linked to this Mobile Number: "+phoneNo);
		}else {
			//No Ids
			dto.setMessage("No Admission ID is linked to this Mobile Number: "+phoneNo);
		}
				
		log.info(dto.toString());
		return dto;
	}

	@GetMapping(path = "/getallotment/{phoneNo}", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public AdmAllotmentDTO getAllotmentsForIDsOfPhone(@PathVariable String phoneNo) {
		//TODO check if phone no is valid one.
		//TODO First check if Id belongs to that phoneNo
		
		AdmAllotmentDTO dto = new AdmAllotmentDTO();
		dto.setLatestPhase("Phase-I");
		dto.setPhoneNo(phoneNo);
		
		ArrayList<String> Ids = new ArrayList<String>();
		int randomNo = new Random().nextInt(10);
		
		if(randomNo < 6) {
			Ids.add("ABCD123");
			
			dto.setIds(Ids);
			
			String idstr = "";
			for(String id: Ids) {
				idstr += id;
				idstr+= ", ";
			}
			
			//System.out.println(idstr+" - "+idstr.length());
			idstr = removeLastCharOptional(idstr);
			//System.out.println(idstr+" - "+idstr.length());
			
			dto.setMessage(" Admission ID : ABCD123 is allotted seat in ABC College in BSc(MPC) English Medium in "+dto.getLatestPhase());
			
		}else if(randomNo >= 6 && randomNo < 8){
			Ids.add("ABCD456");
			Ids.add("ABCD789");
			
			dto.setIds(Ids);
			
			String idstr = "";
			for(String id: Ids) {
				idstr += id;
				idstr+= ", ";
			}
			
			//System.out.println(idstr+" - "+idstr.length());
			idstr = removeLastCharOptional(idstr);
			//System.out.println(idstr+" - "+idstr.length());
			
			dto.setMessage(" Admission ID : ABCD456 is allotted seat in ABC College in BSc(MPC) English Medium in "+dto.getLatestPhase()+", "+
						   " Admission ID : ABCD789 is not allotted any seat in "+dto.getLatestPhase());

		}else {
			dto.setMessage("No Admission ID is linked to this Mobile Number : "+phoneNo);
		}
		
		log.info(dto.toString());
		return dto;
	}
	
	public static String removeLastCharOptional(String s) {
	    return Optional.ofNullable(s)
	      .filter(str -> str.length() != 0)
	      //Remove ", " //2Char
	      .map(str -> str.substring(0, str.length() - 2))
	      .orElse(s);
	}

}
