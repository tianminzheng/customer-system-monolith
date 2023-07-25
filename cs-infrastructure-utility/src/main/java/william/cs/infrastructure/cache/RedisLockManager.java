//package org.geekbang.projects.cs.infrastructure.cache;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//public class RedisLockManager {
//
//    /**
//     * 数据库操作时加锁时间, 单位: 秒
//     */
//    private static final int REDIS_LOCK_TIME = 5;
//
//    @Autowired
//    private RedisManager redisManager;
//
//
//    /**
//     * redis加锁
//     * 到期自动释放锁
//     *
//     * @param lockKey redis lock key
//     */
//    public boolean lockExpired(String lockKey) {
//        if (StringUtils.isBlank(lockKey)) {
//            return false;
//        }
//        return redisManager.setIfNotExists(
//                lockKey, StringUtils.EMPTY, REDIS_LOCK_TIME);
//    }
//
//
//    /**
//     * redis加锁
//     * 到期自动释放锁
//     *
//     * @param lockKey redis lock key
//     * @param seconds redis lock time
//     */
//    public boolean lock(String lockKey, int seconds) {
//        if (StringUtils.isBlank(lockKey)) {
//            return false;
//        }
//        return redisManager.setIfNotExists(
//                lockKey, StringUtils.EMPTY, seconds);
//    }
//
//
//    /**
//     * redis解锁
//     *
//     * @param lockKey redis lock key
//     */
//    public void unLock(String lockKey) {
//        if (StringUtils.isBlank(lockKey)) {
//            return;
//        }
//        redisManager.delete(lockKey);
//    }
//
//    /**
//     * 一个key对应一把锁
//     * 同一个线程可重入锁
//     * 加锁时间可续期
//     *
//     * @param lockKey 锁key
//     * @param seconds 加锁时间
//     * @return true: 加锁成功
//     */
//    public boolean lock2(String lockKey, int seconds) {
//
//        if (StringUtils.isBlank(lockKey)) {
//            return false;
//        }
//        String threadId = String.valueOf(Thread.currentThread().getId());
//        boolean lock = redisManager.setIfNotExists(
//                lockKey, threadId, seconds);
//        if (lock) {
//            return true;
//        }
//
//        String value = redisManager.get(lockKey);
//        return StringUtils.equals(value, threadId);
//    }
//
//    /**
//     * 一个key对应一把锁
//     * value值不一致 表示未获取到锁
//     * 调用该接口时不需要关心是否已获取到锁
//     *
//     * @param lockKey 锁key
//     * @return true: 解锁成功
//     */
//    public boolean unlock2(String lockKey) {
//        if (StringUtils.isBlank(lockKey)) {
//            return false;
//        }
//        String threadId = String.valueOf(Thread.currentThread().getId());
//        String value = redisManager.get(lockKey);
//        if (StringUtils.isBlank(value)) {
//            return true;
//        }
//        if (!StringUtils.equals(value, threadId)) {
//            log.error("尚未获取到锁,解锁失败:{}", lockKey);
//            return false;
//        }
//
//        Boolean delete = redisManager.delete(lockKey);
//        return delete != null && delete;
//    }
//
//    /**
//     * 一个key对应一把锁
//     * 同一个线程可重入锁
//     * 加锁时间可续期
//     *
//     * @param lockKey 锁key
//     * @param seconds 加锁时间
//     * @return true: 加锁成功
//     */
//    public boolean lock3(String lockKey, int seconds) {
//
//        if (StringUtils.isBlank(lockKey)) {
//            return false;
//        }
//        String threadId = String.valueOf(Thread.currentThread().getId());
//        Boolean lock = redisManager.lock(lockKey, threadId, seconds);
//        return lock != null && lock;
//
//    }
//
//    /**
//     * redis解锁
//     *
//     * @param lockKey redis lock key
//     */
//    public void unLock3(String lockKey) {
//        if (StringUtils.isBlank(lockKey)) {
//            return;
//        }
//        redisManager.unlock(lockKey, String.valueOf(Thread.currentThread().getId()));
//    }
//
//
//}
