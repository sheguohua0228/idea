package com.aplus.lk.clothes.mapper;

import java.util.List;
import java.util.Map;

import com.aplus.lk.clothes.entity.Feed;
import com.aplus.lk.clothes.entity.Question;

public interface HelpFeedBackMapper {
	
	public List<Question> queryQuestionList(Map<String, Object> parameterMap);
	
	public int queryQuestionCount(Integer questionType);
	
	public Question queryQuestionDetail(long id);
	
	public List<Feed> queryFeddBackList(Map<String, Object> parameterMap);
	
	public int queryCount(Map<String, Object> parameterMap);
	
	public List<Feed> queryTelFeedList(Map<String, Object> parameterMap);
	
	public int queryTelCount(Map<String, Object> parameterMap);
	
	public Feed queryTelFeedDetail(long feedId);
	
	public Feed queryFeedDetail(long feedId);
	
	public void answerQuestion(Map<String,Object> parameterMap);
	
	public void saveQuestion(Question question);
	
	public void saveFeed(Feed feed);
	
	public void updateQuestion(Question question);
	
	public int deleteQuestion(long[] ids);
}
