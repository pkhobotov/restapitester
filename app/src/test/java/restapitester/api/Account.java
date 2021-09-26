package restapitester.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account{

	@JsonProperty("amount")
	private int amount;

	@JsonProperty("clientId")
	private int clientId;

	@JsonProperty("id")
	private int id;

	@JsonProperty("createStamp")
	private String createStamp;

	public void setAmount(int amount){
		this.amount = amount;
	}

	public int getAmount(){
		return amount;
	}

	public void setClientId(int clientId){
		this.clientId = clientId;
	}

	public int getClientId(){
		return clientId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCreateStamp(String createStamp){
		this.createStamp = createStamp;
	}

	public String getCreateStamp(){
		return createStamp;
	}
}