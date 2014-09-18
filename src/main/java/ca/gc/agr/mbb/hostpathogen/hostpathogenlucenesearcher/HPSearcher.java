package ca.gc.agr.mbb.hostpathogen.hostpathogenlucenesearcher;

import java.util.Properties;
import java.util.List;

import ca.gc.agr.mbb.hostpathogen.nouns.Pathogen;
import ca.gc.agr.mbb.hostpathogen.nouns.Host;


public class HPSearcher implements Searcher{
    public static int LIMIT_MAX = 50;
    
    private String luceneDir = null;

    public static final Searcher newSearcher(final Properties p) throws InitializationException{
	Searcher searcher = null;

	if(p.containsKey(MOCK_PROPERTY)){
	    searcher = new HPSearcherMock();
	}else{
	    searcher = new HPSearcher();
	}
	return searcher.init(p);
    }


    private HPSearcher(){

    }

    /**
     * Initialize the Searcher. Must be run before any other methods.
     *
     * @param p Initialization properties (like Search.MOCK_PROPERTY)
     * @return Searcher
     * @throws InitializationException <<Description>>
     */
    public Searcher init(Properties prop) throws InitializationException{
	if(!prop.containsKey(LUCENE_INDICES_BASE_DIR)){
	    throw new InitializationException("Missing LUCENE_INDICES_BASE_DIR property for location of Lucene indices");
	}
	luceneDir = prop.getProperty(LUCENE_INDICES_BASE_DIR);

	String errorString = Util.existsIsDirIsReadable(luceneDir);
	if(errorString != null){
	    throw new InitializationException(errorString);
	}

	LuceneIndexSearcher<Pathogen> lis = new LuceneIndexSearcher<Pathogen>();
	lis.init("luceneIndexes/luceneIndex.host_pathogens");

	return this;
    }

    ///// Pathogens

    public List<Pathogen>getPathogens(final List<Long> ids) throws IllegalArgumentException{
	Util.checkIds(ids);
	return null;
    }

    public List<Long>getAllPathogens(final long offset, final int limit) throws IllegalOffsetLimitException, IllegalArgumentException{
	Util.checkOffsetAndLimit(offset, limit);
	return null;
    }

    public List<Long>searchPathogens(List<String>queryPrameters, final long offset, final int limit) throws IllegalOffsetLimitException, IllegalArgumentException{
	Util.checkOffsetAndLimit(offset, limit);
	return null;
    }


    ///// Hosts
    public List<Host>getHosts(List<Long> ids) throws IllegalArgumentException{
	Util.checkIds(ids);
	return null;
    }
    public List<Long>getAllHosts(final long offset, final int limit) throws IllegalOffsetLimitException, IllegalArgumentException{
	Util.checkOffsetAndLimit(offset, limit);
	return null;
    }

    public List<Long>searchHosts(List<String>queryPrameters, final long offset, final int limit) throws IllegalOffsetLimitException, IllegalArgumentException{
	Util.checkOffsetAndLimit(offset, limit);
	return null;
    }


    



}
