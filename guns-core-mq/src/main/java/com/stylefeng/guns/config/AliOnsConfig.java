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
import com.stylefeng.guns.modular.alions.AliOnsTransactionChecker;

@Configuration
public class AliOnsConfig {
	@Autowired
	AliOnsProperties prop;
	/**
	 * 定义消息生产者
	 */
	@Bean(initMethod="start", destroyMethod="shutdown")
	public ProducerBean producer() {
		ProducerBean producer = new ProducerBean();
		Properties properties = new Properties();
		properties.setProperty("ProducerId", prop.getProducerId());
		properties.setProperty("AccessKey", prop.getAccessKey());
		properties.setProperty("SecretKey", prop.getSecretKey());
		properties.setProperty("ONSAddr", prop.getOnsAddr());
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
		properties.setProperty("ProducerId", prop.getProducerId());
		properties.setProperty("AccessKey", prop.getAccessKey());
		properties.setProperty("SecretKey", prop.getSecretKey());
		properties.setProperty("ONSAddr", prop.getOnsAddr());
		tranProducer.setProperties(properties);
		tranProducer.setLocalTransactionChecker(new AliOnsTransactionChecker());
		return tranProducer;
	}
	/**
	 * 定义消息消费者
	 */
	@Bean(initMethod="start", destroyMethod="shutdown")
	public ConsumerBean consumer() {
		ConsumerBean consumer = new ConsumerBean();
		Properties properties = new Properties();
		properties.setProperty("ConsumerId", prop.getConsumerId());
		properties.setProperty("AccessKey", prop.getAccessKey());
		properties.setProperty("SecretKey", prop.getSecretKey());
		properties.setProperty("ONSAddr", prop.getOnsAddr());
		if (Objects.nonNull(prop.getConsumeThreadNums())) {
			properties.setProperty("ConsumeThreadNums", prop.getConsumeThreadNums());
		}
		consumer.setProperties(properties);
		Map<Subscription, MessageListener> subscriptionTable = Maps.newHashMap();
		List<Subscription> subscription = prop.getSubscription();
		subscription.forEach(item -> {
			subscriptionTable.put(item, new AliOnsMessageListener());
		});
		consumer.setSubscriptionTable(subscriptionTable);
		return consumer;
	}
}
