package com.echain.web.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class WorkerScheduled {

    @Autowired
    private OperateLog2DbWorker operateLogInfo;

    @Autowired
    private OperateLog2DbWorker operateLogError;

    @Autowired
    private OperateLog2DbWorker operateLogDebug;
    
    @Autowired
    private UserMachineStopWorker userMachineStopWorker;
    
    @Autowired
    private UserProfitWorker userProfitWorker;
    
    @Autowired
    private UserAgentProfitWorker userAgentProfitWorker;
    
    @Autowired
    private UserMachineReturnWorker userMachineReturnWorker;

    @Scheduled(cron = "${system.worker.operatelog.cron:0 0/5 * * * ?}")
    public void operateLogError() {
        operateLogError.worker();
        operateLogInfo.worker();
        operateLogDebug.worker();
    }
    
    @Scheduled(cron = "${system.worker.userMachineStopWorker.cron:0 10 * * * ?}")
    public void userMachineStopWorker() {
    	userMachineStopWorker.worker();
    }
    
    @Scheduled(cron = "${system.worker.userProfitWorker.cron:0 5 1 * * ?}")
    public void userProfitWorker() {
    	userProfitWorker.worker();
    }
    
    @Scheduled(cron = "${system.worker.userAgentProfitWorker.cron:0 35 4 * * ?}")
    public void userAgentProfitWorker() {
    	userAgentProfitWorker.worker();
    }
    
    @Scheduled(cron = "${system.worker.userMachineReturnWorker.cron:0 35 5 * * ?}")
    public void userMachineReturnWorker() {
    	userMachineReturnWorker.worker();
    }
}
