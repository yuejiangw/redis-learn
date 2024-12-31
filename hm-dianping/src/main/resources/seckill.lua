-- 1. 参数列表
-- 1.1 优惠券 id
local voucherId = ARGV[1]
-- 1.2 用户 id
local userId = ARGV[2]

-- 2. 数据 key
-- 2.1 库存 key
local stockKey = 'seckill:stock:' .. voucherId
-- 2.2 订单 key
local orderKey = 'seckill:stock:' .. voucherId

-- 3. 脚本业务
-- 3.1 判断库存是否充足 get stockKey
if (tonumber(redis.call('get', stockKey)) <= 0) then
    -- 3.2 库存不足，返回 1
    return 1
end
-- 3.3 判断用户是否下单 sismember orderKey userId
if (redis.call('sismember', orderKey, userId) == 1) then
    -- 3.3 存在，说明是重复下单，返回2
    return 2
end
-- 3.4 扣库存 incrby sotckKey -1
redis.call('incrby', stockKey, -1)
-- 3.5 下单（保存用户）sadd orderKey userId
redis.call('sadd', orderKey, userId);

return 0