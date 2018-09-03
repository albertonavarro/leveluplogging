import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.logstash.logback.argument.StructuredArguments.kv;

public class Main4 {

    private static final Logger logger = LoggerFactory.getLogger(Main4.class);

    public static void main(String[] args) {

        logger.info("New Market Status {} {}", kv("marketId", 1234), kv("newStatus", "ready"));

    }

}
