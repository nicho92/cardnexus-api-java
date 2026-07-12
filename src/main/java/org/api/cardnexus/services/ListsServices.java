package org.api.cardnexus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.api.cardnexus.configuration.NexusConstants;
import org.api.cardnexus.model.NexusList;
import org.api.cardnexus.model.Pagination;

public class ListsServices extends AbstractNexusService {

    public NexusList getNexusLists(String listId) throws IOException
    {
	return   client.get(ROOT_LISTS_ENDPOINT+"/"+listId, null, NexusList.class);
    }
    
    
    public List<NexusList> listNexusLists() throws IOException
    {
	var ret = new ArrayList<NexusList>();
	var pagination=new Pagination();
	while(pagination.hasMore())
	{
		var result =  client.getPaginated(ROOT_LISTS_ENDPOINT+"?offset="+ret.size()+"&limit="+NexusConstants.LIMIT_LIST_RESULTS, null, NexusList.class);
		ret.addAll(result.getData());
		pagination = result.getPagination();
	}
	return ret;
    }
    
}
