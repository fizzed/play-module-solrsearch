package com.mfizz.play.solrsearch;

import play.Logger;
import play.Play;

public class IndexingJob implements Runnable {

	private final IndexingHandler handler;
	
	public IndexingJob(IndexingHandler handler) {
		super();
		this.handler = handler;
	}

	@Override
	public void run() {
		// need to get a reference to the plugin
		SolrSearchPlugin plugin = Play.application().plugin(SolrSearchPlugin.class);
		long start = System.currentTimeMillis();
		Logger.info("Starting solr search (re)indexing...");
		try {
			handler.reindex(plugin);
			long stop = System.currentTimeMillis();
			Logger.info("Completed solr search (re)index (in " + (stop-start) + " ms)");
		} catch (Exception e) {
			long stop = System.currentTimeMillis();
			Logger.error("Unable to successfully (re)index (in " + (stop-start) + " ms)", e);
		}
	}

}
