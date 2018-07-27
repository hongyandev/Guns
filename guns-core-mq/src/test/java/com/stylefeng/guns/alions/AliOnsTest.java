package com.stylefeng.guns.alions;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.exception.ONSClientException;
import com.aliyun.openservices.ons.api.transaction.TransactionProducer;
import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.modular.alions.AliOnsTopicEnum;

public class AliOnsTest extends BaseJunit {

	@Autowired
	private Producer producer;

	@Autowired
	private TransactionProducer transactionProducer;
	
	@Autowired
	private TestLocalTransactionExecuter testLocalTransactionExecuter;
	
	private final static Logger log = LoggerFactory.getLogger(AliOnsTest.class);

	/**
	 * 先生产消息（生产者测试），再消费消息（消费者测试）
	 */

	/**
	 * 生产者测试
	 */
	@Test
	public void producerTest() {

		// 循环发送消息
		for (int i = 0; i < 100; i++) {
			Message msg = new Message( //
					// Message 所属的 Topic
					AliOnsTopicEnum.COMMON_TOPIC.getTopic(),
					// Message Tag 可理解为 Gmail 中的标签，对消息进行再归类，方便 Consumer 指定过滤条件在
					// MQ 服务器过滤
					"TagA",
					// Message Body 可以是任何二进制形式的数据， MQ 不做任何干预
					// 需要 Producer 与 Consumer 协商好一致的序列化和反序列化方式
					("Hello MQ " + i).getBytes());
			// 设置代表消息的业务关键属性，请尽可能全局唯一
			// 以方便您在无法正常收到消息情况下，可通过 MQ 控制台查询消息并补发
			// 注意：不设置也不会影响消息正常收发
			msg.setKey("ORDERID_" + i);
			// 发送消息，只要不抛异常就是成功
			try {
				SendResult sendResult = producer.send(msg);
				assert sendResult != null;
				log.warn("Send to {} success. MessageId is: {}", msg.getTopic(), sendResult.getMessageId());
			} catch (ONSClientException e) {
				e.printStackTrace();
				log.error("Send to {} failed.", msg.getTopic());
			}
		}
	}

	/**
	 * 事务消息测试
	 */
	@Test
	public void transactionProducerTest() {
		Message msg = new Message(AliOnsTopicEnum.TRANSACTION_TOPIC.getTopic(), "TagA", "Hello MQ transaction===".getBytes());
		try {
			SendResult sendResult = transactionProducer.send(msg, testLocalTransactionExecuter, "TestArgs");
			assert sendResult != null;
			log.warn("Send to {} success. MessageId is: {}", msg.getTopic(), sendResult.getMessageId());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Send to {} failed.", msg.getTopic());
		}
		// demo example 防止进程退出(实际使用不需要这样)
		try {
			TimeUnit.MILLISECONDS.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 消费者测试
	 */
	@Test
	public void consumerTest() {
		// demo example 防止进程退出(实际使用不需要这样)
		try {
			TimeUnit.MILLISECONDS.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
