package com.sandbox.concurrent.phaser;

import java.util.concurrent.Phaser;

public class Match {

    // 模拟了100米赛跑，10名选手，只等裁判一声令下。当所有人都到达终点时，比赛结束。
    public static void main(String[] args) throws InterruptedException {

        final Phaser phaser=new Phaser(1) ;
        // 十名选手
        for (int index = 0; index < 10; index++) {
            phaser.register();
            new Thread(new player(phaser),"player"+index).start();
        }
        System.out.println("Game Start");
        //注销当前线程,比赛开始
        phaser.arriveAndDeregister();
        //是否非终止态一直等待
        while(!phaser.isTerminated()){
        }
        System.out.println("Game Over");
    }

    private static class player implements Runnable{

        private  final Phaser phaser ;

        player(Phaser phaser){
            this.phaser=phaser;
        }
        @Override
        public void run() {
            try {
                // 第一阶段——等待创建好所有线程再开始
                phaser.arriveAndAwaitAdvance();

                // 第二阶段——等待所有选手准备好再开始
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println(Thread.currentThread().getName() + " ready");
                phaser.arriveAndAwaitAdvance();

                // 第三阶段——等待所有选手准备好到达，到达后，该线程从phaser中注销，不在进行下面的阶段。
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println(Thread.currentThread().getName() + " arrived");
                phaser.arriveAndDeregister();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
