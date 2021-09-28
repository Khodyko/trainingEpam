package by.mycloud_zapchast.www.dao.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ItemConnectionPool {
	private final static ItemConnectionPool instance = new ItemConnectionPool();
	private static final String USER_MSG_POOL_EXCP = "У нас технические неполадки, попробуйте зайти на страницу через некоторое время";
	private BlockingQueue<Connection> freeConQueue;
	private BlockingQueue<Connection> usedConQueue;
//	private Set<Connection> usedConQueue;
	private static final Logger LOGGER=LogManager.getLogger();
	private String driverName;
	private String url;
	private String user;
	private String password;
	private int poolSize;
	private int validationConnTimeout;

	public static ItemConnectionPool getInstance() {
		return instance;
	}

	private ItemConnectionPool() {
		DBItemResourceManager dbResourceManager = DBItemResourceManager.getInstance();
		this.driverName = dbResourceManager.getValue(DBItemParameter.DB_ITEM_DRIVER);
		this.url = dbResourceManager.getValue(DBItemParameter.DB_ITEM_URL);
		this.user = dbResourceManager.getValue(DBItemParameter.DB_ITEM_USER);
		this.password = dbResourceManager.getValue(DBItemParameter.DB_ITEM_PASSWORD);

		try {
			this.validationConnTimeout = Integer
					.parseInt(dbResourceManager.getValue(DBItemParameter.DB_ITEM_VALIDATION_CONNECTION_TIME_OUT));
			this.poolSize = Integer.parseInt(dbResourceManager.getValue(DBItemParameter.DB_ITEM_POOLSIZE));
		} catch (NumberFormatException e) {
			poolSize = 10;
			validationConnTimeout = 10;
		}

		try {
			initPoolData();
		} catch (ConnectionPoolException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public void initPoolData() throws ConnectionPoolException {
		Locale.setDefault(Locale.ENGLISH);

		try {
			Class.forName(driverName);
			usedConQueue = new ArrayBlockingQueue<Connection>(poolSize);
//			usedConQueue = new ConcurrentSkipListSet<Connection>();
			freeConQueue = new ArrayBlockingQueue<Connection>(poolSize);

			for (int i = 0; i < poolSize; i++) {

				freeConQueue.add(getNewPooledConnection());
			}
		} catch (SQLException e) {
			LOGGER.warn("Connection Pool: SQLException in ConnectionPool, initialization of pool");
			throw new ConnectionPoolException(USER_MSG_POOL_EXCP, e);
		} catch (ClassNotFoundException e) {
			LOGGER.warn("Connection Pool: Driver for work with DB is not found");
			throw new ConnectionPoolException(USER_MSG_POOL_EXCP, e);
		}
	}

	public Connection takeConnection() throws ConnectionPoolException {
		PooledConnection connection = null;
		while (connection == null) {
			try {
				connection = (PooledConnection) freeConQueue.take();

				if (connection != null) {
					if (!connection.isValid(validationConnTimeout)) {
						LOGGER.info("Connection Pool: connection is not valid. Connection name: "+connection+" we try to close it");
						try {
							connection.reallyClose();
							LOGGER.info("Connection name: "+connection+" connection is closed.");
						} catch (SQLException e) {
							LOGGER.info("Connection name: "+connection+" we can't to close connection.");
						}
						connection = null;
						// synchronized?
					} else if (freeConQueue.size() + usedConQueue.size() < poolSize) {
						freeConQueue.add(getNewPooledConnection());
						LOGGER.info("Connection name:getting of new connection");
					}

				} else {
					throw new ConnectionPoolException(USER_MSG_POOL_EXCP); // log

				}
			} catch (SQLException e) {
				throw new ConnectionPoolException(USER_MSG_POOL_EXCP);
			} catch (InterruptedException e) {
				System.out.println("Error connecting to the data source.");
				throw new ConnectionPoolException(USER_MSG_POOL_EXCP, e);
			}
		}

		usedConQueue.add(connection);
		return connection;
	}

	void freeConnection(PooledConnection connection) {
		try {
			if (connection.isReadOnly()) {
				connection.setReadOnly(false);
			}
			connection.clearWarnings();
			usedConQueue.remove(connection);
			freeConQueue.offer(connection);

		} catch (SQLException e) {
				LOGGER.warn("Connection Pool: problems with returning of connection to freeConQueue");
			}
		}
	

	public void closeItemConnectionPool() throws ConnectionPoolException {
		try {
			usedConQueue.addAll(freeConQueue);
			freeConQueue.clear();
			closeConnectionQueue(usedConQueue);
			closeConnectionQueue(freeConQueue);
		} catch (SQLException e) {
			LOGGER.warn("Connection Pool: problems with closing, in moment of closing ConnectionQueue");
			throw new ConnectionPoolException(USER_MSG_POOL_EXCP, e);
		}
	
	}

	public void closeConnectionQueue(BlockingQueue<Connection> queue) throws SQLException {
		Connection connection;
		try {
			while ((connection = queue.poll()) != null) {
				if (!connection.getAutoCommit()) {
					connection.commit();
				}
				((PooledConnection) connection).reallyClose();
			}
		} catch (SQLException canceled) {
		}
	}

	private Connection getNewPooledConnection() throws SQLException {
		return new PooledConnection(DriverManager.getConnection(url, user, password));
	}

}