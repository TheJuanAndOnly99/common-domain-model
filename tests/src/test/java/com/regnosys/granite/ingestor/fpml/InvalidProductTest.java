package com.regnosys.granite.ingestor.fpml;

import java.net.URL;
import java.util.stream.Stream;

import cdm.event.common.TradeState;
import com.regnosys.granite.ingestor.CdmTestInitialisationUtil;
import com.regnosys.granite.ingestor.IngestionTest;
import org.isda.cdm.CdmRuntimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.provider.Arguments;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import com.regnosys.granite.ingestor.service.IngestionFactory;
import com.regnosys.granite.ingestor.service.IngestionService;

class InvalidProductTest extends IngestionTest<TradeState> {

	private static final String SAMPLE_FILES_DIR = "cdm-sample-files/fpml-5-10/invalid-products/";

	/*
	 * Validation logic is supposed to result in a False outcome.
	 * Those tests which result in a True outcome need to have their associated data rule evaluated and corrected.
	 * The file names correspond to the data rules that are being tested.
	 */
	private static ImmutableList<URL> EXPECTATION_FILES = ImmutableList.<URL>builder()
			.add(Resources.getResource(SAMPLE_FILES_DIR + "expectations.json"))
			.build();

	private static IngestionService ingestionService;

	@BeforeAll
	static void setup() {
		CdmTestInitialisationUtil cdmTestInitialisationUtil = new CdmTestInitialisationUtil();
		initialiseIngestionFactory(new CdmRuntimeModule(), cdmTestInitialisationUtil.getPostProcessors());
		ingestionService = IngestionFactory.getInstance().getFpml510();
	}

	@Override
	protected Class<TradeState> getClazz() {
		return TradeState.class;
	}

	@Override
	protected IngestionService ingestionService() {
		return ingestionService;
	}
	
	@Override
	protected void assertEventEffect(TradeState c) {
	}

	@SuppressWarnings("unused")//used by the junit parameterized test
	private static Stream<Arguments> fpMLFiles() {
		return readExpectationsFrom(EXPECTATION_FILES);
	}
}