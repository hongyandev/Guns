package com.stylefeng.guns.modular.alions;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionChecker;
import com.aliyun.openservices.ons.api.transaction.TransactionStatus;

public class AliOnsTransactionChecker implements LocalTransactionChecker {

	@Override
	public TransactionStatus check(Message message) {
		System.out.println("开始回查本地事务状态");
        return TransactionStatus.CommitTransaction;
	}

}
