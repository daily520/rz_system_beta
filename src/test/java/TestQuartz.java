import com.itqf.entity.ScheduleJob;
import com.itqf.utils.ScheduleUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Scheduler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-quartz.xml")
public class TestQuartz {
    @Resource
    Scheduler scheduler;

    @Test
    public void test() throws Exception{
        System.out.println(scheduler);
        ScheduleJob scheduleJob=new ScheduleJob();
        scheduleJob.setCronExpression("* * * * * ?");
        ScheduleUtils.createTask(scheduler,scheduleJob);


        Thread.sleep(10000);

        scheduleJob.setCronExpression("*/4 * * * * ?");

        System.out.println("修改成功");

        ScheduleUtils.pause(scheduler,1);
        System.out.println("暂停成功！");
        Thread.sleep(10000);



        ScheduleUtils.resume(scheduler,1);
        System.out.println("恢复运行成功！");
        Thread.sleep(10000);


        System.out.println(ScheduleUtils.deleteSchedule(scheduler,1));
    }
}
