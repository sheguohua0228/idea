package com.aplus.lk.clothes.service;

import com.aplus.lk.clothes.bean.FeedInputInfo;
import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.Feed;
import com.aplus.lk.clothes.entity.Question;

public interface HelpFeedBackService {

	public Pager queryQuestionList(Pager pager,Integer type);
	
	public Pager queryFeddBackList(Pager pager,String mobile,
			String username,String orderNum,Integer type);
	
	public Pager queryTelFeedList(Pager pager,String orderNum,String phone,Integer handle,Integer type);
	
	public Feed queryTelFeedDetail(long feedId);
	
 	public Feed queryFeedDetail(long feedId);
	
	public Question queryQuestionDetail(long id);
	
	public void answerQuestion(long feedId,String answer);
	
    public void saveQuestion(Question question);
	
	public void updateQuestion(Question question);
	
	public int deleteQuestion(long[] ids);
	
	public void inputFeed(FeedInputInfo feedInputInfo);
}
