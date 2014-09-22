package ca.gc.agr.mbb.hostpathogen.hostpathogenlucenesearcher;

import java.util.Properties;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;

public class Util{

    // Make sure offset and limits are sane and within constraints.
    public static final void checkOffsetAndLimit(final long offset, final int limit) throws IllegalOffsetLimitException{
	StringBuilder s = new StringBuilder();
	if (offset <0){
	    s.append("Offset is < 0: " + offset + "||");
	}
	if (limit<1){
	    s.append("Limit is < 1: " + limit);
	}

	if (limit>HPSearcher.LIMIT_MAX){
	    s.append("Limit is > max limit value:" + HPSearcher.LIMIT_MAX + " :" + limit); 
	}

	if(s.length() > 0){
	    throw new IllegalOffsetLimitException(s.toString());
	}

    }

    public static final void checkQueryParameters(final Map<String,String>queryPrameters) throws IllegalArgumentException{
	if(queryPrameters == null){
	    throw new IllegalArgumentException("queryPrameters List<String> is null");
	} 

	if(queryPrameters.size() == 0){
	    throw new IllegalArgumentException("queryPrameters List<String> is zero length");
	} 
    }

    public static final void checkList(List list, int limit){
	if(list == null){
		throw new IllegalArgumentException("Null list");
	    }

	int len = list.size();
	if(len == 0){
		throw new IllegalArgumentException("Empty list (size=0)");
	    }
	if(len > limit){
		throw new IllegalArgumentException("List too large " + len + "; greater than limit: " + limit);
	    }
    }

    public static final void checkIds(final List<Long> ids) throws IllegalArgumentException{
	if(ids == null){
	    throw new IllegalArgumentException("ids List<Long> is null");
	}
	if(ids.size() == 0){
	    throw new IllegalArgumentException("ids List<Long> is empty (length=0)");
	}
    }

    public static final int min(final int i, final int j){
	if (j>i){
	    return i;
	}
	return j;
    }

    public static final List<Long> sliceList(final List<Long> list, final long offset, final int limit) throws IllegalArgumentException{
	checkList(list, Integer.MAX_VALUE);
	int len = list.size();
	
	if(offset >len){
	    return new ArrayList<Long>(0);
	}
	return list.subList((int)offset, min((int)(offset+limit), len));
    }


    public static final String existsIsDirIsReadable(String dir){
	File f = new File(dir);
	
	if (!f.exists()){
	    return "Dir does not exist:" + dir;
	}
	if (!f.isDirectory()){
	    return "Is not a directory:" + dir;
	}
	if (!f.canRead()){
	    return "Unable to read dir:" + dir;
	}

	return null;
    }

    public static final void touch(File f) throws java.io.FileNotFoundException{
	    PrintWriter writer = new PrintWriter(f);
	    writer.println("x");
	    writer.flush();
	    writer.close();
    }

}//


