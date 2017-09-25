package com.bpbbank;

public class Dega {

	private String dega;
	private String pergjegjesiIDeges;
	private String celesiIHyrjesDege;
	private String kodiAlarmitDege;
	private String celesiIDeresAtm;
	private String celesiIServerRoom;
	private String celesiTrezor;
	private String kodiAlarmitTrezor;
	private String celesiSef1;
	private String celesiSef2;
	private String kodiShiferSef;

	
	

	

	public Dega(String dega, String pergjegjesiIDeges, String celesiIHyrjesDege, String kodiAlarmitDege,
			String celesiIDeresAtm, String celesiIServerRoom, String celesiTrezor, String kodiAlarmitTrezor,
			String celesiSef1, String celesiSef2, String kodiShiferSef, Dega dega1) {
		super();
		this.dega = dega;
		this.pergjegjesiIDeges = pergjegjesiIDeges;
		this.celesiIHyrjesDege = celesiIHyrjesDege;
		this.kodiAlarmitDege = kodiAlarmitDege;
		this.celesiIDeresAtm = celesiIDeresAtm;
		this.celesiIServerRoom = celesiIServerRoom;
		this.celesiTrezor = celesiTrezor;
		this.kodiAlarmitTrezor = kodiAlarmitTrezor;
		this.celesiSef1 = celesiSef1;
		this.kodiShiferSef = kodiShiferSef;
		this.celesiSef2 = celesiSef2;
		
	}

	public Dega() {
		// TODO Auto-generated constructor stub
	}

	public String getCelesiIDeresAtm() {
		return celesiIDeresAtm;
	}

	public void setCelesiIDeresAtm(String celesiIDeresAtm) {
		this.celesiIDeresAtm = celesiIDeresAtm;
	}

	public String getCelesiIHyrjesDege() {
		return celesiIHyrjesDege;
	}

	public void setCelesiIHyrjesDege(String celesiIHyrjesDege) {
		this.celesiIHyrjesDege = celesiIHyrjesDege;

	}

	public String getCelesiIServerRoom() {
		return celesiIServerRoom;
	}

	public void setCelesiIServerRoom(String celesiIServerRoom) {
		this.celesiIServerRoom = celesiIServerRoom;
	}

	public String getCelesiSef1() {
		return celesiSef1;
	}

	public void setCelesiSef1(String celesiSef1) {
		this.celesiSef1 = celesiSef1;
	}
	
	public String getCelesiSef2() {
		return celesiSef2;
	}
	
	public void setCelesiSef2(String celesiSef2) {
		this.celesiSef2 = celesiSef2;
	}
	
	public String getCelesiTrezor() {
		return celesiTrezor;
	}
	
	public void setCelesiTrezor(String celesiTrezor) {
		this.celesiTrezor = celesiTrezor;
	}
	
	public String getDega() {
		return dega;
	}
	
	public void setDega(String dega) {
		this.dega = dega;
	}
	
	public String getKodiAlarmitDege() {
		return kodiAlarmitDege;
	}
	
	public void setKodiAlarmitDege(String kodiAlarmitDege) {
		this.kodiAlarmitDege = kodiAlarmitDege;
	}
	
	public String getKodiAlarmitTrezor() {
		return kodiAlarmitTrezor;
	}
	
	public void setKodiAlarmitTrezor(String kodiAlarmitTrezor) {
		this.kodiAlarmitTrezor = kodiAlarmitTrezor;
	}
	
	public String getKodiShiferSef() {
		return kodiShiferSef;
	}
	
	public void setKodiShiferSef(String kodiShiferSef) {
		this.kodiShiferSef = kodiShiferSef;
	}
	
	public String getPergjegjesiIDeges() {
		return pergjegjesiIDeges;
	}
	
	public void setPergjegjesiIDeges(String pergjegjesiIDeges) {
		this.pergjegjesiIDeges = pergjegjesiIDeges;
	}
	
	
	

}
