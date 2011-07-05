package org.yseasony.j2se.concurrent.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.yseasony.j2se.concurrent.cyclicBarrier.Total.BillService;
import org.yseasony.j2se.concurrent.cyclicBarrier.Total.TotalService;

/** 
 * 各省数据独立，分库存偖。为了提高计算性能，统计时采用每个省开一个线程先计算单省结果，最后汇总。 
 *  
 * @author guangbo email:weigbo@163.com 
 *  
 */  
public class Total {  
  
	public interface BillService {  
	    /** 
	     * 各省计费 
	     *  
	     * @param code 
	     *            省编码 
	     */  
	    public void bill(String code);  
	}  
	  
	/** 
	 * @author guangbo 
	 *  
	 */  
	public interface TotalService {  
	  
	    /** 
	     * 汇总各省数据 
	     */  
	    public void count();  
	  
	}  
	
	class TotalServiceImpl implements TotalService{
		@Override
		public void count() {
			// TODO Auto-generated method stub
		}
	}
	
	class BillServiceImpl implements BillService{
		@Override
		public void bill(String code) {
			// TODO Auto-generated method stub
		}
		
	}
	
    // private ConcurrentHashMap result = new ConcurrentHashMap();  
  
    public static void main(String[] args) {  
    	Total total = new Total();
        TotalService totalService = total.new TotalServiceImpl();  
        CyclicBarrier barrier = new CyclicBarrier(5,  
                new TotalTask(totalService));  
  
        ExecutorService exe = Executors.newFixedThreadPool(5);
        // 实际系统是查出所有省编码code的列表，然后循环，每个code生成一个线程。  
//        new BillTask(total.new BillServiceImpl(), barrier, "北京").start();  
//		  new BillTask(total.new BillServiceImpl(), barrier, "上海").start();  
//        new BillTask(total.new BillServiceImpl(), barrier, "广西").start();  
//        new BillTask(total.new BillServiceImpl(), barrier, "四川").start();  
//        new BillTask(total.new BillServiceImpl(), barrier, "黑龙江").start();  
        exe.execute(new BillTask(total.new BillServiceImpl(), barrier, "北京"));
        exe.execute(new BillTask(total.new BillServiceImpl(), barrier, "上海"));
        exe.execute(new BillTask(total.new BillServiceImpl(), barrier, "广西"));
        exe.execute(new BillTask(total.new BillServiceImpl(), barrier, "四川"));
        exe.execute(new BillTask(total.new BillServiceImpl(), barrier, "黑龙江"));
        exe.shutdown();
    }  
}  
  
/** 
 * 主任务：汇总任务 
 */  
class TotalTask implements Runnable {  
    private TotalService totalService;  
  
    TotalTask(TotalService totalService) {  
        this.totalService = totalService;  
    }  
  
    public void run() {  
        // 读取内存中各省的数据汇总，过程略。  
        totalService.count();  
        System.out.println("=======================================");  
        System.out.println("开始全国汇总");  
    }  
}  
  
/** 
 * 子任务：计费任务 
 */  
class BillTask extends Thread {  
    // 计费服务  
    private BillService billService;  
    private CyclicBarrier barrier;  
    // 代码，按省代码分类，各省数据库独立。  
    private String code;  
  
    BillTask(BillService billService, CyclicBarrier barrier, String code) {  
        this.billService = billService;  
        this.barrier = barrier;  
        this.code = code;  
    }  
  
    public void run() {  
        System.out.println("开始计算--" + code + "省--数据！");  
        billService.bill(code);  
        // 把bill方法结果存入内存，如ConcurrentHashMap,vector等,代码略  
        System.out.println(code + "省已经计算完成,并通知汇总Service！");  
        try {  
            // 通知barrier已经完成  
            barrier.await();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        } catch (BrokenBarrierException e) {  
            e.printStackTrace();  
        }  
    }  
  
}  
