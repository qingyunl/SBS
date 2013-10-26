package com.sbs.model.crypto;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javassist.bytecode.stackmap.BasicBlock.Catch;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.sbs.exceptions.DecryptException;
import com.sbs.exceptions.EncryptException;
import com.sbs.exceptions.KeyPairGeneratorException;
import com.sbs.exceptions.RetrievePKIException;

public class PkiUtils {

	private String publicKey;
	private String privateKey;

	/*public static void main(String[] args) throws EncryptException, DecryptException, KeyPairGeneratorException, RetrievePKIException {
		PkiUtils p = new PkiUtils();
		String userId = "XX";
		p.createUserKeys(userId);
		
		byte[] crypt = p.encrypt("Hello_lush", userId);
		System.out.println(crypt);
		String plain = p.decrypt(crypt, userId);
		System.out.println(plain);
		
		byte[] crypt = p.encrypt("Hello", userId);
		System.out.println(crypt);
		String plain = p.decrypt(crypt, userId);
		System.out.println(plain);
	}*/

	/*public String encrypt(String plainText, String userId) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		//PublicKey pub = getUserPublicKey(userId);
		//return encrypt(plainText, pub);
		PrivateKey priv = getUserPrivateKey(userId);
		return encrypt(plainText, priv);
	}

	public String decrypt(String cryptText, String userId) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		//PrivateKey priv = getUserPrivateKey(userId);
		//return decrypt(cryptText, priv);
		PublicKey pub = getUserPublicKey(userId);
		return decrypt(cryptText, pub);
	}*/

	public String decrypt(byte[] plainText, String userId) throws DecryptException, RetrievePKIException {
		PublicKey pub = getUserPublicKey(userId);
		return decrypt(plainText, pub);
	}

	public byte[] encrypt(String cryptText, String userId) throws EncryptException, RetrievePKIException {
		PrivateKey priv = getUserPrivateKey(userId);
		return encrypt(cryptText, priv);
	}
	
	public void createUserKeys(String userId) throws KeyPairGeneratorException {
		KeyPairGenerator keyGen = null;
		try {
			keyGen = KeyPairGenerator.getInstance("RSA");
			keyGen.initialize(1024);
			KeyPair key = keyGen.generateKeyPair();
			KeyFactory factory = KeyFactory.getInstance("RSA");
			BASE64Encoder encoder = new BASE64Encoder();

			X509EncodedKeySpec pubKeySpec = factory.getKeySpec(key.getPublic(),
					X509EncodedKeySpec.class);
			publicKey = encoder.encode(pubKeySpec.getEncoded());

			PKCS8EncodedKeySpec privKeySpec = factory.getKeySpec(
					key.getPrivate(), PKCS8EncodedKeySpec.class);
			privateKey = encoder.encode(privKeySpec.getEncoded());
			
			saveUserPki(userId, publicKey, privateKey);

		} catch (NoSuchAlgorithmException e) {
			throw new KeyPairGeneratorException(
					"Error generating Key Pair for userId: " + userId);
		} catch (InvalidKeySpecException e) {
			throw new KeyPairGeneratorException("Error Saving Keys for userId:"
					+ userId);
		}

	}

	private void saveUserPki(String userId, String publicKey, String privateKey) {
		UserPki u = new UserPki();
		u.setUserId(userId);
		u.setPrivateKey(privateKey);
		u.setPublicKey(publicKey);
		UserPkiHibernateManager.saveUserPki(u);
	}
	
	/*private void getUserPki() {
		
	}*/
	
	private PublicKey getUserPublicKey(String userId) throws RetrievePKIException {
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] keyBytes = decoder.decodeBuffer(publicKey);
			X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory factory = KeyFactory.getInstance("RSA");
			return factory.generatePublic(pubKeySpec);
		} catch (InvalidKeySpecException e) {
			throw new RetrievePKIException(
					"Invalid public key format for userId :" + userId);
		} catch (NoSuchAlgorithmException e) {
			throw new RetrievePKIException(
					"Invalid public key algorithm for userId :" + userId);
		} catch (IOException e) {
			throw new RetrievePKIException(
					"Error getting public key for userId :" + userId);
		}
	}

	private PrivateKey getUserPrivateKey(String userId) throws RetrievePKIException {
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] keyBytes = decoder.decodeBuffer(privateKey);
			PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory factory = KeyFactory.getInstance("RSA");
			return factory.generatePrivate(privKeySpec);
		} catch (InvalidKeySpecException e) {
			throw new RetrievePKIException(
					"Invalid public key format for userId :" + userId);
		} catch (NoSuchAlgorithmException e) {
			throw new RetrievePKIException(
					"Invalid public key algorithm for userId :" + userId);
		} catch (IOException e) {
			throw new RetrievePKIException(
					"Error getting public key for userId :" + userId);
		}
	}

	/*private String encrypt(String plainText, PrivateKey privKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, privKey);
		byte[] cryptBytes = cipher.doFinal(plainText.getBytes("ISO-8859-1"));
		return new String(cryptBytes, "ISO-8859-1");
	}

	private String decrypt(String cryptText, PublicKey pubKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, pubKey);
		byte[] plainBytes = cipher.doFinal(cryptText.getBytes("ISO-8859-1"));
		return new String(plainBytes, "ISO-8859-1");
	}*/
	
	private String decrypt(byte[] plainText, PublicKey pubKey) throws DecryptException{
		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, pubKey);
			byte[] cryptBytes = cipher.doFinal(plainText);
			return new String(cryptBytes, "ISO-8859-1");
		} catch (NoSuchAlgorithmException e) {
			throw new DecryptException(
					"Error decrypting by public key: NoSuchAlgorithmException");
		} catch (NoSuchPaddingException e) {
			throw new DecryptException(
					"Error decrypting by public key: NoSuchPaddingException");
		} catch (InvalidKeyException e) {
			throw new DecryptException(
					"Error decrypting by public key: InvalidKeyException");
		} catch (IllegalBlockSizeException e) {
			throw new DecryptException(
					"Error decrypting by public key: DecryptException");
		} catch (BadPaddingException e) {
			throw new DecryptException(
					"Error decrypting by public key: BadPaddingException");
		} catch (UnsupportedEncodingException e) {
			throw new DecryptException(
					"Error decrypting by public key: UnsupportedEncodingException");
		}
	}

	private byte[] encrypt(String cryptText, PrivateKey privKey) throws EncryptException{
		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, privKey);
			// byte[] plainBytes = cipher.doFinal(cryptText.getBytes("UTF8"));
			// return new String(plainBytes, "UTF8");
			return cipher.doFinal(cryptText.getBytes("ISO-8859-1"));
		} catch (NoSuchAlgorithmException e) {
			throw new EncryptException(
					"Error decrypting by public key: NoSuchAlgorithmException");
		} catch (NoSuchPaddingException e) {
			throw new EncryptException(
					"Error decrypting by public key: NoSuchPaddingException");
		} catch (InvalidKeyException e) {
			throw new EncryptException(
					"Error decrypting by public key: InvalidKeyException");
		} catch (IllegalBlockSizeException e) {
			throw new EncryptException(
					"Error decrypting by public key: DecryptException");
		} catch (BadPaddingException e) {
			throw new EncryptException(
					"Error decrypting by public key: BadPaddingException");
		} catch (UnsupportedEncodingException e) {
			throw new EncryptException(
					"Error decrypting by public key: UnsupportedEncodingException");
		}
	}
}
