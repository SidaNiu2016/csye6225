package sida.csye6225.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
@DynamoDBTable(tableName = "Notes")
public class Note extends Item{
	public String Text;

	@DynamoDBHashKey(attributeName = "NoteId")
	public String getItemId() { return this.id; }
	public void setItemId(String id) { this.id = id; }
	
	@DynamoDBAttribute(attributeName = "Text")
	public String getText() { return this.Text; } 
	public void setText(String text) { this.Text = text; }
}
