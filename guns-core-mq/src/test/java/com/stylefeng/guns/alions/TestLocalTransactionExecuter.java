package com.stylefeng.guns.alions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionExecuter;
import com.aliyun.openservices.ons.api.transaction.TransactionStatus;
import com.stylefeng.guns.modular.alions.IAliOnsBusinessService;

@Component
public class TestLocalTransactionExecuter implements LocalTransactionExecuter {
	
	private final static Logger log = LoggerFactory.getLogger(TestLocalTransactionExecuter.class);
	
	@Autowired
	private IAliOnsBusinessService businessService;

	@Override
	public TransactionStatus execute(Message msg, Object arg) {

		// 消息 ID（有可能消息体一样，但消息 ID 不一样，当前消息 ID 在控制台无法查询）
		String msgId = msg.getMsgID();
		// 消息体内容进行 crc32，也可以使用其它的如 MD5
//		long crc32Id = HashUtil.crc32Code(msg.getBody());
		// 消息 ID 和 crc32id 主要是用来防止消息重复
		// 如果业务本身是幂等的，可以忽略，否则需要利用 msgId 或 crc32Id 来做幂等
		// 如果要求消息绝对不重复，推荐做法是对消息体 body 使用 crc32或 md5来防止重复消息
		Object businessArgs = new Object();
		TransactionStatus transactionStatus = TransactionStatus.Unknow;
		try {
			boolean isCommit = businessService.execBusinessService(businessArgs, arg);
			// 如业务暂不返回本地事务结果，即将下面if代码去掉，消息服务将回查本地事务，由LocalTransactionChecker来再次决定事务是提交还是回滚
//			if (isCommit) {
//				// 本地事务成功则提交消息
//				transactionStatus = TransactionStatus.CommitTransaction;
//			} else {
//				// 本地事务失败则回滚消息
//				transactionStatus = TransactionStatus.RollbackTransaction;
//			}
		} catch (Exception e) {
			log.error("Message Id:{}", msgId, e);
		}
		log.warn("Message Id: {} Transaction status: {}", msgId, transactionStatus.name());
		return transactionStatus;
	}

}
