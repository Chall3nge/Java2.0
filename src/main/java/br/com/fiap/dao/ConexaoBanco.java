package br.com.fiap.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConexaoBanco {
    private static HikariDataSource dataSource;
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USUARIO = "rm558798";
    private static final String SENHA = "fiap24";

    // Configurações do pool de conexões
    private static final int MAX_POOL_SIZE = 10;
    private static final int MIN_IDLE = 2;
    private static final long MAX_LIFETIME = TimeUnit.MINUTES.toMillis(20);
    private static final long CONNECTION_TIMEOUT = TimeUnit.SECONDS.toMillis(5);
    private static final long IDLE_TIMEOUT = TimeUnit.MINUTES.toMillis(10);

    static {
        inicializarPool();
    }

    private static void inicializarPool() {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(URL);
            config.setUsername(USUARIO);
            config.setPassword(SENHA);
            config.setDriverClassName("oracle.jdbc.driver.OracleDriver");

            // Configurações do pool
            config.setMaximumPoolSize(MAX_POOL_SIZE);
            config.setMinimumIdle(MIN_IDLE);
            config.setMaxLifetime(MAX_LIFETIME);
            config.setConnectionTimeout(CONNECTION_TIMEOUT);
            config.setIdleTimeout(IDLE_TIMEOUT);

            // Configurações adicionais
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            config.setAutoCommit(true);

            // Validação de conexão
            config.setConnectionTestQuery("SELECT 1 FROM DUAL");
            config.setValidationTimeout(TimeUnit.SECONDS.toMillis(2));

            dataSource = new HikariDataSource(config);
            System.out.println("Pool de conexões inicializado com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao inicializar o pool de conexões: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Falha ao inicializar o pool de conexões", e);
        }
    }

    public static Connection obterConexao() throws SQLException {
        try {
            if (dataSource == null || dataSource.isClosed()) {
                inicializarPool();
            }

            Connection conexao = dataSource.getConnection();
            conexao.setNetworkTimeout(Executors.newSingleThreadExecutor(), (int) CONNECTION_TIMEOUT);

            if (!conexao.isValid(1)) {
                conexao.close();
                throw new SQLException("Conexão inválida");
            }

            return conexao;
        } catch (SQLException e) {
            System.err.println("Erro ao obter conexão: " + e.getMessage());
            throw new SQLException("Não foi possível estabelecer a conexão com o banco de dados", e);
        }
    }

    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                if (!conexao.isClosed()) {
                    conexao.close();
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    public static void encerrarPool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
            System.out.println("Pool de conexões encerrado.");
        }
    }

    // Método para verificar o status do pool
    public static String getPoolStatus() {
        if (dataSource != null) {
            return String.format(
                    "Pool Status:\n" +
                            "Active Connections: %d\n" +
                            "Idle Connections: %d\n" +
                            "Total Connections: %d\n" +
                            "Waiting Threads: %d",
                    dataSource.getHikariPoolMXBean().getActiveConnections(),
                    dataSource.getHikariPoolMXBean().getIdleConnections(),
                    dataSource.getHikariPoolMXBean().getTotalConnections(),
                    dataSource.getHikariPoolMXBean().getThreadsAwaitingConnection()
            );
        }
        return "Pool não inicializado";
    }
}