package servlets;

import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.pro.servlets.AddableingHttpRequest;
import org.mindrot.jbcrypt.BCrypt;


import org.junit.Test;

import com.pro.beans.Configurations;
import com.pro.beans.Utilisateur;
import com.pro.dao.implementation.ConfigurationDaoImpl;
import com.pro.dao.DAOFactory;
import com.pro.dao.intefaces.UtilisateurDao;
import com.pro.forms.IdentificationForm;
import static org.easymock.EasyMock.*;


public class AuthentificationTest extends HttpServlet{
	
	
	
	public static final String CONF_DAO_FACTORY = "daofactory";
	//Creation d'une Requete
	HttpServletRequest request = createMock(HttpServletRequest.class);
	AddableingHttpRequest modifiedRequest = new AddableingHttpRequest(request);

	//recupération d'un DAOFactory (sans accès au fichier de parametrage)
	DAOFactory daoFactory =DAOFactory.getInstanceMock();
	//récupération d'un userDao
	UtilisateurDao utilisateurDao = daoFactory.getUtilisateurDao();

	Utilisateur utilisateur = null;
	IdentificationForm form = new IdentificationForm();
	Configurations configuration = new Configurations();
	
	
	public void testHashFunction() throws Exception{
		String mdp = "rootClient";
		String hashed= BCrypt.hashpw(mdp, BCrypt.gensalt());
		System.out.println(hashed);
		System.out.println("hashedBcrypt "+hashed);
		assertTrue(BCrypt.checkpw(mdp, hashed));
	}
	public void testConfiguration() throws Exception{
		String password = "testt";
		assertTrue(password.matches(configuration.getMdpPattern()));
	}
	public void testGetConfigurationFromDB() throws Exception {
		daoFactory.getConnection();
		ConfigurationDaoImpl configurationDao = new ConfigurationDaoImpl(daoFactory);
		assertNotNull(configurationDao.readConfigurationDefault());
	}
	public void testSetConfigurationOnDB() throws Exception {
		daoFactory.getConnection();
		ConfigurationDaoImpl configurationDao = new ConfigurationDaoImpl(daoFactory);
		Configurations oldConfig = configurationDao.readConfigurationDefault();
		configuration.setMdpPattern(".{5,}");
		configurationDao.updateConfigurationDefault(configuration);
		System.out.println("new Config "+configurationDao.readConfigurationDefault().getMdpPattern());
		System.out.println("old Config "+oldConfig.getMdpPattern());
		String old  = oldConfig.getMdpPattern();
		String New = configurationDao.readConfigurationDefault().getMdpPattern();
		assertFalse(old.equals(New ) );
	}
	public void testGetMessage() throws Exception {
		daoFactory.getConnection();
		String test = utilisateurDao.getMessageUtilisateur(utilisateur);
		System.out.println(test);
		assertFalse(test == null);
	}
	@Test
	public void testGetAllUser() throws Exception{
		daoFactory.getConnection();
		assertFalse(utilisateurDao.getAllUtilisateur() == (null));
	}
	public void connectWithHashPassword() throws SQLException{
		daoFactory.getConnection();
		modifiedRequest.addParameter("email", "client1@ens.etsmtl.ca");
		modifiedRequest.addParameter("motdepasse", "client1");
		Utilisateur utilisateur = form.trouver(modifiedRequest, utilisateurDao);
		assertEquals(utilisateur.getNom(),"francis");
		
	}
	public void testSQLInjectionArentWorking() throws SQLException {
		//Initialisation du driver JDBC et récupération d'une connection
		daoFactory.getConnection();
        
        //INJECTION SQL
		modifiedRequest.addParameter("email", "'or 1=1;");
		modifiedRequest.addParameter("motdepasse", "root");
		Utilisateur utilisateur2 = form.trouver(modifiedRequest, utilisateurDao);
		String str = "Echec de l'injection SQL";
        assertTrue(str, utilisateur2 == null);
	}

}
