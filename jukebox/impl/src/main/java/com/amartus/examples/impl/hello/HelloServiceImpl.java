/*
 * Copyright © 2017 Amartus and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package com.amartus.examples.impl.hello;

import com.google.common.base.Optional;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.ReadOnlyTransaction;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.urn.com.amartus.example.hello.rev171108.*;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.common.RpcError;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;

/**
 * @author bartosz.michalik@amartus.com
 */
public class HelloServiceImpl implements HelloService {
    private static final Logger LOG = LoggerFactory.getLogger(HelloServiceImpl.class);
    private DataBroker broker;
    @Override
    public Future<RpcResult<HelloWorldOutput>> helloWorld(HelloWorldInput input) {

        String prefix = "Hello";

        ReadOnlyTransaction tx = broker.newReadOnlyTransaction();
        try {
            Optional<HelloResponse> resp = tx.read(LogicalDatastoreType.CONFIGURATION, InstanceIdentifier.create(HelloResponse.class)).get();
            if(resp.isPresent()) {
                prefix = resp.get().getPrefix();
            }
        } catch (Exception e) {
            LOG.error("Error", e);
//            return RpcResultBuilder.<UpdateConnectivityServiceOutput>failed()
//                    .withError(RpcError.ErrorType.APPLICATION, e.getMessage()).build();
        }
        LOG.debug("RCP Responding with {} {}", prefix, input.getName());

        HelloWorldOutputBuilder builder = new HelloWorldOutputBuilder();
        HelloWorldOutput output = builder.setGreeting(prefix + " " + input.getName()).build();
        return RpcResultBuilder.success(output).buildFuture();

    }

    public void setBroker(DataBroker broker) {
        this.broker = broker;
    }
}
