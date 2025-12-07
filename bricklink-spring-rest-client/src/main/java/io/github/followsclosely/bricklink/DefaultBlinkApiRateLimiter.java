package io.github.followsclosely.bricklink;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Default implementation of {@link BlinkApiRateLimiter} for controlling API call rate.
 * <p>
 * This class enforces a minimum delay between API calls, with optional random bonus delay and
 * support for borrowing additional delay for the next call. It is thread-safe and suitable for
 * concurrent use. The rate limiter can be customized via constructor parameters.
 * </p>
 * <p>
 * Usage example:
 * <pre>
 *     DefaultBlinkApiRateLimiter limiter = new DefaultBlinkApiRateLimiter();
 *     limiter.waitAsNeeded(); // Enforces delay before API call
 * </pre>
 * </p>
 */
@Slf4j
public final class DefaultBlinkApiRateLimiter implements BlinkApiRateLimiter {

    /**
     * Default singleton instance with standard delay settings. This is convenient for general use cases.
     */
    public static final DefaultBlinkApiRateLimiter DEFAULT_INSTANCE = new DefaultBlinkApiRateLimiter();

    /**
     * The minimum delay required between calls, in milliseconds.
     */
    public static final long MIN_DELAY_MS = 1000;

    /**
     * Minimum delay enforced between API calls, in milliseconds.
     */
    private final long minDelay;
    /**
     * Maximum random bonus added to the delay, in milliseconds.
     */
    private final long minDelayBonus;

    /**
     * Timestamp of the last API call, in milliseconds since epoch.
     */
    private final AtomicLong lastCallTime = new AtomicLong(0);

    /**
     * Additional milliseconds borrowed to be added to the next wait time.
     */
    private final AtomicLong borrowedMillis = new AtomicLong(0);

    /**
     * Total number of API calls made through this rate limiter.
     */
    private final AtomicLong totalCallsMade = new AtomicLong(0);

    /**
     * Constructs a rate limiter with the default minimum delay.
     */
    public DefaultBlinkApiRateLimiter() {
        this(MIN_DELAY_MS);
    }

    /**
     * Constructs a rate limiter with a custom minimum delay and default bonus (10ms).
     *
     * @param minimumDelay Minimum delay between calls in milliseconds.
     */
    public DefaultBlinkApiRateLimiter(long minimumDelay) {
        this(minimumDelay, 10);
    }

    /**
     * Constructs a rate limiter with custom minimum delay and bonus.
     *
     * @param minDelay Minimum delay between calls in milliseconds.
     * @param minDelayBonus Maximum random bonus added to delay in milliseconds.
     */
    public DefaultBlinkApiRateLimiter(long minDelay, long minDelayBonus) {
        this.minDelay = minDelay;
        this.minDelayBonus = minDelayBonus;
    }

    /**
     * Borrows additional milliseconds to be added to the next wait time.
     * This can be used to dynamically adjust the wait time based on
     * external factors or specific API requirements.
     *
     * @param millis The number of milliseconds to borrow.
     */
    @Override
    public void borrow(long millis) {
        totalCallsMade.incrementAndGet();
        borrowedMillis.addAndGet(millis);
    }

    /**
     * Waits for at least the configured minimum delay since the last call.
     * If the required time has not elapsed, the current thread will sleep until it has.
     * A random bonus delay may be added to reduce the risk of hitting rate limits.
     * Thread-safe via AtomicLong fields.
     */
    @Override
    public void waitAsNeeded() {
        totalCallsMade.incrementAndGet();
        long currentTime = System.currentTimeMillis();
        long timeSinceLastCall = currentTime - lastCallTime.get();

        long delayNeeded = minDelay + borrowedMillis.get();
        if (timeSinceLastCall < delayNeeded) {
            long timeToWait = delayNeeded - timeSinceLastCall;
            try {
                long timeToWaitPlus = (timeToWait + ((long) (Math.random() * minDelayBonus)));
                log.info("Call-{}: Need to wait {}ms, but waiting for {}ms to enforce the {}ms delay (plus {}ms)...", totalCallsMade.get(), timeToWait, timeToWaitPlus, minDelay, timeToWaitPlus - timeToWait);
                Thread.sleep(timeToWaitPlus);
                borrowedMillis.set(0);
                lastCallTime.set(System.currentTimeMillis());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("The wait was interrupted.");
            }
        }

    }

    /**
     * Resets the last call time to the current system time.
     * This can be used to indicate that a call has just been made,
     * effectively starting the wait timer anew.
     */
    @Override
    public void resetLastCallTime() {
        lastCallTime.set(System.currentTimeMillis());
    }
}
