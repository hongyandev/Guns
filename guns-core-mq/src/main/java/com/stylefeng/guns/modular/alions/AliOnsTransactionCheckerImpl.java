package com.stylefeng.guns.modular.alions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionChecker;
import com.aliyun.openservices.ons.api.transaction.TransactionStatus;

@Component
public class AliOnsTransactionCheckerImpl implements LocalTransactionChecker {
    private final static Logger log = LoggerFactory.getLogger(AliOnsTransactionCheckerImpl.class);
    
    @Autowired
    IAliOnsBusinessService businessService;
    
	@Override
	public TransactionStatus check(Message message) {
        //消息 ID（有可能消息体一样，但消息 ID 不一样，当前消息属于 Half 消息，所以消息 ID 在控制台无法查询）
        String msgId = message.getMsgID();
        //消息体内容进行 crc32，也可以使用其它的方法如 MD5
//        long crc32Id = HashUtil.crc32Code(message.getBody());
        //消息 ID、消息本 crc32Id 主要是用来防止消息重复
        //如果业务本身是幂等的，可以忽略，否则需要利用 msgId 或 crc32Id 来做幂等
        //如果要求消息绝对不重复，推荐做法是对消息体使用 crc32 或  md5 来防止重复消息
        //业务自己的参数对象，这里只是一个示例，需要您根据实际情况来处理
        Object businessArgs = new Object();
        TransactionStatus transactionStatus = TransactionStatus.Unknow;
        try {
            boolean isCommit = businessService.checkBusinessService(businessArgs);
            if (isCommit) {
                //本地事务已成功则提交消息
                transactionStatus = TransactionStatus.CommitTransaction;
            } else {
                //本地事务已失败则回滚消息
                transactionStatus = TransactionStatus.RollbackTransaction;
            }
        } catch (Exception e) {
            log.error("Message Id:{}", msgId, e);
        }
        log.warn("Message Id:{}; transactionStatus:{}", msgId, transactionStatus.name());
        return transactionStatus;
	}

}
