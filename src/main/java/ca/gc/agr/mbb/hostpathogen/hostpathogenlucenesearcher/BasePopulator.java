package ca.gc.agr.mbb.hostpathogen.hostpathogenlucenesearcher;

import org.apache.lucene.document.Document;
import ca.gc.agr.mbb.hostpathogen.nouns.Pathogen;

abstract public class BasePopulator<T> implements Populator{
    protected String primaryKeyField = null;


    public String getPrimaryKeyField(){
	return primaryKeyField;
    }

    static final long longValue(Document doc, String fieldName) throws FailedPopulateException{
	return longValue(doc, fieldName, false);
    }

    static final long longValue(Document doc, String fieldName, boolean mustExist) throws FailedPopulateException
    {
	checkDocFieldName(doc, fieldName);

	String[] values = doc.getValues(fieldName);
	if(values == null || values.length == 0){
	    if(mustExist){
		throw new NullPointerException("Field " + fieldName + " cannot be empty/null");
	    }
	    return 0l;
	}
	try{
	    return (new Long(values[0])).longValue();
	}catch(NumberFormatException e){
	    throw new FailedPopulateException(e);
	}
    }

    static final String stringValue(Document doc, String fieldName) throws FailedPopulateException{
	return stringValue(doc, fieldName, false);
    }

    static final String stringValue(Document doc, String fieldName, boolean mustExist) throws FailedPopulateException
    {
	checkDocFieldName(doc, fieldName);

	String[] values = doc.getValues(fieldName);
	if(values == null || values.length == 0){
	    if(mustExist){
		throw new NullPointerException("Field " + fieldName + " cannot be empty/null");
	    }
	    return null;
	}
	try{
	    return values[0];
	}catch(NumberFormatException e){
	    throw new FailedPopulateException(e);
	}
    }

    static final void checkDocFieldName(Document doc, String fieldName){
	if (doc == null){
	    throw new FailedPopulateException("Document is null");
	}

	if (fieldName == null || fieldName.length() == 0){
	    throw new FailedPopulateException("Fieldname is null or zero length");
	}
    }


}