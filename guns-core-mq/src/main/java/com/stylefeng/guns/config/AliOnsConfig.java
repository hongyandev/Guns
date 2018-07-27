package com.stylefeng.guns.config;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.bean.ConsumerBean;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.aliyun.openservices.ons.api.bean.Subscription;
import com.aliyun.openservices.ons.api.bean.TransactionProducerBean;
import com.google.common.collect.Maps;
import com.stylefeng.guns.config.properties.AliOnsProperties;
import com.stylefeng.guns.modular.alions.AliOnsMessageListener;
import com.stylefeng.guns.modular.alions.AliOnsTransactionCheckerImpl;

@Configuration
public class AliOnsConfig {
	@Autowired
	AliOnsProperties prop;
	@Autowired
	AliOnsTransactionCheckerImpl localTransactionChecker;
	@Autowired
	AliOnsMessageListener messageListener;
	
	/**
	 * 定义消息生产者
	 */
	@Bean(initMethod="start", destroyMethod="shutdown")
	public ProducerBean producer() {
		ProducerBean producer = new ProducerBean();
		Properties properties = new Properties();
		properties.setProperty(AliOnsProperties.ACCESS_KEY, prop.getAccessKey());
		properties.setProperty(AliOnsProperties.SECRET_KEY, prop.getSecretKey());
		properties.setProperty(AliOnsProperties.MSG_TRACE_SWITCH, prop.getMsgTraceSwitch());
		properties.setProperty(AliOnsProperties.ONS_ADDR, prop.getOnsAddr());
		properties.setProperty(AliOnsProperties.PRODUCER_ID, prop.getProducerId());
		properties.setProperty(AliOnsProperties.SEND_MSG_TIMEOUT_MILLIS, prop.getSendMsgTimeoutMillis());
		producer.setProperties(properties);
		return producer;
	}
	/**
	 * 定义事务消息生产者
	 */
	@Bean(initMethod="start", destroyMethod="shutdown")
	public TransactionProducerBean transactionProducer() {
		TransactionProducerBean tranProducer = new TransactionProducerBean();
		Properties properties = new Properties();
        properties.setProperty(AliOnsProperties.ACCESS_KEY, prop.getAccessKey());
        properties.setProperty(AliOnsProperties.SECRET_KEY, prop.getSecretKey());
        properties.setProperty(AliOnsProperties.MSG_TRACE_SWITCH, prop.getMsgTraceSwitch());
        properties.setProperty(AliOnsProperties.ONS_ADDR, prop.getOnsAddr());
        properties.setProperty(AliOnsProperties.PRODUCER_ID, prop.getProducerId());
        properties.setProperty(AliOnsProperties.SEND_MSG_TIMEOUT_MILLIS, prop.getSendMsgTimeoutMillis());
		tranProducer.setProperties(properties);
		tranProducer.setLocalTransactionChecker(localTransactionChecker);
		return tranProducer;
	}
	/**
	 * 定义消息消费者
	 */
	@Bean(initMethod="start", destroyMethod="shutdown")
	public ConsumerBean consumer() {
		ConsumerBean consumer = new ConsumerBean();
		Properties properties = new Properties();
        properties.setProperty(AliOnsProperties.ACCESS_KEY, prop.getAccessKey());
        properties.setProperty(AliOnsProperties.SECRET_KEY, prop.getSecretKey());
        properties.setProperty(AliOnsProperties.MSG_TRACE_SWITCH, prop.getMsgTraceSwitch());
        properties.setProperty(AliOnsProperties.ONS_ADDR, prop.getOnsAddr());
		properties.setProperty(AliOnsProperties.CONSUMER_ID, prop.getConsumerId());
		if (Objects.nonNull(prop.getConsumeThreadNums())) {
			properties.setProperty(AliOnsProperties.CONSUME_THREAD_NUMS, prop.getConsumeThreadNums());
		}
        properties.setProperty(AliOnsProperties.MESSAGE_MODEL, prop.getMessageModel());
        properties.setProperty(AliOnsProperties.MAX_RECONSUME_TIMES, prop.getMaxReconsumeTimes());
        properties.setProperty(AliOnsProperties.CONSUME_TIMEOUT, prop.getConsumeTimeout());
        properties.setProperty(AliOnsProperties.POST_SUBSCRIPTION_WHEN_PULL, prop.getPostSubscriptionWhenPull());
        properties.setProperty(AliOnsProperties.CONSUME_MESSAGE_BATCH_MAX_SIZE, prop.getConsumeMessageBatchMaxSize());
        properties.setProperty(AliOnsProperties.MAX_CACHED_MESSAGE_AMOUNT, prop.getMaxCachedMessageAmount());
        properties.setProperty(AliOnsProperties.MAX_CACHED_MESSAGE_SIZE_IN_MIB, prop.getMaxCachedMessageSizeInMiB());
		consumer.setProperties(properties);
		Map<Subscription, MessageListener> subscriptionTable = Maps.newHashMap();
		List<Subscription> subscription = prop.getSubscription();
		subscription.forEach(item -> {
			subscriptionTable.put(item, messageListener);
		});
		consumer.setSubscriptionTable(subscriptionTable);
		return consumer;
	}
}
