package team.uptech.max.oliinyk.filter;

import org.hibernate.EmptyInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class HibernateStatisticsInterceptor extends EmptyInterceptor {

	private static final Logger log = LoggerFactory.getLogger(HibernateStatisticsInterceptor.class);

	private ThreadLocal<Long> queryCount = new ThreadLocal<>();

	public void startCounter() {
		queryCount.set(0l);
	}

	public Long getQueryCount() {
		return queryCount.get();
	}

	public void clearCounter() {
		queryCount.remove();
	}

	@Override
	public String onPrepareStatement(String sql) {
		Long count = queryCount.get();
		if (count != null) {
			queryCount.set(count + 1);
		}
		return super.onPrepareStatement(sql);
	}
}