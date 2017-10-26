package com.bpbbank.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "dega")
public class Dega {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "dega", nullable = false)
	private String dega;
	@Column(name = "pergjegjesi", nullable = false)
	private String pergjegjesiIDeges;
	@Column(name = "celesi_hyrjes", nullable = false)
	private String celesiIHyrjesDege;
	@Column(name = "kodi_alarmit_deges", nullable = false)
	private String kodiAlarmitDege;
	@Column(name = "celesi_deres_atm", nullable = false)
	private String celesiIDeresAtm;
	@Column(name = "celesi_server_room", nullable = true)
	private String celesiIServerRoom;
	@Column(name = "celesi_trezor", nullable = false)
	private String celesiTrezor;
	@Column(name = "kodi_alarmit_trezor", nullable = false)
	private String kodiAlarmitTrezor;
	@Column(name = "celesi_sef_1", nullable = false)
	private String celesiSef1;
	@Column(name = "celesi_sef_2", nullable = false)
	private String celesiSef2;
	@Column(name = "kodi_sef", nullable = false)
	private String kodiShiferSef;
	@Column(name = "kodet_digjitale_kasaforte_1", nullable = true, unique = false)
	private String kodetDigjitaleKasaforte1 = "";
	@Column(name = "kodet_digjitale_kasaforte_2", nullable = true)
	private String kodetDigjitaleKasaforte2;
	@Column(name = "krijimi_deges", nullable = true)
	private String krijimiDeges ="";
	@Column(name = "modifikimi_deges", nullable = true)
	private String modifikimiDeges ="";

//	private long nrKolones;

	public Dega(String dega, String pergjegjesiIDeges, String celesiIHyrjesDege, String kodiAlarmitDege,
			String celesiIDeresAtm, String celesiIServerRoom, String celesiTrezor, String kodiAlarmitTrezor,
			String celesiSef1, String celesiSef2, String kodiShiferSef, Dega dega1, String kodetDigjitaleKasaforte1, String kodetDigjitaleKasaforte2, String modifikimiDeges, String krijimiDeges) {
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
		this.kodetDigjitaleKasaforte1 = kodetDigjitaleKasaforte1;
		this.kodetDigjitaleKasaforte2 = kodetDigjitaleKasaforte2;
		this.modifikimiDeges = modifikimiDeges;
		this.krijimiDeges = krijimiDeges;
	}

	public Dega() {
		// TODO Auto-generated constructor stub
	}

	public String getCelesiIDeresAtm() {
		return celesiIDeresAtm;
	}
	public String getKrijimiDeges() {
		return krijimiDeges;
	}
	public void setKrijimiDeges(String krijimiDeges) {
		this.krijimiDeges = krijimiDeges;
	}
	public String getModifikimiDeges() {
		return modifikimiDeges;
	}
	public void setModifikimiDeges(String modifikimiDeges) {
		this.modifikimiDeges = modifikimiDeges;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKodetDigjitaleKasaforte1() {
		return kodetDigjitaleKasaforte1;
	}

	public void setKodetDigjitaleKasaforte1(String kodetDigjitaleKasaforte1) {
		this.kodetDigjitaleKasaforte1 = kodetDigjitaleKasaforte1;
	}

	public String getKodetDigjitaleKasaforte2() {
		return kodetDigjitaleKasaforte2;
	}

	public void setKodetDigjitaleKasaforte2(String kodetDigjitaleKasaforte2) {
		this.kodetDigjitaleKasaforte2 = kodetDigjitaleKasaforte2;
	}
	

//	public long getNrKolones() {
//		return nrKolones;
//
//	}
//
//	public void setNrKolones(long nrKolones) {
//		this.nrKolones = nrKolones;
//	}
//	http://srv-web-02:8080/scm/git/KeyAuthenticationgit 
}
