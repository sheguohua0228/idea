package com.aplus.lk.clothes.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aplus.lk.clothes.bean.AjaxResult;
import com.aplus.lk.clothes.bean.AjaxResult.Status;
import com.aplus.lk.clothes.bean.FeedInputInfo;
import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.Feed;
import com.aplus.lk.clothes.entity.Member;
import com.aplus.lk.clothes.entity.Question;
import com.aplus.lk.clothes.service.HelpFeedBackService;
import com.aplus.lk.clothes.service.MemberService;

@Controller
@RequestMapping("helpFeedBack")
public class HelpFeedBackController {

	@Autowired
	private HelpFeedBackService helpFeedBackService;
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("queryQuestionList")
	public String questionList(Pager pager,Integer type,HttpServletRequest request){
		pager = helpFeedBackService.queryQuestionList(pager,type);
		request.setAttribute("pager", pager);
		request.setAttribute("type", type);
		return "help_feedback_question_list";
	}
	
	@RequestMapping("queryList")
	public String feedBackList(Pager pager,String mobile,
			String username,String orderNum,Integer type,HttpServletRequest request){
		pager=helpFeedBackService.queryFeddBackList(pager, mobile, username, orderNum,type);
		request.setAttribute("pager", pager);
		request.setAttribute("username", username);
		request.setAttribute("mobile", mobile);
		request.setAttribute("orderNum", orderNum);
		request.setAttribute("type", type);
	
		return "help_feedback_list";
	}
	@RequestMapping("queryTelFeedList")
	public String queryTelFeedList(Pager pager,String orderNum,String phone,Integer handle,Integer type,HttpServletRequest request){
		pager=helpFeedBackService.queryTelFeedList(pager,orderNum, phone, handle, type);
		request.setAttribute("phone", phone);
		request.setAttribute("handle", handle);
		request.setAttribute("type", type);
		request.setAttribute("orderNum", orderNum);
		request.setAttribute("pager",pager);
		return "tel_help_feedback_list";
	}
	@RequestMapping("feedbackView")
	public String detail(long feedId,HttpServletRequest request){
		Feed feed = helpFeedBackService.queryFeedDetail(feedId);
		request.setAttribute("feed", feed);
		return "help_feedback_view";
	}
	@RequestMapping("telFeedDetail")
	public String queryTelFeedDetail(long feedId,HttpServletRequest request){
		Feed feed = helpFeedBackService.queryTelFeedDetail(feedId);
		request.setAttribute("feed", feed);
		return "help_feedback_view";
	}
	
	@RequestMapping("questionView")
	public String addQuestionView(Long id,HttpServletRequest request){
		if (id!=null) {
			Question question = helpFeedBackService.queryQuestionDetail(id);
			request.setAttribute("question", question);
		}
		return "help_feedback_question_input";
	}
	@RequestMapping("jumpFeedbackView")
	public String addQuestionView1(String phone,HttpServletRequest request){
		return "feedback_question_input";
	}
	
	@RequestMapping("reply")
	@ResponseBody
	public String reply(long feedId,String answer,HttpServletRequest request){
		try {
			helpFeedBackService.answerQuestion(feedId, answer);
		} catch (Exception e) {
			 return "fail";
		}
		return "success";
	}
	@RequestMapping("save")
	public String save(Question question,HttpServletRequest request){
		helpFeedBackService.saveQuestion(question);
		return "redirect:queryQuestionList";
	}
	@RequestMapping("update")
	public String update(Question question){
		 helpFeedBackService.updateQuestion(question);
		return "redirect:queryQuestionList";
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public AjaxResult delete(long[] ids){
		AjaxResult result = new AjaxResult();
		helpFeedBackService.deleteQuestion(ids);
		result.setStatus(Status.success);
		result.setMessage("删除成功");
		return result;
	}
	
	@RequestMapping("memberInfoView")
	public String queryMemberInfoView(String mobiePhone,HttpServletRequest request){
		if(StringUtils.isNotBlank(mobiePhone)){
			request.getSession().setAttribute("mobiePhone", mobiePhone);
			Member member = memberService.queryByPhoneNo(mobiePhone);
			request.setAttribute("member", member);
		}
		return "member_info";
	}
	@RequestMapping("memberInfo")
	@ResponseBody
	public Member queryMemberInfo(String mobiePhone,HttpServletRequest request){
		request.getSession().setAttribute("mobiePhone", mobiePhone);
		Member member = memberService.queryByPhoneNo(mobiePhone);
		return member;
	}
	@RequestMapping("printOrder")
	public String queryPrintOrder(Pager pager,String memberId,HttpServletRequest request){
		pager=memberService.queryPrintOrder(pager, memberId);
		request.setAttribute("pager", pager);
		return "print_order_list";
	}
	@RequestMapping("inputFeed")
	public String inputFeed(FeedInputInfo feedInputInfo,HttpServletRequest request){
		helpFeedBackService.inputFeed(feedInputInfo);
		return "member_info";
	}
}
