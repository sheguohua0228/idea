package com.leyes.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leyes.app.entity.Community;

public interface CommunityMapper {

	 public Community queryCommunityById(@Param("communityId") String communityId);
	 
	 public List<Community> queryCommunityByIds(@Param("list")List<String> list);
	 
	 public String queryCommunityPropertyPhone(@Param("communityId")String communityId);
	 
	 public List<Community> queryChirldCommunity(@Param("communityId")String communityId);
	 
	 public String queryParentCommunityId(@Param("communityId") String communityId);
	 
	 public List<Community> searchCommunity(@Param("keyWord")String keyword,@Param("status")int status);
}
