import config.TestsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;

public class ResultSender {

    private static final TestsConfig cfg = ConfigFactory.create(TestsConfig.class);
    private static final InfluxDB INFLXUDB = InfluxDBFactory.connect(cfg.influxUrl(), cfg.influxUser(), cfg.influxPassword());

    static{
        INFLXUDB.setDatabase(cfg.influxDB());
    }

    public static void send(final Point point){
        INFLXUDB.write(point);
    }

}