package com.qgj.quartz.util;

import org.quartz.JobExecutionContext;
import com.qgj.quartz.domain.SysJob;

/**
 * 定时任务处理（允许并发执行）
 *
 * @author qgj
 *
 */
public class QuartzJobExecution extends AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception
    {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
