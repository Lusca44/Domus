package br.com.domus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;

public class MongoClientConfig extends AbstractMongoClientConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(MongoClientConfig.class);

	@Value("Domus")
	private String database;

	@Value("${MONGO_URI:mongodb://localhost:27017}")
	private String connectionString;

	@Override
	protected String getDatabaseName() {
		return database;
	}

	@Bean
	public MongoClientSettings mongoClientSettings() {
		LOG.info("=> Creating the MongoClientSettings for MongoClient & MongoTamplate. ");

		return MongoClientSettings.builder().applyConnectionString(new ConnectionString(connectionString)).build();
	}

}
