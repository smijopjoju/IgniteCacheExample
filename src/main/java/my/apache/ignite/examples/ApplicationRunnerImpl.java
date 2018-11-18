package my.apache.ignite.examples;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import my.apache.ignite.examples.config.IgniteCacheManager;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		IgniteCacheManager.INSTANCE.init();
		IgniteCacheManager.INSTANCE.initRecords();
	}

}
