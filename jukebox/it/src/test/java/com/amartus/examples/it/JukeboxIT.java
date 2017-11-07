/*
 * Copyright © 2017 Amartus and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package com.amartus.examples.it;

import static org.ops4j.pax.exam.CoreOptions.composite;
import static org.ops4j.pax.exam.CoreOptions.maven;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.editConfigurationFilePut;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opendaylight.controller.mdsal.it.base.AbstractMdsalTestBase;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.karaf.options.LogLevelOption.LogLevel;
import org.ops4j.pax.exam.options.MavenUrlReference;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class JukeboxIT extends AbstractMdsalTestBase {
    private static final Logger LOG = LoggerFactory.getLogger(JukeboxIT.class);

    @Override
    public MavenUrlReference getFeatureRepo() {
        return maven()
                .groupId("com.amartus.examples")
                .artifactId("features-jukebox")
                .classifier("features")
                .type("xml")
                .versionAsInProject();
    }

    @Override
    public String getFeatureName() {
        return "odl-jukebox-rest";
    }

    @Override
    public Option getLoggingOption() {
        Option option = editConfigurationFilePut(ORG_OPS4J_PAX_LOGGING_CFG,
                logConfiguration(JukeboxIT.class),
                LogLevel.INFO.name());
        option = composite(option, super.getLoggingOption());
        return option;
    }

    @Test
    public void testjukeboxFeatureLoad() {
        Assert.assertTrue(true);
    }
}
