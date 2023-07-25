//package org.geekbang.projects.cs.infrastructure.cache;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.connection.DataType;
//import org.springframework.data.redis.core.Cursor;
//import org.springframework.data.redis.core.ScanOptions;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ZSetOperations;
//import org.springframework.data.redis.core.script.RedisScript;
//import org.springframework.stereotype.Component;
//
//import java.util.*;
//import java.util.concurrent.TimeUnit;
//
//@Component
//public class RedisManager {
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    /**
//     * 默认请求系统
//     */
//    public static final String REQUEST_SYSTEM = "spring-medical-care:";
//
//
//    private static String getKey(String key) {
//        return REQUEST_SYSTEM + key;
//    }
//
//    public StringRedisTemplate getRedisTemplate() {
//        return this.redisTemplate;
//    }
//
//    /* -------------------key相关操作--------------------- */
//
//    /**
//     * 删除key
//     *
//     * @param key
//     */
//    public Boolean delete(String key) {
//        key = getKey(key);
//        return redisTemplate.delete(key);
//    }
//
//    /**
//     * 批量删除key
//     *
//     * @param keys
//     */
//    public void delete(Collection<String> keys) {
//        redisTemplate.delete(keys);
//    }
//
//    /**
//     * 序列化key
//     *
//     * @param key
//     * @return
//     */
//    public byte[] dump(String key) {
//        key = getKey(key);
//        return redisTemplate.dump(key);
//    }
//
//    /**
//     * 是否存在key
//     *
//     * @param key
//     * @return
//     */
//    public Boolean hasKey(String key) {
//        key = getKey(key);
//        return redisTemplate.hasKey(key);
//    }
//
//    /**
//     * 设置过期时间
//     *
//     * @param key
//     * @param timeout
//     * @param unit
//     * @return
//     */
//    public Boolean expire(String key, long timeout, TimeUnit unit) {
//        key = getKey(key);
//        return redisTemplate.expire(key, timeout, unit);
//    }
//
//    /**
//     * 设置过期时间
//     *
//     * @param key
//     * @param date
//     * @return
//     */
//    public Boolean expireAt(String key, Date date) {
//        key = getKey(key);
//        return redisTemplate.expireAt(key, date);
//    }
//
//    /**
//     * 将当前数据库的 key 移动到给定的数据库 db 当中
//     *
//     * @param key
//     * @param dbIndex
//     * @return
//     */
//    public Boolean move(String key, int dbIndex) {
//        key = getKey(key);
//        return redisTemplate.move(key, dbIndex);
//    }
//
//    /**
//     * 移除 key 的过期时间，key 将持久保持
//     *
//     * @param key
//     * @return
//     */
//    public Boolean persist(String key) {
//        key = getKey(key);
//        return redisTemplate.persist(key);
//    }
//
//    /**
//     * 返回 key 的剩余的过期时间
//     *
//     * @param key
//     * @param unit
//     * @return
//     */
//    public Long getExpire(String key, TimeUnit unit) {
//        key = getKey(key);
//        return redisTemplate.getExpire(key, unit);
//    }
//
//    /**
//     * 返回 key 的剩余的过期时间
//     *
//     * @param key
//     * @return
//     */
//    public Long getExpire(String key) {
//        key = getKey(key);
//        return redisTemplate.getExpire(key);
//    }
//
//    /**
//     * 从当前数据库中随机返回一个 key
//     *
//     * @return
//     */
//    public String randomKey() {
//        return redisTemplate.randomKey();
//    }
//
//    /**
//     * 修改 key 的名称
//     *
//     * @param oldKey
//     * @param newKey
//     */
//    public void rename(String oldKey, String newKey) {
//        oldKey = getKey(oldKey);
//        newKey = getKey(newKey);
//        redisTemplate.rename(oldKey, newKey);
//    }
//
//    /**
//     * 仅当 newkey 不存在时，将 oldKey 改名为 newkey
//     *
//     * @param oldKey
//     * @param newKey
//     * @return
//     */
//    public Boolean renameIfAbsent(String oldKey, String newKey) {
//        oldKey = getKey(oldKey);
//        newKey = getKey(newKey);
//        return redisTemplate.renameIfAbsent(oldKey, newKey);
//    }
//
//    /**
//     * 返回 key 所储存的值的类型
//     *
//     * @param key
//     * @return
//     */
//    public DataType type(String key) {
//        key = getKey(key);
//        return redisTemplate.type(key);
//    }
//
//    /* -------------------string相关操作--------------------- */
//
//    /**
//     * 设置指定 key 的值
//     *
//     * @param key
//     * @param value
//     */
//    public void set(String key, String value) {
//        key = getKey(key);
//        redisTemplate.opsForValue().set(key, value);
//    }
//
//    /**
//     * 设置指定 key 的值
//     *
//     * @param key
//     * @param value
//     */
//    public void set(String key, String value, long time, TimeUnit timeUnit) {
//        key = getKey(key);
//        redisTemplate.opsForValue().set(key, value, time, timeUnit);
//    }
//
//    /**
//     * 获取指定 key 的值
//     *
//     * @param key
//     * @return
//     */
//    public String get(String key) {
//        key = getKey(key);
//        return redisTemplate.opsForValue().get(key);
//    }
//
//    /**
//     * 返回 key 中字符串值的子字符
//     *
//     * @param key
//     * @param start
//     * @param end
//     * @return
//     */
//    public String getRange(String key, long start, long end) {
//        key = getKey(key);
//        return redisTemplate.opsForValue().get(key, start, end);
//    }
//
//    /**
//     * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)
//     *
//     * @param key
//     * @param value
//     * @return
//     */
//    public String getAndSet(String key, String value) {
//        key = getKey(key);
//        return redisTemplate.opsForValue().getAndSet(key, value);
//    }
//
//    /**
//     * 对 key 所储存的字符串值，获取指定偏移量上的位(bit)
//     *
//     * @param key
//     * @param offset
//     * @return
//     */
//    public Boolean getBit(String key, long offset) {
//        key = getKey(key);
//        return redisTemplate.opsForValue().getBit(key, offset);
//    }
//
//    /**
//     * 批量获取
//     *
//     * @param keys
//     * @return
//     */
//    public List<String> multiGet(Collection<String> keys) {
//        List<String> keysList = new ArrayList<>();
//        for (String key:keys
//        ) {
//            keysList.add(getKey(key));
//        }
//        return redisTemplate.opsForValue().multiGet(keysList);
//    }
//
//    /**
//     * 设置ASCII码, 字符串'a'的ASCII码是97, 转为二进制是'01100001', 此方法是将二进制第offset位值变为value
//     *
//     * @param key   位置
//     * @param value 值,true为1, false为0
//     * @return
//     */
//    public boolean setBit(String key, long offset, boolean value) {
//        key = getKey(key);
//        return redisTemplate.opsForValue().setBit(key, offset, value);
//    }
//
//    /**
//     * 将值 value 关联到 key ，并将 key 的过期时间设为 timeout
//     *
//     * @param key
//     * @param value
//     * @param timeout 过期时间
//     * @param unit    时间单位, 天:TimeUnit.DAYS 小时:TimeUnit.HOURS 分钟:TimeUnit.MINUTES
//     *                秒:TimeUnit.SECONDS 毫秒:TimeUnit.MILLISECONDS
//     */
//    public void setEx(String key, String value, long timeout, TimeUnit unit) {
//        key = getKey(key);
//        redisTemplate.opsForValue().set(key, value, timeout, unit);
//    }
//
//    /**
//     * 只有在 key 不存在时设置 key 的值
//     *
//     * @param key
//     * @param value
//     * @return 之前已经存在返回false, 不存在返回true
//     */
//    public boolean setIfAbsent(String key, String value) {
//        key = getKey(key);
//        return redisTemplate.opsForValue().setIfAbsent(key, value);
//    }
//
//    /**
//     * 只有在 key 不存在时设置 key 的值
//     *
//     * @param key
//     * @param value
//     * @param timeout：超时时间
//     * @param unit:单位
//     * @return 之前已经存在返回false, 不存在返回true
//     */
//    public boolean setIfAbsent(String key, String value, long timeout, TimeUnit unit) {
//        key = getKey(key);
//        return redisTemplate.opsForValue().setIfAbsent(key, value, timeout, unit);
//    }
//
//
//    /**
//     * 用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始
//     *
//     * @param key
//     * @param value
//     * @param offset 从指定位置开始覆写
//     */
//    public void setRange(String key, String value, long offset) {
//        key = getKey(key);
//        redisTemplate.opsForValue().set(key, value, offset);
//    }
//
//    /**
//     * 获取字符串的长度
//     *
//     * @param key
//     * @return
//     */
//    public Long size(String key) {
//        key = getKey(key);
//        return redisTemplate.opsForValue().size(key);
//    }
//
//    /**
//     * 增加(自增长), 负数则为自减
//     *
//     * @param key
//     * @return
//     */
//    public Long incrBy(String key, long increment) {
//        key = getKey(key);
//        return redisTemplate.opsForValue().increment(key, increment);
//    }
//
//    /**
//     * @param key
//     * @return
//     */
//    public Double incrByFloat(String key, double increment) {
//        key = getKey(key);
//        return redisTemplate.opsForValue().increment(key, increment);
//    }
//
//    /**
//     * 追加到末尾
//     *
//     * @param key
//     * @param value
//     * @return
//     */
//    public Integer append(String key, String value) {
//        key = getKey(key);
//        return redisTemplate.opsForValue().append(key, value);
//    }
//
//    /* -------------------hash相关操作------------------------- */
//
//    /**
//     * 获取存储在哈希表中指定字段的值
//     *
//     * @param key
//     * @param field
//     * @return
//     */
//    public Object hGet(String key, String field) {
//        key = getKey(key);
//        return redisTemplate.opsForHash().get(key, field);
//    }
//
//    /**
//     * 获取所有给定字段的值
//     *
//     * @param key
//     * @return
//     */
//    public Map<Object, Object> hGetAll(String key) {
//        key = getKey(key);
//        return redisTemplate.opsForHash().entries(key);
//    }
//
//    /**
//     * 获取所有给定字段的值
//     *
//     * @param key
//     * @param fields
//     * @return
//     */
//    public List<Object> hMultiGet(String key, Collection<Object> fields) {
//        key = getKey(key);
//        return redisTemplate.opsForHash().multiGet(key, fields);
//    }
//
//    public void hPut(String key, String hashKey, String value) {
//        key = getKey(key);
//        redisTemplate.opsForHash().put(key, hashKey, value);
//    }
//
//    public void hPutAll(String key, Map<String, String> maps) {
//        key = getKey(key);
//        redisTemplate.opsForHash().putAll(key, maps);
//    }
//
//    /**
//     * 仅当hashKey不存在时才设置
//     *
//     * @param key
//     * @param hashKey
//     * @param value
//     * @return
//     */
//    public Boolean hPutIfAbsent(String key, String hashKey, String value) {
//        key = getKey(key);
//        return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
//    }
//
//    /**
//     * 删除一个或多个哈希表字段
//     *
//     * @param key
//     * @param fields
//     * @return
//     */
//    public Long hDelete(String key, Object... fields) {
//        key = getKey(key);
//        return redisTemplate.opsForHash().delete(key, fields);
//    }
//
//    /**
//     * 查看哈希表 key 中，指定的字段是否存在
//     *
//     * @param key
//     * @param field
//     * @return
//     */
//    public boolean hExists(String key, String field) {
//        key = getKey(key);
//        return redisTemplate.opsForHash().hasKey(key, field);
//    }
//
//    /**
//     * 为哈希表 key 中的指定字段的整数值加上增量 increment
//     *
//     * @param key
//     * @param field
//     * @param increment
//     * @return
//     */
//    public Long hIncrBy(String key, Object field, long increment) {
//        key = getKey(key);
//        return redisTemplate.opsForHash().increment(key, field, increment);
//    }
//
//    /**
//     * 为哈希表 key 中的指定字段的整数值加上增量 increment
//     *
//     * @param key
//     * @param field
//     * @param delta
//     * @return
//     */
//    public Double hIncrByFloat(String key, Object field, double delta) {
//        key = getKey(key);
//        return redisTemplate.opsForHash().increment(key, field, delta);
//    }
//
//    /**
//     * 获取所有哈希表中的字段
//     *
//     * @param key
//     * @return
//     */
//    public Set<Object> hKeys(String key) {
//        key = getKey(key);
//        return redisTemplate.opsForHash().keys(key);
//    }
//
//    /**
//     * 获取哈希表中字段的数量
//     *
//     * @param key
//     * @return
//     */
//    public Long hSize(String key) {
//        key = getKey(key);
//        return redisTemplate.opsForHash().size(key);
//    }
//
//    /**
//     * 获取哈希表中所有值
//     *
//     * @param key
//     * @return
//     */
//    public List<Object> hValues(String key) {
//        key = getKey(key);
//        return redisTemplate.opsForHash().values(key);
//    }
//
//    /**
//     * 迭代哈希表中的键值对
//     *
//     * @param key
//     * @param options
//     * @return
//     */
//    public Cursor<Map.Entry<Object, Object>> hScan(String key, ScanOptions options) {
//        key = getKey(key);
//        return redisTemplate.opsForHash().scan(key, options);
//    }
//
//    /* ------------------------list相关操作---------------------------- */
//
//    /**
//     * 通过索引获取列表中的元素
//     *
//     * @param key
//     * @param index
//     * @return
//     */
//    public String lIndex(String key, long index) {
//        key = getKey(key);
//        return redisTemplate.opsForList().index(key, index);
//    }
//
//    /**
//     * 获取列表指定范围内的元素
//     *
//     * @param key
//     * @param start 开始位置, 0是开始位置
//     * @param end   结束位置, -1返回所有
//     * @return
//     */
//    public List<String> lRange(String key, long start, long end) {
//        key = getKey(key);
//        return redisTemplate.opsForList().range(key, start, end);
//    }
//
//    /**
//     * 存储在list头部
//     *
//     * @param key
//     * @param value
//     * @return
//     */
//    public Long lLeftPush(String key, String value) {
//        key = getKey(key);
//        return redisTemplate.opsForList().leftPush(key, value);
//    }
//
//    /**
//     * @param key
//     * @param value
//     * @return
//     */
//    public Long lLeftPushAll(String key, String... value) {
//        key = getKey(key);
//        return redisTemplate.opsForList().leftPushAll(key, value);
//    }
//
//    /**
//     * @param key
//     * @param value
//     * @return
//     */
//    public Long lLeftPushAll(String key, Collection<String> value) {
//        key = getKey(key);
//        return redisTemplate.opsForList().leftPushAll(key, value);
//    }
//
//    /**
//     * 当list存在的时候才加入
//     *
//     * @param key
//     * @param value
//     * @return
//     */
//    public Long lLeftPushIfPresent(String key, String value) {
//        key = getKey(key);
//        return redisTemplate.opsForList().leftPushIfPresent(key, value);
//    }
//
//    /**
//     * 如果pivot存在,再pivot前面添加
//     *
//     * @param key
//     * @param pivot
//     * @param value
//     * @return
//     */
//    public Long lLeftPush(String key, String pivot, String value) {
//        key = getKey(key);
//        return redisTemplate.opsForList().leftPush(key, pivot, value);
//    }
//
//    /**
//     * @param key
//     * @param value
//     * @return
//     */
//    public Long lRightPush(String key, String value) {
//        key = getKey(key);
//        return redisTemplate.opsForList().rightPush(key, value);
//    }
//
//    /**
//     * @param key
//     * @param value
//     * @return
//     */
//    public Long lRightPushAll(String key, String... value) {
//        key = getKey(key);
//        return redisTemplate.opsForList().rightPushAll(key, value);
//    }
//
//    /**
//     * @param key
//     * @param value
//     * @return
//     */
//    public Long lRightPushAll(String key, Collection<String> value) {
//        key = getKey(key);
//        return redisTemplate.opsForList().rightPushAll(key, value);
//    }
//
//    /**
//     * 为已存在的列表添加值
//     *
//     * @param key
//     * @param value
//     * @return
//     */
//    public Long lRightPushIfPresent(String key, String value) {
//        key = getKey(key);
//        return redisTemplate.opsForList().rightPushIfPresent(key, value);
//    }
//
//    /**
//     * 在pivot元素的右边添加值
//     *
//     * @param key
//     * @param pivot
//     * @param value
//     * @return
//     */
//    public Long lRightPush(String key, String pivot, String value) {
//        key = getKey(key);
//        return redisTemplate.opsForList().rightPush(key, pivot, value);
//    }
//
//    /**
//     * 通过索引设置列表元素的值
//     *
//     * @param key
//     * @param index 位置
//     * @param value
//     */
//    public void lSet(String key, long index, String value) {
//        key = getKey(key);
//        redisTemplate.opsForList().set(key, index, value);
//    }
//
//    /**
//     * 移出并获取列表的第一个元素
//     *
//     * @param key
//     * @return 删除的元素
//     */
//    public String lLeftPop(String key) {
//        key = getKey(key);
//        return redisTemplate.opsForList().leftPop(key);
//    }
//
//    /**
//     * 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
//     *
//     * @param key
//     * @param timeout 等待时间
//     * @param unit    时间单位
//     * @return
//     */
//    public String lBLeftPop(String key, long timeout, TimeUnit unit) {
//        key = getKey(key);
//        return redisTemplate.opsForList().leftPop(key, timeout, unit);
//    }
//
//    /**
//     * 移除并获取列表最后一个元素
//     *
//     * @param key
//     * @return 删除的元素
//     */
//    public String lRightPop(String key) {
//        key = getKey(key);
//        return redisTemplate.opsForList().rightPop(key);
//    }
//
//    /**
//     * 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
//     *
//     * @param key
//     * @param timeout 等待时间
//     * @param unit    时间单位
//     * @return
//     */
//    public String lBRightPop(String key, long timeout, TimeUnit unit) {
//        key = getKey(key);
//        return redisTemplate.opsForList().rightPop(key, timeout, unit);
//    }
//
//    /**
//     * 移除列表的最后一个元素，并将该元素添加到另一个列表并返回
//     *
//     * @param sourceKey
//     * @param destinationKey
//     * @return
//     */
//    public String lRightPopAndLeftPush(String sourceKey, String destinationKey) {
//        sourceKey = getKey(sourceKey);
//        destinationKey = getKey(destinationKey);
//        return redisTemplate.opsForList().rightPopAndLeftPush(sourceKey,
//                destinationKey);
//    }
//
//    /**
//     * 从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
//     *
//     * @param sourceKey
//     * @param destinationKey
//     * @param timeout
//     * @param unit
//     * @return
//     */
//    public String lBRightPopAndLeftPush(String sourceKey, String destinationKey,
//                                        long timeout, TimeUnit unit) {
//        sourceKey = getKey(sourceKey);
//        destinationKey = getKey(destinationKey);
//        return redisTemplate.opsForList().rightPopAndLeftPush(sourceKey,
//                destinationKey, timeout, unit);
//    }
//
//    /**
//     * 删除集合中值等于value得元素
//     *
//     * @param key
//     * @param index index=0, 删除所有值等于value的元素; index>0, 从头部开始删除第一个值等于value的元素;
//     *              index<0, 从尾部开始删除第一个值等于value的元素;
//     * @param value
//     * @return
//     */
//    public Long lRemove(String key, long index, String value) {
//        key = getKey(key);
//        return redisTemplate.opsForList().remove(key, index, value);
//    }
//
//    /**
//     * 裁剪list
//     *
//     * @param key
//     * @param start
//     * @param end
//     */
//    public void lTrim(String key, long start, long end) {
//        key = getKey(key);
//        redisTemplate.opsForList().trim(key, start, end);
//    }
//
//    /**
//     * 获取列表长度
//     *
//     * @param key
//     * @return
//     */
//    public Long lLen(String key) {
//        key = getKey(key);
//        return redisTemplate.opsForList().size(key);
//    }
//
//    /* --------------------set相关操作-------------------------- */
//
//    /**
//     * set添加元素
//     *
//     * @param key
//     * @param values
//     * @return
//     */
//    public Long sAdd(String key, String... values) {
//        key = getKey(key);
//        return redisTemplate.opsForSet().add(key, values);
//    }
//
//    /**
//     * set移除元素
//     *
//     * @param key
//     * @param values
//     * @return
//     */
//    public Long sRemove(String key, Object... values) {
//        key = getKey(key);
//        return redisTemplate.opsForSet().remove(key, values);
//    }
//
//    /**
//     * 移除并返回集合的一个随机元素
//     *
//     * @param key
//     * @return
//     */
//    public String sPop(String key) {
//        key = getKey(key);
//        return redisTemplate.opsForSet().pop(key);
//    }
//
//    /**
//     * 将元素value从一个集合移到另一个集合
//     *
//     * @param key
//     * @param value
//     * @param destKey
//     * @return
//     */
//    public Boolean sMove(String key, String value, String destKey) {
//        key = getKey(key);
//        destKey = getKey(destKey);
//        return redisTemplate.opsForSet().move(key, value, destKey);
//    }
//
//    /**
//     * 获取集合的大小
//     *
//     * @param key
//     * @return
//     */
//    public Long sSize(String key) {
//        key = getKey(key);
//        return redisTemplate.opsForSet().size(key);
//    }
//
//    /**
//     * 判断集合是否包含value
//     *
//     * @param key
//     * @param value
//     * @return
//     */
//    public Boolean sIsMember(String key, Object value) {
//        key = getKey(key);
//        return redisTemplate.opsForSet().isMember(key, value);
//    }
//
//    /**
//     * 获取两个集合的交集
//     *
//     * @param key
//     * @param otherKey
//     * @return
//     */
//    public Set<String> sIntersect(String key, String otherKey) {
//        key = getKey(key);
//        otherKey = getKey(otherKey);
//        return redisTemplate.opsForSet().intersect(key, otherKey);
//    }
//
//    /**
//     * 获取集合所有元素
//     *
//     * @param key
//     * @return
//     */
//    public Set<String> setMembers(String key) {
//        key = getKey(key);
//        return redisTemplate.opsForSet().members(key);
//    }
//
//    /**
//     * 随机获取集合中的一个元素
//     *
//     * @param key
//     * @return
//     */
//    public String sRandomMember(String key) {
//        key = getKey(key);
//        return redisTemplate.opsForSet().randomMember(key);
//    }
//
//    /**
//     * 随机获取集合中count个元素
//     *
//     * @param key
//     * @param count
//     * @return
//     */
//    public List<String> sRandomMembers(String key, long count) {
//        key = getKey(key);
//        return redisTemplate.opsForSet().randomMembers(key, count);
//    }
//
//    /**
//     * 随机获取集合中count个元素并且去除重复的
//     *
//     * @param key
//     * @param count
//     * @return
//     */
//    public Set<String> sDistinctRandomMembers(String key, long count) {
//        key = getKey(key);
//        return redisTemplate.opsForSet().distinctRandomMembers(key, count);
//    }
//
//    /**
//     * @param key
//     * @param options
//     * @return
//     */
//    public Cursor<String> sScan(String key, ScanOptions options) {
//        key = getKey(key);
//        return redisTemplate.opsForSet().scan(key, options);
//    }
//
//    /*------------------zSet相关操作--------------------------------*/
//
//    /**
//     * 添加元素,有序集合是按照元素的score值由小到大排列
//     *
//     * @param key
//     * @param value
//     * @param score
//     * @return
//     */
//    public Boolean zAdd(String key, String value, double score) {
//        key = getKey(key);
//        return redisTemplate.opsForZSet().add(key, value, score);
//    }
//
//    /**
//     * @param key
//     * @param values
//     * @return
//     */
//    public Long zAdd(String key, Set<ZSetOperations.TypedTuple<String>> values) {
//        key = getKey(key);
//        return redisTemplate.opsForZSet().add(key, values);
//    }
//
//    /**
//     * @param key
//     * @param values
//     * @return
//     */
//    public Long zRemove(String key, Object... values) {
//        key = getKey(key);
//        return redisTemplate.opsForZSet().remove(key, values);
//    }
//
//    /**
//     * 增加元素的score值，并返回增加后的值
//     *
//     * @param key
//     * @param value
//     * @param delta
//     * @return
//     */
//    public Double zIncrementScore(String key, String value, double delta) {
//        key = getKey(key);
//        return redisTemplate.opsForZSet().incrementScore(key, value, delta);
//    }
//
//    /**
//     * 返回元素在集合的排名,有序集合是按照元素的score值由小到大排列
//     *
//     * @param key
//     * @param value
//     * @return 0表示第一位
//     */
//    public Long zRank(String key, Object value) {
//        key = getKey(key);
//        return redisTemplate.opsForZSet().rank(key, value);
//    }
//
//    /**
//     * 返回元素在集合的排名,按元素的score值由大到小排列
//     *
//     * @param key
//     * @param value
//     * @return
//     */
//    public Long zReverseRank(String key, Object value) {
//        key = getKey(key);
//        return redisTemplate.opsForZSet().reverseRank(key, value);
//    }
//
//    /**
//     * 获取集合的元素, 从小到大排序
//     *
//     * @param key
//     * @param start 开始位置
//     * @param end   结束位置, -1查询所有
//     * @return
//     */
//    public Set<String> zRange(String key, long start, long end) {
//        key = getKey(key);
//        return redisTemplate.opsForZSet().range(key, start, end);
//    }
//
//    /**
//     * 获取集合元素, 并且把score值也获取
//     *
//     * @param key
//     * @param start
//     * @param end
//     * @return
//     */
//    public Set<ZSetOperations.TypedTuple<String>> zRangeWithScores(String key, long start,
//                                                                   long end) {
//        key = getKey(key);
//        return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
//    }
//
//    /**
//     * 根据Score值查询集合元素
//     *
//     * @param key
//     * @param min 最小值
//     * @param max 最大值
//     * @return
//     */
//    public Set<String> zRangeByScore(String key, double min, double max) {
//        key = getKey(key);
//        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
//    }
//
//    /**
//     * 根据Score值查询集合元素, 从小到大排序
//     *
//     * @param key
//     * @param min 最小值
//     * @param max 最大值
//     * @return
//     */
//    public Set<ZSetOperations.TypedTuple<String>> zRangeByScoreWithScores(String key,
//                                                                          double min, double max) {
//        key = getKey(key);
//        return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
//    }
//
//    /**
//     * @param key
//     * @param min
//     * @param max
//     * @param start
//     * @param end
//     * @return
//     */
//    public Set<ZSetOperations.TypedTuple<String>> zRangeByScoreWithScores(String key,
//                                                                          double min, double max, long start, long end) {
//        key = getKey(key);
//        return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max,
//                start, end);
//    }
//
//    /**
//     * 获取集合的元素, 从大到小排序
//     *
//     * @param key
//     * @param start
//     * @param end
//     * @return
//     */
//    public Set<String> zReverseRange(String key, long start, long end) {
//        key = getKey(key);
//        return redisTemplate.opsForZSet().reverseRange(key, start, end);
//    }
//
//    /**
//     * 获取集合的元素, 从大到小排序, 并返回score值
//     *
//     * @param key
//     * @param start
//     * @param end
//     * @return
//     */
//    public Set<ZSetOperations.TypedTuple<String>> zReverseRangeWithScores(String key,
//                                                                          long start, long end) {
//        key = getKey(key);
//        return redisTemplate.opsForZSet().reverseRangeWithScores(key, start,
//                end);
//    }
//
//    /**
//     * 根据Score值查询集合元素, 从大到小排序
//     *
//     * @param key
//     * @param min
//     * @param max
//     * @return
//     */
//    public Set<String> zReverseRangeByScore(String key, double min,
//                                            double max) {
//        key = getKey(key);
//        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
//    }
//
//    /**
//     * 根据Score值查询集合元素, 从大到小排序
//     *
//     * @param key
//     * @param min
//     * @param max
//     * @return
//     */
//    public Set<ZSetOperations.TypedTuple<String>> zReverseRangeByScoreWithScores(
//            String key, double min, double max) {
//        key = getKey(key);
//        return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key,
//                min, max);
//    }
//
//    /**
//     * @param key
//     * @param min
//     * @param max
//     * @param start
//     * @param end
//     * @return
//     */
//    public Set<String> zReverseRangeByScore(String key, double min,
//                                            double max, long start, long end) {
//        key = getKey(key);
//        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max,
//                start, end);
//    }
//
//    /**
//     * 根据score值获取集合元素数量
//     *
//     * @param key
//     * @param min
//     * @param max
//     * @return
//     */
//    public Long zCount(String key, double min, double max) {
//        key = getKey(key);
//        return redisTemplate.opsForZSet().count(key, min, max);
//    }
//
//    /**
//     * 获取集合大小
//     *
//     * @param key
//     * @return
//     */
//    public Long zSize(String key) {
//        key = getKey(key);
//        return redisTemplate.opsForZSet().size(key);
//    }
//
//    /**
//     * 获取集合大小
//     *
//     * @param key
//     * @return
//     */
//    public Long zZCard(String key) {
//        key = getKey(key);
//        return redisTemplate.opsForZSet().zCard(key);
//    }
//
//    /**
//     * 获取集合中value元素的score值
//     *
//     * @param key
//     * @param value
//     * @return
//     */
//    public Double zScore(String key, Object value) {
//        key = getKey(key);
//        return redisTemplate.opsForZSet().score(key, value);
//    }
//
//    /**
//     * 移除指定索引位置的成员
//     *
//     * @param key
//     * @param start
//     * @param end
//     * @return
//     */
//    public Long zRemoveRange(String key, long start, long end) {
//        key = getKey(key);
//        return redisTemplate.opsForZSet().removeRange(key, start, end);
//    }
//
//    /**
//     * 根据指定的score值的范围来移除成员
//     *
//     * @param key
//     * @param min
//     * @param max
//     * @return
//     */
//    public Long zRemoveRangeByScore(String key, double min, double max) {
//        key = getKey(key);
//        return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
//    }
//
//
//    /*------------------锁相关操作--------------------------------*/
//    /**
//     * 如果不存在则设置
//     */
//    public boolean setIfNotExists(String key, String value, int seconds) {
//        key = getKey(key);
//        return redisTemplate.opsForValue().setIfAbsent(key, value,seconds,TimeUnit.SECONDS);
//    }
//
//    // lua脚本，用来释放分布式锁
//    private static final String UNLOCK_LUA_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] " +
//            "then return redis.call('del',KEYS[1]) " +
//            "else return 0 " +
//            "end";
//
//    private static final RedisScript<Boolean> UNLOCK_SCRIPT = null;// = RedisScript.of(UNLOCK_LUA_SCRIPT, Boolean.class);
//
//    // lua脚本，用来释放分布式锁
//    private static final String LOCK_LUA_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] " +
//            "then return redis.call('del',KEYS[1]) " +
//            "else return 0 " +
//            "end";
//
//    private static final RedisScript<Boolean> LOCK_SCRIPT = null;// = RedisScript.of(LOCK_LUA_SCRIPT, Boolean.class);
//
//    /**
//     * 如果不存在则设置
//     */
//    public Boolean unlock(String key, String value) {
//        key = getKey(key);
//        return redisTemplate.execute(UNLOCK_SCRIPT, Collections.singletonList(key), value);
//    }
//
//
//    public Boolean lock(String key, String value, int expireSeconds) {
//        key = getKey(key);
//
//        return redisTemplate.execute(LOCK_SCRIPT, Collections.singletonList(key), value);
//    }
//}
