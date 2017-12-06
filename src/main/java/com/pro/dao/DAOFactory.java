package com.pro.dao;

import com.pro.dao.exceptions.DAOConfigurationException;
import com.pro.dao.implementation.ConfigurationDaoImpl;
import com.pro.dao.implementation.LogDaoImpl;
import com.pro.dao.implementation.UtilisateurDaoImpl;
import com.pro.dao.intefaces.ConfigurationDao;
import com.pro.dao.intefaces.LogDao;
import com.pro.dao.intefaces.UtilisateurDao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {

    private static final String FICHIER_PROPERTIES = "/dao.properties";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
    private static final String PROPERTY_MOT_DE_PASSE = "motdepasse";
    private static final DAOFactory _instance = createInstance();
    private String url;
    private String username;
    private String password;

    private UtilisateurDao utilisateurDao = new UtilisateurDaoImpl(this);
    private ConfigurationDao configurationDao = new ConfigurationDaoImpl(this);
    private LogDao logDao = new LogDaoImpl(this);

    private DAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }


    public static DAOFactory getInstance() {
        return _instance;
    }

    /*
     * Méthode chargée de récupérer les informations de connexion à la base de
     * données, charger le driver JDBC et retourner une instance de la Factory
     */
    private static DAOFactory createInstance() throws DAOConfigurationException {
        Properties properties = new Properties();
        String url;
        String driver;
        String nomUtilisateur;
        String motDePasse;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream(FICHIER_PROPERTIES);

        if (fichierProperties == null) {
            throw new DAOConfigurationException("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable.");
        }

        try {
            properties.load(fichierProperties);
            url = properties.getProperty(PROPERTY_URL);
            driver = properties.getProperty(PROPERTY_DRIVER);
            nomUtilisateur = properties.getProperty(PROPERTY_NOM_UTILISATEUR);
            motDePasse = properties.getProperty(PROPERTY_MOT_DE_PASSE);
        } catch (IOException e) {
            throw new DAOConfigurationException("Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e);
        }

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DAOConfigurationException("Le driver est introuvable dans le classpath.", e);
        }

        DAOFactory instance = new DAOFactory(url, nomUtilisateur, motDePasse);
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public UtilisateurDao getUtilisateurDao() {
        return utilisateurDao;
    }

    public ConfigurationDao getConfigurationDao() {
        return configurationDao;
    }

    public LogDao getLogDao() {
        return logDao;
    }
}