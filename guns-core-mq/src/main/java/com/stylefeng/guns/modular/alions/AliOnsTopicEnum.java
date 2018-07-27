package com.stylefeng.guns.modular.alions;

public enum AliOnsTopicEnum {
	/**
	 * 事务消息
	 */
	TRANSACTION_TOPIC("TopicMQ1"),
	/**
	 * 普通消息
	 */
	COMMON_TOPIC("TopicMQ2");
	
	private String topic;
	
	AliOnsTopicEnum(String topic) {
		this.setTopic(topic);
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	
}
