package com.lunchtasting.server.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolService {
	
	private ExecutorService proExe;
	
	private ExecutorService regExe;
	private int proExe_corePoolSize;
	private int proExe_mximumPoolSize;
	private int proExe_blockingQueueSize;
	private int regExe_corePoolSize;
	private int regExe_mximumPoolSize;
	private int regExe_blockingQueueSize;
	
	
	private ThreadPoolService(){
	}
	
	public void init(){
		proExe = new ThreadPoolExecutor(proExe_corePoolSize, proExe_mximumPoolSize, 0L,
				TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(proExe_blockingQueueSize),
				new ThreadPoolExecutor.DiscardOldestPolicy());
		
		regExe = new ThreadPoolExecutor(regExe_corePoolSize, regExe_mximumPoolSize,
				0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(regExe_blockingQueueSize),
				new ThreadPoolExecutor.DiscardOldestPolicy());
	}
	
	public void destroy(){
		proExe.shutdown();
		proExe =null;
		regExe.shutdown();
		regExe = null;
	}

	public int getProExe_corePoolSize() {
		return proExe_corePoolSize;
	}

	public void setProExe_corePoolSize(int proExe_corePoolSize) {
		this.proExe_corePoolSize = proExe_corePoolSize;
	}

	public int getProExe_mximumPoolSize() {
		return proExe_mximumPoolSize;
	}

	public void setProExe_mximumPoolSize(int proExe_mximumPoolSize) {
		this.proExe_mximumPoolSize = proExe_mximumPoolSize;
	}

	public int getProExe_blockingQueueSize() {
		return proExe_blockingQueueSize;
	}

	public void setProExe_blockingQueueSize(int proExe_blockingQueueSize) {
		this.proExe_blockingQueueSize = proExe_blockingQueueSize;
	}

	public int getRegExe_corePoolSize() {
		return regExe_corePoolSize;
	}

	public void setRegExe_corePoolSize(int regExe_corePoolSize) {
		this.regExe_corePoolSize = regExe_corePoolSize;
	}

	public int getRegExe_mximumPoolSize() {
		return regExe_mximumPoolSize;
	}

	public void setRegExe_mximumPoolSize(int regExe_mximumPoolSize) {
		this.regExe_mximumPoolSize = regExe_mximumPoolSize;
	}

	public int getRegExe_blockingQueueSize() {
		return regExe_blockingQueueSize;
	}

	public void setRegExe_blockingQueueSize(int regExe_blockingQueueSize) {
		this.regExe_blockingQueueSize = regExe_blockingQueueSize;
	}

	public ExecutorService getProExe() {
		return proExe;
	}

	public void setProExe(ExecutorService proExe) {
		this.proExe = proExe;
	}

	public ExecutorService getRegExe() {
		return regExe;
	}

	public void setRegExe(ExecutorService regExe) {
		this.regExe = regExe;
	}
	
}
