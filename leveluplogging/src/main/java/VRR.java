import net.logstash.logback.argument.StructuredArgument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import java.util.UUID;
import static java.lang.System.currentTimeMillis;
import static net.logstash.logback.argument.StructuredArguments.kv;

public class VRR {

    private static final String IMPORTANCE = "importance";
    private static final StructuredArgument LOW = kv(IMPORTANCE, "LOW");
    private static final StructuredArgument IMP = kv(IMPORTANCE, "IMP");
    private static final StructuredArgument CRIT = kv(IMPORTANCE, "CRIT");

    private static final Logger logger = LoggerFactory.getLogger(VRR.class);

    public static void main(String[] args) {
        MDC.put("rid", UUID.randomUUID().toString());
        try {
            long startTime = currentTimeMillis();
            someFunction();
            logger.info("important message, useful to so some metrics {} {}",
                    kv("elapsedmillis", currentTimeMillis() - startTime),
                    IMP);
        } catch (Exception e) {
            logger.error("This is a low importance message as it won't have value after few weeks", e);
        }
    }

    static void someFunction() throws Exception {
        logger.info("low importance message, helps to trace errors, begin someFunction {} {} {}",
                kv("user","anavarro"),
                kv("action","file-create"),
                LOW);

        Thread.sleep(500L); //some work

        logger.info("critical message, audit trail for user action {} {} {}",
                kv("user","anavarro"),
                kv("action","file-create"),
                CRIT);
    }
}
