package com.tinkerpop.blueprints.impls.orient;

import com.tinkerpop.blueprints.Graph;
import org.hamcrest.core.IsEqual;
import org.junit.Assume;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author Andrey Lomakin (a.lomakin-at-orientechnologies.com)
 * @since 2/6/14
 */
@RunWith(JUnit4.class)
public class OrientGraphClassicRemoteTest extends OrientGraphRemoteTest {
	@Before
        @Override
	public void setUp() throws Exception {
		Assume.assumeThat(getEnvironment(), IsEqual.equalTo(OrientGraphTest.ENV.RELEASE));
		super.setUp();
	}

        @Override
	public Graph generateGraph(final String graphDirectoryName) {
		final OrientGraph graph = (OrientGraph) super.generateGraph(graphDirectoryName);
		graph.setUseLightweightEdges(false);
		graph.setUseClassForEdgeLabel(false);
		graph.setUseClassForVertexLabel(false);
		graph.setUseVertexFieldsForEdgeLabels(false);
		return graph;
	}
}
