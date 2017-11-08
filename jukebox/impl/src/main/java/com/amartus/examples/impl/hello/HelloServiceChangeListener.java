/*
 * Copyright © 2017 Amartus and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package com.amartus.examples.impl.hello;

import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.DataTreeChangeListener;
import org.opendaylight.controller.md.sal.binding.api.DataTreeIdentifier;
import org.opendaylight.controller.md.sal.binding.api.DataTreeModification;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.urn.com.amartus.example.hello.rev171108.HelloResponse;
import org.opendaylight.yangtools.concepts.ListenerRegistration;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * @author bartosz.michalik@amartus.com
 */
public class HelloServiceChangeListener implements DataTreeChangeListener<HelloResponse> {
    private static final Logger LOG = LoggerFactory.getLogger(HelloServiceChangeListener.class);

    private final DataBroker dataBroker;
    private ListenerRegistration<HelloServiceChangeListener> registration;

    public HelloServiceChangeListener(DataBroker dataBroker) {
        LOG.info("Hello listener started");
        this.dataBroker = dataBroker;
    }

    @Override
    public void onDataTreeChanged(@Nonnull Collection<DataTreeModification<HelloResponse>> changes) {
        LOG.debug("E: {}", changes);
    }

    public void init() {
        LOG.info("Registering change listener");
        registration = dataBroker.registerDataTreeChangeListener(new DataTreeIdentifier<>(LogicalDatastoreType.CONFIGURATION, InstanceIdentifier.create(HelloResponse.class)), this);
    }

    public void close() {
        if(registration != null)
            registration.close();
    }
}
