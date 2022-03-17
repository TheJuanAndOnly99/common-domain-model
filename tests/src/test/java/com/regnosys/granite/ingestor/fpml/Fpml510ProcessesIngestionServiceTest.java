package com.regnosys.granite.ingestor.fpml;

import cdm.event.workflow.WorkflowStep;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import com.regnosys.granite.ingestor.CdmTestInitialisationUtil;
import com.regnosys.granite.ingestor.IngestionTest;
import com.regnosys.granite.ingestor.service.IngestionFactory;
import com.regnosys.granite.ingestor.service.IngestionService;
import org.isda.cdm.CdmRuntimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.provider.Arguments;

import java.net.URL;
import java.util.stream.Stream;

public class Fpml510ProcessesIngestionServiceTest extends IngestionTest<WorkflowStep> {

	private static final String PROCESSES = "cdm-sample-files/fpml-5-10/processes/";

	private static ImmutableList<URL> EXPECTATION_FILES = ImmutableList.<URL>builder()
			.add(Resources.getResource(PROCESSES + "expectations.json"))
			.build();

	private static IngestionService ingestionService;

	@BeforeAll
	static void setup() {
		CdmTestInitialisationUtil cdmTestInitialisationUtil = new CdmTestInitialisationUtil();
		initialiseIngestionFactory(new CdmRuntimeModule(), cdmTestInitialisationUtil.getPostProcessors());
		ingestionService = IngestionFactory.getInstance().getFpml510Processes();
	}
	
	@Override
	protected Class<WorkflowStep> getClazz() {
		return WorkflowStep.class;
	}

	@Override
	protected IngestionService ingestionService() {
		return ingestionService;
	}
	
	@Override
	protected void assertEventEffect(WorkflowStep c) {
	}
	
	@SuppressWarnings("unused")//used by the junit parameterized test
	private static Stream<Arguments> fpMLFiles() {
		return readExpectationsFrom(EXPECTATION_FILES);
	}
}
