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
public class AdmIdsDTO {
	
	public ArrayList<String> ids;
	public String phoneNo;
	
	public String message;

	@Override
	public String toString() {
		return "AdmIdsDTO [ids=" + ids + ", phoneNo=" + phoneNo + ", message=" + message + "]";
	}
	
	
}
