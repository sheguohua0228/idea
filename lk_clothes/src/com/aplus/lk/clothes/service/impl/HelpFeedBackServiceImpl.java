package com.aplus.lk.clothes.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplus.lk.clothes.bean.FeedInputInfo;
import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.Feed;
import com.aplus.lk.clothes.entity.Question;
import com.aplus.lk.clothes.mapper.HelpFeedBackMapper;
import com.aplus.lk.clothes.service.HelpFeedBackService;
@Service
public class HelpFeedBackServiceImpl implements HelpFeedBackService{

	@Autowired
	private HelpFeedBackMapper helpFeedBackMapper;
	
	
	@Override
	public Pager queryQuestionList(Pager pager,Integer type) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("firstResult", (pager.getPageNo() - 1)*pager.getPageSize());
		parameterMap.put("maxResult", pager.getPageSize());
		parameterMap.put("questionType", type);
		pager.setDataList(helpFeedBackMapper.queryQuestionList(parameterMap));
		pager.setTotalRecords(helpFeedBackMapper.queryQuestionCount(type));
		return pager;
	}

	@Override
	public Pager queryFeddBackList(Pager pager,String mobile,
			String username,String orderNum,Integer type){
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("firstResult", (pager.getPageNo() - 1)*pager.getPageSize());
		parameterMap.put("maxResult", pager.getPageSize());
		parameterMap.put("mobile", mobile);
		parameterMap.put("username", username);
		parameterMap.put("orderNum", orderNum);
		parameterMap.put("type",type);
		pager.setDataList(helpFeedBackMapper.queryFeddBackList(parameterMap));
		pager.setTotalRecords(helpFeedBackMapper.queryCount(parameterMap));
		return pager;
	}

	@Override
	public Pager queryTelFeedList(Pager pager,String orderNum,String phone, Integer handle,Integer type) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("firstResult", (pager.getPageNo() - 1)*pager.getPageSize());
		parameterMap.put("maxResult", pager.getPageSize());
		parameterMap.put("type", type);
		parameterMap.put("orderNum", orderNum);
		parameterMap.put("phone", phone);
		parameterMap.put("handle", handle);
		pager.setDataList(helpFeedBackMapper.queryTelFeedList(parameterMap));
		pager.setTotalRecords(helpFeedBackMapper.queryTelCount(parameterMap));
		return pager;
	}

	@Override
	public Feed queryFeedDetail(long feedId) {
		return helpFeedBackMapper.queryFeedDetail(feedId);
	}

	@Override
	public Feed queryTelFeedDetail(long feedId) {
		return helpFeedBackMapper.queryTelFeedDetail(feedId);
	}

	@Override
	public void answerQuestion(long feedId,String answer) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("answer", answer);
		parameterMap.put("feedId", feedId);
		helpFeedBackMapper.answerQuestion(parameterMap);
	}

	@Override
	public Question queryQuestionDetail(long id) {
		return helpFeedBackMapper.queryQuestionDetail(id);
	}

	@Override
	public void saveQuestion(Question question) {
		helpFeedBackMapper.saveQuestion(question);
	}

	@Override
	public void updateQuestion(Question question) {
		helpFeedBackMapper.updateQuestion(question);
	}

	@Override
	public int deleteQuestion(long[] ids) {
		return helpFeedBackMapper.deleteQuestion(ids);
	}

	@Override
	public void inputFeed(FeedInputInfo feedInputInfo) {
		Question question = new Question();
		question.setDescription(feedInputInfo.getDescription());
		question.setTitle(feedInputInfo.getTitle());
		question.setQuestionType(feedInputInfo.getQuestionType());
		helpFeedBackMapper.saveQuestion(question);
		Feed feed = new Feed();
		feed.setFeedType(1);
		feed.setIdHandle(false);
		feed.setQuestionId(question.getId());
		feed.setOrderNum(feedInputInfo.getOrderNum());
		feed.setPhoneNo(feedInputInfo.getPhone());
		feed.setQuestionDetail(feedInputInfo.getDescription());
		feed.setUserId("0");
		helpFeedBackMapper.saveFeed(feed);
	}

	
}
