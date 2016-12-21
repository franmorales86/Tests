/**
 * Project: RecCore
 * Created by: fjmorales at 19/11/2012 21:31:13
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.core.repository.generic;

import com.mongodb.Mongo;
import com.reclabs.recomendar.core.config.external.mongo.RecMoneyConverters;
import de.flapdoodle.embed.mongo.Command;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.RuntimeConfigBuilder;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.config.IRuntimeConfig;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.BeforeClass;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author fjmorales
 */
public class RepositoryTest {
    private static final Logger LOGGER = Logger.getLogger(RepositoryTest.class.getName());

    private static final String LOCALHOST = "127.0.0.1";
    protected static final String DB_NAME = "iTest";
    private static final int MONGO_TEST_PORT = 27028;

    private static MongodProcess mongoProcess = null;
    protected static Mongo mongo = null;

    /**
     * @throws IOException
     */
    @BeforeClass
    public static void initializeDB() throws Exception {
        if (mongoProcess == null) {
            createMongoProcess();
        }

        if (mongo == null) {
            mongo = new Mongo(LOCALHOST, MONGO_TEST_PORT);
            mongo.getDB(DB_NAME);
        }
    }

    /**
     * @throws Exception
     */
    private static void createMongoProcess() throws Exception {
        MongodConfig mongodConfig = new MongodConfig(Version.Main.PRODUCTION, MONGO_TEST_PORT, Network.localhostIsIPv6());

        IRuntimeConfig runtimeConfig = new RuntimeConfigBuilder().defaultsWithLogger(Command.MongoD, LOGGER).build();

        MongodStarter runtime = MongodStarter.getInstance(runtimeConfig);
        MongodExecutable mongodExecutable = runtime.prepare(mongodConfig);
        mongoProcess = mongodExecutable.start();
    }

    /**
     * @return Template of mongo
     */
    @SuppressWarnings("unchecked")
    public static MongoTemplate createMongoTemplate() {
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongo, DB_NAME);
        MappingContext mappingContext = new MongoMappingContext();
        MappingMongoConverter mappingMongoConverter = new MappingMongoConverter(mongoDbFactory, mappingContext);
        mappingMongoConverter.setCustomConversions(new CustomConversions((List<?>) RecMoneyConverters.getConvertersToRegister()));

        return new MongoTemplate(mongoDbFactory, mappingMongoConverter);
    }


}