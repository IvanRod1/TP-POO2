package sa.searcher.composite;

import sa.searcher.simpleQuery.IQuery;


public abstract class CompositeQuery implements IQuery{
	protected IQuery fstQuery;
	protected IQuery sndQuery;
	
	protected CompositeQuery(IQuery firstQuery, IQuery secondQuery) {
		this.fstQuery = firstQuery;
		this.sndQuery = secondQuery;
	}

}
