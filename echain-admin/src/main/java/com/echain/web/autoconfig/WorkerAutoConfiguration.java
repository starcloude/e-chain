package com.echain.web.autoconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.echain.web.worker.OperateLog2DbWorker;
import com.echain.web.worker.UserAgentProfitWorker;
import com.echain.web.worker.UserMachineReturnWorker;
import com.echain.web.worker.UserMachineStopWorker;
import com.echain.web.worker.UserProfitWorker;
import com.echain.web.worker.WorkerScheduled;

@Configuration
public class WorkerAutoConfiguration {

    @Bean("operateLogInfo")
    public OperateLog2DbWorker operateLogInfo() {
        OperateLog2DbWorker logWorker = new OperateLog2DbWorker();
        logWorker.setLevel("INFO");
        return logWorker;
    }

    @Bean("operateLogError")
    public OperateLog2DbWorker operateLogError() {
        OperateLog2DbWorker logWorker = new OperateLog2DbWorker();
        logWorker.setLevel("ERROR");
        return logWorker;
    }

    @Bean("operateLogDebug")
    public OperateLog2DbWorker operateLogDebug() {
        OperateLog2DbWorker logWorker = new OperateLog2DbWorker();
        logWorker.setLevel("DEBUG");
        return logWorker;
    }

    @Bean("workerScheduled")
    public WorkerScheduled workerScheduled() {
        return new WorkerScheduled();
    }
    
    @Bean("userMachineStopWorker")
    public UserMachineStopWorker userMachineStopWorker() {
    	return new UserMachineStopWorker();
    }
    
    @Bean("userProfitWorker")
    public UserProfitWorker userProfitWorker() {
    	return new UserProfitWorker();
    }
    
    @Bean("userAgentProfitWorker")
    public UserAgentProfitWorker userAgentProfitWorker() {
    	return new UserAgentProfitWorker();
    }
    
    @Bean("userMachineReturnWorker")
    public UserMachineReturnWorker userMachineReturnWorker() {
    	return new UserMachineReturnWorker();
    }
}
 