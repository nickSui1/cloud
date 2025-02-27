package priv.ns.ecommerce.util;

/**
 * 雪花算法Id生成器
 */
public class SnowflakeIdGenerator {
    private static final long epoch = 1288834974657L; // 自定义的时间基准
    private static final long machineIdBits = 5L;  // 机器ID长度
    private static final long dataCenterIdBits = 5L; // 数据中心ID长度
    private static final long sequenceBits = 12L; // 序列号长度

    private static final long maxMachineId = -1L ^ (-1L << machineIdBits); // 机器ID的最大值
    private static final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits); // 数据中心ID最大值
    private static final long sequenceMask = -1L ^ (-1L << sequenceBits); // 序列号最大值

    private long machineId; // 机器ID
    private long dataCenterId; // 数据中心ID
    private long sequence = 0L; // 当前毫秒内的序列
    private long lastTimestamp = -1L; // 上次生成ID的时间戳

    public SnowflakeIdGenerator(long machineId, long dataCenterId) {
        if (machineId > maxMachineId || machineId < 0) {
            throw new IllegalArgumentException("Machine ID out of range");
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException("DataCenter ID out of range");
        }
        this.machineId = machineId;
        this.dataCenterId = dataCenterId;
    }

    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards, refusing to generate id");
        }

        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = waitForNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;

        return (timestamp - epoch) << (machineIdBits + dataCenterIdBits + sequenceBits) |
                (dataCenterId << (machineIdBits + sequenceBits)) |
                (machineId << sequenceBits) |
                sequence;
    }

    private long waitForNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}
