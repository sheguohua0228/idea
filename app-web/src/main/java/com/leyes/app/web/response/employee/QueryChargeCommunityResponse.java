package com.leyes.app.web.response.employee;

public class QueryChargeCommunityResponse {

	private String communityId;
	
	private String communityName;

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public QueryChargeCommunityResponse(String communityId, String communityName) {
		super();
		this.communityId = communityId;
		this.communityName = communityName;
	}

	public QueryChargeCommunityResponse() {
		super();
	}
	
}
