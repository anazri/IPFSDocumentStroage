package documentStorage.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class IPFSFiles {
	private List<Document> documents = new ArrayList<Document>();

	public List<Document> getDocument() {
		return Collections.unmodifiableList(this.documents);
	}

	public void setDocument(List<Document> document) {
		this.documents = document;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().appendSuper(super.hashCode()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		return new EqualsBuilder().appendSuper(super.equals(obj)).isEquals();
	}
	
	

}
