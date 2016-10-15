package com.leyes.app.web.response.print;

import java.util.ArrayList;
import java.util.List;

import com.leyes.app.dto.print.VisaPhotoRequireInfoDto;

public class QueryVisaPhotoRequireInfoResponse {

	List<VisaPhotoRequireInfoDto> data;
	
	public void addDatd(VisaPhotoRequireInfoDto dto){
		if(data==null){
			data=new ArrayList<VisaPhotoRequireInfoDto>();
		}
		data.add(dto);
	}

	public List<VisaPhotoRequireInfoDto> getData() {
		return data;
	}

	public void setData(List<VisaPhotoRequireInfoDto> data) {
		this.data = data;
	}
	
}
