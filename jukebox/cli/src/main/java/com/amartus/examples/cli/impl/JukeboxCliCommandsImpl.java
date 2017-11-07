/*
 * Copyright © 2017 Amartus and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package com.amartus.examples.cli.impl;

import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.amartus.examples.cli.api.JukeboxCliCommands;

public class JukeboxCliCommandsImpl implements JukeboxCliCommands {

    private static final Logger LOG = LoggerFactory.getLogger(JukeboxCliCommandsImpl.class);
    private final DataBroker dataBroker;

    public JukeboxCliCommandsImpl(final DataBroker db) {
        this.dataBroker = db;
        LOG.info("JukeboxCliCommandImpl initialized");
    }

    @Override
    public Object testCommand(Object testArgument) {
        return "This is a test implementation of test-command";
    }
}