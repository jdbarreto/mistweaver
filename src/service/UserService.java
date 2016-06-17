package service;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import entity.User;

@SessionScoped
@SuppressWarnings("unchecked")
public class UserService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public User loginVerify(String email, String password) {

		User userFound = null;

		try {
			email = email.toLowerCase().trim();
			Query q = em.createNamedQuery(User.FIND_BY_EMAIL_PASSWORD);
			q.setParameter("email", email);
			q.setParameter("password", stringParaMd5(password));
			List<User> user = q.getResultList();
			if (user.size() == 1) {
				userFound = (User) user.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userFound;
	}

	private String stringParaMd5(String valor) {
		MessageDigest mDigest;
		try {
			// Instanciamos o nosso HASH MD5, poder�amos usar outro como
			// SHA, por exemplo, mas optamos por MD5.
			mDigest = MessageDigest.getInstance("MD5");

			// Converter String para um array de bytes em MD5
			byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));

			// Convertemos os bytes para hexadecimal, assim podemos salvar
			// no banco para posterior compara��o se senhas
			StringBuffer sb = new StringBuffer();
			for (byte b : valorMD5) {
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
			}

			return sb.toString();

		} catch (NoSuchAlgorithmException e) {
			// TODO: arrume ou aguente os cascudos
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			// TODO: arrume ou aguente os cascudos
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	public void userInsert(User user) {
		user.setName(user.getName());
		user.setEmail(user.getEmail());
		user.setPhone(user.getPhone());
		user.setSex(user.getSex());
		user.setCity(user.getCity());
		user.setState(user.getState());
		user.setRegisterDate(new Date());
    	user.setPassword(stringParaMd5(user.getPassword()));
		em.persist(user);		
	}
	
	@Transactional
	public List<User> userList() {
		Query q = em.createNamedQuery("User.findAll");
		return q.getResultList();
	}	
}
