package lowleveldesigns;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * IP Rate Limiter using Token Bucket Algorithm
 * <p>
 * This implements a thread-safe rate limiter that tracks and limits request rates per IP address.
 * Each IP maintains its own request bucket with a fixed capacity and refill rate per second.
 * The Token Bucket algorithm allows burst traffic up to capacity while maintaining an average rate of perSecond requests.
 * <p>
 * Thread Safety: Multiple concurrent requests from different IPs are handled safely using ConcurrentHashMap,
 * and individual bucket operations are synchronized to prevent race conditions.
 */
public class IpRateLimiter {
	private final int capacity;
	private final int perSecond;
	private final Map<String, RequestBucket> mapOfRequests = new ConcurrentHashMap<>();
	
	public IpRateLimiter(int capacity, int perSecond) {
		this.capacity = capacity;
		this.perSecond = perSecond;
	}
	
	public boolean allowRequest(String ip) {
		RequestBucket request = mapOfRequests.computeIfAbsent(ip, key -> new RequestBucket(capacity, perSecond));
		return request.tryToConsume();
	}
	
	public static class RequestBucket {
		private final int capacity;
		private final int refillRate;
		private double currentBalance;
		private long lastRequestTimestamp;
		
		public RequestBucket(int totalRequests, int refillRate) {
			this.capacity = totalRequests;
			this.refillRate = refillRate;
			this.currentBalance = totalRequests;
			this.lastRequestTimestamp = System.currentTimeMillis();
		}
		
		public synchronized boolean tryToConsume() {
			refillBalance();
			
			if (currentBalance >= 1) {
				currentBalance -= 1;
				return true;
			}
			return false;
		}
		
		public void refillBalance() {
			long now = System.currentTimeMillis();
			double timeDiff = (now - lastRequestTimestamp) / 1000.0;
			double balanceToBeFilled = timeDiff * refillRate;

			currentBalance = Math.min(capacity, currentBalance + balanceToBeFilled);
			lastRequestTimestamp = now;
		}
	}
	
	public static void main(String[] args) {
		IpRateLimiter limiter = new IpRateLimiter(40, 1);
		
		for (int i = 1; i <= 50; i++) {
			System.out.printf("Request - %s with IP: %s is allowed: %s%n", i, "192.168.1.1", limiter.allowRequest("192.168.1.1"));
        }
	}
}
