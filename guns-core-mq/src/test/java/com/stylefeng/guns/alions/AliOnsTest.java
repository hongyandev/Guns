package com.stylefeng.guns.alions;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.exception.ONSClientException;
import com.stylefeng.guns.base.BaseJunit;

public class AliOnsTest extends BaseJunit {
	
	@Autowired
	private Producer producer;
	
	/**
	 * 先生产消息（生产者测试），再消费消息（消费者测试）
	 */
	
	/**
	 * 生产者测试
	 */
	@Test
	public void producerTest() {
		
		//循环发送消息
        for (int i = 0; i < 100; i++) {
            Message msg = new Message( //
                    // Message 所属的 Topic
                    "TopicMQ2",
                    // Message Tag 可理解为 Gmail 中的标签，对消息进行再归类，方便 Consumer 指定过滤条件在 MQ 服务器过滤
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
                System.out.println("send success: " + sendResult.getMessageId());
            }catch (ONSClientException e) {
            	e.printStackTrace();
                System.out.println("发送失败");
            }
        }
	}
	/**
	 * 消费者测试
	 */
	@Test
	public void consumerTest() {
		
	}
}
