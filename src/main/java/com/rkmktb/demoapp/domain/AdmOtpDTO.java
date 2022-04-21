package com.rkmktb.demoapp.domain;

//import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdmOtpDTO {
	
	public String id;
	public String otp;
	
	//@Size(min = 10, max = 10)
	public String phoneNo;
	
	public String message;

	@Override
	public String toString() {
		return "AdmOtpDTO [id=" + id + ", otp=" + otp + ", phoneNo=" + phoneNo + ", message=" + message + "]";
	}
	
	
}
