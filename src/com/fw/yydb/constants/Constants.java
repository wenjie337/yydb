package com.fw.yydb.constants;

public class Constants {

	// 夺宝号起始号码
	public static Long START_NUMBER = 10000001L;

	// 订单状态
	public static final class ORDER_STATUS {

		// 待支付
		public static final Long TO_BE_PAY = 0L;
		// 支付中
		public static final Long PROCESSING = 1L;
		// 支付成功
		public static final Long PAY_SUCCESS = 2L;
		// 支付失败
		public static final Long PAY_FAIL = 3L;
		//撤销中
		public static final Long RECALL_PROCESSING = 8l;
		//撤销成功
		public static final Long RECALL_SUCCESS = 9l;
		//撤销失败
		public static final Long RECALL_FAIL = 10l;
	}

	// 期数状态
	public static final class ACTIVITY_STATUS {

		// 未开始
		public static final Integer NOT_START = 0;
		// 进行中
		public static final Integer PROCESSING = 1;
		// 结束
		public static final Integer END = 2;
		// 待开奖
		public static final Integer TO_BE_LOTTERY = 3;

		// 异常状态
		public static final Long EXCEPTION = 4L;
	}

}
