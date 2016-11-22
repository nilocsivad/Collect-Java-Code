import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author Colin
 * 
 */
public class SchedulerTest {

	/**
	 * 
	 */
	public SchedulerTest() {
	}

	public static void main(String[] args) throws Exception {


		JobDetail job = JobBuilder.newJob(CallerJob.class).withIdentity("callerID1", "groupAT").build();

		SimpleScheduleBuilder sch = SimpleScheduleBuilder.repeatSecondlyForever(10).withRepeatCount(3);
		// CronScheduleBuilder sch = CronScheduleBuilder.cronSchedule("0/5 * * *
		// * ?");

		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("simpleTrigger", "groupAT").withSchedule(sch).startNow().build();

		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.scheduleJob(job, trigger);
		scheduler.start();

		System.out.println(scheduler.toString());

	}

}
