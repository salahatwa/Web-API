package org.currency.starter.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="MC40200",indexes = { @Index(name = "DEX_ROW_ID", columnList = "CURNCYID") })
public class MC40200 {

	@Id
	@Column(name = "CURNCYID" )
//	@Size(max = 15)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;

	@Column(name = "CURRNIDX")
	private int currencyIndex;

	@Column(name = "CRNCYDSC", length = 31)
	private String currencyDesc;

	@Column(name = "CRNCYDSCA", length = 61)
	private String currencyDescAr;

	@Column(name = "CRNCYSYM", length = 3)
	private String currencySymbol;

	/**
	 * True or false
	 */
	@Column(name = "INCLSPAC")
	private boolean incSpace;

	@Column(name = "NEGSYMBL")
	private short negSymbole;

	@Column(name = "NGSMAMPC")
	private short negSymboleSign;

	/**
	 * Separators decimal ( Period, Comma, Space)
	 */
	@Column(name = "DECSYMBL")
	private short decSYM;

	/**
	 * ( Period, Comma, Space)
	 */
	@Column(name = "THOUSSYM")
	private short thousSYM;

	@Column(name = "CURTEXT_1", length = 25)
	private String currencyUnit;

	@Column(name = "CURTEXT_2", length = 25)
	private String unitSubUnitConnector;

	@Column(name = "CURTEXT_3", length = 25)
	private String currencySubUnit;

	@Column(name = "CURTEXTA_1", length = 55)
	private String currencyUnitAr;

	@Column(name = "CURTEXTA_2", length = 55)
	private String unitSubUnitConnectorAr;

	@Column(name = "CURTEXTA_3", length = 55)
	private String currencySubUnitAr;

	@Column(name = "DEX_ROW_TS")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
	private java.util.Date date;
	
	
	public MC40200()
	{
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCurrencyIndex() {
		return currencyIndex;
	}

	public void setCurrencyIndex(int currencyIndex) {
		this.currencyIndex = currencyIndex;
	}

	public String getCurrencyDesc() {
		return currencyDesc;
	}

	public void setCurrencyDesc(String currencyDesc) {
		this.currencyDesc = currencyDesc;
	}

	public String getCurrencyDescAr() {
		return currencyDescAr;
	}

	public void setCurrencyDescAr(String currencyDescAr) {
		this.currencyDescAr = currencyDescAr;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public boolean isIncSpace() {
		return incSpace;
	}

	public void setIncSpace(boolean incSpace) {
		this.incSpace = incSpace;
	}

	public short getNegSymbole() {
		return negSymbole;
	}

	public void setNegSymbole(short negSymbole) {
		this.negSymbole = negSymbole;
	}

	public short getNegSymboleSign() {
		return negSymboleSign;
	}

	public void setNegSymboleSign(short negSymboleSign) {
		this.negSymboleSign = negSymboleSign;
	}

	public short getDecSYM() {
		return decSYM;
	}

	public void setDecSYM(short decSYM) {
		this.decSYM = decSYM;
	}

	public short getThousSYM() {
		return thousSYM;
	}

	public void setThousSYM(short thousSYM) {
		this.thousSYM = thousSYM;
	}

	public String getCurrencyUnit() {
		return currencyUnit;
	}

	public void setCurrencyUnit(String currencyUnit) {
		this.currencyUnit = currencyUnit;
	}

	public String getUnitSubUnitConnector() {
		return unitSubUnitConnector;
	}

	public void setUnitSubUnitConnector(String unitSubUnitConnector) {
		this.unitSubUnitConnector = unitSubUnitConnector;
	}

	public String getCurrencySubUnit() {
		return currencySubUnit;
	}

	public void setCurrencySubUnit(String currencySubUnit) {
		this.currencySubUnit = currencySubUnit;
	}

	public String getCurrencyUnitAr() {
		return currencyUnitAr;
	}

	public void setCurrencyUnitAr(String currencyUnitAr) {
		this.currencyUnitAr = currencyUnitAr;
	}

	public String getUnitSubUnitConnectorAr() {
		return unitSubUnitConnectorAr;
	}

	public void setUnitSubUnitConnectorAr(String unitSubUnitConnectorAr) {
		this.unitSubUnitConnectorAr = unitSubUnitConnectorAr;
	}

	public String getCurrencySubUnitAr() {
		return currencySubUnitAr;
	}

	public void setCurrencySubUnitAr(String currencySubUnitAr) {
		this.currencySubUnitAr = currencySubUnitAr;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	
}
