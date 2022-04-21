package com.rkmktb.demoapp.domain;

import java.util.ArrayList;

//import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdmAllotmentDTO {
	
	public ArrayList<String> ids;
	public String latestPhase;

	//@Size(min = 10, max = 10)
	public String phoneNo;
	
	public String message;

	@Override
	public String toString() {
		return "AdmAllotmentDTO [ids=" + ids + ", latestPhase=" + latestPhase + ", phoneNo=" + phoneNo + ", message="
				+ message + "]";
	}
	
	
}
