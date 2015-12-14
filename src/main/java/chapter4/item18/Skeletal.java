package chapter4.item18;

/**
 * Created by tmolloy on 14/12/2015.
 */
/**
 * The Interface
 *
 */
interface RedisConnection
{
    int connect();
    boolean isConnected();
    int disconnect();
    int getDatabaseNumber();
}

/**
 * Abstract class which implements the interface.
 * This is called Abstract Interface
 *
 */
abstract class AbstractRedisConnection implements RedisConnection
{
    @Override
    public final int connect()
    {
        //... lots of code to connect to Redis
    }

    @Override
    public final boolean isConnected()
    {
        //... code to check Redis connection
    }

    @Override
    public final int disconnect()
    {
        //... lots of code to disconnect from Redis and perform cleanup
    }
}

/**
 * A subclass which extends from the Abstract Interface
 *
 */
class RedisOptOut extends AbstractRedisConnection {…}

/**
 * A class showing the forwarding technique. This class implements
 * an interface, but forwards all interface method invocations
 * to an abstract class, the Abstract Interface.
 */
class RedisCounters implements RedisConnection {

    // inner class extending Abstract Interface
    private class RedisConnectionForwarder extends AbstractRedisConnection {
        public void RedisConnectionForwarder() {
        }
    }
    RedisConnectionForwarder r = new RedisConnectionForwarder();

    @Override
    public int connect() {
        // Simply forward the request to the Forwarding class.
        return r.connect();

    }

    @Override
    public boolean isConnected() {
        // Simply forward the request to the Forwarding class.
        return r.isConnected();
    }

    @Override
    public int disconnect() {
        // Simply forward the request to the Forwarding class.
        return r.disconnect();
    }

    /**
     * Other code specific to storing and retreiving **counters**
     */
}
