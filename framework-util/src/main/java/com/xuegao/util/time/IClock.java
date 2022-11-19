package com.xuegao.util.time;

public interface IClock {

    /**
     * 返回从纪元开始的毫秒数.
     *
     * @return 从纪元开始的毫秒数
     */
    long nowMillis();

    /**
     * 创建系统时钟.
     *
     * @return 系统时钟
     */
    static IClock systemClock() {
        return new SystemClock();
    }

    final class SystemClock implements IClock {
        @Override
        public long nowMillis() {
            return System.currentTimeMillis();
        }
    }
}
