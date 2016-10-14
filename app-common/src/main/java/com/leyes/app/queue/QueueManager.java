package com.leyes.app.queue;

public class QueueManager {

	public static final String HOST="localhost";
	
	/**账户收支明细**/
	public static final String ACCOUNT_INCOMEOUT_DETAIL_QUEUE="account_incomeout_detail_queue";
	/**推送消息**/
	public static final String PUSH_MESSAGE_QUEUE="push_message_queue";
	/**修改用户所在小区，绑定社区消息**/
	public static final String UPDATE_COMMUNITY_BIND_MESSAGE_QUEUE="update_community_bind_message_queue";
    /**订单统计（洗衣、打印、值得买）**/                            
	public static final String ORDER_STATISTICS_QUEUE="order_statistics_queue";
	
	
	public static final String REGIST="regist";
}
