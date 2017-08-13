package documentStorage.model.api;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("serial")
public class GenericResponse implements Serializable {
	private Status status;
	
	public GenericResponse(Status status){
		this.status = status;
	}

	public Status getStatus() {
		return this.status;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		return new EqualsBuilder().isEquals();
	}
	
	
	
	
}
