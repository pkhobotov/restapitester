package restapitester;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Account{

	@JsonProperty("amount")
	private double amount;
	@JsonProperty("clientId")
	private int clientId;
	@JsonProperty("id")
	private long id;
	@JsonProperty("createStamp")
	private Timestamp createStamp;

	@Basic
	@Column(name = "AMOUNT")
	public double getAmount(){
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setAmount(int amount){
		this.amount = amount;
	}

	public int getClientId(){
		return clientId;
	}

	public void setClientId(int clientId){
		this.clientId = clientId;
	}

	@Id
	@Column(name = "ID")
	public long getId(){
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setId(int id){
		this.id = id;
	}

	@Basic
	@Column(name = "CREATE_STAMP")
	public Timestamp getCreateStamp(){
		return createStamp;
	}

	public void setCreateStamp(Timestamp createStamp) {
		this.createStamp = createStamp;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Account account = (Account) o;
		return amount == account.amount && id == account.id && Objects.equals(createStamp, account.createStamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, amount, createStamp);
	}
}