package documentStorage.model.api;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import documentStorage.model.IPFSFiles;

@SuppressWarnings("serial")
public class IPFSResponse extends GenericResponse {

	private IPFSFiles ipfsObj;
	
	public IPFSResponse(IPFSFiles ipfsObj) {
		super(Status.SUCCESS);
		this.ipfsObj = ipfsObj;
	}

	public IPFSFiles getIpfsObj() {
		return ipfsObj;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;

		return new EqualsBuilder().isEquals();
	}
	
	

}
