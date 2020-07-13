package org.jit.sose.interceptor;


import org.jit.sose.util.QcloudSmsUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

/**
 * 定时执行任务
 * 
 */
@Component
public class TimerManager {

	// “0 0 12 * * ?” 每天中午十二点触发
	// “0 15 10 ? * *” 每天早上10：15触发
	// “0 15 10 * * ?” 每天早上10：15触发
	// “0 15 10 * * ? *” 每天早上10：15触发
	// “0 15 10 * * ? 2005” 2005年的每天早上10：15触发
	// “0 * 14 * * ?” 每天从下午2点开始到2点59分每分钟一次触发
	// “0 0/5 14 * * ?” 每天从下午2点开始到2：55分结束每5分钟一次触发
	// “0 0/5 14,18 * * ?” 每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发
	// “0 0-5 14 * * ?” 每天14:00至14:05每分钟一次触发
	// “0 10,44 14 ? 3 WED” 三月的每周三的14：10和14：44触发
	// “0 15 10 ? * MON-FRI” 每个周一、周二、周三、周四、周五的10：15触发

	// 短信发送工具类
	QcloudSmsUtil qUtil = new QcloudSmsUtil();

	// 初始化、没有此方法会报错但是不影响项目运行
	@Bean
	public TaskScheduler scheduledExecutorService() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(8);
		scheduler.setThreadNamePrefix("scheduled-thread-");
		return scheduler;
	}

	/**
	 * 测试，将此方法取消注释运行项目即可看到效果
	 */
//	@Scheduled(cron = "0/2 * * * * ?") // 每隔10秒隔行一次、用于测试
//	public void test2() {
//		Date date = new Date();
//		System.out.println("当前时间为:" + date);
//		System.out.println("*********************两秒测试集************************* ");
//	}

	/**
	 * 每天10点启动任务,10日至28日不间断发送短信(已竣工验收但是未送审)
	 * 
	 * @throws Exception
	 */
	@Scheduled(cron = "0 0 10 * * ?")
	public void warn() throws Exception {
	}

	/**
	 * 每天10点启动任务,28日之后发送一条短信(发送完逻辑删除数据、未在规定时间内送审)
	 * 
	 * @throws Exception
	 */
	@Scheduled(cron = "0 0 10 * * ?")
	public void error() throws Exception {
	}

}
