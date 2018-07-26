package com.stylefeng.guns.config.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.aliyun.openservices.ons.api.bean.Subscription;

import lombok.Data;
@Data
@Component
@ConfigurationProperties(prefix = AliOnsProperties.ALIONSCONF_PREFIX)
public class AliOnsProperties {

	public static final String ALIONSCONF_PREFIX = "aliyun.ons";
	public static final String ACCESS_KEY = "AccessKey";
    public static final String SECRET_KEY = "SecretKey";
    /**
     * MQ消息轨迹功能开关
     */
    public static final String MSG_TRACE_SWITCH = "MsgTraceSwitch";
    /**
     * 消息队列服务接入点
     */
    public static final String ONS_ADDR = "ONSAddr";
    /**
     * 生产者ID
     */
    public static final String PRODUCER_ID = "ProducerId";
    /**
     * 消息发送超时时间
     */
    public static final String SEND_MSG_TIMEOUT_MILLIS = "SendMsgTimeoutMillis";
    /**
     * 消费者ID
     */
    public static final String CONSUMER_ID = "ConsumerId";
    /**
     * 消费线程数量
     */
    public static final String CONSUME_THREAD_NUMS = "ConsumeThreadNums";
    /**
     * 消费模式
     */
    public static final String MESSAGE_MODEL = "MessageModel";
    /**
     * 消息消费失败时的最大重试次数
     */
    public static final String MAX_RECONSUME_TIMES = "MaxReconsumeTimes";
    /**
     * 每条消息消费的最大超时时间
     */
    public static final String CONSUME_TIMEOUT = "ConsumeTimeout";
    /**
     * 是否每次请求都带上最新的订阅关系
     */
    public static final String POST_SUBSCRIPTION_WHEN_PULL = "PostSubscriptionWhenPull";
    /**
     * 每次批量消费的最大消息数量
     */
    public static final String CONSUME_MESSAGE_BATCH_MAX_SIZE = "ConsumeMessageBatchMaxSize";
    /**
     * 允许在客户端中缓存的最大消息数量
     */
    public static final String MAX_CACHED_MESSAGE_AMOUNT = "MaxCachedMessageAmount";
    /**
     * 允许在客户端中缓存的最大消息容量
     */
    public static final String MAX_CACHED_MESSAGE_SIZE_IN_MIB = "MaxCachedMessageSizeInMiB";

	String accessKey;
	String secretKey;
	String msgTraceSwitch = "true";
	String onsAddr;
	String producerId;
    String sendMsgTimeoutMillis = "5000";
	String consumerId;
	String consumeThreadNums;
	String messageModel = "CLUSTERING";
	String maxReconsumeTimes = "16";
	String consumeTimeout = "16";
	String postSubscriptionWhenPull = "false";
	String consumeMessageBatchMaxSize = "1";
	String maxCachedMessageAmount = "5000";
	String maxCachedMessageSizeInMiB = "512";
	List<Subscription> subscription;

}
